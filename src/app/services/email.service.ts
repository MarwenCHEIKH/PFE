import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Email } from '../models/Email';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EmailService {

  email: Email = new Email ;
  constructor(private httpClient: HttpClient ) {}
  sendEmail(email: Email) : Observable<Email>{
    return this.httpClient.post<Email>('http://localhost:8080/send-email',this.email);
  }
}
