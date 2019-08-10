import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { DepartmentComponent } from './department/department.component';
import { TodoComponent } from './todo/todo.component';


const routes: Routes = [


  {
    path: 'employeeList',
    component: EmployeeListComponent
  },
  {
    path: 'employee/:userId',
    component: EmployeeDetailsComponent
  },
  {
    path: 'departmentList',
    component: DepartmentComponent
  },
  {
    path: 'todo',
    component: TodoComponent
  },
  {
    path:'todo/:userId',
    component: TodoComponent
    
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
