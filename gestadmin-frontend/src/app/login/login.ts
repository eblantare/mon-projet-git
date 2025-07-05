import { CommonModule } from '@angular/common';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder,FormGroup,FormsModule,ReactiveFormsModule,Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth-service';
import { MatCheckboxModule } from '@angular/material/checkbox';

@Component({
  selector: 'app-login',
  standalone : true,
  imports: [ReactiveFormsModule,
            CommonModule,
            MatCardModule,
            MatButtonModule,
            MatInputModule,
            FormsModule,
            MatCheckboxModule
            
          ],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  form!:FormGroup;
  username='';
  password='';
  error='';
  currentTime='';
constructor(private fb:FormBuilder, private authService:AuthService,
  private http:HttpClient,private router:Router){
  	this.form = this.fb.group({
		username:['',[Validators.required]],
		password:['',[Validators.required,Validators.minLength(8)]],
    rememberMe:[false]
	});
}

ngOnInit(){
  this.updateTime();
  setInterval(()=>this.updateTime(),1000);//Met à jour l'heure chaque seconde
}

updateTime(){
  const now = new Date();
  this.currentTime = now.toLocaleTimeString();
}


  

  onSubmit(){
    if(this.form.invalid){return;}
    const{username,password,rememberMe} = this.form.value;
    // this.http.post('/login',{username,password},{observe:'response'})
    this.authService.login(this.username,this.password)
    .subscribe({
      next: () => {
        //Une fois connecté, on vérifie les rôles
        this.authService.getCurrentUser().subscribe(user =>{
          if(user.roles.includes('ROLE_ADMIN')){
            this.router.navigate(['/create-user']);
          }else{
                  this.router.navigate(['/']);
          }
        });
        // Si "rememberMe" est coché, on peut stocker le token localement ici
        if(rememberMe){
          localStorage.setItem('rememberedUser',username);
        }else{
          localStorage.removeItem('rememberedUser');
        }

      },
      // (_res:HttpResponse<any> )=> {
      //   this.http.get<{roles:string[]}>('/api/admin/me').subscribe(user => {
      //     if(user.roles.includes('ROLE_ADMIN')){
      //       this.router.navigate(['/create-user']);
      //     }else{
      //       this.router.navigate(['/']);
      //     }
      //   });
      // },
      error: e => this.error ='Nom d\’utilisateur ou mot de passe incorrect.'
    });
  }
  
}
