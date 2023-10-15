import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


public class PictureMemoryGame extends JFrame {
    private ArrayList<String> imagePaths;
    private ArrayList<String> cardImages;
    private JButton[] cardButtons;
    private int numberOfMatches;
    private int firstCardIndex;
    private int secondCardIndex;



    public PictureMemoryGame() {
        setTitle("Picture Memory Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();
        imagePaths.add("img_1.png");
        imagePaths.add("img_1.png");
        imagePaths.add("img_2.png");
        imagePaths.add("img_2.png");
        imagePaths.add("img_3.png");
        imagePaths.add("img_3.png");
        imagePaths.add("img_4.png");
        imagePaths.add("img_4.png");
        imagePaths.add("img_5.png");
        imagePaths.add("img_5.png");
        imagePaths.add("img_6.png");
        imagePaths.add("img_6.png");
        //imagePaths.add("img_7.png");
        //imagePaths.add("img_7.png");
        //imagePaths.add("img_8.png");
        //imagePaths.add("img_8.png");
        //imagePaths.add("img_9.png");
        //imagePaths.add("img_9.png");
        //imagePaths.add("img_10.png");
        //imagePaths.add("img_10.png");
        //imagePaths.add("img_11.png");
        //imagePaths.add("img_11.png");
        //imagePaths.add("img_12.png");
        //imagePaths.add("img_12.png");

        cardImages = new ArrayList<>();
        for (String imagePath : imagePaths) {
            cardImages.add("");
        }

        Collections.shuffle(imagePaths);
        Collections.shuffle(cardImages);
        JPanel cardPanel = new JPanel(new GridLayout(4, 3));
        cardButtons = new JButton[12];

        for (int i = 0; i < cardButtons.length; i++) {
            final int index = i;
            cardButtons[i] = new JButton();
            cardButtons[i].setIcon(new ImageIcon("blank.png"));
            cardButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCardClick(index);
                }
            });
            cardPanel.add(cardButtons[i]);
        }
        add(cardPanel);
    }
    private void handleCardClick(int index) {
        if (cardButtons[index].getIcon() == null) {
            return;
        }
        if (firstCardIndex == -1) {
            firstCardIndex = index;
            cardButtons[firstCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));
        } else {
            secondCardIndex = index;
            cardButtons[secondCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));

            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (imagePaths.get(firstCardIndex).equals(imagePaths.get(secondCardIndex))) {
                        cardButtons[firstCardIndex].setIcon(null);
                        cardButtons[secondCardIndex].setIcon(null);
                        cardImages.set(firstCardIndex, null);
                        cardImages.set(secondCardIndex, null);
                        numberOfMatches++;

                        if (numberOfMatches == imagePaths.size() / 2) {

                            JOptionPane.showMessageDialog(null, "Congratulations! You've won!");
                            System.exit(0);
                        }
                    } else {
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("blank.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("blank.png"));
                    }
                    firstCardIndex = -1;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new PictureMemoryGame().setVisible(true);}
        });
    }
}



