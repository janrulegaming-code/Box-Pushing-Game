package Tiles;

import Main.GamePanel;
import Scenes.Level;

import javax.swing.*;
import java.awt.*;

public class Wall extends TileObject{
    public Wall(Level level) {
        super(level);
    }

    @Override
    public void updateTurn(int keyPressed) {

    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.drawImage((new ImageIcon("res/wall.png")).getImage(), x, y, size, size, null);
    }
}
