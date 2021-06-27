package Reversi;

import javafx.application.Application;
import javafx.collections.ObservableList;
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
    int clickCounter = 0;

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

        Integer colIndex = board.getColumnIndex(source);
        Integer rowIndex = board.getRowIndex(source);

        var selected = (BoardSquare)getNodeByRowColumnIndex(rowIndex, colIndex, board);
        VectorOperations vectorOperations = new VectorOperations(colIndex, rowIndex);

        // modify pawn()
        selected.getPawn().setVisible(true);
        if(clickCounter%2 ==0){
            selected.getPawn().setFill(Color.BLACK);
            vectorOperations.setPaintOfStartingPawn(Color.BLACK);
        }else{
            selected.getPawn().setFill(Color.WHITE);
            vectorOperations.setPaintOfStartingPawn(Color.WHITE);
        }

        System.out.println(selected);
        System.out.println(clickCounter);
        clickCounter++;


        vectorOperations.refreshBoard(board, selected);
    }

    private void addGridEvent() {
        board.getChildren().forEach(item -> {
            item.setOnMouseClicked(this::handle);});}

    public static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    // col(x), row (y)
    // local center of axis x0,y0 -> selected pawn




}
