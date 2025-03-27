import { Component } from '@angular/core';
import {FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-blog-form',
    imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      RouterLink
    ],
  templateUrl: './blog-form.component.html',
  styleUrl: './blog-form.component.css'
})
export class BlogFormComponent {
  blogForm: FormGroup = new FormGroup({});

}
