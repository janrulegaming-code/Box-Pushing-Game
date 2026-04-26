package Tiles;

import Main.GamePanel;
import Scenes.Level;

import javax.swing.*;
import java.awt.*;

public class Goal extends TileObject{

    public Goal(Level level) {
        super(level);
    }

    @Override
    public void updateTurn(int keyPressed) {

    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.drawImage((new ImageIcon("res/goal.png")).getImage(), x, y, size, size, null);
    }
}
