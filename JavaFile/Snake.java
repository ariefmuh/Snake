package src.snake;

import java.util.ArrayList;
import java.awt.Rectangle;
public class Snake {
    private ArrayList<Rectangle> body;

    private String move;

    public Snake() {
        // MAKING THE SNAKE
        move = "NOTHING";
        body = new ArrayList<>();
        Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
        temp.setLocation(((Game.width/2) + 20) * Game.dimension, ((Game.heigth/2) + 9) * Game.dimension);
        body.add(temp);

        temp = new Rectangle(Game.dimension, Game.dimension);
        temp.setLocation(((Game.width/2) + 19) * Game.dimension, ((Game.heigth/2) + 9) * Game.dimension);
        body.add(temp);

        temp = new Rectangle(Game.dimension, Game.dimension);
        temp.setLocation(((Game.width/2) + 18) * Game.dimension, ((Game.heigth/2) + 9) * Game.dimension);
        body.add(temp);
    }

    public void move() {
        // SNAKE MOVEMENT

        if(move != "NOTHING") {
            Rectangle first = body.get(0);
            Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
        //if (player.getX() < 0 || player.getX() >= (width + 45) * dimension || player.getY() < 0 || player.getY() >= (heigth + 9) * dimension) {
            
            if(move == "UP" && getY() < 1) temp.setLocation(first.x, (Game.heigth + 10) * Game.dimension);
            else if (move == "DOWN" && getY() > (Game.heigth + 9) * Game.dimension) {temp.setLocation(first.x, 0);}
            else if(move == "LEFT" && getX() < 1) temp.setLocation((Game.width + 45) * Game.dimension, first.y);
            else if (move == "RIGHT" && getX() > (Game.width + 44) * Game.dimension) {temp.setLocation(0, first.y);}
            else if(move == "UP") temp.setLocation(first.x, first.y - Game.dimension);
            else if (move == "DOWN") {temp.setLocation(first.x, first.y + Game.dimension);}
            else if (move == "RIGHT") {temp.setLocation(first.x + Game.dimension, first.y);}
            else if (move == "LEFT") {temp.setLocation(first.x - Game.dimension, first.y);}

            body.add(0, temp);
            body.remove(body.size()-1);
        }
    }

    public void collisionMove() {
        
    }

    public void grow() {
        // IF SNAKE EAT FOOD
        Rectangle first = body.get(0);
        Rectangle temp = new Rectangle(Game.dimension, Game.dimension);

        if(move == "UP") temp.setLocation(first.x, first.y - Game.dimension);
        else if (move == "DOWN") temp.setLocation(first.x, first.y + Game.dimension);
        else if (move == "RIGHT") temp.setLocation(first.x + Game.dimension, first.y);
        else if (move == "LEFT") temp.setLocation(first.x - Game.dimension, first.y);
        body.add(0, temp);
    }


    public ArrayList<Rectangle> getBody() {
        return body;
    }

    public void setBody(ArrayList<Rectangle> body) {
        this.body = body;
    }

    public int getX() {
        return body.get(0).x;
    }
    public int getY() {
        return body.get(0).y;
    }

    public void up() {
        move = "UP";
    }
    public void right() {
        move = "RIGHT";
    }
    public void down() {
        move = "DOWN";
    }
    public void left() {
        move = "LEFT";
    }
    public String getMove() {
        return move;
    }
}