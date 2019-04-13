/** The driver class to Card, Deck, Player, and Game.
* @author Lloyd Black
* @author lblack@chapman.edu
* @version 1.01
*/

import java.util.Scanner;

public class Blackjack{

  /** The main method. Runs games of Blackjack until told to stop. Carries over names and bank totals from game to game.
  * @param args Optional command line argument that names the computer the player plays against. Names the computer "Dealer" by default.
  */
  public static void main(String[] args) throws InterruptedException{

    String cName;
    try{
      cName = args[0];
    }catch(IndexOutOfBoundsException e){
      cName = "Dealer";
    }
    int q = 1000;
    Scanner kb = new Scanner(System.in);
    String go = "y";
    System.out.print("\nWelcome to Blackjack!\nEnter your name: ");
    String pName = kb.nextLine();

    while(go.equals("y")){

      Game g = new Game(q, pName, cName);
      q = g.play();
      q = q == 0 ? 1000 : q;
      System.out.print("Would you like to play again (y or n)? ");
      go = kb.next();

    }

    System.out.println("\nGoodbye!");

  }

}
