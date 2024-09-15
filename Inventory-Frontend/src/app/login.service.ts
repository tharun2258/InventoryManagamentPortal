import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from './user';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

 
  apiUrl : String ="http://localhost:8080/user";
  
  private isLoggedIn$ = new BehaviorSubject<boolean>(false);
  isLoggedInpub$ = this.isLoggedIn$.asObservable();

constructor(private http: HttpClient){
  const token = localStorage.getItem('jwtToken')
  if (token != null) {

  
    this.isLoggedIn$.next(!!token);
}



}

 public loginUser(user:User): Observable<any>{
  return this.http.post<User>('http://localhost:8080/user/login',user);
  
}

  loginUserDetails(usern:User): Observable<any>{
  
    return this.loginUser(usern).pipe(
     tap((response:any) => {
     localStorage.setItem('token' , response.token);
     localStorage.setItem('role' , JSON.stringify(response.role));
    //  localStorage.setItem('username',response.username);
     localStorage.setItem('userid',response.userid);
     localStorage.setItem('response', response);
    
  })
    );
 }

 public getUsername(){
  return localStorage.getItem('username');
 }

 public getUserid(){
  return localStorage.getItem('userid');
 }

 public getRole(){
  const role= localStorage.getItem('role');
  if(role != null){
  const roleObj =role.replace(/["\[\]]/g, '');
  return roleObj;

  }
  else{
    return null;
  }
 }

 public userdetailsbyname(username:string){
  return this.http.get<User>("http://localhost:8080/user/byusername/"+username);
 }

 public updateUserDetails(userid:number,usern:User){
  return this.http.put<User>("http://localhost:8080/user/updateUserById/"+userid, usern);
 }

 
 public userdetailsbyid(userid:number){
 
  return this.http.get<User>("http://localhost:8080/user/getUserById/"+userid);
 }


 public registerUser(user:User){
  return this.http.post<User>("http://localhost:8080/user/createUser", user);
 }

 public resetPassword(userid:number,user:User){
  return this.http.put<User>("http://localhost:8080/user/resetpassowrd/{userid}", user);
 }

 public isLoggedIn() {
  return this.getToken();
}
  public getToken() {
    return localStorage.getItem('token');
  }

logout(){
  this.isLoggedIn$.next(false);
  localStorage.removeItem('token');
  localStorage.removeItem('role');
  localStorage.removeItem('userid');
  localStorage.removeItem('username');
  localStorage.removeItem('email');
  localStorage.removeItem('mobilenum');
  localStorage.removeItem('response');
  

}


}

