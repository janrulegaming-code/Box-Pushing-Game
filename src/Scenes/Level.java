package Scenes;

import Main.GamePanel;
import Tiles.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Level extends Scene{
    int tileRow;
    int tileCol;
    public List<List<Tile>> tilemap = new ArrayList<>();
    int level;

    public Level(GamePanel gamePanel, int level){
        super(gamePanel);
        this.level = level;

        try{
            BufferedReader br = new BufferedReader(new FileReader("res/level" +  level + ".map"));
            int colCount = 0;
            for(String line; (line = br.readLine()) != null;){
                line = line.trim();
                tileRow = line.length();
                colCount++;
            }
            tileCol = colCount;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader("res/level" +  level + ".map"));
            int colCount = 0;
            boolean columnsCreated = false;
            for(String line; (line = br.readLine()) != null;){
                line = line.trim();

                if(!columnsCreated) {
                    for(int i = 0; i < tileRow; i++){
                        List<Tile> columnTiles = new ArrayList<>();
                        tilemap.add(columnTiles);
                    }
                }
                for(int i = 0; i < tileRow; i++){
                    ArrayList<TileObject> tileObjects = new ArrayList<>();
                    if(line.substring(i, i + 1).equals("#")){
                        tileObjects.add(new Wall(this));
                    }
                    if(line.substring(i, i + 1).equals("p")){
                        tileObjects.add(new Player(this));
                    }
                    if(line.substring(i, i + 1).equals("b")){
                        tileObjects.add(new Box(this));
                    }
                    if(line.substring(i, i + 1).equals("g")){
                        tileObjects.add(new Goal(this));
                    }
                    tilemap.get(i).add(new Tile(this, i, colCount, tileObjects));
                }
                columnsCreated = true;
                colCount++;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {
        if(keyH.upPressed){
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).updateTurn(0);
                }
            }
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).resetTurns();
                }
            }
        }
        else if(keyH.rightPressed){
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).updateTurn(1);
                }
            }
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).resetTurns();
                }
            }
        }
        else if(keyH.downPressed){
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).updateTurn(2);
                }
            }
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).resetTurns();
                }
            }
        }
        else if(keyH.leftPressed){
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).updateTurn(3);
                }
            }
            for(int r = 0; r < tileRow; r++){
                for(int c = 0; c < tileCol; c++){
                    tilemap.get(r).get(c).resetTurns();
                }
            }
        }
        else if(keyH.rKeyPressed){
            gamePanel.transitionScene(new LevelSelect(gamePanel));
        }
        int finishedGoals = 0;
        int goals = 0;
        for(int r = 0; r < tileRow; r++){
            for(int c = 0; c < tileCol; c++){
                boolean goalHere = tilemap.get(r).get(c).tileObjects.stream().anyMatch(tileObject ->  tileObject instanceof Goal);
                boolean boxHere = tilemap.get(r).get(c).tileObjects.stream().anyMatch(tileObject ->  tileObject instanceof Box);
                if(goalHere)
                    goals++;
                if(goalHere && boxHere){
                    finishedGoals++;
                }
            }
        }
        if(finishedGoals == goals){
            gamePanel.transitionScene(new WinScreen(gamePanel));
        }
    }

    @Override
    public void draw(Graphics2D g) {
        for(int r = 0; r < tileRow; r++){
            for(int c = 0; c < tileCol; c++){
                tilemap.get(r).get(c).draw(g);
            }
        }
        if(level == 1){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 35));
            g.drawString("WASD to move", 30, 90);
            g.drawString("R to exit level", 30, 130);
            g.drawString("Push the box", 30, 170);
            g.drawString("onto the button", 30, 200);
            g.drawString("to win", 30, 230);
        }
    }

    public int getTileRow() {
        return tileRow;
    }

    public void setTileRow(int tileRow) {
        this.tileRow = tileRow;
    }

    public int getTileCol() {
        return tileCol;
    }

    public void setTileCol(int tileCol) {
        this.tileCol = tileCol;
    }
}