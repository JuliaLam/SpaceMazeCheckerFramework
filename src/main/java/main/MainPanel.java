package main;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import tile.TileManager;
import entities.Player;

import javax.swing.*;
import java.awt.*;
final public class MainPanel extends JPanel implements Runnable {
    public final int baseObjectSize = 49;
    public final int maxRows = 18;
    public final int maxColumns = 24;
    public final int screenWidth = baseObjectSize * maxColumns;
    public final int screenHeight = baseObjectSize * maxRows;
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    @MonotonicNonNull Thread gameThread = null;
    Player player = new Player(this, keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);


    public MainPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            update();
            setValues();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setValues(){player.setValues();}
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }



}
