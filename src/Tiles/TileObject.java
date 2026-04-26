package Tiles;

import Main.GamePanel;
import Scenes.Level;

import java.awt.*;

public abstract class TileObject {
    int zIndex = 0;
    Level level;
    boolean turnUsed = false;


    public TileObject(Level level) {
        this.level = level;
    }

    public void endTurn(){
        turnUsed = false;
    }

    public abstract void updateTurn(int keyPressed);

    public abstract void draw(Graphics2D g, int x, int y, int size);

    protected boolean checkIfHasWall(int x, int y) {
        return level.tilemap.get(x).get(y).tileObjects.stream().anyMatch(tileObject -> tileObject instanceof Wall);
    }

    protected boolean checkIfHasTile(int x, int y, TileObject tileObj) {
        //tileObj.getClass().getName()
        return level.tilemap.get(x).get(y).tileObjects.stream().anyMatch(tileObject -> tileObject.getClass().getName().equals(tileObj.getClass().getName()));
    }

}
