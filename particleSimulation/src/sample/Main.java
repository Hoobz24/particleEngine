package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;


public class Main extends Application {

    private AnimationTimer timer;
    final double SCENE_WIDTH = 800;
    final double SCENE_HEIGHT = 800;
    ArrayList<particle> parts = new ArrayList<particle>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(SCENE_WIDTH, SCENE_WIDTH);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRect(10, 10, 10, 10);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        canvas.setOnMouseClicked(mouseHandler);

        parts.add(new particle(10, 10, "SAND", parts.size()));
        parts.add(new particle(10, 20, "SAND", parts.size()));

        loop(gc);
    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){

        public void handle(MouseEvent mouseEvent){
            System.out.println((int)(Math.floor(mouseEvent.getX() / 10)));
            parts.add(new particle((int)(Math.floor(mouseEvent.getX() / 10)), (int)(Math.floor(mouseEvent.getY() / 10)), "SAND", parts.size()));
        }

    };

    public void loop(GraphicsContext gc) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(int i = 0; i < parts.size(); i++){
                    parts.get(i).update(parts);

                }
                draw(gc);
            }

        };

        timer.start();
    }

    public void draw(GraphicsContext gc){

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        gc.setFill(Color.CORNFLOWERBLUE);
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).draw(gc);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
