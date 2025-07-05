import { Routes } from '@angular/router';
import { Login }from './login/login'
import { CreateUser} from './create-user/create-user';
import { UserList } from './user-list/user-list';

export const routes: Routes = [
    { path:'',component:Login},
    { path:'create-user',component:CreateUser},
    {path:'user-list', component:UserList}
    
];
