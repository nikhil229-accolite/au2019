import { Component } from '@angular/core';
import { Router } from '@angular/router';
Router
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  _router: string;
  constructor(public router: Router){

    this._router = router.url; 
  }
  title = 'Assignment';

  showVar: boolean = false;
  showDepList: boolean = false;

  toggleEmpList(){

      this.showVar = true;
      this.showDepList = false;
}

  toggleDepList(){
    this.showDepList = true;
    this.showVar = false;
  }

}
