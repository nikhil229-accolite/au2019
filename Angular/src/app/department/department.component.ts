import { Component, OnInit } from '@angular/core';
import { AppServiceService } from '../app-service.service';


@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {
  //@Input() showDepartmentList: boolean;
  depArray : any;
  constructor(private as : AppServiceService) { }

  ngOnInit() {
    this.depArray=this.as.getDepList();
  }

  

}
