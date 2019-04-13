/** A class to build a deck of Card objects in the manner of two standard 52 card decks.
* @author Lloyd Black
* @author lblack@chapman.edu
* @version 1.0
*/

import java.util.LinkedList;
import java.util.Random;

public class Deck{

  private LinkedList<Card> m_cards;

  /** The default constructor. Builds a deck by building a Card for every combination of suit and value twice.
  * @param none
  */
  public Deck(){
    m_cards = new LinkedList<Card>();
    for(int q = 0; q < 2; ++q){
      for(int i = 0; i < 4; ++i){
        for(int n = 1; n < 13; ++n){
          m_cards.push(new Card(i, n));
        }
      }
    }
  }

  /** Used to clear and fill a deck with all 52 cards twice.
  * @param none
  */
  public void repopulate(){
    m_cards.clear();
    for(int q = 0; q < 2; ++q){
      for(int i = 0; i < 4; ++i){
        for(int n = 1; n < 13; ++n){
          m_cards.push(new Card(i, n));
        }
      }
    }
  }

  /** A helper method that returns a card from the set of the deck's cards, and removes it from m_cards as well.
  * @param i The index of the card you want to pop from m_cards.
  * @return The popped card object.
  */
  private Card pop(int i){
    Card tempCard = new Card(m_cards.get(i));
    m_cards.remove(i);
    return tempCard;
  }

  /** A method to pop a random card from the deck.
  * @param none
  * @return A random card object from m_cards.
  */
  public Card deal(){
    Random rng = new Random();
    int val = rng.nextInt(m_cards.size());
    Card tempCard = pop(val);
    return tempCard;
  }

}
