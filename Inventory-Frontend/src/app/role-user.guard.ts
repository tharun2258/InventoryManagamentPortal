import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const roleUserGuard: CanActivateFn = (route, state) => {
  
  const router = inject(Router);
  let RoleObj= localStorage.getItem('role');
  if(RoleObj == '"[User]"'){
    
    
     return true;
     
  }
  router.navigateByUrl("/admin");

return false;
};