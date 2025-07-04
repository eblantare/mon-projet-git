import { Routes } from '@angular/router';
import { CreateUser} from './create-user/create-user';
import { UserList } from './user-list/user-list';

export const routes: Routes = [
    {path:'', component:UserList},
    { path:'create-user',component:CreateUser}
];
