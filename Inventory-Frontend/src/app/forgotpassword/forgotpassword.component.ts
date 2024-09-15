import { Component, inject, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { User } from '../user';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-forgotpassword',
  standalone: true,
  imports: [MatButtonModule, MatDividerModule, MatIconModule,CommonModule,FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule],
  templateUrl: './forgotpassword.component.html',
  styleUrl: './forgotpassword.component.css'
})
export class ForgotpasswordComponent implements OnInit {

user: User = new User();
  
  ngOnInit(): void {
    // this.resetPassword();
  }

  loginSer = inject(LoginService);

  // public resetPassword(){
  // this.loginSer.resetPassword(this.user, this.user.userid).subscribe((data=>{
  //   console.log(data);
  //   if(data!= null){
  //     alert("password reseted successfully!!!");
  //   }
  //   else{
  //     console.log("error");
  //   }
  // }))
  // }

}
