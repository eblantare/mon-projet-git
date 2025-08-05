import { Routes } from '@angular/router';
import { Login }from './login/login';
import { Home } from './home/home';
import { CreateUser} from './create-user/create-user';
import { Dashboard } from './dashboard/dashboard';
import { Register } from './register/register';
import { UserList } from './user-list/user-list';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path:'home',component:Home},
    { path:'create-user',component:CreateUser},
    { path:'register',component:Register},
    { path:'dashboard',component:Dashboard},
    {path:'user-list', component:UserList},
    { path: '**', redirectTo: '/login' }

];
