package Tiles;

import Main.GamePanel;
import Scenes.Level;

import javax.swing.*;
import java.awt.*;

public class Box extends TileObject{
    public Box(Level level) {
        super(level);
    }

    @Override
    public void updateTurn(int keyPressed) {

    }

    public boolean pushed(int dir){
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
        if(dir == 0){
            if(checkIfHasTile(x,y-1,new Wall(level))||checkIfHasTile(x,y-1,new Box(level))||checkIfHasTile(x,y-1, new Player(level))){
                return false;
            }
            else{
                level.tilemap.get(x).get(y-1).tileObjects.add(this);
                level.tilemap.get(x).get(y).tileObjects.remove(this);
                return true;
            }
        }
        if(dir == 1){
            if(checkIfHasTile(x+1,y,new Wall(level))||checkIfHasTile(x+1,y,new Box(level))|| checkIfHasTile(x+1,y,new Player(level))){
                return false;
            }
            else{
                level.tilemap.get(x+1).get(y).tileObjects.add(this);
                level.tilemap.get(x).get(y).tileObjects.remove(this);
                return true;
            }
        }
        if(dir == 2){
            if(checkIfHasTile(x,y+1,new Wall(level))||checkIfHasTile(x,y+1,new Box(level))||checkIfHasTile(x,y+1,new Player(level))){
                return false;
            }
            else{
                level.tilemap.get(x).get(y+1).tileObjects.add(this);
                level.tilemap.get(x).get(y).tileObjects.remove(this);
                return true;
            }
        }
        if(dir == 3){
            if(checkIfHasTile(x-1,y,new Wall(level))||checkIfHasTile(x-1,y,new Box(level))|| checkIfHasTile(x-1,y,new Player(level))){
                return false;
            }
            else{
                level.tilemap.get(x-1).get(y).tileObjects.add(this);
                level.tilemap.get(x).get(y).tileObjects.remove(this);
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.drawImage((new ImageIcon("res/box.png")).getImage(), x, y, size, size, null);
    }
}
