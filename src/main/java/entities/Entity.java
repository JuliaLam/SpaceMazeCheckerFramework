package entities;

import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public @MonotonicNonNull BufferedImage right;
    public @MonotonicNonNull BufferedImage left;
    public @MonotonicNonNull Rectangle solidArea;
    public String direction = ""; //enum? how
    public boolean collisionOn = false;

    @EnsuresNonNull("this.solidArea")
    Entity(int x, int y, int width, int height){
        this.solidArea = new Rectangle(x, y, width, height);
    }

}
