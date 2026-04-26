package utils;

import Main.GamePanel;
import Main.MouseHandler;
import Scenes.Scene;

import javax.swing.*;
import java.awt.*;

public class JanButton {
    public int x;
    public int y;
    public int width;
    public int height;
    public int clickTimer = 0;
    public ImageIcon unpressed;
    public ImageIcon pressed;
    public MouseHandler mouseH;
    public boolean clicked = false;
    public JanButton(int x, int y, int width, int height, ImageIcon unpressed, ImageIcon pressed , GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mouseH = gamePanel.mouseH;
        this.unpressed = unpressed;
        this.pressed = pressed;
    }

    public JanButton(int x, int y, int width, int height, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mouseH = gamePanel.mouseH;
    }

    public void update() {
        clickTimer--;
        clicked = false;
        if(mouseH.mouseX >= x && mouseH.mouseX <= x+width && mouseH.mouseY >= y && mouseH.mouseY <= y+height && mouseH.mousePressed) {
            clicked = true;
            clickTimer = 2;
        }
    }
    public void draw(Graphics2D g) {
        if(unpressed != null &&  pressed != null) {
            if(clicked || clickTimer > 0) {
                g.drawImage(pressed.getImage(), x, y, width, height, null);
            }
            else
                g.drawImage(unpressed.getImage(), x, y, width, height, null);
        }
        else{
            if(clicked || clickTimer > 0) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(x, y, width, height);
            }
            else {
                g.setColor(Color.GRAY);
                g.fillRect(x, y, width, height);
            }
        }
    }
}
