import java.awt.event.*;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;

public class Board
{
    // Array to hold board cards
    private FlippableCard cards[];

    private int DeckSize;
    private FlippableCard Selected1;
    private FlippableCard Selected2;
    private boolean matched;

    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    public Board(int size, ActionListener AL)
    {
        DeckSize = size;
        matched = false;
        // Allocate and configure the game board: an array of cards
        cards = new FlippableCard[size];

        // Fill the Cards array
        int imageIdx = 1;
        for (int i = 0; i < size; i++) {

            // Load the front image from the resources folder
            String imgPath = "res/hub" + imageIdx + ".jpg";
            ImageIcon img = new ImageIcon(loader.getResource(imgPath));

            // Setup one card at a time
            FlippableCard c = new FlippableCard(img, imageIdx);

            if(i % 2 != 0){ //We only want two cards to have the same image, so change the index on every odd i
                imageIdx++;  // get ready for the next pair of cards
            }

//            c.addActionListener(new ActionListener(){
//                public void actionPerformed(ActionEvent e){
//                    if(Selected1 != null && Selected2 != null){
//
//                        if(Selected1.getId() == 13){
//                            Selected1.setEnabled(false);
//                            Selected1 = Selected2;
//                        }else{
//                            if(Selected2.getId() == 13){
//                                Selected2.setEnabled(false);
//                                Selected2 = Selected1;
//                            }
//                        }
//                        if(!matched) {
//                            Selected1.hideFront();
//                            Selected2.hideFront();
//                        }else{
//                            Selected1.setEnabled(false);
//                            Selected2.setEnabled(false);
//                        }
//                        Selected1=null;
//                        Selected2=null;
//                    }
//                    c.showFront();
//                    if(Selected1 == null){
//                        Selected1 = c;
//
//                        c.showFront();
//                    }else{
//                        if(Selected2 == null){
//                            Selected2 = c;
//                            c.showFront();
//                            if(checkMatch()){
//                                matched = true;
//
//                                //super.increaseErrors();
//                            }else{
//                                matched = false;
//                            }
//                        }
//                    }
//                }
//            });

            // Add them to the array
            cards[i] = c;
        }

        /*
         * To-Do: Randomize the card positions - DONE
         */
        //shuffleCards();
        resetBoard();

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
        for (FlippableCard c : cards){
            c.hideFront();
            c.setEnabled(true);

        }
    }

    public FlippableCard[] getCards(){
        return this.cards;
    }



    public void shuffleCards(){
        Random rand = new Random();
        FlippableCard temp[] = new FlippableCard[DeckSize];
        for(int i = 0; i < DeckSize; i++){
            boolean check = false;
            while(!check) {
                int value = rand.nextInt(DeckSize);
                if (temp[value] == null){
                    temp[value] = cards[i];
                    check = true;
                }
            }
        }
        for(int i = 0; i < DeckSize; i++){
            cards[i] = temp[i];
        }
    }
}
