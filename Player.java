/** A class to define a player that fits the rules of Blackjack.
* @author Lloyd Black
* @author lblack@chapman.edu
* @version 1.0
*/

import java.util.LinkedList;

public class Player{

  private String m_name;
  private int m_bank;
  private LinkedList<Card> m_hand;

  /** The default constructor. Builds a player by initializing a Card list for the player's hand.
  * @param none
  */
  public Player(String name, int bank){
    m_name = name;
    m_bank = bank;
    m_hand = new LinkedList<Card>();
  }

  /** A special mutator for the player's bank. Subtracts the argument from the bank total.
  * @param i The amount to be bet (subtracts from the player's bank).
  */
  public void bet(int i){
    m_bank -= i;
  }

  /** Another special mutator for the bank. Adds the argument to the bank total.
  * @param i The amount the player has won (adds to the player's bank).
  */
  public void roundWin(int i){
    m_bank += i;
  }

  /** The accessor for the player's name.
  * @param none
  * @return A string, the player's name.
  */
  public String getName(){
    return m_name;
  }

  /** The accessor for the player's bank.
  * @param none
  * @return An int, the player's bank total.
  */
  public int getBank(){
    return m_bank;
  }

  /** A special accessor for the player's bank; returns formatted string instead of just int.
  * @param none
  * @return A string, the player's name and bank total.
  */
  public String getStringBank(){
    return "\n" + m_name + "'s Bank: " + m_bank;
  }

  /** A method to add a card to the player's hand.
  * @param inCards A card object to be added to the deck.
  */
  public void collect(Card inCard){
    m_hand.add(inCard);
  }

  /** A method to remove the cards from the player's hand.
  * @param none
  */
  public void dump(){
    m_hand.clear();
  }

  /** A method to generate a string containing the player's number and the player's cards.
  * @param none
  * @return A string built of all the player's name, the player's cards' toString outputs, and the hand's score, plus a bit of formatting.
  */
  public String toString(){
    StringBuilder str = new StringBuilder();
    str.append("\n" + m_name + "'s Hand\n");
    for(int x = 0; x < m_hand.size()-1; ++x){
      str.append(m_hand.get(x).toString());
      str.append(" , ");
    }
    if(m_hand.size() != 0){
      str.append(m_hand.getLast().toString());
    }
    str.append("\nHand Score: " + handScore());
    return str.toString();
  }

  /** Shows the first card in the player's hand (used for the starts of hands).
  * @param none
  * @return A formatted string with the player's name and first card.
  */
  public String showFirst(){
    StringBuilder str = new StringBuilder();
    str.append("\n" + m_name + "'s Hand\n");
    str.append(m_hand.getFirst().toString());
    str.append(" , ??");
    return str.toString();
  }

  /** A method to calculate the score of the player's hand. Takes aces' mutability between 11 and 1 into account, and changes them as necessary.
  * @param none
  * @return An int, the sum of the hand's cards' scores, aces adjusted as necessary.
  */
  public int handScore(){
    LinkedList<Integer> vals = new LinkedList<Integer>();
    Integer ace = new Integer(11);
    for(Card c : m_hand){
      vals.add(new Integer(c.getNumValue()));
    }
    while(vals.stream().mapToInt(Integer::intValue).sum() > 21 && vals.contains(ace)){ // While the hand score is greater than 21 and there is an unchanged ace in the hand.
      int index = vals.indexOf(ace); // Finds the first ace
      m_hand.get(index).setNumValue(1); // Treats that ace as a 1
      vals.set(index, 1); // Accounts for changed ace without re-iterating through list.
    }
    int score = vals.stream().mapToInt(Integer::intValue).sum();
    return score;
  }

}
