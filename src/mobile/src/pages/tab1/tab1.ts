import { Component } from '@angular/core';
import { IonicPage, NavController,LoadingController } from 'ionic-angular';
import { Http } from '@angular/http';
import {RequestService} from '../services/request.service';
import 'rxjs/add/operator/map';

/**
 * Generated class for the Tab1Page page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-tab1',
  templateUrl: 'tab1.html',
})
export class Tab1Page {
  malagasy
  francais
information: any[];
music : any [];
informationCateogrie: any [];
  constructor(public navCtrl: NavController,private http : Http,public loadingCtrl: LoadingController,public service:RequestService) {
    this.malagasy = {"valeur" : "Malagasy"}
    this.francais = {"valeur" : "Francais"}
  	let localData = http.get('https://webservtc.herokuapp.com/musiqueTotal').map(res => res.json());
    localData.subscribe(data => {
      
      this.information = data;console.log(this.information.length);
     
    })
  this.initializeItems();
  }
initializeItems() {
 
    this.information;
    this.informationsurCategorie();
}


  informationsurCategorie(){
    let loader =this.loadingCtrl.create({
      content:"Veuillez patienter...",
      dismissOnPageChange :true,
    })
     loader.present();
    let localData = this.service.getCategorieTotal();
     localData.then(data => {
      this.informationCateogrie=data;
      console.log('aizaaaaa');
      console.log(this.informationCateogrie.length)
      
        //this.tabBarElement.style.display='none';
      loader.dismiss();
     })
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

  toggleSection(i,j) {
  console.log('tt'+j.id);

    this.information[i].open = !this.information[i].open;
  }

  toggleSection2() {
      this.malagasy.open = !this.malagasy.open;
    }
    toggleSection3() {
      this.francais.open = !this.francais.open;
    }
  buyItem(i){
    console.log("test"+i);
   document.location.href = 'app/jeu.html'
  }
 /* prendreitems(itemMusic){
 this.listMusicCategory = [];
 for(let i=0;i<this.information.length;i++){

 let listmusic=this.information[i].titre;
 let indice=0;
 listmusic.filter((item) => {
 if (item.toLowerCase().indexOf(itemMusic.toLowerCase()) > -1){
  indice=1
    }
    })
    if(indice == 1){

  this.listMusicCategory.push(this.information[i])
  }
  }

  }*/
  toggleItem(i, j) {
    this.information[i].children[j].open = !this.information[i].children[j].open;
  }

  viewFicheMusic(music : any){
    let loader =this.loadingCtrl.create({
      content:"Veuillez patienter...",
      dismissOnPageChange :true,
     })
     loader.present();
    /* let response =this.http.get('https://webservtc.herokuapp.com/'+music.id).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue au plat list : '+error))
     response.then(data =>{
     this.music =data
   //this.navCtrl.push(JeuPage,{musicParam :music});
  })*/
console.log('id de la mucis'+music.id)
  this.navCtrl.setRoot('JeuPage',{idMusic :music.id,nomMu : music.titre,ficheMusic : music.fichier});
    

  setTimeout(() =>{
    //this.tabBarElement.style.display='none';
       loader.dismiss();
  },2000);  

}
}