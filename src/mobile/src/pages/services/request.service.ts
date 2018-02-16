import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import {User} from '../modele/modele.user'

@Injectable()
export class RequestService {

    userAuth
    coinEarn

    //url : String = "http://localhost:8080"
    url : String = "https://webservtc.herokuapp.com"

    constructor(private http: Http) { }

    getMusicTotal(){
        return this.http.get(''+this.url+'/musiqueTotal').toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue au plat list : '+error))
    }

    getOneMusic(id){
          return this.http.get(''+this.url+'/findMusicById/'+id).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue au plat list : '+error))
    }
    getUser(identifiant, password){
        return this.http.get(''+this.url+'/findUsers/'+identifiant+'/'+password).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue a la recherche d utilisateur : '+error))
    }

    getUserById(id){
        return this.http.get(''+this.url+'/findUserById/'+id).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue a la recherche d utilisateur par id : '+error))
    }

    getCategorieTotal(){
        return this.http.get(''+this.url+'/listCategorie').toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue a la recherche d utilisateur par id : '+error))
    }

  /*  insertUtilisateur(user : User){
            let use= JSON.stringify(user);
        return this.http.post(''+this.url+'/createUser',use,).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue a la recherche d utilisateur par id : '+error))
    }*/

    createUtilisateur(email,pseudo,mdp,sexe,nationalite,status){
        
    return this.http.get(''+this.url+'/createuser/'+email+'/'+pseudo+'/'+mdp+'/'+sexe+'/'+nationalite+'/'+status).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue a la recherche d utilisateur par id : '+error))
}
createGame(joueur,chanson,points){
        
    return this.http.get(''+this.url+'/createGame/'+joueur+'/'+chanson+'/'+points).toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue a la recherche d utilisateur par id : '+error))
}
   /* signUp(identifiant, name, surname, email, password){
        return this.http.get(''+this.url+/InsertUser?identifiant='+identifiant+'&name='+name+'&surname='+surname+'&email='+email+'&password='+password+'').toPromise().then(data => data.json()).catch(error => console.log('Une erreur est survenue a l inscription d utilisateur : '+error))
    }*/

    logOut(){
        this.userAuth = null
    }
}

