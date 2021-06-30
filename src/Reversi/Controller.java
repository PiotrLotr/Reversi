package Reversi;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import other.BoardSquare;
import other.VectorOperations;

import static Reversi.Model.getNodeByRowColumnIndex;

public class Controller {

    public int countPawns(GridPane board, Paint checkedColor){
        int counter = 0;

        for (int row = 0; row < View.BOARD_ROW; row++) {
            for (int col = 0; col < View.BOARD_COL; col++) {
                var checkedSquare = (BoardSquare)getNodeByRowColumnIndex(row, col, board);
                if(checkedSquare.getPawnColor() == checkedColor){
                    counter ++;
                }
            }
        } return counter;
    }

    public boolean isEndOfGame(GridPane board) {
        VectorOperations vo = new VectorOperations();
        for (int row = 2; row < View.BOARD_ROW - 1; row++) {
            for (int col = 2; col < View.BOARD_COL - 1; col++) {
                var check = (BoardSquare) getNodeByRowColumnIndex(row, col, board);
                if (check.getPawnColor() == null) {
                    return false;
                }
            }
        }
        return true;
    }



}


