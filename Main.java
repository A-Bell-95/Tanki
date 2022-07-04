package com.company;

import javax.swing.*;
import java.awt.*;


public class Main extends JPanel {

    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;
    final int OBJECT_SIZE = 64;

    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;

    String[][] Objects ={
            {"W","W","S","G","G","R","R","R","B"},
            {"W","W","S","B","B","R","R","B","B"},
            {"S","S","S","B","B","R","R","R","B"},
            {"R","B","R","R","B","B","B","R","R"},
            {"R","B","R","R","R","R","R","R","B"},
            {"R","B","R","R","B","R","R","R","R"},
            {"R","R","R","R","R","R","R","G","G"},
            {"W","S","R","B","B","R","R","S","W"},
            {"W","W","R","R","R","R","R","S","W"}
        };
    int direction = UP;

    int bulletX = 320;
    int bulletY = 320;

    int tankX = 256;
    int tankY = 256;

    void move(int direction) throws Exception{
        this.direction = direction;

        if (direction == UP){
            tankY--;
        } else if (direction == DOWN) {
            tankY++;
        } else if (direction == LEFT) {
            tankX--;
        } else if (direction == RIGHT) {
            tankX++;
        }
        Thread.sleep(33);
        repaint();
    }

    void fire() throws Exception{
        bulletX = tankX + 25;
        bulletY = tankY + 25;

        while (bulletX > 0 && bulletX < BF_WIDTH && bulletY > 0 && bulletY < BF_HEIGHT){
            switch (direction) {
                case UP -> bulletY--;
                case DOWN -> bulletY++;
                case LEFT -> bulletX--;
                case RIGHT -> bulletX++;
            }
            Thread.sleep(10);
             repaint();
        }
        bulletX = -100;
        bulletY = -100;
        repaint();
    }

    void runTheGame() throws Exception{
        fire();

    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.runTheGame();
    }
    Main() {
        JFrame frame = new JFrame("Mario Tanks");
        frame.setMinimumSize(new Dimension(BF_WIDTH + 16,BF_HEIGHT + 40));
        frame.getContentPane().add(this);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color cc = new Color(146, 37, 37);
        Color cf = new Color(190, 197, 197);
        Color cg = new Color(10,140,15);
        //Draw objects
        for (int i = 0; i < Objects.length; i++) {
            for (int j = 0; j < Objects.length; j++) {
                switch (Objects[i][j]) {
                    case "W" -> g.setColor(Color.CYAN);
                    case "B" -> g.setColor(cc);
                    case "R" -> g.setColor(cf);
                    case "G" -> g.setColor(cg);
                    case "S" -> g.setColor(Color.ORANGE);
                }
                g.fillRect(OBJECT_SIZE * i, OBJECT_SIZE * j, OBJECT_SIZE, OBJECT_SIZE);
            }
        }
        //Draw tank
        g.setColor(Color.RED);
        g.fillRect(tankX, tankY, OBJECT_SIZE, OBJECT_SIZE);
        g.setColor(Color.WHITE);
        g.drawLine(tankX + 37, tankY + 50, tankX + 42, tankY + 60);
        g.drawLine(tankX + 37, tankY + 50, tankX + 32, tankY + 60);
        g.drawLine(tankX + 32, tankY + 60, tankX + 27, tankY + 50);
        g.drawLine(tankX + 27, tankY + 50, tankX + 22, tankY + 60);
        //Draw Gun
        g.setColor(Color.YELLOW);
        if (direction == UP) {
            g.fillRect(tankX + 20, tankY, 24, 34);
        } else if (direction == DOWN) {
            g.fillRect(tankX + 20, tankY + 30, 24, 34);
        } else if (direction == LEFT) {
            g.fillRect(tankX, tankY + 20, 34, 24);
        } else if (direction == RIGHT) {
            g.fillRect(tankX + 30, tankY + 20, 34, 24);
        }
        //Draw bullet
        g.setColor(Color.BLACK);
        g.fillRect(bulletX, bulletY, 14, 14);

    }
}

