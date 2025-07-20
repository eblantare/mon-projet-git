import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import {  MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { response } from 'express';
import { UserlistService,UserListDto } from '../services/userlist-service';


interface User {id:number; username:string; roles:string[]}
@Component({
  selector: 'app-user-list',
  standalone:true,
  imports: [ReactiveFormsModule,
            CommonModule,
            MatCardModule,
            MatInputModule,
            MatTableModule,
            MatPaginatorModule
            
            
          ],
  templateUrl: './user-list.html',
  styleUrl: './user-list.scss'
})
export class UserList implements OnInit{
  users:UserListDto[] = [];
  page=0;
  size=10;
  totalPages=0;
  search='';
  sort:string[]=['id','asc'];
  error ='';

  constructor(private http:HttpClient,private userlistService:UserlistService){}

  ngOnInit(){
        this.loadUser();
         
  }
public loadUser(){
  this.userlistService.getUsers(this.page,this.size,this.sort,this.search)
                        .subscribe(response =>{
                          this.users = response.content;
                          this.totalPages = response.totalPages;
                        });
}
 onSearchChange(value:string){
            this.search = value;
            this.page = 0;
            this.loadUser();
 }

 onPageChange(newPage:number){
  this.page = newPage;
  this.loadUser();
 }
}
