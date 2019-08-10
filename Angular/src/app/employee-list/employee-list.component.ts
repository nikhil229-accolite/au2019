import { Component, OnInit, Input } from '@angular/core';

import { AppServiceService } from '../app-service.service.js';
import { Router } from '@angular/router';



@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})


export class EmployeeListComponent implements OnInit {
  public empDataArray : any;
  public showFlag:any;
  _router: string;


  constructor(public as: AppServiceService, public router: Router) {
    this._router = router.url; 
   }

  ngOnInit() {
    this.as.empDataOb.subscribe(recVal=>{
      this.empDataArray = recVal;
    });

    this.as.empDataOb.subscribe(val=>{
      this.showFlag = val;
    });

  }

  showDetails(e:any)
  {
    if( this._router === '/employeeList'){
      this.router.navigate(['/employee',e.userId]);
      
    }
    else{
      
      this.router.navigate(['/todo',e.userId]);
    }

    
  }


  deleteEmployee(employee: any) {
    var result = this.arrayRemove(this.empDataArray, employee);
    this.as.setEmpList(result);
  }

  arrayRemove(arr, value) {
    return arr.filter(function (ele) {
      return ele != value;
    });
  }


}

