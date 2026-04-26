package Scenes;

import Main.GamePanel;
import Main.KeyHandler;
import Main.MouseHandler;

import java.awt.*;

public abstract class Scene {
    GamePanel gamePanel;
    MouseHandler mouseH;
    KeyHandler keyH;

    public Scene(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mouseH = gamePanel.mouseH;
        keyH = gamePanel.keyH;
    }

    public abstract void enter();
    public abstract void exit();

    public abstract void update();
    public abstract void draw(Graphics2D g);
}
