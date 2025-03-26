import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {SignupForm} from '../model/signup-form';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl + "/auth"

  constructor(private http: HttpClient) {
  }

  signup(form: SignupForm): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/signup`, form);
  }
}
