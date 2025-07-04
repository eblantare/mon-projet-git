import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
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
   
   constructor(
	  private fb:FormBuilder,
	  private userService:UserService,
	  private router:Router
	  
   ){
	this.userForm = this.fb.group({
		username:['',[Validators.required]],
		email:['',[Validators.required,Validators.email]],
		role:['',Validators.required],
		password:['',[Validators.required,Validators.minLength(8)]]
	});
	
	
   }
   get f(){
	return this.userForm.controls;
   }
   
   onSubmit():void{
	this.submitted=true;
	if(this.userForm.invalid){
	}
	this.userService.createUser(this.userForm.value).subscribe({
		next:()=>this.router.navigate(['/users']),
		error:(err)=>this.errorMessage='Erreur lors de la création de l\'utilisateur!'
	}
		
	);
   }
   
}
