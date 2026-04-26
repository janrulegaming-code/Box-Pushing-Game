package Tiles;

import Main.GamePanel;
import Scenes.Level;
import utils.Const;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tile {
    int x;
    int y;
    int tileX;
    int tileY;
    int size;
    Level level;
    public ArrayList<TileObject> tileObjects = new ArrayList<TileObject>();

    public Tile(Level level, int tileX, int tileY, ArrayList<TileObject> tileObjects) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.level = level;
        this.tileObjects = tileObjects;
        int height = level.getTileCol();
        int width = level.getTileRow();

        double sizeWidth = (double) Const.screenWidth /width;
        double sizeHeight = (double) Const.screenHeight /height;

        boolean stickToX = true;
        if(sizeWidth > sizeHeight){
            size = Math.toIntExact(Math.round(sizeHeight));
        } else {
            stickToX = false;
            size = Math.toIntExact(Math.round(sizeWidth));
        }

        if(stickToX) {
            double halfScreen = (double) Const.screenWidth / 2;
            int numOfTilesX = level.getTileRow();
            x = (int) (halfScreen - (double) (numOfTilesX * size) / 2) + size*tileX;
            y = size * tileY;
        } else {
            double halfScreen = (double) Const.screenHeight / 2;
            int numOfTilesY = level.getTileCol();
            y = (int) (halfScreen - (double) (numOfTilesY * size) / 2) + size*tileY;
            x = size * tileX;

        }

    }

    public void updateTurn(int keyPressed) {
        System.out.println("Tile turn updated: "+keyPressed);
        for(int i = 0; i < tileObjects.size(); i++) {
            tileObjects.get(i).updateTurn(keyPressed);
        }
    }

    public void resetTurns(){
        for(int i = 0; i < tileObjects.size(); i++) {
            tileObjects.get(i).endTurn();
        }
    }

    public void draw(Graphics2D g){
        g.drawImage((new ImageIcon("res/tile.png")).getImage(), x, y, size, size, null);
        for(int i = 0; i < tileObjects.size(); i++){
            tileObjects.get(i).draw(g,x,y,size);
        }
    }
}
