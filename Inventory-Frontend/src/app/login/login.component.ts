import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../user';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatButtonModule, MatDividerModule, MatIconModule,CommonModule,FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  user:User = new User(); 
 
  ngOnInit(): void {
    //this.usernamemethos();
  }

 constructor(private loginservice: LoginService )
{

}
  router = inject(Router);

  
  loginUser(){
    
    this.loginservice.loginUserDetails(this.user).subscribe(
      (data:any)=>{
    
  // Handle the response here
    console.log('Login successful', data);
    // const roleObj = localStorage.getItem('authorities[authority]['USER']');
   const role = JSON.parse(localStorage.getItem('role')|| '[]');
   
  
   
    // const user = localStorage.getItem('role[0]?.authority');
    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');

    
    console.log(username);
    console.log(role);
    console.log(token);
  //   const rolesArray = JSON.parse(user) as Array<{ authority: string }>;

  //   // Extract the authority value from the first object in the array
  //   authorityValue = rolesArray[0]?.authority;
  //   console.log(userRole);

  const roleobj = this.loginservice.getRole();
    if(roleobj =="Admin"){
    this.router.navigateByUrl("/admin");
  
    }
    else if (roleobj == "User"){
    this.router.navigateByUrl("/inventory");
  }
  },
  (error) => {
    // Handle the error here
    console.error('Login error', error);
  }
);
}



}
