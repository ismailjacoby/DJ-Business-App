import {Routes} from '@angular/router';
import {DashboardComponent} from './components/dashboard/dashboard.component';

export const routes: Routes = [
  {path:'dashboard', component: DashboardComponent},
  {path: 'auth', loadChildren: () => import('./components/auth/auth.routes').then(m => m.authRoutes)}
];
