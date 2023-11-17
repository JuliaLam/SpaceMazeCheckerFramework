package tile;
import main.MainPanel;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.initialization.qual.UnderInitialization;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    public Tile[] tile;
    public int[][] mapTileNum;

    private final int baseObjectSize, maxColumns, maxRows;
    
    public TileManager(final int baseObjectSize, final int maxColumns, final int maxRows){
        this.baseObjectSize = baseObjectSize;
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
        getTileImage();
        loadMap("/maps/map.txt");

    }


    @EnsuresNonNull("this.tile")
    public void getTileImage(@UnderInitialization TileManager this) {
        tile = new Tile[10];
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/space.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rocks.png")));
            tile[1].collision = true;
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    @EnsuresNonNull("this.mapTileNum")
    public void loadMap(@UnderInitialization TileManager this, String filePath) {
            mapTileNum = new int[maxColumns][maxRows];
            try {
                InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(filePath));
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0;
                int row = 0;

                while (col < maxColumns && row < maxRows) {

                    String line = br.readLine();
                    @SuppressWarnings("nullness") //br is nonempty so .readLine() will never return null
                    @NonNull String nonNullLine = line;


                    while (col < maxColumns) {
                        String[] numbers = nonNullLine.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        col++;
                    }

                    if (col == maxColumns) {
                        col = 0;
                        row++;
                    }

                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < maxColumns && row < maxRows){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, baseObjectSize, baseObjectSize, null);
            col++;
            x+= baseObjectSize;

            if(col == maxColumns){
                col = 0;
                x = 0;
                row++;
                y += baseObjectSize;

            }
        }

    }

}

