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
    Paint paintOfStartingPawn;


    public VectorOperations(int col0, int row0) {
        this.col0 = col0;
        this.row0 = row0;
    }

    public boolean isFittingOccAlongDir(Vector vector, GridPane board, BoardSquare startingPawn) {
        var neighbourAlongDir = getNeighbourAlongDir(vector, board, startingPawn);

        if (neighbourAlongDir.getPawn().isVisible() == true &&
                neighbourAlongDir.getPawn().getFill() != startingPawn.getPawn().getFill() &&
                neighbourAlongDir.getPawn() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void refreshInDirection(Vector vector, GridPane board, BoardSquare startingPawn) {
        ArrayList<BoardSquare> pawnsInfected = new ArrayList<>();
        BoardSquare neighbourAlongDir = getNeighbourAlongDir(vector, board, startingPawn);

        while (isFittingOccAlongDir(vector, board, neighbourAlongDir)) {
            pawnsInfected.add(neighbourAlongDir);
            neighbourAlongDir = getNeighbourAlongDir(vector, board, neighbourAlongDir);
            System.out.println(neighbourAlongDir);
        }

        if (getNeighbourAlongDir(vector, board, neighbourAlongDir).getPawn().getFill() == paintOfStartingPawn) {
            for (BoardSquare boardSquare : pawnsInfected) {
                boardSquare.getPawn().setFill(paintOfStartingPawn);
            }
        }
    }

    public BoardSquare getNeighbourAlongDir(Vector vector, GridPane board, BoardSquare startingPawn) {
        var neighbourAlongDir = (BoardSquare) getNodeByRowColumnIndex(startingPawn.getPawn().getY() + vector.getyDir(), startingPawn.getPawn().getX() + vector.getxDir(), board);
        return neighbourAlongDir;
    }

    public void refreshBoard(GridPane board, BoardSquare startingPawn) {
        Arrays.asList(Vector.values())
                .forEach(vector -> refreshInDirection(vector, board, startingPawn));
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

    public void setPaintOfStartingPawn(Paint paintOfStartingPawn) {
        this.paintOfStartingPawn = paintOfStartingPawn;
    }
}