import java.awt.event.*;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;

public class Board
{
    // Array to hold board cards
    private FlippableCard cards[];

    private int deckSize;

    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    public Board(int size, ActionListener AL)
    {
        deckSize = size;
        // Allocate and configure the game board: an array of cards
        cards = new FlippableCard[size];

        // Fill the Cards array
        int imageIdx = 1;
        for (int i = 0; i < size; i++) {

            // Load the front image from the resources folder
            String imgPath = "res/hub" + imageIdx + ".jpg";
            ImageIcon img = new ImageIcon(loader.getResource(imgPath));

            if(i % 2 != 0){ //We only want two cards to have the same image, so change the index on every odd i
                imageIdx++;  // get ready for the next pair of cards
            }

            // Setup one card at a time
            FlippableCard c = new FlippableCard(img);

            // Add them to the array
            cards[i] = c;
        }

        /*
         * To-Do: Randomize the card positions - DONE
         */
        shuffleCards();

    }

    public void fillBoardView(JPanel view)
    {
        for (FlippableCard c : cards) {
            view.add(c);
        }
    }

    public void resetBoard()
    {
        /*
         * To-Do: Reset the flipped cards and randomize the card positions----------------------------------------------
         */

        shuffleCards();
    }

    public void shuffleCards(){
        Random rand = new Random();
        FlippableCard temp[] = new FlippableCard[deckSize];
        for(int i = 0; i < deckSize; i++){
            boolean check = false;
            while(!check) {
                int value = rand.nextInt(deckSize);
                if (temp[value] == null){
                    temp[value] = cards[i];
                    check = true;
                }
            }
        }
        for(int i = 0; i < deckSize; i++){
            cards[i] = temp[i];
        }
    }
}
