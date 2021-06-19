//package Reversi;
//
//import javafx.application.Application;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    private static final int TILE_SIZE = 100;
//    private static final int width = 8;
//    private static final int height = 8;
//
//
//    private Parent createContent(){
//        Pane root = new Pane();
//        root.setPrefSize(width*TILE_SIZE, height*TILE_SIZE);
//
//        for (int y = 0; y < Y_TILES; y++) {
//            for (int x = 0; x < X_TILES; x++) {
//                Tile tile = new Tile(x,y,  true);
//                grid[x][y] = tile;
//                root.getChildren().add(tile);
//            }
//        }
//        return root;
//    }
//
//    private class Tile extends StackPane {
//        private int x, y;
//        private boolean isShown;
//
//        private Rectangle field = new Rectangle(TILE_SIZE - 2,TILE_SIZE -2);
//
//        public Tile(int x, int y, boolean isShown){
//            this.x = x;
//            this.y = y;
//            this.isShown = isShown;
//
//            field.setStroke(Color.GREEN);
//            getChildren().addAll(field);
//
//        }
//    }
//
//
//
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Scene scene = new Scene(createContent());
//        stage.setScene(scene);
//        stage.show();
//    }
//
//
//}
