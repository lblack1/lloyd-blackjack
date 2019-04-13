/** A class to define a card by suit and value (Blackjack score worth and display).
* @author Lloyd Black
* @author lblack@chapman.edu
* @version 1.0
*/

import java.util.*;

public class Card{

  private char m_suit;
  private char m_value;
  private int m_numValue;

  /** A constructor that builds a card with an inputted suit and value. Weird argument types to facilitate interation through list building.
  * @param suit An int which represents the index of the suit of the card ('♥', '♦', '♣', '♠').
  * @param value An int representing the index of the value (from ace up to king, 0-12).
  */
  public Card(int suit, int value){
    char[] suits = new char[] {'♥', '♦', '♣', '♠'};
    char[] values = new char[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
    m_suit = suits[suit];
    m_value = values[value];
    if(value == 0){
      m_numValue = 11;
    }else if(0 < value && value < 10){
      m_numValue = ++value;
    }else{
      m_numValue = 10;
    }
  }

  /** Another constructor for a card that essentially copies another card.
  * @param c The card object to be copied.
  */
  public Card(Card c){
    m_suit = c.m_suit;
    m_value = c.m_value;
    m_numValue = c.m_numValue;
  }

  /** A mutator for the number value of a card.
  * @param numValue An int represeting the number value.
  */
  public void setNumValue(int numValue){
    m_numValue = numValue;
  }

  /** An accessor for the card's number/score value.
  * @param none
  * @return An int, the card's number/score Value.
  */
  public int getNumValue(){
    return m_numValue;
  }

  /** A method to generate a string containing a card's info (suit and display value). Formats 'T' to become "10".
  * @param none
  * @return A string built of the suit followed by the display value.
  */
  public String toString(){
    StringBuilder str = new StringBuilder();
    str.append(m_suit + " ");
    String temp = m_value == 'T' ? "10" : Character.toString(m_value);
    str.append(temp);
    return str.toString();
  }

}
