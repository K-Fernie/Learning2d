package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16/16 tile, this is the standard size for characters
    final int scale = 3; //This scales it to 48x48

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; //16 tiles horizontally
    public final int maxScreenRow = 12; //16 tiles vertically
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;
    //Thread is used for FPS, you need to implement runnable for a game thread
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        //Double buffered improves the games rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        //With this the game panel can be "focused to receive key input
        this.setFocusable(true);
    }
    public void startGameThread(){
        //pass gamepanel to the constructor
        gameThread = new Thread(this);
        gameThread.start();
    }
    //Game loop using the sleep method -----------------
//    @Override
//    public void run() {
//        //Draw interval is 60 Frames per second
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        //Create the game loop (core of the game), as long as the thread exists repeat
//        while(gameThread != null){
//            //nanoTime returns the current value of the running JVM high res time source in nanoseconds
//            //System.out.println("The game loop is running");
//            //1: UPDATE INFORMATION: such as character positions
//            update();
//            //2: DRAW: draw the screen with the updated information
//            //repaint() is how you call the paintComponent method
//            repaint();
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                //converts remaining time to milliseconds
//                remainingTime = remainingTime/1000000;
//                //this makes sure that the thread doesn't sleep if it doesn't need to
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }

    //Delta interval method for redrawing the screen
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread!= null){

            currentTime = System.nanoTime();

            delta+=(currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }

    //standard method to draw things on JPanel
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //dispose is a good practice to dispose of the graphics context and release any system resources
        //draw tile first then player on top of tile
        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
