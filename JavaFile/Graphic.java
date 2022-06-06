package src.snake;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Graphic extends JPanel implements ActionListener{
    private Timer t = new Timer(50, this);
    public String state;

    private Snake snake;
    private Food food;
    private Game game;


    public Graphic(Game g) {
        t.start();
        state = "START";

        game = g;
        snake = g.getPlayer();
        food = g.getFood();

        //
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, (Game.width + 47) * Game.dimension, (Game.heigth + 11) * Game.dimension);

        if(state == "START") {
            //Start
            g2d.setColor(Color.WHITE);
            g2d.drawString("Press any key to start", ((Game.width + 100) * Game.dimension) / 4, ((Game.heigth + 50) * Game.dimension) /4);
        } else if (state == "RUN") {
            // Food
            g2d.setColor(Color.red);
            g2d.fillRect(food.getX() * Game.dimension, food.getY() * Game.dimension, Game.dimension, Game.dimension);

            //Snake
            g2d.setColor(Color.white);
            for (Rectangle r : snake.getBody()) {
                g2d.fill(r);   
            }

        } else if (state == "END") {
            //GAME OVER
            g2d.setColor(Color.white);
            g2d.drawString("YOUR SCORE : " + (snake.getBody().size() - 3), (((Game.width + 30) * Game.dimension) / 2) + 80, ((Game.heigth + 13) * Game.dimension) /2);
            g2d.drawString("Press R to Restart", (((Game.width + 30) * Game.dimension) / 2) + 80, (((Game.heigth + 15) * Game.dimension) / 2));
            g2d.drawString("Press Q to Quit", (((Game.width + 30) * Game.dimension) / 2) + 80, (((Game.heigth + 17) * Game.dimension) / 2));
        } else if (state == "WON") {
            g2d.setColor(Color.white);
            g2d.drawString("You Win My Game Congrtas please end me", (((Game.width + 30) * Game.dimension) / 2) + 80, ((Game.heigth + 13) * Game.dimension) /2);
            g2d.drawString("Press R to Restart", (((Game.width + 30) * Game.dimension) / 2) + 80, (((Game.heigth + 15) * Game.dimension) / 2));
            g2d.drawString("Press SPACEBAR to Continue", (((Game.width + 30) * Game.dimension) / 2) + 80, (((Game.heigth + 17) * Game.dimension) / 2));
            g2d.drawString("Press Q to Quit", (((Game.width + 30) * Game.dimension) / 2) + 80, (((Game.heigth + 19) * Game.dimension) / 2));
        } else if (state == "PAUSE") {
            g2d.setColor(Color.white);
            g2d.drawString("Game is Paused...", (((Game.width + 30) * Game.dimension) / 2) + 80, ((Game.heigth + 13) * Game.dimension) /2);
            g2d.drawString("YOUR SCORE : " + (snake.getBody().size() - 3), (((Game.width + 30) * Game.dimension) / 2) + 80, ((Game.heigth + 15) * Game.dimension) /2);
        }
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        game.update();
    }
    
}
