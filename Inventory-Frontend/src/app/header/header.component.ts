import { Component, inject } from '@angular/core';
import { Inventory } from '../inventory';
import { Router, RouterLink } from '@angular/router';
import { LoginService } from '../login.service';
import { InventoryService } from '../inventory.service';
import { CommonModule } from '@angular/common';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatNavList } from '@angular/material/list';
import { MatToolbar } from '@angular/material/toolbar';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatButtonModule, MatDividerModule, MatIconModule,CommonModule,FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,RouterLink,MatSidenavModule,MatNavList,MatToolbar ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',

})
export class HeaderComponent {
  toolbarState = 'visible';
  contentState = 'visible';

  inv : Inventory[]=[];
  token!:string;

  router = inject(Router);
  loginSer = inject(LoginService);
  invService = inject(InventoryService);
  
  username=localStorage.getItem('username');
  
  
  
  public getRole(){
     const role = this.loginSer.getRole();
     return role;
  }
  
    public getToken(){
      const  token =localStorage.getItem('token');
      
     return token;
   }
    
  
   login(){
    this.router.navigateByUrl("/");
   }
  logout(){
    this.loginSer.logout();
    this.router.navigateByUrl("/");
  }
  
  isLoggedIn(){
   return this.loginSer.isLoggedIn();
  }


  getUserId(){
    const id = this.loginSer.getUserid();
    return Number(id);
  }

  
  
  }
  
    
