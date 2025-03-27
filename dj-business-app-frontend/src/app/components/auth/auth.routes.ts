import {Routes} from '@angular/router';
import {SignupComponent} from './signup/signup.component';
import {LoginComponent} from './login/login.component';

export const authRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path:'signup', component: SignupComponent}
]
