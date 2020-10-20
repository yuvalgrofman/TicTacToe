package yuval.grofman;

public class Model {

    private int[][] board;
    private boolean isPlayerOneTurn = true;
    private boolean gameOngoing = true;
    private int player1Wins = 0;
    private int player2Wins = 0;
    private int rowsAndCols;

    Model(int rowsAndCols){
        this.rowsAndCols = rowsAndCols;
        board = new int[rowsAndCols][rowsAndCols];

        for (int i = 0; i < rowsAndCols; i++){
            for (int j = 0; j < rowsAndCols; j++){
                board[i][j] = 0;
            }
        }
    }

    /*
    no input this function uses the global board variable.
     returns 1 if X won, returns 2 if O won,
     returns -1 if the game didn't end yet and returns 0 if it's a tie.
     */
    public int checkVictory(){
        int result;


        for (int i = 0; i < rowsAndCols; i++){//checking for vertical and horizontal victory's


            result = checkRow(i);

            if (result != 0){
                gameOngoing = false;
                return result;
            }

            result = checkColumn(i);

            if (result != 0){
                gameOngoing = false;
                return result;
            }
        }

        result = checkFirstDiagonal();//checks for the diagonal that starts top left

        if (result != 0){
            gameOngoing = false;
            return result;
        }

        result = checkSecondDiagonal();////checks for the diagonal that starts bottom left

        if (result != 0){
            gameOngoing = false;
            return result;
        }

        if(checkForTie()){//checks for a tie
            gameOngoing = false;
            return 0;

        }else {//if nobody won and there isn't a tie returns -1

            return -1;
        }
    }

    /*
    if a certain player wins in that row returns that players number else returns 0
    input: the row you want to check
     */
    public int checkRow(int rowNum){
        int previousElement = this.board[0][rowNum];
        boolean isRowVictory = true;

        for (int i = 0; i < rowsAndCols; i++){
            int currentElement = this.board[i][rowNum];

            if (previousElement != currentElement || currentElement == 0){
                isRowVictory = false;
            }

            previousElement = currentElement;
        }

        if (isRowVictory){
            return previousElement;
        }

        return 0;
    }

    /*
    if a certain player wins in that column returns that players number else returns 0
    input: the column you want to check
     */
    public int checkColumn(int columnNum){

        int previousElement = this.board[columnNum][0];
        boolean isColumnVictory = true;

        for (int i = 0; i < rowsAndCols; i++){
            int currentElement = this.board[columnNum][i];

            if (previousElement != currentElement || currentElement == 0){
                isColumnVictory = false;
            }

            previousElement = currentElement;
        }

        if (isColumnVictory){
            return previousElement;
        }

        return 0;
    }

    /*
    if a certain player wins in the first diagonal (the one that starts top left) returns that players number else returns 0
    input: none
     */
    public int checkFirstDiagonal(){
        int previousElement = this.board[0][0];
        boolean diagonalVictory = true;

        for (int i = 0; i < rowsAndCols; i++){
            int currentElement = this.board[i][i];

            if (previousElement != currentElement || currentElement == 0){
                diagonalVictory = false;
            }

            previousElement = currentElement;
        }

        if (diagonalVictory){
            return previousElement;
        }

        return 0;

    }

    /*
   if a certain player wins in the second diagonal (the one that starts bottom left) returns that players number else returns 0
   input: none
    */
    public int checkSecondDiagonal(){
        int previousElement = this.board[0][rowsAndCols - 1];
        boolean diagonalVictory = true;

        for (int i = 0; i < rowsAndCols; i++){
            int currentElement = this.board[i][rowsAndCols - i - 1];

            if (previousElement != currentElement || currentElement == 0){
                diagonalVictory = false;
            }

            previousElement = currentElement;
        }

        if (diagonalVictory){
            return previousElement;
        }

        return 0;

    }

    //checks for a tie
    public boolean checkForTie(){
        boolean isTie = true;

        for (int i = 0; i < rowsAndCols; i++){
            for (int j = 0; j < rowsAndCols; j++){

                int currentElement = this.board[i][j];
                if (currentElement == 0){
                    isTie = false;
                }
            }
        }

        return isTie;
    }

    /*
    sets a point then checks if that point caused a player to win and
    sets each players wins accordingly.
    input is the coordinates of the point that's to be placed.
    returns 1 if X won, returns 2 if O won,
    returns -1 if the game didn't end yet and returns 0 if it's a tie.
     */
    public int setPoint(int x, int y){
        int value;

        if (isPlayerOneTurn) {
             value = 1;

        }else{
             value = 2;

        }

        board[x][y] = value;
        isPlayerOneTurn ^= true;// if isPlayerOneTrue was true then turns to false and vice versa

        int result = this.checkVictory();

        if (result == 1){
            player1Wins++;

        }else if (result == 2){
            player2Wins++;

        }

        return result;
    }

    public int getPoint(int x, int y){
        return board[x][y];
    }

    public boolean isPlayerOneTurn(){
        return isPlayerOneTurn;
    }

    public boolean isGameOngoing(){
        return gameOngoing;
    }

    /*
    resets the board in the model
     */
    public void resetBoard(){
        for (int i = 0; i < rowsAndCols; i++){
            for (int j = 0; j < rowsAndCols; j++){
                board[i][j] = 0;

            }
        }

        isPlayerOneTurn = true;
        gameOngoing = true;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }


}
