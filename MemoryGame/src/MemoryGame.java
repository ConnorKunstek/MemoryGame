import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryGame extends JFrame implements ActionListener
{
    // Core game play objects
    private Board gameBoard;
    private FlippableCard prevCard1, prevCard2;

    // Labels to display game info
    private JLabel errorLabel, timerLabel;

    // layout objects: Views of the board and the label area
    private JPanel boardView, labelView;

    // Record keeping counts and times
    private int clickCount = 0, gameTime = 0, errorCount = 0;
    private int pairsFound = 0;

    // Game timer: will be configured to trigger an event every second
    private Timer gameTimer;

    public MemoryGame()
    {
        // Call the base class constructor
        super("Hubble Memory Game");

        // Allocate the interface elements
        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");
        timerLabel = new JLabel("Timer: 0");
        errorLabel = new JLabel("Errors: 0");

        /*
         * To-Do: Setup the interface objects (timer, error counter, buttons)-------------------------------------------
         * and any event handling they need to perform
         */

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

        // Add the game board to the board layout area
        boardView.setLayout(new GridLayout(5, 5, 2, 0));
        gameBoard.fillBoardView(boardView);

        // Add required interface elements to the "label" JPanel
        labelView.setLayout(new GridLayout(1, 4, 2, 2));
        labelView.add(quit);
        labelView.add(restart);
        labelView.add(timerLabel);
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

        /*
         * To-Do: What happens when a card gets clicked?----------------------------------------------------------------
         */
        for(int i = 0; i < 25; i++){
            if (gameBoard.getCards()[i] == (FlippableCard)e.getSource()) {
                gameBoard.getCards()[i].showFront();
            }
        }
    }

    private void restartGame()
    {
        pairsFound = 0;
        gameTime = 0;
        clickCount = 0;
        errorCount = 0;
        timerLabel.setText("Timer: 2");
        errorLabel.setText("Errors: 2");

        // Clear the boardView and have the gameBoard generate a new layout
        boardView.removeAll();
        gameBoard.resetBoard();
        gameBoard.fillBoardView(boardView);
    }

    public static void main(String args[])
    {
        MemoryGame M = new MemoryGame();
        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
