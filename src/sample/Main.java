package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    GridPane board = new GridPane();
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(getBoard(), 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public GridPane getBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                BoardSquare square = new BoardSquare(Color.GREEN);
                board.add(square.createBoardSquare(col,row), col, row);
            }
        }
        for (int row = 0; row < 8; row++) {
            RowConstraints constrains = new RowConstraints();
            constrains.setPercentHeight(20);
            board.getRowConstraints().add(constrains);
        }
        for (int col = 0; col < 8; col++) {
            ColumnConstraints constrains = new ColumnConstraints();
            constrains.setPercentWidth(20);
            board.getColumnConstraints().add(constrains);
        }
        return board;
    }

//    public void refreshBoard(){
//        for (int col = 0; col < pawns.length; col++) {
//            for (int row = 0; row < pawns[col].length; col++) {
//
//            }
//        }
//    }

    public static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

}