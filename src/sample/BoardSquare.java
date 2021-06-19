package sample;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class BoardSquare extends Region implements EventHandler<MouseEvent> {

    private Color color;
    Pawn[][] pawns = new Pawn[8][8];
    Paint previousPawnColor;

    StackPane sp;

    public BoardSquare(Color defaultColor) {
        this.color = defaultColor;
        setColor(color);
        setPrefSize(200, 200);
    }

    public void highlight() {
        setColor(Color.YELLOW);
    }

    public void blacken() {
        setColor(color);
    }

    public void setColor(Color color) {
        BackgroundFill bgFill = new BackgroundFill(color, CornerRadii.EMPTY, new Insets(2));
        Background bg = new Background(bgFill);
        setBackground(bg);
    }

    public StackPane createBoardSquare(int row, int col) {
        StackPane stackPane = new StackPane();

        BoardSquare square = new BoardSquare(Color.GREEN);
        stackPane.setOnMouseEntered(e -> square.highlight());
        stackPane.setOnMouseExited(e -> square.blacken());

        Pawn pawn = new Pawn(col, row, null);
        pawns[col][row] = pawn;

        pawn.radiusProperty().bind(Bindings.when(square.widthProperty().
                greaterThan(square.heightProperty())).
                then(square.heightProperty().subtract(12).divide(2)).
                otherwise(square.widthProperty().subtract(12).divide(2)));

        this.sp = stackPane;
        stackPane.getChildren().add(square);
        stackPane.getChildren().add(pawn);
        stackPane.setOnMouseClicked(this);

        if((row == 3 & col == 3) || (row == 4 & col ==4)) {
            pawn.setVisible(true);
            pawn.setFill(Color.WHITE);
        } else if ((row == 3 & col ==4) || (row == 4 & col == 3)){
            pawn.setVisible(true);
            pawn.setFill(Color.BLACK);
        }else {
            pawn.setVisible(false);
        }

        return stackPane;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(previousPawnColor == Color.WHITE){
            pawn.setVisible(true);
            pawn.setFill(Color.BLACK);
        }else{
            pawn.setVisible(true);
            pawn.setFill(Color.WHITE);
        }
        this.previousPawnColor = pawn.getFill();
    }


}

