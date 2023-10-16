import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PictureMemoryGame extends JFrame {
    private ArrayList<String> imagePath;
    private ArrayList<String> cardImages;
    private JButton[] cardButtons;
    private int numberOfMatches;
    private int firstCardIndex;
    private int secondCardIndex;
    private long gameTime;
    private int moveCounter;
    private JLabel moveLabel;

    public PictureMemoryGame(int matrixSize) {
        gameTime = System.currentTimeMillis();
        imagePath = new ArrayList<>();

        if (matrixSize == 12) {
            setTitle("Picture Memory Game 12");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            imagePath.add("img_1.png");
            imagePath.add("img_2.png");
            imagePath.add("img_3.png");
            imagePath.add("img_4.png");
            imagePath.add("img_5.png");
            imagePath.add("img_6.png");

            imagePath.add("img_1.png");
            imagePath.add("img_2.png");
            imagePath.add("img_3.png");
            imagePath.add("img_4.png");
            imagePath.add("img_5.png");
            imagePath.add("img_6.png");

        } else if (matrixSize == 24) {
            setTitle("Picture Memory Game 24");
            setSize(600, 800);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            imagePath.add("img_1.png");
            imagePath.add("img_2.png");
            imagePath.add("img_3.png");
            imagePath.add("img_4.png");
            imagePath.add("img_5.png");
            imagePath.add("img_6.png");
            imagePath.add("img_7.png");
            imagePath.add("img_8.png");
            imagePath.add("img_9.png");
            imagePath.add("img_10.png");
            imagePath.add("img_11.png");
            imagePath.add("img_12.png");

            imagePath.add("img_1.png");
            imagePath.add("img_2.png");
            imagePath.add("img_3.png");
            imagePath.add("img_4.png");
            imagePath.add("img_5.png");
            imagePath.add("img_6.png");
            imagePath.add("img_7.png");
            imagePath.add("img_8.png");
            imagePath.add("img_9.png");
            imagePath.add("img_10.png");
            imagePath.add("img_11.png");
            imagePath.add("img_12.png");

        } else {
            JOptionPane.showMessageDialog(null, "Invalid matrix size.");
            System.exit(0);
        }

        cardImages = new ArrayList<>();
        for (int i = 0; i < matrixSize; i++) {
            cardImages.add("");
        }

        Collections.shuffle(imagePath);
        Collections.shuffle(cardImages);

        JPanel cardPanel = new JPanel(new GridLayout((int) Math.sqrt(matrixSize), (int) Math.sqrt(matrixSize)));
        cardButtons = new JButton[matrixSize];

        for (int i = 0; i < matrixSize; i++) {
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

        moveCounter = 0;

        moveLabel = new JLabel("Moves: " + moveCounter);
        add(moveLabel, BorderLayout.NORTH);

        add(cardPanel);
    }

    private void handleCardClick(int index) {
        if (cardButtons[index].getIcon() == null) {
            return;
        }

        if (firstCardIndex == -1) {
            firstCardIndex = index;
            cardButtons[firstCardIndex].setIcon(new ImageIcon(imagePath.get(index)));
        } else {
            secondCardIndex = index;
            cardButtons[secondCardIndex].setIcon(new ImageIcon(imagePath.get(index)));

            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (imagePath.get(firstCardIndex).equals(imagePath.get(secondCardIndex))) {
                        cardButtons[firstCardIndex].setIcon(null);
                        cardButtons[secondCardIndex].setIcon(null);
                        cardImages.set(firstCardIndex, null);
                        cardImages.set(secondCardIndex, null);
                        numberOfMatches++;

                        if (numberOfMatches == imagePath.size() / 2) {
                            long elapsedTime = System.currentTimeMillis() - gameTime;
                            JOptionPane.showMessageDialog(null, "Congratulations! You've won!\nElapsed Time: " + (elapsedTime / 1000) + " seconds\nTotal Moves: " + moveCounter);
                            resetGame();
                        }
                    } else {
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("blank.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("blank.png"));
                    }
                    firstCardIndex = -1;
                    incrementMoveCounter();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void incrementMoveCounter() {
        moveCounter++;
        moveLabel.setText("Moves: " + moveCounter);
    }

    private void resetGame() {
        dispose();

        String[] matrixSizeOptions = {"12 Cards", "24 Cards"};
        int matrixSize = JOptionPane.showOptionDialog(null, "Select the matrix size:", "Matrix Size",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, matrixSizeOptions, matrixSizeOptions[0]);

        if (matrixSize == 0) {
            new PictureMemoryGame(12).setVisible(true);
        } else if (matrixSize == 1) {
            new PictureMemoryGame(24).setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String[] matrixSizeOptions = {"12 Cards", "24 Cards"};
                int matrixSize = JOptionPane.showOptionDialog(null, "Select the matrix size:", "Matrix Size",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, matrixSizeOptions, matrixSizeOptions[0]);

                if (matrixSize == 0) {
                    new PictureMemoryGame(12).setVisible(true);
                } else if (matrixSize == 1) {
                    new PictureMemoryGame(24).setVisible(true);
                }
            }
        });
    }
}
