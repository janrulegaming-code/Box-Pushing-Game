package Scenes;

import Main.GamePanel;
import utils.Const;
import utils.JanButton;

import java.awt.*;
import java.util.ArrayList;

public class LevelSelect extends Scene{

    ArrayList<JanButton> levels = new ArrayList<>();
    int transitionTimer = 0;
    int selectedLevel;

    public LevelSelect(GamePanel gamePanel) {
        super(gamePanel);
        for(int i = 0; i < 5; i++){
            levels.add(new JanButton(200 + 110*i,200,100,100,gamePanel));
        }
    }

    @Override
    public void enter() {
        System.out.println("Entering LevelSelect");
    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {
        transitionTimer--;
        for(int i = 0; i < levels.size(); i++){
            levels.get(i).update();
            if(levels.get(i).clicked){
                selectedLevel = i+1;
                transitionTimer = 3;
            }
        }
        if(transitionTimer == 1)
            gamePanel.transitionScene(new Level(gamePanel, selectedLevel));
    }

    @Override
    public void draw(Graphics2D g) {
        for(int i = 0; i < levels.size(); i++){
            levels.get(i).draw(g);
        }
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("Level Select",Const.screenWidth/2-250,Const.screenHeight/2-200);
        for(int i = 0; i < levels.size(); i++){
            g.drawString((i+1)+"",250 + 110*i,260);
        }
    }
}
