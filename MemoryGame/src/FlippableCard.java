import javax.swing.*;

public class FlippableCard extends JButton
{
    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    // Card front icon
    private Icon front;
    // Card back image
    private Icon back = new ImageIcon(loader.getResource("res/Back.jpg"));

    // ID + Name
    private int id;
    private String customName;

    // Default constructor
    public FlippableCard() { super(); }

    // Constructor with card front initialization
    public FlippableCard(ImageIcon frontImage, int index)
    {
        super();
        front = frontImage;
        super.setIcon(front);
        id = index;
        //super.setIcon(back);
        //this.setFrontImage(frontImage);
        //this.hideFront();
    }

    // Set the image used as the front of the card
    public void setFrontImage(ImageIcon frontImage) { front = frontImage; }

    // Card flipping functions
    public void showFront() {
        /* To-Do: Show the card front */
        //--------------------------------------------------------------------------------------------------------------
        this.setIcon(front);

    }
    public void hideFront() {

        /* To-Do: Show the card back  */
        //--------------------------------------------------------------------------------------------------------------

        this.setIcon(back);

    }

    public int getId(){
        return this.id;
    }

    // Metadata: ID number
    public int id() { return id; }
    public void setID(int i) { id = i; }

    // Metadata: Custom name
    public String customName() { return customName; }
    public void setCustomName(String s) { customName = s; }
}
