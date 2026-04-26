package Main;

import Scenes.MainMenu;
import Scenes.Scene;
import utils.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    Thread gameThread;
    public MouseHandler mouseH = new MouseHandler();
    public KeyHandler keyH = new KeyHandler();
    public Scene currentScene = new MainMenu(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(Const.screenWidth,Const.screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        while(gameThread.isAlive()){
            double drawInterval = 1000000000.0 / Const.FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            while (gameThread.isAlive()) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime)/drawInterval;
                lastTime = currentTime;
                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
            }
        }
    }

    public void update(){
        currentScene.update();

        keyH.update();
        mouseH.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        currentScene.draw(g2d);
    }

    public void transitionScene(Scene newScene){
        currentScene.exit();
        newScene.enter();
        currentScene = newScene;
    }
}
