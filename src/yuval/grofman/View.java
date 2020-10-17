package yuval.grofman;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel playerPanel = new JLabel();
    private JLabel titleTextField = new JLabel();
    public JButton[][] jButtons = new JButton[3][3];

    View(){

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

        //setting the title panel(the title text field is added to the title panel)
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,200);

        //setting the button panel
        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(150,150,150));

        //creating a 3 3 grid of buttons
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){

                jButtons[i][j] = new JButton();
                buttonPanel.add(jButtons[i][j]);
                jButtons[i][j].setFocusable(false);
                jButtons[i][j].setFont(new Font("Arial",Font.PLAIN ,100));

            }
        }

        //adding all the components to the frame
        titlePanel.add(titleTextField);
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

    protected void resetView(){

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                jButtons[i][j].setText("");
            }
        }
    }

}
