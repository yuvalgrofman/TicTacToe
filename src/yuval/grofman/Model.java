package yuval.grofman;

public class Model {

    private int[][] board = new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    private boolean isPlayerOneTurn = true;
    private boolean gameOngoing = true;
    private int player1Wins = 0;
    private int player2Wins = 0;


    /*
    no input this function uses the global board variable.
     returns 1 if X won, returns 2 if O won,
     returns -1 if the game didn't end yet and returns 0 if it's a tie.
     */
    public int checkVictory(){
        for (int i = 0; i < 3; i++){

            if (this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board[i][2] && this.board[i][0] != 0){
                gameOngoing = false;
                return board[i][0];

            }else if (this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i] && this.board[0][i] != 0){
                gameOngoing = false;
                return this.board[0][i];

            }
        }

        if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] && this.board[0][0] != 0){
            gameOngoing = false;
            return board[0][0];

        }else if(this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0] && this.board[1][1] != 0){
            gameOngoing = false;
            return this.board[1][1];

        }else{

            int result = 0;

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (this.board[i][j] == 0){
                        result = -1;
                    }
                }
            }
            if (result == 0){
                gameOngoing = false;
            }

            return result;
        }
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

    public void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
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
