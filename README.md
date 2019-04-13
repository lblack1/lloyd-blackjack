# lloyd-blackjack
A few classes in Java that allow for interactive games of command line Blackjack to be played.

Lloyd Black
2295968
lblack@chapman.edu
CPSC 231-01
Blackjack

2. Source Files - Blackjack.java, Card.java, Deck.java, Game.java, Player.java

3. Issues - None. Well, one or two Deprecated Integers, but besides that nothing.

4. Resources - https://www.arkadium.com/games/blackjack/ for figuring out how blackjack works, https://stackoverflow.com/questions/30125296/how-to-sum-a-list-of-integers-with-java-streams for the first half of the first boolean expression in line 116 of Player.java, and other general StackOverflow/Java Docs.

5. Description of Program - Blackjack.java - The driver class which plays games of blackjack until told to quit (carries player's bank over from one game to the next). Card.java - defines a card by suit and value (Blackjack score worth and display). Deck.java - builds a deck of Card objects in the manner of two standard 52 card decks. Game.java - defines the procedures of a game of Blackjack. Player.java - defines a player that fits the rules of Blackjack.

6. Functions - javac *.java, java Blackjack name, where name is an optional command line argument if you feel like naming the computer something other than 'Dealer'.
