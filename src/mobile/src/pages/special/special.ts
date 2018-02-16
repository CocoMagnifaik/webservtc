import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the SpecialPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-special',
  templateUrl: 'special.html',
})
export class SpecialPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }
 slides = [
    {
      title: "Welcome to the Docs!",
      description: "",
      image: "assets/imgs/ica-slidebox-img-1.png",
    },
    {
      title: "C'est quoi TiaCaraks?",
      description: "",
      image: "assets/imgs/ica-slidebox-img-2.png",
    },
    {
      title: "C'est son atout?",
      description: ".",
      image: "assets/imgs/ica-slidebox-img-3.png",
    }
  ];
  ionViewDidLoad() {
    console.log('ionViewDidLoad SpecialPage');
  }

}
