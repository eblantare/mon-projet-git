import { CommonModule } from '@angular/common';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component,Signal} from '@angular/core';
import { AbstractControl, FormBuilder,FormGroup,FormsModule,ReactiveFormsModule,ValidationErrors,Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth-service';
import { MatCheckboxModule } from '@angular/material/checkbox';
import {MatIconModule} from '@angular/material/icon';



@Component({
  selector: 'app-login',
  standalone : true,
  imports: [ReactiveFormsModule,
            CommonModule,
            MatCardModule,
            MatButtonModule,
            MatInputModule,
            FormsModule,
            MatCheckboxModule,
            MatIconModule

          ],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})

export class Login {

  loginForm!:FormGroup;
  username='';
  password='';
  error='';
  currentTime='';
   hide = true;
   canLogin = false;
constructor(private fb:FormBuilder, private authService:AuthService,
  private http:HttpClient,private router:Router){
  	this.loginForm = this.fb.group({
		username:['',[Validators.required]],
		password:['',[Validators.required,this.passwordValidator]],
    rememberMe:[false]
	});
}

  togglePasswordVisibility(event:MouseEvent){
    event.preventDefault();
    this.hide = !this.hide;
    console.log('Mot de passe visible ?', !this.hide);
  }
ngOnInit(){
  this.updateTime();
  setInterval(()=>this.updateTime(),1000);//Met à jour l'heure chaque seconde
   this.loginForm.valueChanges.subscribe(()=>{
    this.canLogin = this.loginForm.valid && this.loginForm.get('username')?.value?.trim()!=''
    && this.loginForm.get('password')?.value?.trim()!='';
   });
}

updateTime(){
  const now = new Date();
  this.currentTime = now.toLocaleTimeString();
}

   toggleVisibility(){
    this.hide = !this.hide;
   }
  getEmail(){
    return this.loginForm.get('email');
  }

  getUsername(){
    return this.loginForm.get('username');
  }

  getPassword(){
    return this.loginForm.get('password');
  }
 canSubmit(): boolean {
  const username = this.loginForm.get('username');
  const password = this.loginForm.get('password');
  if (!username || !password){return false;}
  return this.loginForm.valid &&
    username.valid &&
    password.valid &&
    username.value?.trim() !== '' &&
    password.value?.trim() !== '';
}
passwordValidator(control:AbstractControl):ValidationErrors | null{
  const value = control.value;
  if (!value){return null;}
  const valid =/^(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9])(?=.{8,})/.test(value);
  return  valid ? null : { weakPassword:true};

}


passwordTouchedAndInvalid():boolean{
  const control = this.loginForm.get('password');
  return !!control && control.touched && control.dirty && control.hasError('weakPassword');

}

  onSubmit(){
    console.log('ONSUBMIT DE LA METHODE LOGIN****1');
    if(this.loginForm.invalid){return;}
    const{username,password,rememberMe} = this.loginForm.value;
    // this.http.post('/login',{username,password},{observe:'response'})
    this.authService.login(username,password).subscribe({
    next: (res) => {
      console.log("Login success", res.body);
      this.authService.getCurrentUser().subscribe({
        next: (user) => {
          // Stockage local si "se souvenir"
          console.log("Login success", user.body);
          if (rememberMe) {
            localStorage.setItem('rememberedUser', username);

            console.log('ONSUBMIT DE LA METHODE LOGIN ******* 2');
          } else {
            localStorage.removeItem('rememberedUser');
          }
          // Redirection selon le rôle
          // const user = response.body;
          // if (user.roles && user.roles.includes('ROLE_ADMIN')) {
          //   this.router.navigate(['/home']);
          //   console.log('ONSUBMIT DE LA METHODE LOGIN ******* 2');
          // } else {
          //   this.router.navigate(['/create-user']); // ou '/'
          // }
          this.router.navigateByUrl('/home');
        },
        error: () => {
          console.error("Login error *********1");
          this.error = "Impossible de récupérer les rôles.";
        }
      });
    },
error: (err) => {
  console.error("Login error******2", err); // log complet
  this.error = "Nom d’utilisateur ou mot de passe incorrect.";
}
  });
  console.log('***********FIN ONSUBMIT');
}
}
