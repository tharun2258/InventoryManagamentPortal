import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const roleAdminGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  let RoleObj= localStorage.getItem('role');
  console.log(RoleObj);
  if(RoleObj == '"[Admin]"'){
    
    
     return true;
     
  }

  router.navigateByUrl("/not-authorized")
return false;
};
