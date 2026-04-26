package Scenes;

import Main.GamePanel;
import utils.Const;
import utils.JanButton;

import java.awt.*;

public class WinScreen extends Scene{

    JanButton startButton;
    int transitionTimer = 0;

    public WinScreen(GamePanel gamePanel) {
        super(gamePanel);
        startButton  = new JanButton(Const.screenWidth/2-300, Const.screenHeight/2+150,600, 100,gamePanel);
    }

    @Override
    public void enter() {
        System.out.println("Entering MainMenu");
    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {
        transitionTimer--;
        startButton.update();
        if(startButton.clicked)
            transitionTimer = 3;
        if(transitionTimer == 1)
            gamePanel.transitionScene(new LevelSelect(gamePanel));
    }

    @Override
    public void draw(Graphics2D g) {
        startButton.draw(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("YOU WIN!!!!!!",Const.screenWidth/2-250,Const.screenHeight/2-200);
    }
}
