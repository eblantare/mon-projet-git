import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }
  login(username:string,password:string):Observable<HttpResponse<any>>{
    const body = new URLSearchParams();
    body.set('username',username);
    body.set('password',password);

    return this.http.post('/login',body.toString(),{
      observe:'response',
      withCredentials:true,
      headers:{'Content-Type':'application/x-ww-form-urlencoded'}
    });
  }

  getCurrentUser():Observable<any>{
    return this.http.get('/api/me',{withCredentials:true});
  }
  logout():Observable<any>{
    return this.http.post('/logout',{},{withCredentials:true});
  }
}
