package tictactoe.ui.game.screen;

public class Game {

    private Player player1;
    private Player player2;

    public boolean isRec_flag() {
        return rec_flag;
    }

    public void setRec_flag(boolean rec_flag) {
        this.rec_flag = rec_flag;
    }
    private Board squares;
    private boolean online;
    private char currentPlayerSymbol;
    private Recording[] rec = new Recording[9];
    private boolean rec_flag;
    private int counter =0;
    
    public Game(boolean online) {
        this.online = online;
        this.squares = new Board();
        this.currentPlayerSymbol = 'X';
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }



    public boolean placeXO(int pos) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;

        if (pos >= 1 && pos <= 9 && squares.isCellEmpty(row, col)) {
            //System.out.println("row:"+row);
            //System.out.println("col:"+col);
            //System.out.println(currentPlayerSymbol);
            
            squares.setGrid(row, col, currentPlayerSymbol);
            
            
            if(rec_flag){
                Recording r = new Recording(pos, X_OR_O.getEnum(currentPlayerSymbol));
                System.out.println("recording object "+r.getPosition()+r.getPlayed_char().toChar());
                rec[counter] = r;
                counter ++;
            }
            switchPlayer();
            
            for(int i = 0; i<squares.getGrid().length;i++){
                for(int j = 0; j<squares.getGrid().length;j++){
                    System.out.println("rowGrid:"+i);
                    System.out.println("colGrid:"+j);
                    System.out.println(squares.getGrid()[i][j]);
                    System.out.println("---------------------------");
                }
            }
            return true;
        }
        return false;
    }

    public int[] calculateWinner() {
        char[][] mygrid = squares.getGrid();

        for (int row = 0; row < 3; row++) {
            if (mygrid[row][0] != 0 && (mygrid[row][0] == mygrid[row][1]) && (mygrid[row][1] == mygrid[row][2])) {
                return new int[]{row * 3 + 1, row * 3 + 2, row * 3 + 3};
            }
        }
        for (int col = 0; col < 3; col++) {
            if (mygrid[0][col] != 0 && (mygrid[0][col] == mygrid[1][col]) && (mygrid[1][col] == mygrid[2][col])) {
                return new int[]{col + 1, col + 4, col + 7};
            }
        }
        if (mygrid[0][0] != 0 && (mygrid[0][0] == mygrid[1][1]) && (mygrid[1][1] == mygrid[2][2])) {
            return new int[]{1, 5, 9};
        }
        if (mygrid[0][2] != 0 && (mygrid[0][2] == mygrid[1][1]) && (mygrid[1][1] == mygrid[2][0])) {
            return new int[]{3, 5, 7};
        }
        return null;
    }

    private void switchPlayer() {
        currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }

    public void resetGame() {
        squares = new Board();
        currentPlayerSymbol = 'X';
    }

    public Board getSquares() {
        return squares;
    }

    public void setSquares(Board squares) {
        this.squares = squares;
    }

    public Recording[] getRec() {
        return rec;
    }

    public String recToString(){
        String s = null;
        for(int i=0;i<rec.length;i++){
           String carxo = rec[i].getPlayed_char().toString()+"###";
           String rowcol = String.valueOf(rec[i].getPosition())+"###";
           s = carxo + rowcol+"---";
            System.out.println("recording "+s);
        }
        return s;
    }
    //error
    /*public void setRec(Recording[] rec) {
        this.rec = rec;
    }*/
    
    
    
}