import { HttpErrorResponse, HttpEvent, HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
export const AuthInterceptor: HttpInterceptorFn = (req:HttpRequest<any>, next:HttpHandlerFn):Observable<HttpEvent<any>>=>{
  const router=inject(Router);
    return next(req).pipe(
      catchError((error:HttpErrorResponse)=>{
        if(error.status === 401 && !req.url.endsWith('/api/me')){
          router.navigate(['/login']);
        }
        return throwError(()=>error);
      })
    );
  };


