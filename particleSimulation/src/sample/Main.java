package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.ArrayList;


public class Main extends Application {

    private AnimationTimer timer;
    final double SCENE_WIDTH = 800;
    final double SCENE_HEIGHT = 800;
    private boolean spawning = false;
    private double mx;
    private double my;
    private ArrayList<String> partTypes = new ArrayList<String>();
    private int sel = 1;
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
        canvas.setOnMousePressed(mousePressed);
        canvas.setOnMouseReleased(mouseReleased);
        canvas.setOnMouseDragged(mouseDragged);
        canvas.setOnKeyReleased(keyPressed);
        partTypes.add("WATER");
        partTypes.add("WALL");
        partTypes.add("SAND");
        partTypes.add("CONWAY");

        loop(gc);
    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){

        public void handle(MouseEvent mouseEvent){


            System.out.println((int)(Math.floor(mouseEvent.getX() / 10)));

        }



    };

    EventHandler<MouseEvent> mouseDragged = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event){
            mx = event.getX();
            my = event.getY();
            System.out.println("clicking");
            spawning = true;
        }

    };

    EventHandler<MouseEvent> mousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(event.isPrimaryButtonDown()) {
                mx = event.getX();
                my = event.getY();
                System.out.println("clicking");
                spawning = true;
            } else if(event.isSecondaryButtonDown()){
                sel++;
                if(sel > partTypes.size() - 1) sel = 0;
            }
        }
    };

    EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            System.out.println("AYUP[");

        }
    };

    EventHandler<MouseEvent> mouseReleased = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            spawning = false;
        }
    };

    public void loop(GraphicsContext gc) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(int i = 0; i < parts.size(); i++){
                    parts.get(i).update(parts);

                }
                int diam = 3;
                if(spawning){

                    parts.add(new particle(1 +(int)mx / 8,(int)my / 8, partTypes.get(sel), parts.size()));
                    parts.add(new particle(-1 + (int)mx / 8,(int)my / 8, partTypes.get(sel), parts.size()));
                    parts.add(new particle((int)mx / 8,1 + (int)my / 8, partTypes.get(sel), parts.size()));
                    parts.add(new particle((int)mx / 8,-1 + (int)my / 8, partTypes.get(sel), parts.size()));


                }
                draw(gc);
            }

        };

        timer.start();
    }

    public void draw(GraphicsContext gc){

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        int offset = 0;
        gc.setFont(Font.font("Veranda", 30));
        gc.setFill(Color.WHITE);
        for (String type:partTypes) {
            if(type.equals(partTypes.get(sel))) gc.setFill(Color.RED);
            gc.fillText(type, offset, 40);
            offset += 150;
            gc.setFill(Color.WHITE);
        }
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).draw(gc);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
