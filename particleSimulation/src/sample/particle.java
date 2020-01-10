package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

import java.util.ArrayList;

public class particle {
    int x;
    int y;
    String type;
    int id;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public particle(int x, int y, String type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
    }

    public void update(ArrayList<particle> parts) {
        if(type == "SAND") {
                Boolean grounded = false;
                Boolean falling = true;
                int fall = 0;
                if (this.y > 98) grounded = true;
                if (grounded == false) {
                    for (int i = 0; i < parts.size(); i++) {
                        if (parts.get(i).getId() != this.id) {
                            if (this.y + 1 == parts.get(i).getY() && this.x == parts.get(i).getX()) {
                                grounded = true;
                            }
                        }
                    }
                    if (grounded == false) {
                        this.y += 1;
                    }
                }

            if(grounded == true) {
                Boolean CANLEFT = false;
                Boolean CANRIGHT = false;
                int fl = 0;
                int fr = 0;
                if(this.y > 98) {
                    for (int i = 0; i < parts.size(); i++) {
                        if(parts.get(i).getId() != this.id) {
                          if(this.x - 1 == parts.get(i).getX() && this.y + 1 == parts.get(i).getY()) {
                            fl++;
                            System.out.println("one left");
                          }
                          if(this.x + 1 == parts.get(i).getX() && this.y + 1 == parts.get(i).getY()){
                             fr++;

                          }
                        }
                    }

                    if(fr == 0) {CANRIGHT = true;}
                    if(fl == 0) {CANLEFT = true;}

                    if(CANRIGHT && CANLEFT){
                        double randr = Math.random() * 2;
                        if(randr < 1){
                            if(this.x > 0) {
                                this.x -= 1;
                            }

                        } else {
                            if(this.x < 99) {
                                this.x += 1;
                            }
                        }

                    }
                }
            }



            }
        }


    public void draw(GraphicsContext gc){
        gc.fillRect(this.x * 8, this.y * 8, 8, 8);
    }
}
