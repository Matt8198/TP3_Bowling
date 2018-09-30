/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bowling.Frame;
import com.mycompany.tp3_bowlingcorrection.MultiPlayer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class MultiPlayerJUnitTest {
    
    MultiPlayer mpg;
    
    String[] playerName = {"Manon","Matthias","Pierre","Diego"} ;
    
    @Before
    public void setUp() {
        mpg=new MultiPlayer();
        
    }
   
    
    @Test
    public void Initialiation() throws Exception{
        mpg.startNewGame(playerName);
        //System.out.println(mpg.startNewGame(playerName));
        assertEquals(mpg.startNewGame(playerName),"Prochain tir : joueur Manon, Tour n°1, Boule n°1");
    }

    @Test (expected = NullPointerException.class)
    public void MauvaiseInitialisation() throws Exception{
        String[] test = {};
        assertEquals(mpg.startNewGame(test),"Il n'y a pas de joueurs. Il n'y aura pas de partie.");  
    }
    
    @Test
    
   public void Spare() throws Exception{
       mpg.startNewGame(playerName);
       rollMany(2,4);
       rollMany(2,5);
       rollMany(16,4);
       assertEquals(30,mpg.scoreFor("Matthias"));
   }
    
    @Test
    public void lancerJoueur1() throws Exception{
        mpg.startNewGame(playerName);
        
        assertEquals(rollMany(2,2),"Prochain tir : joueur Matthias, Tour n°1, Boule n°1");
    }
    
    
    @Test
    public void lancerJoueur3() throws Exception{
        mpg.startNewGame(playerName);
        assertEquals(rollMany(5,2),"Prochain tir : joueur Pierre, Tour n°1, Boule n°2");
    }
    
    @Test 
    public void scoreJoueur1() throws Exception{
        mpg.startNewGame(playerName);
        rollMany(4,2);
        assertEquals(4,mpg.scoreFor("Manon"));
    }
    
    @Test
    public void scoreJoueur2() throws Exception{
        mpg.startNewGame(playerName);
        rollMany(12,5);
        assertEquals(25,mpg.scoreFor("Manon"));
    }
    
    @Test
	public void testPerfectGame() throws Exception {
                mpg.startNewGame(playerName);
		rollMany(44, 10);
		assertEquals(300, mpg.scoreFor("Manon"));
	}
        
    @Test
    public void StrikeJoueur2() throws Exception{
        mpg.startNewGame(playerName);
        
        assertEquals(rollMany(3,10),"Prochain tir : joueur Diego, Tour n°1, Boule n°1");
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void PasDemarre() throws Exception{
        rollMany(2,1);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void Fin() throws Exception{
        mpg.startNewGame(playerName);
        rollMany(80,2);
    }
    
    @Test
    public void QueDesUn() throws Exception{
        mpg.startNewGame(playerName);
        rollMany(79,1);
        assertEquals(mpg.scoreFor("Pierre"),20);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void ScoreJoueurInexistant() throws Exception{
        mpg.startNewGame(playerName);
        rollMany(14,3);
        mpg.scoreFor("Gérard");
    }

// Quelques methodes utilitaires pour faciliter l'écriture des tests
	private String rollMany(int n, int pins) throws Exception {
		for (int i = 0; i < n; i++) {
			mpg.lancer(pins);
		}
	return mpg.lancer(pins);
        }
        
	private String rollSpare() throws Exception, Exception, Exception {
		mpg.lancer(7);
		return mpg.lancer(3);
	}

	private String rollStrike() throws Exception {
		return mpg.lancer(10);
	}
        
}