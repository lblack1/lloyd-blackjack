/** A class to define the procedures of a game of Blackjack.
* @author Lloyd Black
* @author lblack@chapman.edu
* @version 1.0
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class Game{

  private Player m_player;
  private Player m_comp;
  private Deck m_deck;
  private int m_pot;
  private Scanner kb = new Scanner(System.in);

  /** The Construtor for a game of Blackjack.
  * @param startBank An int representing the value at which the player's bank should begin.
  * @param pName A string, the player's name.
  * @param cName A string, the computer's name.
  */
  public Game(int startBank, String pName, String cName){

    m_player = new Player(pName, startBank);
    m_comp = new Player(cName, 0);
    m_deck = new Deck();
    m_pot = 0;

  }

  /** A method that contains the logic for one round/hand of Blackjack (including betting, player drawing, computer drawing, doubling down, determining who wins, etc.)
  * @param none
  * @return A bit weird to have a return for a method like this, but returns a boolean. True if the game should continue, false if not. Makes breaking out of method and play() defintion easier.
  */
  private boolean round() throws InterruptedException{ // returns false if the game should end, true if another round should happen.

    m_player.dump();
    m_comp.dump();
    m_deck.repopulate();

    System.out.println(m_player.getStringBank());
    while(true){
      try{
        System.out.print("Enter your wager (or '-1' to cash out): ");
        m_pot = kb.nextInt();
        if(m_pot == -1){
          return false;
        }else if(m_pot > m_player.getBank()){
          m_pot = m_player.getBank();
        }
        m_player.bet(m_pot);
        break;
      }catch(InputMismatchException e){
        System.out.println("A number value, silly. Let's try that again.\n");
        String trash = kb.nextLine();
      }
    }

    m_player.collect(m_deck.deal());
    m_player.collect(m_deck.deal());
    m_comp.collect(m_deck.deal());
    m_comp.collect(m_deck.deal());

    System.out.println(m_comp.showFirst());
    System.out.println(m_player);

    while(m_player.handScore() < 21){
      System.out.print("Hit (h), Stand (s), or double down (d)? ");
      String dec = kb.next();
      if(dec.equals("h")){
        m_player.collect(m_deck.deal());
        System.out.println(m_player);
      }else if(dec.equals("s")){
        break;
      }else if(dec.equals("d")){
        if(m_player.getBank() < m_pot){
          m_pot += m_player.getBank();
          m_player.bet(m_player.getBank());
        }else{
          m_player.bet(m_pot);
          m_pot *= 2;
        }
        m_player.collect(m_deck.deal());
        System.out.println(m_player);
        break;
      }
      if(m_player.handScore() == 21){
        System.out.println("We're holding for you, as you have a score of 21.");
      }
    }

    if(m_player.handScore() > 21){
      System.out.println("Bust!");
      System.out.println(m_comp);
      Thread.sleep(1500);
      System.out.println(m_comp.getName() + " wins!");
      m_comp.roundWin(m_pot*2);
    }else{
      while(m_comp.handScore() < m_player.handScore()){
        System.out.println(m_comp);
        m_comp.collect(m_deck.deal());
        Thread.sleep(1500);
      }
      if(m_comp.handScore() > 21){
        System.out.println(m_comp);
        System.out.println("Bust! " + m_player.getName() + " wins!");
        m_player.roundWin(m_pot*2);
        Thread.sleep(1000);
      }else{
        System.out.println(m_comp);
        System.out.println(m_comp.getName() + " wins!");
        m_comp.roundWin(m_pot*2);
        Thread.sleep(1000);
      }
    }

    if(m_player.getBank() == 0){
      System.out.println("You've run out of money in your bank. Time to auction off that precious family heirloom in a desparate, drunken bid to recover your dignity.\n");
      return false;
    }else{
      return true;
    }

  }

  /** A method to run a game of Blackjack until the player runs out of money or cashes out.
  * @param none
  * @return An int, representing the player's bank total at the end of the game. Used to carry over the player's bank total to the next game.
  */
  public int play() throws InterruptedException{

    boolean keepPlaying = true;
    while(keepPlaying){
      keepPlaying = round();
      m_deck.repopulate();
    }

    if(m_player.getBank() == 0){
      return 1000;
    }else{
      System.out.println("You leave the table with " + m_player.getBank() + " in chips.\n");
      return m_player.getBank();
    }

  }

}
