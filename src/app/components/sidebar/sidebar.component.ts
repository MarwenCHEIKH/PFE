import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  isDropped1: boolean = false;
  isDropped2: boolean = false;
  constructor() { }
  
    ngOnInit() {
    }
    dropdown1(): void {
      this.isDropped1 = !this.isDropped1;
      
    }
    dropdown2(): void {
      this.isDropped2 = !this.isDropped2;
      
    }
  
  }
