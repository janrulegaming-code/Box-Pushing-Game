package Tiles;

import Main.GamePanel;
import Scenes.Level;

import javax.swing.*;
import java.awt.*;

public class Player extends TileObject{
    public Player(Level level) {
        super(level);
    }

    @Override
    public void updateTurn(int keyPressed) {
        if (turnUsed) {return;}
        int x = 0;
        int y = 0;
        for(int r = 0; r < level.getTileRow(); r++){
            for(int c = 0; c < level.getTileCol(); c++){
                if(level.tilemap.get(r).get(c).tileObjects.contains(this)){
                    x = r;
                    y = c;
                    break;
                }
            }
        }
        turnUsed = true;
        int enteringX = 0;
        int enteringY = 0;
        if(keyPressed == 0){
            enteringX = x;
            enteringY = y-1;
            boolean pushableBox = true;
            if(!checkIfHasTile(enteringX, enteringY, new Wall(level))){
                if(checkIfHasTile(enteringX, enteringY, new Box(level))){
                    Box found;
                    for (TileObject tile : level.tilemap.get(enteringX).get(enteringY).tileObjects) {
                        if (tile instanceof Box) {
                            found = (Box) tile;
                            pushableBox = found.pushed(keyPressed);
                            break;
                        }
                    }
                }
                if(pushableBox){
                    level.tilemap.get(enteringX).get(enteringY).tileObjects.add(this);
                    level.tilemap.get(x).get(y).tileObjects.remove(this);
                }
            }
        }else if(keyPressed == 1){
            enteringX = x+1;
            enteringY = y;
            boolean pushableBox = true;
            if(!checkIfHasTile(enteringX, enteringY, new Wall(level))){
                if(checkIfHasTile(enteringX, enteringY, new Box(level))){
                    Box found;
                    for (TileObject tile : level.tilemap.get(enteringX).get(enteringY).tileObjects) {
                        if (tile instanceof Box) {
                            found = (Box) tile;
                            pushableBox = found.pushed(keyPressed);
                            break;
                        }
                    }
                }
                if(pushableBox){
                    level.tilemap.get(enteringX).get(enteringY).tileObjects.add(this);
                    level.tilemap.get(x).get(y).tileObjects.remove(this);
                }
            }
        }else if(keyPressed == 2){
            enteringX = x;
            enteringY = y+1;
            boolean pushableBox = true;
            if(!checkIfHasTile(enteringX, enteringY, new Wall(level))){
                if(checkIfHasTile(enteringX, enteringY, new Box(level))){
                    Box found;
                    for (TileObject tile : level.tilemap.get(enteringX).get(enteringY).tileObjects) {
                        if (tile instanceof Box) {
                            found = (Box) tile;
                            pushableBox = found.pushed(keyPressed);
                            break;
                        }
                    }
                }
                if(pushableBox){
                    level.tilemap.get(enteringX).get(enteringY).tileObjects.add(this);
                    level.tilemap.get(x).get(y).tileObjects.remove(this);
                }
            }
        }else if(keyPressed == 3){
            enteringX = x-1;
            enteringY = y;
            boolean pushableBox = true;
            if(!checkIfHasTile(enteringX, enteringY, new Wall(level))){
                if(checkIfHasTile(enteringX, enteringY, new Box(level))){
                    Box found;
                    for (TileObject tile : level.tilemap.get(enteringX).get(enteringY).tileObjects) {
                        if (tile instanceof Box) {
                            found = (Box) tile;
                            pushableBox = found.pushed(keyPressed);
                            break;
                        }
                    }
                }
                if(pushableBox){
                    level.tilemap.get(enteringX).get(enteringY).tileObjects.add(this);
                    level.tilemap.get(x).get(y).tileObjects.remove(this);
                }
            }
        }
        System.out.println("KeyPressed: "+keyPressed);
        System.out.println("Player moved to: " + x + "," + y);

    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.drawImage((new ImageIcon("res/player.png")).getImage(), x, y, size, size, null);
    }
}
