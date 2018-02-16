import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams,LoadingController } from 'ionic-angular';
import { ToastController } from 'ionic-angular';
import {RequestService} from '../services/request.service';
import {User} from '../modele/modele.user'
/**
 * Generated class for the RegisterPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-register',
  templateUrl: 'register.html',
})
export class RegisterPage {


  email:string
  pseudo:string
  pwd:string
  sexe : string
  nationalite : string
  tabuser : any;
  constructor(public navCtrl: NavController, public navParams: NavParams,private toastCtrl:ToastController,public loadingCtrl: LoadingController,public service:RequestService) {
  }
presentToast() {
  let toast = this.toastCtrl.create({
    message: 'User was added successfully',
    duration: 1500,
    position: 'top'
  });
  toast.onDidDismiss(() => {
      console.log('Dismissed toast');
    });

    toast.present();
    }
 doRegister() {
  let loader =this.loadingCtrl.create({
    content:"patienter s'il vous plait.....",
    dismissOnPageChange :true,
   })
   loader.present();
   console.log(this.email)
   console.log(this.pseudo)
   console.log(this.pwd)
   console.log(this.sexe)
   console.log(this.nationalite)
   let donneuser =  this.service.createUtilisateur(this.email,this.pseudo,this.pwd,this.sexe,this.nationalite,0);
   donneuser.then(data => {
      console.log('aizaaaaa');
       this.tabuser=data;
       console.log('tafiditra');
           loader.dismiss();
     

     })
 
    this.presentToast();
    this.navCtrl.setRoot('MenuPage',{id :this.tabuser[0].id});
    //his.navCtrl.setRoot('MenuPage');
  }



}
