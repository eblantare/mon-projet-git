import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';



export interface User {
      username : string;
      email : string;
      role : string;
      password : string;
  
}
@Injectable({
  providedIn: 'root'
})
export class UserService{
  private apiurl='http://localhost:8084/api/admin/create-user';

  constructor(private http:HttpClient){}

  //Créer un urilisateur
  createUser(user:User):Observable<User>{
    return this.http.post<User>(this.apiurl,user);

  }
  //Liste de tous les utilisateurs (facultatif)
  getUsers():Observable<User[]>{
            return this.http.get<User[]>(this.apiurl);
  }
  
  //Supprimer un utilisateur (facultaif)

  deleteUser(id:string):Observable<any>{
    return this.http.delete('${this.apiurl}/${id}');
  }

  //Modifier un utilisateur (facultatif)
  updateUser(id:string,user:User):Observable<User>{
    return this.http.put<User>('${this.apiurl}/${id}',user);
  }
    // Trouver un utilisateur par ID (facultatif)
    getUserById(id:string):Observable<User>{
      return this.http.get<User>('${this.apiurl}/${id}');
    }
}