/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tp3_bowlingcorrection;

import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Matthias
 */
public class MultiPlayer implements MultiPlayerGame {

    private  HashMap <String,SinglePlayerGame> jeu = new HashMap <String,SinglePlayerGame>();
    
    private ArrayList<String> name = new ArrayList<String>();
    
    private int currentPlayer=-1;
    
    @Override
    public String startNewGame(String[] playerName) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if (playerName.length==0){
            throw new NullPointerException("Il n'y a pas de joueurs. Il n'y aura pas de partie.");
        }
        else{
        for (int i=0; i< playerName.length;i++){
            jeu.put(playerName[i], new SinglePlayerGame());
            name.add(playerName[i]);
        }
        }
        this.currentPlayer=0;
        return affichage(this.currentPlayer,1,1);
    }

    public String affichage(int pl, int numberFrame, int numberBall){
        return "Prochain tir : joueur " + name.get(pl)+ ", Tour n°"+numberBall+ ", Boule n°" + numberBall;
    }
    
    
    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (this.currentPlayer==-1){
            throw new UnsupportedOperationException("La partie n'a pas démarré");
        }
        
        if (this.jeu.get(name.get(this.currentPlayer)).getCurrentFrame() ==null){
                throw new UnsupportedOperationException("Le jeu est fini");
        }
        
        if (this.jeu.get(name.get(currentPlayer)).getCurrentFrame().isFinished() ){
            this.currentPlayer=(this.currentPlayer+1) % name.size();     
        }
        
        if (this.jeu.get(name.get(this.currentPlayer)).getCurrentFrame() !=null){
        	this.jeu.get(name.get(this.currentPlayer)).lancer(nombreDeQuillesAbattues);
        }
        
        return affichage(this.currentPlayer,this.jeu.get(name.get(this.currentPlayer)).getCurrentFrame().getFrameNumber(),this.jeu.get(name.get(this.currentPlayer)).getCurrentFrame().getBallsThrown());
    }
    

    @Override
    public int scoreFor(String playerName) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (this.jeu.containsKey(playerName) == false){
            throw new UnsupportedOperationException("Ce joueur n'est pas dans la partie");
        }
        return this.jeu.get(playerName).score();
    }
    
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
}
