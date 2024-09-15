import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-authorized',
  standalone: true,
  imports: [],
  templateUrl: './not-authorized.component.html',
  styleUrl: './not-authorized.component.css'
})
export class NotAuthorizedComponent {

  router = inject(Router);

  goHome() {
    this.router.navigate(['/inventory']); // Navigate to the home page or any other page
  }
}
