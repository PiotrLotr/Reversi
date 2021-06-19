package other;

import javafx.scene.layout.StackPane;

public class MyStackPane extends StackPane {

    BoardSquare boardSquare;
    Pawn pawn;

    public BoardSquare getBoardSquare() {
        return boardSquare;
    }

    public void setBoardSquare(BoardSquare boardSquare) {
        this.boardSquare = boardSquare;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }



}
