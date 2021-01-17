# Boggle

Boggle is a word game which is played using a plastic grid of lettered dice, in which players attempt to find words in sequences of adjacent letters.
Have you played a Boggle game? This is really a nice game to kill time at the same time to increase our vocabulary. But what if I tell you that in just few seconds you can find all words in the boggle board.  Do you want to quickly search for all the valid words in the Boggle board? Why not try? Through this code you can easily find those words. 

I made this project as a requirement for CMSC 22. This is just a simple project that will output a string in a command line. Hence, I'm planning to upgrade this program - enhancing my codes for it to be userfriendly, and making a GUI as I learn java in the long run. 

For the features of this program. It specifically solves a Boggle. It finds all the valid words in the user defined 4x4 board. 
  A word is valid if:
       1. Letters in the word are connected in adjacent way.
       2. The words are in the dictionary text file.
       3. The word length is 3 or more.
 
 A trie data structure is used to compose the words inside the dictionary into a Trie Tree so that it will be more efficient to find words.
 
 For the Design Patterns I applied the following:
 1. Creational Design Pattern - Prototype Design Pattern
 2. Structural Design Pattern - Composite Design Pattern
 3. Behavioral Design Pattern - Iterator Pattern & Command Pattern
