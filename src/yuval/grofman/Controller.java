package yuval.grofman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller{

    private Model model;
    private View  view;
    private int rowsAndCols;

    /*
    adds action listener to all the buttons
    The input of this function is the size of the grid.
    The grid is always a square so we only need one input to create the grid.
    */
    Controller(int rowsAndCols){

        if (rowsAndCols < 0 || rowsAndCols > 20){//above twenty the text in the view is too small
            System.out.println("The input for this class must be between 0 and 20 not including 0");
            throw new IllegalArgumentException();
        }

        this.rowsAndCols = rowsAndCols;
        this.model = new Model(rowsAndCols);
        this.view = new View(rowsAndCols);

        for (int i = 0; i < rowsAndCols; i++){
            for (int j = 0; j < rowsAndCols; j++){
                this.view.getButton(i,j).addActionListener(new mouseListener());
            }
        }
    }

    //returns true of isPlayerOneTurn and false if it isn't
    public boolean isPlayerOneTurn(){
            return model.isPlayerOneTurn();
        }


    class mouseListener implements ActionListener{

        //this function sets what happens when the button are click
        @Override
        public void actionPerformed(ActionEvent event) {
            try {

                if(!model.isGameOngoing()){
                    view.resetView();
                    model.resetBoard();
                    view.setTextField("Tic-Tac-Toe");

                }else{

                    view.setTextField("Tic-Tac-Toe");
                    int result = Integer.MAX_VALUE;//just initializing value so there won't be build errors

                    for (int i = 0; i < rowsAndCols; i++) {
                        for (int j = 0; j < rowsAndCols; j++) {
                            if (event.getSource() == view.getButton(i, j) && model.isGameOngoing()) {
                                if (view.getButton(i, j).getText() == "") {

                                    String sign;//We check who's turn it is before setting the point because the setPoint function modifies isPlayerOneTurn
                                    if (model.isPlayerOneTurn()){
                                        sign = "X";
                                    }else{
                                        sign = "O";
                                    }

                                    result = model.setPoint(j, i);
                                    //The reason the i and j here are switched, is because the order of the buttons is first rows then columns starting from the top left,
                                    //unlike in 2d arrays where the order is columns then rows while also starting from the top left.
                                    //Because of that when setting the points ,the x and y switch places when dealing with the view.setPointText and model.setPoint functions.

                                    view.setPointText(i,j,sign);
                                }
                            }
                        }
                    }

                    if (result == 1) {
                        view.setTextField("Player 1 won!!!");
                        view.incrementScore(1);

                    } else if (result == 2) {
                        view.setTextField("Player 2 won!!!");
                        view.incrementScore(2);

                    } else if (result == 0) {
                        view.setTextField("  Tie   ");
                    }
                }

            }catch (Exception exception){
                System.out.println("There has been an unknown exception");
                System.out.println("The exception details are: ");
                System.out.println(exception.getCause());
                System.out.println(exception.getMessage());
                System.out.println(exception.getLocalizedMessage());
                System.out.println(exception.getStackTrace()[0].getLineNumber());
            }
        }
    }
}
