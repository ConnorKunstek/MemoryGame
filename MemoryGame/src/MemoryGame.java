import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryGame extends JFrame implements ActionListener
{
    // Core game play objects
    private Board gameBoard;
    private FlippableCard prevCard1, prevCard2;

    // Labels to display game info
    private JLabel errorLabel;

    // Labels to display game info
    private JLabel matchesLabel;

    // layout objects: Views of the board and the label area
    private JPanel boardView, labelView;

    // Record keeping counts
    private int errorCount = 0;
    private int matchesCount = 0;

    private FlippableCard Selected1;
    private FlippableCard Selected2;
    private boolean matched;

    public MemoryGame()
    {
        // Call the base class constructor
        super("Hubble Memory Game");

        // Allocate the interface elements
        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");
        errorLabel = new JLabel("Errors: 0");
        matchesLabel = new JLabel("Matches: 0");

        restart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                restartGame();
            }
        });

        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });


        // Allocate two major panels to hold interface
        labelView = new JPanel();  // used to hold labels
        boardView = new JPanel();  // used to hold game board

        // get the content pane, onto which everything is eventually added
        Container c = getContentPane();

        // Setup the game board with cards
        gameBoard = new Board(25, this);
        FlippableCard[] cards = gameBoard.getCards();

        for(FlippableCard card : cards) {

            card.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (Selected1 != null && Selected2 != null) {

                        if (Selected1.getId() == 13) {
                            Selected1.setEnabled(false);
                            Selected1 = Selected2;
                        } else {
                            if (Selected2.getId() == 13) {
                                Selected2.setEnabled(false);
                                Selected2 = Selected1;
                            }
                        }
                        if (!matched) {
                            Selected1.hideFront();
                            Selected2.hideFront();
                        } else {
                            Selected1.setEnabled(false);
                            Selected2.setEnabled(false);
                        }
                        Selected1 = null;
                        Selected2 = null;
                    }
                    card.showFront();
                    if (Selected1 == null) {
                        Selected1 = card;

                        card.showFront();
                    } else {
                        if (Selected2 == null) {
                            Selected2 = card;
                            card.showFront();
                            if (checkMatch()) {
                                matched = true;
                                increaseMatches();
                            } else {
                                matched = false;
                                increaseErrors();
                            }
                        }
                    }
                }
            });
        }

        // Add the game board to the board layout area
        boardView.setLayout(new GridLayout(5, 5, 2, 0));
        gameBoard.fillBoardView(boardView);

        // Add required interface elements to the "label" JPanel
        labelView.setLayout(new GridLayout(1, 4, 2, 4));
        labelView.add(quit);
        labelView.add(restart);
        labelView.add(matchesLabel);
        labelView.add(errorLabel);

        // Both panels should now be individually layed out
        // Add both panels to the container
        c.add(labelView, BorderLayout.NORTH);
        c.add(boardView, BorderLayout.SOUTH);

        setSize(745, 500);
        setVisible(true);
    }

    /* Handle anything that gets clicked and that uses MemoryGame as an
     * ActionListener */
    public void actionPerformed(ActionEvent e)
    {
        // Get the currently clicked card from a click event
        FlippableCard currCard = (FlippableCard)e.getSource();

    }

    public void increaseErrors(){
        errorCount++;
        errorLabel.setText("Errors: "+ errorCount);
    }

    public void increaseMatches(){
        matchesCount++;
        errorLabel.setText("Matches: "+ matchesCount);
    }

    private void restartGame()
    {
        errorCount = 0;
        matchesCount = 0;
        errorLabel.setText("Errors: 0");
        matchesLabel.setText("Matches: 0");

        // Clear the boardView and have the gameBoard generate a new layout
        boardView.removeAll();
        gameBoard.resetBoard();
        gameBoard.fillBoardView(boardView);
        repaint();
    }

    public boolean checkMatch(){
        boolean flag = false;
        if(Selected1.getId() == Selected2.getId()){
            flag = true;
        }
        return flag;
    }

    public static void main(String args[])
    {
        MemoryGame M = new MemoryGame();
        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
