import {Routes} from '@angular/router';

export const routes: Routes = [
  {path: 'auth', loadChildren: () => import('./components/auth/auth.routes').then(m => m.authRoutes)}
];
