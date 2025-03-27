import {Routes} from '@angular/router';
import {BlogComponent} from './blog/blog.component';
import {BlogFormComponent} from './blog-form/blog-form.component';


export const blogRoutes: Routes = [
  {path:'', component: BlogComponent},
  {path:'create', component: BlogFormComponent}
]
