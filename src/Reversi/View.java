package Reversi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import other.BoardSquare;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import other.VectorOperations;

import static Reversi.Model.getNodeByRowColumnIndex;

public class View extends Application {

    public static final int BOARD_ROW = 10;
    public static final int BOARD_COL = 10;

    GridPane board = new GridPane();
    Background bg = new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY));
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    Label label = new Label("BLACK STARTS");
    Label whiteResult = new Label("0");
    Circle white = new Circle();
    Label blackResult = new Label();
    Circle black = new Circle();

    Stage stage = new Stage();
    int clickCounter = 0;
    Rectangle turnIndicator = new Rectangle();

    Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) {
        setTurnIndicatorParameters();
        setResultIndicatorsParameters();

        vbox.setPadding(new Insets(5, 5, 5, 50));
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(
                label,
                turnIndicator,
                black,
                blackResult,
                white,
                whiteResult
        );

        Scene scene = new Scene(hbox, 900, 800);

        hbox.setBackground(bg);
        hbox.getChildren().addAll(createMenu(), vbox, drawBoard());
        addGridEvent();

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private VBox createMenu() {
        Menu m = new Menu("File");
        MenuBar mb = new MenuBar();

        final Menu menu1 = new Menu("Save");
        final Menu menu2 = new Menu("Open");

        m.getItems().addAll(menu1, menu2);
        mb.getMenus().add(m);


        VBox vBox = new VBox(mb);

        return vBox;
    }


    private void setResultIndicatorsParameters() {
        white.setFill(Color.WHITE);
        white.setVisible(true);
        white.setRadius(40);
        black.setFill(Color.BLACK);
        black.setVisible(true);
        black.setRadius(40);

    }

    private void setTurnIndicatorParameters() {
        turnIndicator.setVisible(true);
        turnIndicator.setHeight(100);
        turnIndicator.setWidth(100);
    }

    public void changeIndicatorColor(Paint paint) {
        turnIndicator.setFill(paint);
    }

    public void changeResult() {
        var whitePawns = controller.countPawns(board, Color.WHITE);
        var blackPawns = controller.countPawns(board, Color.BLACK);

        whiteResult.setText(String.valueOf(whitePawns));
        blackResult.setText(String.valueOf(blackPawns));

        if (controller.isEndOfGame(board)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (whitePawns > blackPawns) {
                alert.setTitle("WHITE WINS!!");
                alert.setContentText("END OF THE GAME. WHITE PAWNS WINS!!!");
                alert.showAndWait();
            } else {
                alert.setTitle("BLACK WINS!!");
                alert.setContentText("END OF THE GAME. BLACK PAWNS WINS!!!");
                alert.showAndWait();
            }
        }

    }

    public GridPane drawBoard() {
        for (int row = 0; row < BOARD_ROW; row++) {
            for (int col = 0; col < BOARD_COL; col++) {
                BoardSquare boardSquare = new BoardSquare(Color.GREEN, col, row);
                board.add(boardSquare, boardSquare.getCol(), boardSquare.getRow());
            }
        }
        for (int row = 0; row < 8; row++) {
            RowConstraints constrains = new RowConstraints();
            constrains.setPercentHeight(10);
            board.getRowConstraints().add(constrains);
        }
        for (int col = 0; col < 8; col++) {
            ColumnConstraints constrains = new ColumnConstraints();
            constrains.setPercentWidth(10);
            board.getColumnConstraints().add(constrains);
        }
        return board;
    }

    public void handle(MouseEvent e) {
        Node source = (Node) e.getSource();

        Integer colIndex = board.getColumnIndex(source);
        Integer rowIndex = board.getRowIndex(source);

        var selected = (BoardSquare) getNodeByRowColumnIndex(rowIndex, colIndex, board);
        VectorOperations vectorOperations = new VectorOperations();
        
        if (selected.getPawnColor() == null) {
            if (clickCounter % 2 == 0) {
                selected.setPawnColor(Color.BLACK);
            } else {
                selected.setPawnColor(Color.WHITE);
            }
            vectorOperations.setPaintOfStartingPawn(selected.getPawnColor());

            // modify pawn()
            if (vectorOperations.isSquareValid(board, selected)) {
                selected.getPawn().setVisible(true);

                vectorOperations.refreshBoard(board, selected);
                System.out.println(selected);
                System.out.println(clickCounter);

                if (clickCounter % 2 == 0) {
                    changeIndicatorColor(Color.WHITE);
                    label.setText("WHITE TURN");
                } else {
                    changeIndicatorColor(Color.BLACK);
                    label.setText("BLACK TURN");
                }
                clickCounter++;

            }
        }
        changeResult();
    }

    private void addGridEvent() {
        board.getChildren().forEach(item -> {
            item.setOnMouseClicked(this::handle);
        });
    }

}
