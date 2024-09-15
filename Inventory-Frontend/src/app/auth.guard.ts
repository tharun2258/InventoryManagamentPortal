import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from './login.service';

export const authGuard: CanActivateFn = (route, state) => {
  
  const service = inject(LoginService);
  const router = inject(Router);
   
   if(service.isLoggedIn()){
    return true;
   }
   else{
    alert("you are not logged in!!!")
    router.navigate(['/login']); // Redirect to login if not authenticated
    return false;
   }
}
