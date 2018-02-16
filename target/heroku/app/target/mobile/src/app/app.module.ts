import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { HttpModule } from '@angular/http';
import { MyApp } from './app.component';
import { Media } from '@ionic-native/media';
import { File } from '@ionic-native/file';
import { StreamingMedia } from '@ionic-native/streaming-media';
import { FileTransfer, FileUploadOptions, FileTransferObject } from '@ionic-native/file-transfer';
import { Http } from '@angular/http';
import { RequestService } from '../pages/services/request.service';
import { User } from '../pages/modele/modele.user';
/**
 * Sample custom factory function to use with ionic-audio
 */


@NgModule({
  declarations: [
    MyApp
  ],
  imports: [
    BrowserModule,
     HttpModule,
      // User,
    IonicModule.forRoot(MyApp)

  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp

  ],
  providers: [
    StatusBar,
    SplashScreen,
    Media,
    File,
    StreamingMedia,
    FileTransfer,
       FileTransferObject,
        HttpModule,
        RequestService,
       // User,
                   {provide: ErrorHandler, useClass: IonicErrorHandler},

  ],



})
export class AppModule {}
