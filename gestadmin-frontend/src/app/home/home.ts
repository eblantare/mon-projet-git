import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth-service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterModule],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home {
  username ='';
  isAdmin=false;

  constructor(private authService:AuthService, private router:Router){
    this.authService.getCurrentUser().subscribe( user =>{
      this.username = user.username;
      this.isAdmin=user.roles.includes('ROLE_ADMIN');

    });

  }

  logout(){
    this.authService.logout().subscribe(()=>{this.router.navigate(['/login']);

    });
  }

}
