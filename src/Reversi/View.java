package Reversi;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import other.BoardSquare;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View extends Application {

    GridPane board = new GridPane();
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(drawBoard(), 600, 600);
        addGridEvent();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public GridPane drawBoard() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                BoardSquare boardSquare = new BoardSquare(Color.GREEN, col,row);
                board.add(boardSquare, boardSquare.getCol(), boardSquare.getRow());
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

//    private void mouseEntered(MouseEvent e) {
//        Node source = (Node)e.getSource() ;
//        Integer colIndex = GridPane.getColumnIndex(source);
//        Integer rowIndex = GridPane.getRowIndex(source);
//        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
//    }

    public void handle(MouseEvent e) {
        Node source = (Node) e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        var selected = (BoardSquare)getNodeByRowColumnIndex(rowIndex, colIndex, board);
        selected.getPawn().setVisible(true);
        System.out.println(selected);

    }

    private void addGridEvent() {
        board.getChildren().forEach(item -> {
            item.setOnMouseClicked(this::handle);});}


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
