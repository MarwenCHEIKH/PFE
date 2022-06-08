import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { EmailComponent } from './pages/email/email.component';

const routes: Routes = [
  { path: 'sendEmail', component: EmailComponent },
  { path: '', component: DashboardComponent }
  
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
