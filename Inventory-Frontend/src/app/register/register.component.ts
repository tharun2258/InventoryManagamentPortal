import { Component, inject, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { User } from '../user';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [MatButtonModule, MatDividerModule, MatIconModule,CommonModule,FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule, ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{

userservice = inject(LoginService);
user: User = new User();

  ngOnInit(): void {
  }


  public registerUser(){
    this.userservice.registerUser(this.user).subscribe((data=>{
      alert("successfully registered!!!");
    console.log(data);
    }))
  }

  checkUsernameExist(form: NgForm) {
    
    const usernameControl = form.controls['username'];
    const username = usernameControl.value;
    console.log(username);
  
    // Remove any previous error
    usernameControl.setErrors(null);
  
    this.userservice.userdetailsbyname(username).subscribe((data) => {
      if (data) {
        console.log(data);
        // Username exists, set the custom error
        usernameControl.setErrors({ usernameExist: true });
      } else {
        // Username does not exist, remove any error
        usernameControl.setErrors(null);
      }
    });
  }

  validatePassword(form:NgForm){

    if(form.controls['password'].value!==form.controls['confirmpassword'].value){

      form.controls['confirmpassword'].setErrors({passwordMismatch:true});

    }else{

      form.controls['confirmpassword'].setErrors(null);

    }
  }
}
