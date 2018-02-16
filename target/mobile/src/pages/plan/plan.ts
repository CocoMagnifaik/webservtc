import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams ,LoadingController} from 'ionic-angular';
import { Http } from '@angular/http';
import { RequestService } from '../services/request.service';
/**
 * Generated class for the PlanPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-plan',
  templateUrl: 'plan.html',
})
export class PlanPage {
information: any[];
informationCateogrie : any;
  constructor(public navCtrl: NavController,public navParams: NavParams,private http : Http,public loadingCtrl: LoadingController,public service:RequestService) {

  let  local=this.http.get('https://webservtc.herokuapp.com/musiqueTotal')
     .map(res => res.json())
    .subscribe(data => {this.information= data ;  })
    this.initializeItems();
  }
initializeItems() {
    this.information;
    //this.informationsurCategorie;
  }



   getItems(ev: any) {
      // Reset items back to all of the items
      this.initializeItems();

      // set val to the value of the searchbar
      let val = ev.target.value;console.log(val);
      // if the value is an empty string don't filter the items
      if (val && val.trim() != '') {
        this.information = this.information.filter((item) => {

          return (item.toString().toLowerCase().indexOf(val.toLowerCase()) > -1);
        })
      }
      else{ this.initializeItems();}
    }


  ionViewDidLoad() {
      console.log('ionViewDidLoad PlanPage');
  }

}
