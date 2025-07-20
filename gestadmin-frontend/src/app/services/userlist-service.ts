import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// ✅ Interface pour la pagination
export interface Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
}
// ✅ Interface UserListDto à définir ici ou à importer d’un autre fichier
export interface UserListDto {
  id: number;
  username: string;
  nom: string;
  prenoms: string;
  email: string;
  telephone: string;
  roles: string[];
}

@Injectable({
  providedIn: 'root'
})
export class UserlistService {

  constructor(private http:HttpClient) { }

  getUsers(page:number =0,size:number=10,sort:string[]=['id','asc'],search:string='')
            :Observable<Page<UserListDto>>{
  const params= new HttpParams()
                  .set('page',page)
                  .set('size',size)
                  .set('sort',sort.join(','))
                  .set('search',search);
  
  return this.http.get<Page<UserListDto>>('/api/admin/getAllUsers',{params});

  }


}
