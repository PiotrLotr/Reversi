package other;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoardSquare extends StackPane {

    private Color color;

    private Pawn pawn;
    private Region square;
    private int col;
    private int row;


    public BoardSquare(Color defaultColor, int col, int row) {
        this.color = defaultColor;
        setColor(color);
        setPrefSize(200, 200);
        this.row =row;
        this.col =col;

        Pawn pawn = new Pawn(col, row, null);
        this.pawn = pawn;
        Region square = new Region();
        this.square = square;

        this.setOnMouseEntered(e -> this.highlight());
        this.setOnMouseExited(e -> this.blacken());

        pawn.radiusProperty().bind(Bindings.when(square.widthProperty().
                greaterThan(square.heightProperty())).
                then(square.heightProperty().subtract(12).divide(2)).
                otherwise(square.widthProperty().subtract(12).divide(2)));

        this.getChildren().addAll(square, pawn);

        startingPawnsSet(row, col, pawn);
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

    private void startingPawnsSet(int row, int col, Pawn pawn) {
        if((row == 3 & col == 3) || (row == 4 & col ==4)) {
            pawn.setVisible(true);
            pawn.setFill(Color.WHITE);
        } else if ((row == 3 & col ==4) || (row == 4 & col == 3)){
            pawn.setVisible(true);
            pawn.setFill(Color.BLACK);
        }else {
            pawn.setVisible(false);
        }
    }

    public Pawn getPawn() {
        return pawn;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    //    @Override
//    public void handle(MouseEvent mouseEvent) {
//        var ob = (Pawn)sp.getChildren().get(sp.getChildren().size()-1);
//        if(this.previousPawnColor == Color.WHITE){
//            ob.setVisible(true);
//            ob.setFill(Color.BLACK);
//        }else{
//            ob.setVisible(true);
//            ob.setFill(Color.WHITE);
//        }
//        this.previousPawnColor = ob.getFill();
//    }


}

