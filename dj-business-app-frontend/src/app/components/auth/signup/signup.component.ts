import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common'
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../service/auth.service';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit {
  successMessage: string = '';
  errorMessage: string = "";
  signupForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(64)]],
    })
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      this.errorMessage = "Please fill in all required fields.";
      return;
    }

    this.authService.signup(this.signupForm.value).subscribe(
      data => {
        this.errorMessage = '';
        this.successMessage = data?.message  || "User registered successfully.";
        this.signupForm.reset();
      }, error => {
        this.successMessage = '';
        this.errorMessage = error?.error?.message || "An error occurred while registering."
      })
  }

}
