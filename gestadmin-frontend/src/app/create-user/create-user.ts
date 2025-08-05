import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators, ValidationErrors } from '@angular/forms';
import { UserService } from '../services/user';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-user',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './create-user.html',
  styleUrls: ['./create-user.scss']
})
export class CreateUser {
   userForm:FormGroup;
   submitted=false;
   errorMessage='';
   successMessage='';
   hide=true;


   constructor(
	  private fb:FormBuilder,
	  private userService:UserService,
	  private router:Router

   ){
	this.userForm = this.fb.group({
		username:['',[Validators.required]],
    nom:['',[Validators.required]],
    prenoms:['',[Validators.required]],
    telephone:['',[Validators.required]],
		email:['',[Validators.required,Validators.email]],
		role:['',Validators.required],
		password:['',[Validators.required,this.passwordValidator]]
	});

   }

   get f(){
	return this.userForm.controls;
   }

   passwordValidator(control:AbstractControl):ValidationErrors | null {
    const value = control.value;
    if(!value) return null;
    const valid = /^(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9])(?=.{8,})/.test(value);
  return valid ? null : { weakPassword: true };

   }

   toggleVisibility(){
    this.hide=!this.hide;
   }

   onSubmit():void{
	this.submitted=true;
  this.successMessage='';
  this.errorMessage='';
	if(this.userForm.invalid){
	}
	this.userService.createUser(this.userForm.value).subscribe({
		next:()=>{
      this.successMessage='save succeed';
      this.userForm.reset();
      this.submitted=false;
      setTimeout(() => this.successMessage = '', 3000); // Cache le message après 3s
      // this.router.navigate(['/users'])
    },
		error:(err)=>this.errorMessage='Erreur lors de la création de l\'utilisateur!'
	}

	);
   }

}
