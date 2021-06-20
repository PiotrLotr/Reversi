package Reversi;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import other.BoardSquare;
import other.Vector;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {


    // checkWinCondition(){}

}

class VectorOperations {

    int col0;
    int row0;

    public VectorOperations(int col0, int row0) {
        this.col0 = col0;
        this.row0 = row0;
    }

    public boolean isFittingOccAlongDir(Vector vector, GridPane board) {
        var startingPawn = (BoardSquare) getNodeByRowColumnIndex(row0, col0, board);
        var neighbourAlongDir = getNeighbourAlongDir(vector, board, startingPawn);

        if (neighbourAlongDir.getPawn().isVisible() == true && neighbourAlongDir.getPawn().getFill() != startingPawn.getPawn().getFill()) {
            return true;
        } else {
            return false;
        }
    }

    public void refreshInDirection(Vector vector, GridPane board, BoardSquare startingPawn, BoardSquare neighbourAlongDir){
        Paint beginningPaint = startingPawn.getPawn().getFill();
        ArrayList <BoardSquare> pawnsInfected = new ArrayList<>();

        while(neighbourAlongDir.getPawn().isVisible() == true ||
                neighbourAlongDir.getPawn().getFill() != beginningPaint ||
                neighbourAlongDir.getPawn() != null) {

            if (isFittingOccAlongDir(vector, board)) {
                pawnsInfected.add(neighbourAlongDir);
                new VectorOperations(row0 + vector.getyDir(),col0 + vector.getxDir());
            }
        }
        for (BoardSquare boardSquare: pawnsInfected) {
            boardSquare.getPawn().setFill(beginningPaint);
        }
    }

    public BoardSquare getNeighbourAlongDir(Vector vector, GridPane board,BoardSquare startingPawn){
        var neighbourAlongDir = (BoardSquare) getNodeByRowColumnIndex(row0 + vector.getyDir(),col0 + vector.getxDir(), board);
        return neighbourAlongDir;
    }

    public void refreshBoard(GridPane board, BoardSquare startingPawn){
        Arrays.asList(Vector.values())
                .forEach(vector -> refreshInDirection(vector, board, startingPawn, getNeighbourAlongDir(vector, board, startingPawn)));
    }


    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
}