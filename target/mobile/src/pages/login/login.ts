import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams,LoadingController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import { Http } from '@angular/http';
import { RequestService } from '../services/request.service';
/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  email:string
   psd:string
 	splash=true;
	tabBarElement:any;
  tabUserMdp : any ;
  
  constructor(public navCtrl: NavController, public navParams: NavParams,private alertCtrl:AlertController,public loadingCtrl: LoadingController,private http: Http,public service:RequestService) {
   this.tabBarElement=document.querySelector('.page-login');
     if(this.navParams.get('pasSplash')!=undefined){
               this.splash=false;
               console.log('ve')
         }



  }
  valeurLogin(){
    let loader =this.loadingCtrl.create({
      content:"patienter s'il vous plait.....",
      dismissOnPageChange :true,
     })
     loader.present();
    let localData = this.service.getUser(this.email,this.psd);
     localData.then(data => {

      console.log(data);
      console.log("Ok cedy")
     this.tabUserMdp=data;
     //console.log('f'+this.tabUserMdp)
     //console.log('t'+this.tabUserMdp.length);
     
     // console.log('lol'+this.tabUserMdp[0]);
     // if(this.tabUserMdp[0].email==this.email && this.tabUserMdp[0].mdp==this.psd)
     


   //if(this.tabUserMdp.length != 0 && this.tabUserMdp =='undefined')
     // {

        console.log('zz'+this.tabUserMdp[0].email);
          if(this.tabUserMdp[0].pseudo == '' && this.tabUserMdp[0].mdp== '' )
          {
            this.presentAlertAucun();
            loader.present();
            this.navCtrl.setRoot('LoginPage');
          }
          if(this.tabUserMdp[0].pseudo == this.email && this.tabUserMdp[0].mdp== this.psd){
          console.log(this.email);
          loader.present();
          this.presentAlertValidate();
          this.navCtrl.setRoot('MenuPage',{id :this.tabUserMdp[0].id});
          
          }
          else{

            this.presentAlertErreur();
        loader.present();
        this.navCtrl.setRoot('LoginPage');
          }
   /* }else{
      this.presentAlertConnection()
      this.navCtrl.setRoot('LoginPage');

    }*/
  
        
   setTimeout(() =>{
      //this.tabBarElement.style.display='none';
         loader.dismiss();
    },1000);



    })
  

  }
  presentAlertConnection() {
    let alert = this.alertCtrl.create({
      title: ' verifier votre connexion',
      subTitle: 'session limite',
      buttons: ['valider']
    });
    alert.present();
  }
  presentAlertAucun() {
    let alert = this.alertCtrl.create({
      title: ' verifier votre champ est vide'+this.email,
      subTitle: 'session limite',
      buttons: ['valider']
    });
    alert.present();
  }
  presentAlertValidate() {
    let alert = this.alertCtrl.create({
      title: 'Votre compte existe avec succes'+this.email,
      subTitle: 'session limite',
      buttons: ['valider']
    });
    alert.present();
  }
  presentAlertErreur() {
    let alert = this.alertCtrl.create({
      title: 'Compte nxiste pas'+this.email,
      subTitle: 'session limite',
      buttons: ['valider']
    });
    alert.present();
  }

 ionViewDidLoad(){
   setTimeout(() =>{
		//this.tabBarElement.style.display='none';
  		this.splash=false;
  		//this.tabBarElement.style.display='flex';
  },4000);
  }
 /*doLogin() {
  let loader =this.loadingCtrl.create({
      content:"patienter s'il vous plait.....",
      dismissOnPageChange :true,
     })
     loader.present();
         setTimeout(() =>{
         		//this.tabBarElement.style.display='none';
                loader.dismiss();
           },1000);
           let valinyLogin=this.valeurLogin(this.email,this.psd);
           if(valinyLogin != null){
                   console.log(this.email);
                    this.navCtrl.setRoot('MenuPage');
     }
     else{
       this.presentAlert();
       this.navCtrl.setRoot('LoginPage');
     }
    // this.navCtrl.push('MenuPage',{pasSplash:true});
  }*/

 

   doInscription() {
   let loader =this.loadingCtrl.create({
    spinner:'crescent',
    content:"patienter s'il vous plait....."
    });

    loader.present();
    

      // console.log(this.email);
      this.navCtrl.setRoot('RegisterPage');
           
          // this.navCtrl.push('MenuPage',{pasSplash:true});
          setTimeout(() =>{
            //this.tabBarElement.style.display='none';
            loader.dismiss();
          },1000);
    
   }





}
