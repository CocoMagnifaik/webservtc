import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { JeuPage } from './jeu';

@NgModule({
  declarations: [
    JeuPage,
  ],
  imports: [
    IonicPageModule.forChild(JeuPage),
  ],
})
export class JeuPageModule {}
