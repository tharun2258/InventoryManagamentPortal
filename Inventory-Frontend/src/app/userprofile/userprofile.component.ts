import { Component, Inject, inject, OnInit } from '@angular/core';
import { EditprofileComponent } from '../editprofile/editprofile.component';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-userprofile',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,MatIcon],
  templateUrl: './userprofile.component.html',
  styleUrl: './userprofile.component.css'
})
export class UserprofileComponent implements OnInit{
  userid!: number;
  user: User = new User();

  route = inject(ActivatedRoute);
  uservice = inject(LoginService);
  matDialog =inject(MatDialog);
  router= Inject(Router);
  

  ngOnInit(): void {

  
    this.userid= this.route.snapshot.params['userid']

    console.log(this.userid)
    this.uservice.userdetailsbyid(this.userid).subscribe((data)=>{
    this.user=data;
   
    console.log(data);
    })
  }

  

    openDialogProfile(){
      this.matDialog.open(EditprofileComponent,{
        width:'350px'
        
          })
          
        }
}
  





