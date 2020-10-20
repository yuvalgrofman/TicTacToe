package yuval.grofman;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel scorePanel = new JPanel();
    private JLabel playerPanel = new JLabel();
    private JLabel titleTextField = new JLabel();
    private JLabel scoreTextField = new JLabel();
    public JButton[][] jButtons = new JButton[3][3];
    private int player1Score = 0;
    private int player2Score = 0;
    private int rowsAndCols;

    View(int rowsAndCols){

        this.rowsAndCols = rowsAndCols;

        //creating the frame setting its size, border layout and default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,900);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());

        //setting title color, location, font and text
        titleTextField.setBackground(new Color(25,25,25));
        titleTextField.setForeground(new Color(25,255,0));
        titleTextField.setHorizontalAlignment(JLabel.CENTER);
        titleTextField.setVerticalAlignment(JLabel.TOP);
        titleTextField.setFont(new Font("Commissioner",Font.BOLD,75));
        titleTextField.setText("Tic-Tac-Toe");
        titleTextField.setOpaque(true);

        //setting player score color, location, font and text
        scoreTextField.setBackground(new Color(25,25,25));
        scoreTextField.setForeground(new Color(25,255,0));
        scoreTextField.setHorizontalAlignment(JLabel.CENTER);
        scoreTextField.setVerticalAlignment(JLabel.TOP);
        scoreTextField.setFont(new Font("Commissioner",Font.BOLD,45));
        scoreTextField.setText("Player 1 Wins: " + player1Score +" , Player 2 wins: " + player2Score);
        scoreTextField.setOpaque(true);


        //setting the title panel(the title text field is added to the title panel)
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,200);

        //setting the button panel
        buttonPanel.setLayout(new GridLayout(rowsAndCols,rowsAndCols));
        buttonPanel.setBackground(new Color(150,150,150));

        //setting score panel
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBounds(0,800, 800,100);

        jButtons = new JButton[rowsAndCols][rowsAndCols];

        //creating a 3 3 grid of buttons
        for (int i = 0; i < rowsAndCols; i++){
            for (int j = 0; j < rowsAndCols; j++){

                jButtons[i][j] = new JButton();
                buttonPanel.add(jButtons[i][j]);
                jButtons[i][j].setFocusable(false);
                jButtons[i][j].setFont(new Font("Arial",Font.PLAIN , (int) (900/Math.pow(rowsAndCols,1.6))));
                /*
                 The reason the size is set like that is because we need a equation that answers the following demands:
                 when rowsAndCols grows the size gets smaller and that the size when rowsAndColumns is 3 is about 100.
                 And after a bit of testing i found that this equation answers these demands quite well.
                 (works up to rowsAndCols being about 20)
                 */


            }
        }

        //adding each label to its respective panel
        titlePanel.add(titleTextField);
        scorePanel.add(scoreTextField);

        //adding all the components to the frame
        frame.add(scorePanel,BorderLayout.SOUTH);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    public JButton getButton(int x, int y){
        return jButtons[x][y];
    }

    public void setPointText(int x ,int y, String playerSign){
        jButtons[x][y].setText(playerSign);
    }

    public void setTextField(String text){
        titleTextField.setText(text);
    }

    //resets the visual part of the board
    public void resetView(){

        for (int i = 0; i < this.rowsAndCols; i++){
            for (int j = 0; j < this.rowsAndCols; j++){
                jButtons[i][j].setText("");
            }
        }
    }

    /* increments the score of a certain player.
    If playerNum equals one increments player one's score.
    If playerNum equals two increments player two's score.
    If playerNum is not equal to one or two the function throws a IllegalArgumentException
    because in this code the input parameter must be one or two.
    I intentionally make this function throw an error because this is a problem with the code.
    */
    public void incrementScore(int playerNum){

        if (playerNum == 1){
            player1Score++;
        }else if (playerNum == 2){
            player2Score++;
        } else {
            throw new IllegalArgumentException();
        }

        scoreTextField.setText("Player 1 Wins: " + player1Score +" , Player 2 wins: " + player2Score);
    }

}
