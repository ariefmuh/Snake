
package src.snake;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game 
implements KeyListener{

    private Snake player;
    private Food food;
    private Graphic graphics;
    private JFrame window;
    private int winCondition = 10; // penambahan

    public static final int width = 28;
    public static final int heigth = 28;
    public static final int dimension = 20;

    public Game() {
        window = new JFrame();
        player = new Snake();
        food = new Food(player);
        graphics = new Graphic(this);

        window.add(graphics);

        window.setTitle("Ular-Ularan By : Kelompok 4");
        window.setSize(((width + 47) * dimension), ((heigth + 13) * dimension));
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start() {
        graphics.state = "RUN";
    }

    public void update() {
        if (graphics.state == "RUN") {
            if(checkFoodCollision()) {
                player.grow();
                food.randomSpawn(player);
            } 
            else if (player.getBody().size() == winCondition) {
                graphics.state = "WON";
            } 
            else if (selfCollisionCheck()) {
                graphics.state = "END";
            } 
            else {
                player.move();
            }
        }
    }

    // public boolean checkWallCollision() {
    //     if (player.getX() < 0 || player.getX() >= (width + 45) * dimension || player.getY() < 0 || player.getY() >= (heigth + 9) * dimension) {
    //         return true;
    //     }

    //     return false;
    // }

    private boolean checkFoodCollision() {
        if (player.getX() == food.getX() * dimension && player.getY() == food.getY() * dimension) {
            return true;
        }
        return false;
    }
    
    private boolean selfCollisionCheck() {
        for(int i = 1; i < player.getBody().size(); i++) {
            if(player.getX() == player.getBody().get(i).x && player.getY() == player.getBody().get(i).y) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
            if (graphics.state == "RUN") {
                if (keyCode == KeyEvent.VK_W && player.getMove() != "DOWN") {
                    player.up();
                } else if (keyCode == KeyEvent.VK_A && player.getMove() != "RIGHT") {
                    player.left();
                } else if (keyCode == KeyEvent.VK_S && player.getMove() != "UP") {
                    player.down();
                }else if (keyCode == KeyEvent.VK_D && player.getMove() != "LEFT") {
                    player.right();
                }else if (keyCode == KeyEvent.VK_P) {
                    graphics.state = "PAUSE";
                }
            } else if (graphics.state == "END" || graphics.state == "WON") {
                if (keyCode == KeyEvent.VK_R) {
                    window.dispose();
                    Game newGame = new Game();
                    // window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                }
                else if (keyCode == KeyEvent.VK_Q) {
                    System.exit(0);
                }
                else if (keyCode == KeyEvent.VK_SPACE) {
                    graphics.state = "Run";
                    winCondition = -1;
                }
            } else if (graphics.state == "PAUSE") {
                if (keyCode == KeyEvent.VK_P) {
                    graphics.state = "RUN";
                }
            } 
            else {
                this.start();
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }


    public Snake getPlayer() {
        return player;
    }


    public void setPlayer(Snake player) {
        this.player = player;
    }


    public Food getFood() {
        return food;
    }


    public void setFood(Food food) {
        this.food = food;
    }


    public JFrame getWindow() {
        return window;
    }


    public void setWindow(JFrame window) {
        this.window = window;
    }
}
