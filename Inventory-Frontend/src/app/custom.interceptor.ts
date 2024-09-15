import { HttpInterceptorFn } from '@angular/common/http';

export const customInterceptor: HttpInterceptorFn = (req, next) => {

  if(req.url.includes("/user/login") || req.url.includes("user/createUser") || req.url.includes("user/resetpassword") || req.url.includes("user/byusername/")){
    return next (req);
  }
  
  const token = localStorage.getItem('token');
  const cloneRequest = req.clone({
    setHeaders:{
      Authorization: `Bearer ${token}`
    }
  })
  return next(cloneRequest);
};
