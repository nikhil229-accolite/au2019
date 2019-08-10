import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppServiceService } from '../app-service.service';


@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  public _router:any;
  public recId: any;
  public emp: any;
  public empDataArray: any;
  public showText : false;

  constructor(private router: Router, private as: AppServiceService, private route: ActivatedRoute) { 
    this._router = router.url;
  }

  ngOnInit() {
    
    this.route.params.subscribe(val => {
      this.recId = val.userId;
      this.emp = this.as.getEmpFromId(this.recId);

    });

    this.as.empDataOb.subscribe(recVal=>{
      this.empDataArray = recVal;
    });

    
    console.log(this.empDataArray);

    this.as.setEmpArray(this.empDataArray);

}

showUpdatedItem(newItem){
  let updateItem = this.empDataArray.items.find(this.findIndexToUpdate, newItem.userId);

  let index = this.empDataArray.items.indexOf(updateItem);


  this.empDataArray.items[index] = newItem;


  console.log(this.empDataArray);
}

findIndexToUpdate(newItem) { 
      return newItem.userId === this;
}


showInput()
{
  this.showText = !this.showText;
}





  

}
