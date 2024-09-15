import { Component, inject, OnInit } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-sample',
  standalone: true,
  imports: [],
  templateUrl: './sample.component.html',
  styleUrl: './sample.component.css'
})
export class SampleComponent implements OnInit{
  ngOnInit(): void {
   const role= this.loginser.getRole();
console.log(role);
  }

  loginser = inject(LoginService);



}
