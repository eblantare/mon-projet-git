import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth-service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterModule],
  templateUrl: './home.html',
  standalone:true,
  styleUrls: ['./home.scss']
})
export class Home {
  username ='';
  isAdmin=false;
  photo='';
  isMenuOpen=false;

  constructor(private authService:AuthService, private router:Router){
    this.authService.getCurrentUser().subscribe({next: (user) =>{
      console.log('Utilisateur connecté ',user);
      this.username = user.username;
      this.isAdmin=user.roles.includes('ROLE_ADMIN');

      if(this.username==='admin'){
        this.photo='assets/images/avatar.png';
      }
      else{
        this.photo=user.photo || 'assets/images/default-user.png';
      }

    },
    error:(err)=>{
      if(err.statut === 401){
        console.warn('Utilisateur non connecté');
      }else{
        console.log("Erreur de getCurrentUser ",err);
      }

      this.router.navigate(['/login']);
    }
  });

  }

  logout(){
    this.authService.logout().subscribe({
      next:()=>{this.router.navigate(['/login']);
    },
    error:(err)=>{
      console.log('Erreur de déconnexion', err);
      this.router.navigate(['/login']);
    }

    });
  }
  toggleMenu(){
    this.isMenuOpen =!this.isMenuOpen;
  }
  closeMenu(){
    this.isMenuOpen = false;
  }
  goToProfile(){
    this.router.navigate(['/profile']);
  }

}
