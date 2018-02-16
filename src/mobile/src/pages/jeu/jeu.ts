import { Component } from '@angular/core';
import { Platform,IonicPage, NavController, NavParams,LoadingController,ToastController  } from 'ionic-angular';
import { StreamingMedia, StreamingVideoOptions, StreamingAudioOptions } from '@ionic-native/streaming-media';
import { Media, MediaObject } from '@ionic-native/media';
import { File } from '@ionic-native/file';
import { FileTransfer, FileUploadOptions, FileTransferObject } from '@ionic-native/file-transfer';
import { AlertController } from 'ionic-angular';
import { HttpModule } from '@angular/http';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';

/*
 * Generated class for the JeuPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */


@IonicPage()
@Component({
  selector: 'page-jeu',
  templateUrl: 'jeu.html',

})

export class JeuPage {
     myTracks: any[];
      allTracks: any[];
      recording: boolean = false;
      filePath: string;
      fileName: string;
      audio: MediaObject;
      audioList: any[] = [];
      filename: any = "Hotel California";
        curr_playing_file: MediaObject;
        storageDirectory: any;

        is_playing: boolean = false;
        is_in_play: boolean = false;
        is_ready: boolean = false;

        message: any;

        duration: any = -1;
        duration_string: string;
        position: any = 0;

        get_duration_interval: any;
      get_position_interval: any;
      paroleList : any [];
      minutageMusic : any [];
      paroleSplit : any [];
      valinyMusic : any [];
      paroleindice : any [];
    posline : any [];
    reponse: string;
    taille : any [];
    point : number =0;


  constructor(public navCtrl: NavController, public navParams: NavParams, private file: File,private media: Media,public platform: Platform,private streamingMedia: StreamingMedia,private transfer: FileTransfer,public loadingCtrl: LoadingController,private http: Http,public alertCtrl: AlertController,private toastCtrl: ToastController) {

          this.getMessage();

           this.platform.ready().then(() => {
                  if(this.platform.is('ios')) {
                    this.storageDirectory = this.file.dataDirectory;
                  } else if(this.platform.is('android')) {
                    this.storageDirectory = this.file.externalDataDirectory;
                  }
          });
      this.myTracks = [{
            src: 'https://archive.org/download/JM2013-10-05.flac16/V0/jm2013-10-05-t12-MP3-V0.mp3',
            artist: 'John Mayer',
            title: 'Why Georgia',
            art: 'img/johnmayer.jpg',
            preload: 'metadata' // tell the plugin to preload metadata such as duration for this track, set to 'none' to turn off
          },
          {
            src: 'https://archive.org/download/JM2013-10-05.flac16/V0/jm2013-10-05-t30-MP3-V0.mp3',
            artist: 'John Mayer',
            title: 'Who Says',
            art: 'img/johnmayer.jpg',
            preload: 'metadata' // tell the plugin to preload metadata such as duration for this track,  set to 'none' to turn off
          }];


  }

   /* presentAlert() {
     let trim= response.data.paroles.trim().split("...");
      let alert = this.alertCtrl.create({
        title: 'test'+trim,
        subTitle: 'session limite',
        buttons: ['valider']
      });
      alert.present();
    }*/
  getMessage(){


    let idpurMusic =this.navParams.get('idMusic');
    let nomMusic= this.navParams.get('nomMu');
  this.http.get('https://webservtc.herokuapp.com/findMusiqueIdTitre/'+idpurMusic+'/'+nomMusic)
   .map(res => res.json())
  .subscribe(data => {this.paroleList= data ;
   var line= this.paroleList[0].paroles.trim().split("...")
               .map(function (line){
               return line.split(/[-"]/gi);

                 });
                  var lineminutes= this.paroleList[0].minutage.trim().split("...")
                                .map(function (lineminutes){
                                return lineminutes.split(/[-"]/gi);

                                  });
                                   var linereponse= this.paroleList[0].valiny.trim().split("...")
                                                                  .map(function (linereponse){
                                                                  return linereponse.split(/[-"]/gi);

                                                                    });
                                                                    /* var psitionline= this.position.trim().split(".")
                                                                                                    .map(function (psitionline){
                                                                                                    return psitionline.split(/[-"]/gi);

                                                                                                      SSS});*/
//alert(line.length);
alert(lineminutes[0]);
this.minutageMusic=lineminutes;
this.paroleindice=line;
  this.paroleSplit=line[0];
  this.valinyMusic=linereponse;
 // this.taille=linereponse;
//  this.posline=positionline;
  /* var i =0;
  var intervalle=setInterval(() =>{
                	     document.getElementById("changerParole").innerHTML= line[i];
                	     this.presentPrompt();
                	                     	     //  this.paroleSplit=line[0];
                	                     	      console.log('ddd'+this.position*1000 +'=='+parseInt(this.minutageMusic[i]/1000));
                         console.log(i+1);console.log(line[i+1]);

 i++;
 if(line.length == i){
    clearInterval(intervalle);
 }
                  },2000);*/
             /*var i =0;


                           var intervalle=setInterval(() =>{

                                                	      document.getElementById("changerParole").innerHTML= line[i];
                                                	      console.log('dddo'+parseInt(this.position)+' =='+ parseInt(this.minutageMusic[i]));
                                                          //  console.log('ppppp'+this.get_position_interval);
                                         	     if(this.position == parseInt(this.minutageMusic[i])){
                                                        /// console.log('ddd'+this.position +'=='+parseInt(this.minutageMusic[i]/1000));
                                         	     this.presentPrompt();
                                         	     this.pausePlayRecording();
                                              	     }
                                         	                     	     //  this.paroleSplit=line[0];
                                              //    console.log(i);console.log('line'+line[i]);

                          i++;
                          if(line.length == i){
                             clearInterval(intervalle);
                          }
                                           },1000);*/






  }
                  , err => {
                          console.log("Oops erreur de chargment!");
                      }


  )



  }


/*controlVolume(){

 presentAlert();
}*/
    ionViewWillEnter(){
      // comment out the following line when adjusting UI in browsers
      this.prepareAudioFile();
     // this.spliterparoles();

  }


  viewMusicController(){
    let loader =this.loadingCtrl.create({
      content:"patienter s'il vous plait.....",
      dismissOnPageChange :true,
     })
     loader.present();
    /* let response =this.http.get('https://webservtc.herokuapp.com/'+music.id).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue au plat list : '+error))
     response.then(data =>{
     this.music =data
   //this.navCtrl.push(JeuPage,{musicParam :music});
  })*/

  

    

  setTimeout(() =>{
    //this.tabBarElement.style.display='none';
       loader.dismiss();
  },2000);  

}
  /*spliterparoles(){

    var line= this.position[0].paroles.trim().split("...")
             .map(function (line){
             return line.split(/[-"]/gi);

               });
      alert(line);
      }*/
/*getAudioList() {
  if(localStorage.getItem("audiolist")) {
    this.audioList = JSON.parse(localStorage.getItem("audiolist"));
    console.log(this.audioList);
  }
}
ionViewWillEnter() {
  this.getAudioList();
}*/



   prepareAudioFile() {
     let fiche = this.navParams.get('ficheMusic');console.log('fich'+fiche)
      let url = fiche;
      this.platform.ready().then(() => {
        this.file.resolveDirectoryUrl(this.storageDirectory).then((resolvedDirectory) => {
          // inspired by: https://github.com/ionic-team/ionic-native/issues/1711
          console.log("resolved  directory: " + resolvedDirectory.nativeURL);
          this.file.checkFile(resolvedDirectory.nativeURL, "tsy atakaloko.mp3").then((data) => {
            if(data == true) {  // exist
              this.getDurationAndSetToPlay();
            } else {  // not sure if File plugin will return false. go to download
              console.log("not found!");
              throw {code: 1, message: "NOT_FOUND_ERR"};
            }
          }).catch(err => {
            console.log("Error occurred while checking local files:");
            console.log(err);
            if(err.code == 1) {
              // not found! download!
              console.log("not found! download!");
              let loading = this.loadingCtrl.create({
                content: 'Downloading the song from the web...'
              });
              loading.present();
              const fileTransfer: FileTransferObject = this.transfer.create();
              fileTransfer.download(url, this.storageDirectory + "tsy atakaloko.mp3").then((entry) => {
                console.log('download complete' + entry.toURL());
                loading.dismiss();
                this.getDurationAndSetToPlay();
              }).catch(err_2 => {
                console.log("Download error!");
                loading.dismiss();
                console.log(err_2);
              });
            }
          });
        });
      });
  }

   createAudioFile(pathToDirectory, filename): MediaObject {
      if (this.platform.is('ios')) {  //ios
        return this.media.create((pathToDirectory).replace(/^file:\/\//, '') + '/' + filename);
      } else {  // android
        return this.media.create(pathToDirectory + filename);
      }
    }

    getDurationAndSetToPlay() {
      this.curr_playing_file = this.createAudioFile(this.storageDirectory, "tsy atakaloko.mp3");
      this.curr_playing_file.play();
      this.curr_playing_file.setVolume(0.0);  // you don't want users to notice that you are playing the file
      let self = this;
      this.get_duration_interval = setInterval(function() {
        if(self.duration == -1) {
          self.duration = ~~(self.curr_playing_file.getDuration());  // make it an integer
          console.log('self'+self.duration);
          // self.duration_string = self.fmtMSS(self.duration);   // replaced by the Angular DatePipe
        } else {
          self.curr_playing_file.stop();
          self.curr_playing_file.release();
          self.setRecordingToPlay();
          clearInterval(self.get_duration_interval);
        }
      }, 100);
  }

  getAndSetCurrentAudioPosition() {
      let diff = 1;
      let self = this;
      this.get_position_interval = setInterval(function() {
        let last_position = self.position;
        self.curr_playing_file.getCurrentPosition().then((position) => {
          if (position >= 0 && position < self.duration) {
            if(Math.abs(last_position - position) >= diff) {
              // set position
              self.curr_playing_file.seekTo(last_position*1000);
            } else {
              // update position for display
              self.position = position;
            }
          } else if (position >= self.duration) {
            self.stopPlayRecording();
            self.setRecordingToPlay();
          }
        });
      }, 100);
    }

    setRecordingToPlay() {
      this.curr_playing_file = this.createAudioFile(this.storageDirectory, "tsy atakaloko.mp3");
      this.curr_playing_file.onStatusUpdate.subscribe(status => {
        // 2: playing
        // 3: pause
        // 4: stop
        this.message = status;
        switch(status) {
          case 1:
            this.is_in_play = false;
            break;
          case 2:   // 2: playing
            this.is_in_play = true;
            this.is_playing = true;
            break;
          case 3:   // 3: pause
            this.is_in_play = true;
            this.is_playing = false;
            break;
          case 4:   // 4: stop
          default:
            this.is_in_play = false;
            this.is_playing = false;
            break;
        }
      })
      console.log("audio file set");
      this.message = "audio file set";
      this.is_ready = true;
      this.getAndSetCurrentAudioPosition();
    }

    playRecording() {
    console.log('miditra ato am le hira');
        this.curr_playing_file.play();
        var i =0;var u=0;var j=0;
        var p= parseInt(this.position);
         var intervalle=setInterval(() =>{


                              	      // alert('ddd'+parseInt(this.position) +'=='+ parseInt(this.minutageMusic[i]));
 //

                       	       if(parseInt(this.position) == parseInt(this.minutageMusic[i])/1000){
                       	        //alert('ato am le if');
                                j++;
                       	              document.getElementById("changerParole").innerHTML= this.paroleindice[j];
                       	               this.pausePlayRecording();
                                       // this.presentPrompt();
                                       let alert = this.alertCtrl.create({
                                        title: 'Mot Manquants n°'+this.valinyMusic[i].length,
                                        inputs: [
                                          {
                                            name: 'mot',
                                            placeholder: 'Mot'
                                          },
                                        ],
                                        buttons: [
                                        {
                                                  text: 'valider',
                                                  role: 'valider',
                                                  handler: data => {
                                                    console.log('valider clicked eto');
                                                      this.reponse=data.mot;
                                                         if(this.reponse == this.valinyMusic[u]){
                                                                this.curr_playing_file.play();
                                                                console.log('play')
                                                                this.presentToast()
                                                                this.point=this.point+1
                                                                /*if(this.position*1000== this.duration*1000-1){
                                                                  let loader =this.loadingCtrl.create({
                                                                    content:"patienter s'il vous plait.....",
                                                                    dismissOnPageChange :true,
                                                                   })
                                                                   loader.present();

                                                                        this.presentAlertFinGame()
                                                                        setTimeout(() =>{
                                                                          //this.tabBarElement.style.display='none';
                                                                          loader.dismiss();
                                                                        },1000);

                                                                }*/

                                                           }
                                                           
                                                           else{
                                                            this.presentToastDiso();
                                                            this.curr_playing_file.play();
                                                             
                                                            // this.presentPrompt();
                                                         }

                                                        u++;
                                                  }
                                                },
                                          
                                  
                                        ]
                                      });
                                      alert.present();

                                      i++;

                               }



                         },1000);




    }

    pausePlayRecording() {

      this.curr_playing_file.pause();
    }

    stopPlayRecording() {
      this.curr_playing_file.stop();
      this.curr_playing_file.release();
      clearInterval(this.get_position_interval);
      this.position = 0;
    }

    controlSeconds(action) {
      let step = 15;

      let number = this.position;
      switch(action) {
        case 'back':
          this.position = number < step ? 0.001 : number - step;
          break;
        case 'forward':
          this.position = number + step < this.duration ? number + step : this.duration;
          break;
        default:
          break;
  }
  }

    presentPrompt() {
    let alert = this.alertCtrl.create({
      title: 'Mot Manquants n°'+this.valinyMusic.length,
      inputs: [
        {
          name: 'mot',
          placeholder: 'Mot'
        },
      ],
      buttons: [
      {
                text: 'valider',
                role: 'valider',
                handler: data => {
                  console.log('valider clicked eto');
                    this.reponse=data.mot;
                 
                }
              },
        {
          text: 'Cancel',
          role: 'cancel',
          handler: data => {
            console.log('Cancel clicked');

          }
        },

      ]
    });
    alert.present();
}


presentAlertAucun() {
  let alert = this.alertCtrl.create({
    title: ' Pas encore le moment de saisir le champ',
    subTitle: 'Erreur',
    buttons: ['valider']
  });
  alert.present();
}
presentAlertErreur() {
  let alert = this.alertCtrl.create({
    title: ' Erreur de  verification',
    subTitle: 'Erreur',
    buttons: ['valider']
  });
  alert.present();
}
presentAlertFinGame() {
  let alert = this.alertCtrl.create({
    title: ' Fin de la Partie',
    subTitle: 'Vous avez reusi la manche',
    buttons: ['valider']
  });
  alert.present();
}
presentAlertvalidate() {
  let alert = this.alertCtrl.create({
    title: ' Votre reponse es correct',
    subTitle: 'Erreur',
    buttons: ['valider']
  });
  alert.present();
}
presentToast() {
  let toast = this.toastCtrl.create({
    message: 'Votre reponse est correct',
    duration: 1500,
    position: 'middle'
  });

  toast.onDidDismiss(() => {
    console.log('Dismissed toast');
  });

  toast.present();
}

presentToastDiso() {
  let toast = this.toastCtrl.create({
    message: 'Votre reponse est incorrect',
    duration: 1500,
    position: 'middle'
  });

  toast.onDidDismiss(() => {
    console.log('Dismissed toast');
  });

  toast.present();
}
  retour() {
    // console.log(this.email);
       this.navCtrl.setRoot('MenuPage');
      // this.navCtrl.push('MenuPage',{pasSplash:true});
    }



}
