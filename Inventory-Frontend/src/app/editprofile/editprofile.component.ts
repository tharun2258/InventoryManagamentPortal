import { Component, inject, OnInit } from '@angular/core';
import { MatDialogClose, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../user';
import { UserprofileComponent } from '../userprofile/userprofile.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-editprofile',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,MatIcon,MatDialogClose],
  templateUrl: './editprofile.component.html',
  styleUrl: './editprofile.component.css'
})
export class EditprofileComponent implements OnInit{
  userid!: number;

  usernew: User= new User();

 

  uService= inject(LoginService);
  dialogRef = inject(MatDialogRef<UserprofileComponent>)
  router = inject(Router);

  constructor(){
    if (typeof localStorage !== 'undefined') {
    const storedUserid = localStorage.getItem('userid');
    this.userid =  Number(storedUserid) ;
    }
  }
  
  
  ngOnInit(): void {
    this.uService.userdetailsbyid(this.userid).subscribe((data)=>{
    this.usernew=data;
    }),
    (error:HttpErrorResponse)=>{
      console.log(error);
    }
    
  }
  

  public  saveDetails(){
    return this.uService.updateUserDetails(this.userid,this.usernew).subscribe((data=>{
    this.usernew=data;
    this.dialogRef.close();
    location.reload();
    // this.router.navigateByUrl("/user")

    }))
  }

}


