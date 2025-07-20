import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // private baseurl = 'http://localhost:8084/api';

  constructor(private http:HttpClient) { }
  login(username:string,password:string):Observable<any>{
    console.log('LOGIN DU SERVICE********1');
    // const body = new URLSearchParams();
    // body.set('username',username);
    // body.set('password',password);
    const body = {username,password};

    return this.http.post('/api/login',JSON.stringify(body), {
    headers: {
      // 'Content-Type': 'application/x-www-form-urlencoded'
      'Content-Type':'application/json'
    },
    withCredentials: true,
    observe: 'response'

  });
  }
  getCurrentUser():Observable<any>{
    return this.http.get(`/api/me`, { withCredentials: true });
  }
  logout():Observable<any>{
    return this.http.post('/logout',{},{withCredentials:true});
  }
}
