package other;

import Reversi.View;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;

import static Reversi.Model.getNodeByRowColumnIndex;


public class VectorOperations {

    Paint paintOfStartingPawn;

    public VectorOperations() {
    }

    public void setPaintOfStartingPawn(Paint paintOfStartingPawn) {
        this.paintOfStartingPawn = paintOfStartingPawn;
    }

    public BoardSquare getNextAlongDir(Vector vector, GridPane board, BoardSquare startingPawn) {
        var neighbourAlongDir = (BoardSquare) getNodeByRowColumnIndex(startingPawn.getPawn().getY() + vector.getyDir(), startingPawn.getPawn().getX() + vector.getxDir(), board);
        return neighbourAlongDir;
    }

    public boolean isOpponentPawn(Vector vector, GridPane board, BoardSquare squareChecked) {
        if (
                squareChecked.getPawn().isVisible() == true &&
                squareChecked.getPawnColor() != paintOfStartingPawn &&
                squareChecked.getPawn() != null)
        {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<BoardSquare> getLineOfPawns(Vector vector, GridPane board, BoardSquare boardSquare) {
        ArrayList<BoardSquare> pawnsAlongLine = new ArrayList<>();
        BoardSquare nextAlongDir = getNextAlongDir(vector, board, boardSquare);

        while (isOpponentPawn(vector, board, nextAlongDir)) {
            pawnsAlongLine.add(nextAlongDir);
                nextAlongDir = getNextAlongDir(vector, board, nextAlongDir);
                System.out.println(nextAlongDir);
            }
        pawnsAlongLine.add(nextAlongDir);
        return pawnsAlongLine;
    }

        public void refreshInDirection (Vector vector, GridPane board, BoardSquare startingPawn){
            ArrayList<BoardSquare> tempArray = new ArrayList();
            tempArray.addAll(getLineOfPawns(vector, board, startingPawn));
            var lastSquare = tempArray.get(tempArray.size() - 1);

            if (lastSquare.getPawnColor() == paintOfStartingPawn && tempArray.size() > 1) {
                for (BoardSquare boardSquare : tempArray) {
                    boardSquare.setPawnColor(paintOfStartingPawn);
                }
            }
        }


    public void refreshBoard(GridPane board, BoardSquare startingPawn) {
        Arrays.asList(Vector.values())
                .forEach(vector -> refreshInDirection(vector, board, startingPawn));
    }

    public boolean isSquareValid(GridPane board, BoardSquare boardSquare) {
        for (Vector vector : Vector.values()) {

            ArrayList<BoardSquare> tempArray = new ArrayList();
            tempArray.addAll(getLineOfPawns(vector, board, boardSquare));
            var lastSquare = tempArray.get(tempArray.size() - 1);

            if (lastSquare.getPawnColor() != null &&
                    lastSquare.getPawnColor() == paintOfStartingPawn
                    && tempArray.size() > 1)
                return true;
        }
        return false;
    }


}


