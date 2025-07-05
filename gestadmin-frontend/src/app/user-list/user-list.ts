import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import {  MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';

interface User {id:number; username:string; roles:string[]}
@Component({
  selector: 'app-user-list',
  standalone:true,
  imports: [ReactiveFormsModule,
            CommonModule,
            MatCardModule,
            MatInputModule,
            MatTableModule
            
          ],
  templateUrl: './user-list.html',
  styleUrl: './user-list.scss'
})
export class UserList implements OnInit{
  users:User[] = [];
  error ='';

  constructor(private http:HttpClient){}

  ngOnInit(){
      this.http.get<User[]>('api/admin/users')
      .subscribe({
        next: data =>this.users = data,
        error:e =>{setTimeout(()=>{
              this.error = 'Impossible de récupérer les utilisateurs'
        })
      },
              
      });
         
  }

}
