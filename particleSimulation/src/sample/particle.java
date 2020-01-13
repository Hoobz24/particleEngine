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
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.canvas.*;
import sun.security.ssl.Debug;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class particle {
    int x;
    int y;
    String type;
    int id;
    Color c;
    boolean alive = false;

    Boolean fixed = false;
    int fixframe = 0;
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public particle(int x, int y, String type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
        if(type.equals("SAND")) {
            double rand = Math.random() * 60;
            if (rand < 30) {
                c = Color.LIGHTYELLOW;

            }
            if (rand > 30 && rand < 60) {
                c = Color.SANDYBROWN;

            }
        }
        if(type.equals("WATER")) {
            double rand = Math.random() * 60;
            if (rand < 30) {
                c = Color.CORNFLOWERBLUE;

            }
            if (rand > 30 && rand < 60) {
                c = Color.BLUE;

            }
        }
        if(type.equals("WALL")){
            c = Color.LIGHTGRAY;

        }
    }

    public void update(ArrayList<particle> parts) {

        switch(type) {
            case "SAND": updateSand(parts);
            break;
            case "WATER": updateWater(parts);
            break;
            case "WALL": updateWall(parts);
            break;
            case "CONWAY": updateConway(parts);
        }
    }

    private void updateConway(ArrayList<particle> parts){
        double rand = Math.random() * 100;
        if(rand < 10){
            alive = true;
        }
        int neis = 0;
        for (int i = 0; i < parts.size(); i++) {
                 if (parts.get(i).getId() != this.id && parts.get(i).getType().equals("CONWAY")) {
                     if((parts.get(i).getX() == this.x - 1 && parts.get(i).getY() == this.y) || (parts.get(i).getX() == this.x + 1 && parts.get(i).getY() == this.y) ||
                             (parts.get(i).getX() == this.x && parts.get(i).getY() == this.y + 1) || (parts.get(i).getX() == this.x - 1 && parts.get(i).getY() == this.y - 1)){
                         neis++;
                     }
                }
            }

        if(neis == 3 && alive == false){
            alive = true;
        }
        if(alive == true && neis > 3){
            alive = false;
        }
        if(alive == true && neis < 2){
            alive = false;
        }
        }

    private void updateWall(ArrayList<particle> parts){

    }
    private void updateWater(ArrayList<particle> parts){
        Boolean grounded = false;
        Boolean falling = false;
        Boolean groundedWall = false;
        int fall = 0;
        if (this.y < 99) falling = true;

        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getId() != this.id) {
                if (this.y + 1 == parts.get(i).getY() && this.x == parts.get(i).getX()) {
                    if(parts.get(i).getType().equals("WALL")) groundedWall = true;
                    falling = false;
                }
            }
        }
        if (falling == true) {
            this.y += 1;
            this.x += ThreadLocalRandom.current().nextInt(-2, 2 + 1);
            falling = false;
        } else {
            this.x += ThreadLocalRandom.current().nextInt(-2, 2 + 1);

            int dist = this.y;
            int account = 0;
            for (int i = 0; i < parts.size(); i++) {
                if (parts.get(i).getX() == this.x && parts.get(i).getY() > this.y) account++;
            }
            if (dist + account == 99 || groundedWall) grounded = true;

            Boolean CANLEFT = false;
            Boolean CANRIGHT = false;
            int fl = 0;
            int fr = 0;
            if (this.y < 99) {


            }
            int tl = 0;
            int tr = 0;
            for (int i = 0; i < parts.size(); i++) {
                if (this.x - 1 == parts.get(i).getX()) {
                    tl++;
                }
                if (this.x + 1 == parts.get(i).getX()) {
                    tr++;
                }
            }
            if (tl == 0 && tr == 0) {
                int move = ThreadLocalRandom.current().nextInt(-2, 2 + 1);
                if (this.x + 1 < 100 && this.x - 1 > 0) {
                    this.x += move;
                }
                if(move < 0){
                    if (tl == 0 && tr != 0) {
                        if (this.x - 1 > 0) {
                            this.x--;
                        }

                    } else {
                        if (this.x + 1 < 100) {
                            this.x++;
                        }
                    }
                } else {
                    if(tl == 0 && tr != 0){
                        if (this.x + 1 < 100) {
                            this.x++;
                        }
                    }
                    else {
                        if (this.x - 1 > 0) {
                            this.x--;
                        }

                    }
                }

            }


        }

    }

    private void updateSand(ArrayList<particle> parts){
        int px = this.x;
        int py = this.y;
        if(!fixed) {

            Boolean grounded = false;
            Boolean falling = false;
            Boolean groundedWall = false;
            int fall = 0;
            if (this.y < 99) falling = true;

            for (int i = 0; i < parts.size(); i++) {
                if (parts.get(i).getId() != this.id) {
                    if (this.y + 1 == parts.get(i).getY() && this.x == parts.get(i).getX()) {
                        if (parts.get(i).getType().equals("WALL")) groundedWall = true;
                        falling = false;
                    }
                }
            }
            if (falling == true) {
                this.y += 1;
                falling = false;
            }


            int dist = this.y;
            int account = 0;
            for (int i = 0; i < parts.size(); i++) {
                if (parts.get(i).getX() == this.x && parts.get(i).getY() > this.y) account++;
            }
            if (dist + account == 99 || groundedWall) grounded = true;

            Boolean CANLEFT = false;
            Boolean CANRIGHT = false;
            int fl = 0;
            int fr = 0;
            if (this.y < 99) {
                for (int i = 0; i < parts.size(); i++) {
                    if (parts.get(i).getId() != this.id) {
                        if (this.x - 1 == parts.get(i).getX() && this.y + 1 == parts.get(i).getY()) {
                            fl++;
                        }
                        if (this.x + 1 == parts.get(i).getX() && this.y + 1 == parts.get(i).getY()) {
                            fr++;


                        }
                    }
                }
                if (fr == 0) CANRIGHT = true;
                if (fl == 0) CANLEFT = true;
                if (CANLEFT && CANRIGHT) {
                    int rand = (int) Math.floor(Math.random() * 400);
                    if (rand < 100 && rand > 50) {
                        this.x--;
                    } else if (rand < 50) {
                        this.x++;
                    }

                } else if (CANLEFT && !CANRIGHT) {
                    this.x--;

                } else if (!CANLEFT && CANRIGHT) {
                    this.x++;

                }

            }
            if (px == this.x && py == this.y) {
                fixframe++;
            } else{
                fixframe = 0;
            }
            if(fixframe > 25){
                fixed = true;
            }
        }
        }


    public void draw(GraphicsContext gc){
        gc.setFill(c);
        if(this.type != "CONWAY") {
            gc.fillRect(this.x * 8, this.y * 8, 8, 8);
        } else if (this.type == "CONWAY" && alive == true){
            gc.fillRect(this.x * 8, this.y * 8, 8, 8);
        }
    }
}
