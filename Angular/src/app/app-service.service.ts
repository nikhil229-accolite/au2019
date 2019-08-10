import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as data from './employees.json';
import * as dep from './department.json';

@Injectable({
  providedIn: 'root'
})
export class AppServiceService {

  private showFlag : false;
  private showFlagBehaviourObj = new BehaviorSubject(this.showFlag);
  showFlagObs = this.showFlagBehaviourObj.asObservable();


  private employeeDetails : any;
  private dataSource = new BehaviorSubject(this.employeeDetails);
  currentData = this.dataSource.asObservable();
  

  private employeeList = data.default;
  private EmployeeList = new BehaviorSubject(this.employeeList);
  empDataOb = this.EmployeeList.asObservable();

  private depList = dep.default;
  private depBehaviourObj = new BehaviorSubject(this.depList);
  depListOb = this.depBehaviourObj.asObservable();

 
  constructor() { }

  getEmpDetails(){
    return this.employeeDetails;
  }

  getShowFlag(){
    return this.showFlag;
  }

  setShowFlag(val)
  {
    this.showFlag = val;
  }


  setEmpDetails(val: any){
    this.dataSource.next(val);
  }

  getDepList(){
    return this.depList;
  }

  getEmpList(){
    return this.employeeList;
  }


  getEmpFromId(id:any)
  {
    var e = this.employeeList.find(function(element){
      return element.userId == id;
    });
    return e;
  }

  setEmpList(val:any){
    this.EmployeeList.next(val);
  }



  setEmpArray(val:any){
    this.employeeList = val;
  }



}
