package entities;


import main.MainPanel;
import main.KeyHandler;
import org.checkerframework.checker.initialization.qual.UnderInitialization;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    //MainPanel gp;
    KeyHandler keyH;
    private final int baseObjectSize;
    @EnsuresNonNull("this.solidArea")
    public Player(MainPanel gp, KeyHandler keyH, final int baseObjectSize){
        super(6, 2, 30,30);
        //this.gp = gp;
        this.keyH = keyH;
        this.baseObjectSize = baseObjectSize;
    }

    public void setDefaultValues(entities.Player this){
        x = 1078;
        y = 833;
        speed = 4;
        direction = "up";

    }

    public void getPlayerImage() {
        try{
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/astronaut.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/astronautright.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setValues(){
        setDefaultValues();
        getPlayerImage();
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            //gp.cChecker.checkTile(this);
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }

            }
        }
    }
    public void draw(Player this, Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "up":
                image = left;
                break;
            case "down":
                image = left;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }
        g2.drawImage(image,x,y, baseObjectSize, baseObjectSize, null);

    }


}