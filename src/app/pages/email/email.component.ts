import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Email } from '../../models/Email';
import { Employee } from 'src/app/models/Employee';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent implements OnInit {
  isSelect: boolean = false;
  editorForm: FormGroup = new FormGroup({
    'editor': new FormControl(null),
    'to' : new FormControl(null),
    'subject': new FormControl(null),
  });
  textMessage: String = "";
  to: String = "";
  subject: String = "";
  email:Email = new Email()
  employees: Employee[] = [
  {firstName: 'marwen', lastName:'cheikh',fullName: 'Marwen Cheikh',emailAddress:'marouene.cheikh@gmail.com'},
  {firstName: 'john', lastName:'snow',fullName:'john Snow',emailAddress:'m.cheikh2712@gmail.com'}];

  dropdownList:any[]=[];
  selectedItems:any[]=[];
  dropdownSettings:IDropdownSettings = {};
  
  constructor(private httpClient: HttpClient ) { }

  ngOnInit():void {
    this.employees.forEach(e => {
    this.dropdownList.push ({ item_id: e.emailAddress, item_text: e.fullName});
    });
    this.selectedItems = [];
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit:3 ,
      allowSearchFilter: true
    };
  }
   onSubmit(){
    this.email.subject = this.editorForm.get('subject')?.value;;
    this.email.message = this.editorForm.get('editor')?.value;
    if(this.isSelect == false) { 
      if (confirm("are you sure you want to send this email?")) {
      this.employees.forEach(e => {
          this.email.to = e.emailAddress;
            this.httpClient.post('http://localhost:8080/send-attachment',this.email)
            .subscribe(res => {
              console.log(res);
            },
            err => {
              console.log(err);
            });
          });
          }
          else {
            window.alert('email not sent!');
          }
      }
    else {
     if (confirm("are you sure you want to send this email?")) {
       this.selectedItems.forEach(i => {
         this.email.to = i.item_id;
         this.httpClient.post('http://localhost:8080/send-attachment',this.email)
         .subscribe(res => {
           console.log(res);
          },
          err => {
            console.log(err);
          });
        });
      }
      else {
        window.alert('email not sent!');
      }
    }  
    }
    onItemSelect(item: any) {
      this.selectedItems.push(item);
    }
    onSelectAll(items: any[]) {
      items.forEach(i=> {
        this.selectedItems.push(i);
      });
    }
    selectChangeHandler (event: any) {
      this.isSelect = event.target.value=="select employee";
    }
  }