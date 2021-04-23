package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class GAmeField extends JPanel implements ActionListener {

    Image backGround = new ImageIcon("BackGround1.png").getImage();

    Image part = new ImageIcon("part.png").getImage();
    Image part1 = new ImageIcon("part1.png").getImage();
    Image cherry = new ImageIcon("cherry1.png").getImage();

    JFrame frame;
    int h;
    int w;

    private final int[] temp = new int[2];//для изм координат в функции
    Image dot, dotGhostRed, dotGhostBlue, dotGhostPink, dotGhostOrange, dotLivePm;
    Image dotSuperCell = new ImageIcon("superCell.png").getImage();

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean isCherryAvailable = false;

    private int superCellX = (int) (Math.random()*(w-32));//755;////позволяет есть призраков
    private int superCellY = (int) (Math.random()*(h-32));//299;//

    private int pmx = 755;
    private int pmy = 299;
    int berryX = 755;
    int berryY = 455;
    int speedPM = 13;

    private int ghostRedX = 755;
    private int ghostRedY = 359;
    private int speedRed = 5;

    private int ghostBlueX = 755;
    private int ghostBlueY = 419;
    private int speedBlue = 5;

    private int ghostPinkX = 695;
    private int ghostPinkY = 359;
    private int speedPink = 5;

    private int ghostOrangeX = 815;
    private int ghostOrangeY = 359;
    private int speedOrange = 5;

    private int score = 0;
    private int scoreForGhosts = 0;
    int livesPM = 3;

    int [][]pmColl = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26,  0, 0, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26,  0},
            {0, 52, 0, 0, 0, 0, 52, 0, 0, 0, 0, 0, 52, 0, 0, 52, 0, 0, 0, 0, 0, 52, 0, 0, 0, 0, 52, 0},
            {0, 78, 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 78, 0, 0, 78, 0, 0, 0, 0, 0, 78, 0, 0, 0, 0, 78, 0},
            {0, 104, 0, 0, 0, 0, 104, 0, 0, 0, 0, 0, 104, 0, 0, 104, 0, 0, 0, 0, 0, 104, 0, 0, 0, 0, 104, 0},
            {0, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 0},
            {0, 156, 0, 0, 0, 0, 156, 0, 0, 156, 0, 0, 0, 0, 0, 0, 0, 0, 156, 0, 0, 156, 0, 0, 0, 0, 156, 0},
            {0, 182, 0, 0, 0, 0, 182, 0, 0, 182, 0, 0, 0, 0, 0, 0, 0, 0, 182, 0, 0, 182, 0, 0, 0, 0, 182, 0},
            {0, 208, 208, 208, 208, 208, 208, 0, 0, 208, 208, 208, 208, 0, 0, 208, 208, 208, 208, 0, 0, 208, 208, 208, 208, 208, 208, 0},
            {0, 0, 0, 0, 0, 0, 234, 0, 0, 0, 0, 0, 234, 0, 0, 234, 0, 0, 0, 0, 0, 234, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 260, 0, 0, 0, 0, 0, 260, 0, 0, 260, 0, 0, 0, 0, 0, 260, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 286, 0, 0, 286, 286, 286, 286, 286, 286, 286, 286, 286, 286, 0, 0, 286, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 312, 0, 0, 312, 0, 0, 0, 0, 0, 0, 0, 0, 312, 0, 0, 312, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 338, 0, 0, 338, 0,-1,-1,-1,-1,-1,-1, 0, 338, 0, 0, 338, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 364, 364, 364, 364, 0,-1,-1,-1,-1,-1,-1, 0, 364, 364, 364, 364, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 390, 0, 0, 390, 0,-1,-1,-1,-1,-1,-1, 0, 390, 0, 0, 390, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 416, 0, 0, 416, 0, 0, 0, 0, 0, 0, 0, 0, 416, 0, 0, 416, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 442, 0, 0, 442, 442, 442, 442, 442, 442, 442, 442, 442, 442, 0, 0, 442, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 468, 0, 0, 468, 0, 0, 0, 0, 0, 0, 0, 0, 468, 0, 0, 468, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 494, 0, 0, 494, 0, 0, 0, 0, 0, 0, 0, 0, 494, 0, 0, 494, 0, 0, 0, 0, 0, 0},
            {0, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 0, 0, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 0},
            {0, 546, 0, 0, 0, 0, 546, 0, 0, 0, 0, 0, 546, 0, 0, 546, 0, 0, 0, 0, 0, 546, 0, 0, 0, 0, 546, 0},
            {0, 572, 0, 0, 0, 0, 572, 0, 0, 0, 0, 0, 572, 0, 0, 572, 0, 0, 0, 0, 0, 572, 0, 0, 0, 0, 572, 0},
            {0, 598, 598, 598, 0, 0, 598, 598, 598, 598, 598, 598, 598, 598, 598, 598, 598, 598, 598, 598, 598, 598, 0, 0, 598, 598, 598, 0},
            {0, 0, 0, 624, 0, 0, 624, 0, 0, 624, 0, 0, 0, 0, 0, 0, 0, 0, 624, 0, 0, 624, 0, 0, 624, 0, 0, 0},
            {0, 0, 0, 650, 0, 0, 650, 0, 0, 650, 0, 0, 0, 0, 0, 0, 0, 0, 650, 0, 0, 650, 0, 0, 650, 0, 0, 0},
            {0, 676, 676, 676, 676, 676, 676, 0, 0, 676, 676, 676, 676, 0, 0, 676, 676, 676, 676, 0, 0, 676, 676, 676, 676, 676, 676, 0},
            {0, 702, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 702, 0, 0, 702, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 702, 0},
            {0, 728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 728, 0, 0, 728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 728, 0},
            {0, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 754, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    boolean [][]partColl = new boolean[31][28];
    boolean redInCentre = true;
    boolean blueInCentre = true;
    boolean pinkInCentre = true;
    boolean orangeInCentre = true;
    private boolean attackModePM = false;
    public boolean inGame = true;

    public void paint (Graphics g) {
        super.paintComponent(g);//перерисовка игры
        setBackground(Color.black);
        g.drawImage(backGround, (w - h / 8 * 7) / 2, h / 64, h / 8 * 7, h / 32 * 31, this);
        g.drawImage(dot, pmx, pmy, h / 32, h / 32, this);
        g.setColor(Color.white);
        g.drawString("HIGH SCORE", h / 16, h / 16);
        g.drawString(Integer.toString(score), h / 16, h / 16 + h / 32);
        if(livesPM==0){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
        if(!inGame) {
            restartAndOverGame(true);
            g.drawImage(dot, pmx, pmy, h / 32, h / 32, this);
            g.drawImage(dotGhostRed, ghostRedX, ghostRedY, h / 32, h / 32, this);
            g.drawImage(dotGhostBlue, ghostBlueX, ghostBlueY, h / 32, h / 32, this);
            g.drawImage(dotGhostPink, ghostPinkX, ghostPinkY, h / 32, h / 32, this);
            g.drawImage(dotGhostOrange, ghostOrangeX, ghostOrangeY, h / 32, h / 32, this);
            g.drawImage(dotSuperCell, superCellX, superCellY, h / 32, h / 32, this);
            g.drawImage(dotLivePm, w - 100, h - 100, this);
            restartAndOverGame(false);
        }
        else {
            checkCollisionPm();
            g.drawImage(dot, pmx, pmy, h / 32, h / 32, this);
            g.drawImage(dotGhostRed, ghostRedX, ghostRedY, h / 32, h / 32, this);
            g.drawImage(dotGhostBlue, ghostBlueX, ghostBlueY, h / 32, h / 32, this);
            g.drawImage(dotGhostPink, ghostPinkX, ghostPinkY, h / 32, h / 32, this);
            g.drawImage(dotGhostOrange, ghostOrangeX, ghostOrangeY, h / 32, h / 32, this);
            g.drawImage(dotSuperCell, superCellX, superCellY, h / 32, h / 32, this);
            g.drawImage(dotLivePm, w - 100, h - 100, this);
        }
        if (isCherryAvailable) {
            g.drawImage(cherry, berryX, berryY, h / 32, h / 32, this);
        }
        for (int i = 0; i < partColl.length; i++)
            for (int j = 0; j < partColl[i].length; j++)
                if (partColl[i][j])
                    g.drawImage(part, j * (h / 32) + (w - h / 8 * 7) / 2,i * (h / 32) + h / 64 , h / 32, h / 32, this);
                else
                    g.drawImage(part1, j * (h / 32) + (w - h / 8 * 7) / 2,i * (h / 32) + h / 64 , h / 32, h / 32, this);
    }

    public GAmeField(JFrame frame, int h, int w) {
        this.frame = frame;
        this.h = h;
        this.w = w;
        initGame();
        addKeyListener(new FieldKeyListener());//обработчик событий
        setFocusable(true);//чтобы нажатие влияло только на игровое поле
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            partEating();
            openGhosts();
            cherrySpawn();
            cherryEating();
            if (inGame) { //movePacman
                movePacman();//r-red b-blue p-pink o-orange
                if ((checkCollisionObjects(ghostRedX, ghostRedY, pmx, pmy) && attackModePM) || !redInCentre)
                    checkGhostToCentre('r');
                else moveGhostRed();
                if ((checkCollisionObjects(ghostBlueX, ghostBlueY, pmx, pmy) && attackModePM) || !blueInCentre)
                    checkGhostToCentre('b');
                else moveGhostBlue();
                if ((checkCollisionObjects(ghostPinkX, ghostPinkY, pmx, pmy) && attackModePM) || !pinkInCentre)
                    checkGhostToCentre('p');
                else moveGhostPink();
                if ((checkCollisionObjects(ghostOrangeX, ghostOrangeY, pmx, pmy) && attackModePM) || !orangeInCentre)
                    checkGhostToCentre('o');
                else moveGhostOrange();
            }
        }
        repaint();//перерисовка
    }

    Timer cherryTimer = new Timer (10000, this);
    Timer timerRunAway = new Timer(150, new ActionListener() {
        int t = 0;

        public void actionPerformed(ActionEvent e) {
            speedBlue = 0;
            speedOrange = 0;
            speedPink = 0;
            speedRed = 0;

            DistanceRunawayGhost(ghostBlueX, ghostBlueY, 'b');
            ghostBlueX = (temp[0] != 0) ? temp[0] : ghostBlueX;
            ghostBlueY = (temp[1] != 0) ? temp[1] : ghostBlueY;

            DistanceRunawayGhost(ghostRedX, ghostRedY, 'r');
            ghostRedX = (temp[0] != 0) ? temp[0] : ghostRedX;
            ghostRedY = (temp[1] != 0) ? temp[1] : ghostRedY;


            DistanceRunawayGhost(ghostOrangeX, ghostOrangeY, 'o');
            ghostOrangeX = (temp[0] != 0) ? temp[0] : ghostOrangeX;
            ghostOrangeY = (temp[1] != 0) ? temp[1] : ghostOrangeY;

            DistanceRunawayGhost(ghostPinkX, ghostPinkY, 'p');
            ghostPinkX = (temp[0] != 0) ? temp[0] : ghostPinkX;
            ghostPinkY = (temp[1] != 0) ? temp[1] : ghostPinkY;

            t++;
            timerRunAway.start();
            if (t == 20) {
                t = 0;
                timerRunAway.stop();
                speedBlue = 9;
                speedRed = 8;
                speedPink = 9;
                speedOrange = 9;
            }
        }
    });
    Timer timerForTimerRunAway = new Timer(20000, new ActionListener() {//для запуска timerRunAway
        public void actionPerformed(ActionEvent e) {
            timerRunAway.start();
            timerForTimerRunAway.restart();
            timerRunAway.restart();
        }
    });

    Timer timerForSuperCell = new Timer(10000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            attackModePM = false;
        }
    });

    public void initGame() { // старт игры
        timer.start();
        timerForTimerRunAway.start();
        attackModePM = false;
        for (int i = 0; i < pmColl.length; i++) {
            for (int j = 0; j < pmColl[i].length; j++) {
                if (pmColl[i][j] != 0 && pmColl[i][j] != -1) {
                    partColl[i][j] = true;
                }
            }
        }
    }
    Timer timer = new Timer(150, this);

    public void openGhosts() { // призраки выходят из комнаты по достижении 1000 очков
        if (scoreForGhosts == 1000) {
            ghostRedY = 299;
            ghostPinkY = 299;
            ghostBlueY = 299;
            ghostOrangeY = 299;
        }
    }

    public void cherrySpawn() { // появление ягоды по достижении 10000 очков
        if (score == 10000) {
            isCherryAvailable = true;
            cherryTimer.start();
        }
    }

    public void cherryEating() { // предание ягоды
        if (pmx == berryX && pmy == berryY && isCherryAvailable) {
            score += 5000;
            isCherryAvailable = false;
        }
    }

    public void partEating() { // поедание частиц
        if (left && partColl[pmy / (h / 32)][(pmx + h / 32 - (w - h / 8 * 7) / 2 - h / 64) / (h / 32)]) {
            partColl[pmy / (h / 32)][(pmx + h / 32 - (w - h / 8 * 7) / 2 - h / 64) / (h / 32)] = false;
            score += 50;
            if (scoreForGhosts <= 1000)
                scoreForGhosts += 50;
        }
        if (right && partColl[pmy / (h / 32)][(pmx + h / 64 - h / 32 - (w - h / 8 * 7) / 2 + h / 64) / (h / 32)]) {
            partColl[pmy / (h / 32)][(pmx + h / 64 - h / 32 - (w - h / 8 * 7) / 2 + h / 64) / (h / 32)] = false;
            score += 50;
            if (scoreForGhosts <= 1000)
                scoreForGhosts += 50;
        }
        if (up && partColl[pmy / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)]) {
            partColl[pmy / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] = false;
            score += 50;
            if (scoreForGhosts <= 1000)
                scoreForGhosts += 50;
        }
        if (down && partColl[(pmy + h / 64 - h / 32) / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)]) {
            partColl[(pmy + h / 64 - h / 32) / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] = false;
            score += 50;
            if (scoreForGhosts <= 1000)
                scoreForGhosts += 50;
        }
    }

    public boolean LifeCounterPm() {
        ImageIcon iid=new ImageIcon("pacRip.gif");
        dot = iid.getImage();
        -- livesPM;
        return livesPM == 0;
    }

    public boolean checkCollisionObjects(int x, int y, int x1, int y1) {
        Rectangle xy=new Rectangle(x, y, h / 32, h / 32);
        Rectangle x1y1=new Rectangle(x1, y1, h / 32, h / 32);
        return xy.intersects(x1y1);
    }

    public void movePacman() { // 1-left 2-up 3-right 4-down
        if (left && pmColl[pmy / (h / 32)][(pmx - (w - h / 8 * 7) / 2 - h / 64) / (h / 32)] != 0) {
            pmx -= speedPM;
            loadImagesPm(1);
        }
        if (right && pmColl[pmy / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2 + h / 64) / (h / 32)] != 0) {
            pmx += speedPM;
            loadImagesPm(3);
        }
        if (up && pmColl[(pmy - h / 32) / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] != 0) {
            pmy -= speedPM;
            loadImagesPm(2);
        }
        if (down && pmColl[(pmy + h / 64) / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] != 0) {
            pmy += speedPM;
            loadImagesPm(4);
        }
    }

    public void checkCollisionPm() {
        if(checkCollisionObjects(pmx, pmy, superCellX, superCellY)){
            attackModePM = true;
            superCellX = (int) (Math.random()*(w-h / 32));//позволяет есть призраков
            superCellY = (int) (Math.random()*(h-h / 32));
            timerForSuperCell.start();
        }
        if (checkCollisionObjects(ghostRedX, ghostRedY, pmx, pmy) && !attackModePM) {
            if (LifeCounterPm()) inGame = false;
            else {
                inGame = false;
            }
        }
        if (checkCollisionObjects(ghostPinkX, ghostPinkY, pmx, pmy) && !attackModePM) {
            if (LifeCounterPm()) inGame = false;
            else {
                inGame = false;
            }
        }
        if (checkCollisionObjects(ghostBlueX, ghostBlueY, pmx, pmy) && !attackModePM) {
            if (LifeCounterPm()) inGame = false;
            else {
                inGame = false;
            }
        }
        if (checkCollisionObjects(ghostOrangeX, ghostOrangeY, pmx, pmy) && !attackModePM) {
            if (LifeCounterPm()) inGame = false;
            else {
                inGame = false;
            }
        }
        if (pmx >= w) inGame = false;
        if (pmx < 0) inGame = false;
        if (pmy >= h) inGame = false;
        if (pmy < 0) inGame = false;
    }

    public boolean isLeftDirFree() { // определение свободного пути в направлении движения
        return pmColl[pmy / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2 - h / 32) / (h / 32)] == (pmy - h / 64);
    }

    public boolean isRightDirFree() {
        return pmColl[pmy / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2 + h / 32) / (h / 32)] == (pmy - h / 64);
    }

    public boolean isUpDirFree() {
        if (right)
            return pmColl[(pmy - h / 32) / (h / 32)][(pmx - (w - h / 8 * 7) / 2) / (h / 32)] == (pmy - h / 32 - h / 64);
        return pmColl[(pmy - h / 32) / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] == (pmy - h / 32 - h / 64);
    }

    public boolean isDownDirFree() {
        if (right)
            return pmColl[(pmy + h / 32) / (h / 32)][(pmx - (w - h / 8 * 7) / 2) / (h / 32)] == (pmy + h / 32 - h / 64);
        return pmColl[(pmy + h / 32) / (h / 32)][(pmx + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] == (pmy + h / 32 - h / 64);
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getExtendedKeyCode();//считывает нажатие клавишой
            if (key == KeyEvent.VK_LEFT && !right && isLeftDirFree()) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left && isRightDirFree()) {
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down && isUpDirFree()) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up && isDownDirFree()) {
                right = false;
                down = true;
                left = false;
            }
            if (key == KeyEvent.VK_ESCAPE) {
                inGame = false;
            }
            if (key == KeyEvent.VK_ENTER) {
                System.exit('0');
            }
        }
    }
    public void loadImagesPm(int direction) { //0-static 1-left 2-up 3-right 4-down//
        ImageIcon iid;
        if (direction == 1) {
            iid = new ImageIcon("pac1.gif");
            dot = iid.getImage();
        }
        if (direction == 2) {
            iid = new ImageIcon("pac2.gif");
            dot = iid.getImage();
        }
        if (direction == 3) {
            iid = new ImageIcon("pac3.gif");
            dot = iid.getImage();
        }
        if (direction == 4) {
            iid = new ImageIcon("pac4.gif");
            dot = iid.getImage();
        }

        if (livesPM == 3) {
            iid = new ImageIcon("threeLive.png");
            dotLivePm = iid.getImage();
        }
        if (livesPM == 2) {
            iid = new ImageIcon("twoLive.png");
            dotLivePm = iid.getImage();
        }
        if (livesPM == 1) {
            iid = new ImageIcon("oneLive.png");
            dotLivePm = iid.getImage();
        }
    }

    public int distanceToPMFromRed() {// 1-left 2-up 3-right 4-down//
        double ghostRight, ghostLeft, ghostDown, ghostUp;
        double[][] distance = new double[2][4];
        int k = 0;
        if (PossibleMoveLeft(ghostRedX, ghostRedY, speedRed)) {
            double distanceToPM1 = Math.sqrt(Math.pow((ghostRedX - speedRed - pmx), 2) + Math.pow((ghostRedY - pmy), 2));
            distance[0][k] = distanceToPM1;
            ghostLeft = 1;
            distance[1][k] = ghostLeft;
        }
        k++;
        if (PossibleMoveUp(ghostRedX, ghostRedY, speedRed)) {
            double distanceToPM2 = Math.sqrt(Math.pow((ghostRedX - pmx), 2) + Math.pow((ghostRedY - speedRed - pmy), 2));
            distance[0][k] = distanceToPM2;
            ghostUp = 1;
            distance[1][k] = ghostUp;
        }
        k++;
        if (PossibleMoveRight(ghostRedX, ghostRedY, speedRed)) {
            double distanceToPM3 = Math.sqrt(Math.pow((ghostRedX + speedRed - pmx), 2) + Math.pow((ghostRedY - pmy), 2));
            distance[0][k] = distanceToPM3;
            ghostRight = 1;
            distance[1][k] = ghostRight;
        }
        k++;
        if (PossibleMoveDown(ghostRedX, ghostRedY, speedRed)) {
            double distanceToPM4 = Math.sqrt(Math.pow((ghostRedX - pmx), 2) + Math.pow((ghostRedY + speedRed - pmy), 2));
            distance[0][k] = distanceToPM4;
            ghostDown = 1;
            distance[1][k] = ghostDown;
        }
        double distanceToPMMin = Math.sqrt(Math.pow((ghostRedX - pmx), 2) + Math.pow((ghostRedY - pmy), 2));
        //минимальное расстояние до пакмена//
        for (int i = 0; i < 4; i++) {
            if (distanceToPMMin > distance[0][i] && distance[1][i] == 1)
                distanceToPMMin = distance[0][i];
        }
        if (distanceToPMMin == distance[0][0] && distance[1][0] != 0) return 1;
        if (distanceToPMMin == distance[0][1] && distance[1][1] != 0) return 2;
        if (distanceToPMMin == distance[0][2] && distance[1][2] != 0) return 3;
        if (distanceToPMMin == distance[0][3] && distance[1][3] != 0) return 4;
        return 0;
    }
    public void moveGhostRed() {// 1-left 2-up 3-right 4-down//
        if (distanceToPMFromRed() == 1) {
            ghostRedX -= speedRed;
            dotGhostRed=loadImagesGhost(1, "ghostRed");
        }
        if (distanceToPMFromRed() == 2) {
            ghostRedY -= speedRed;
            dotGhostRed=loadImagesGhost(2, "ghostRed");
        }
        if (distanceToPMFromRed() == 3) {
            ghostRedX += speedRed;
            dotGhostRed=loadImagesGhost(3, "ghostRed");
        }
        if (distanceToPMFromRed() == 4) {
            ghostRedY += speedRed;
            dotGhostRed=loadImagesGhost(4, "ghostRed");
        }
    }

    public int distanceToPMFromBlue() {// 1-left 2-up 3-right 4-down//
        double ghostRight, ghostLeft, ghostDown, ghostUp;
        double[][] distance = new double[2][4];
        int k = 0;
        if (PossibleMoveLeft(ghostBlueX, ghostBlueY, speedBlue)) {
            double distanceToPM1 = Math.sqrt(Math.pow(2 * pmx - ghostRedX - ghostBlueX - speedBlue, 2) + Math.pow(ghostBlueY + ghostRedY - 2 * pmy, 2));
            distance[0][k] = distanceToPM1;
            ghostLeft = 1;
            distance[1][k] = ghostLeft;
        }
        k++;
        if (PossibleMoveUp(ghostBlueX, ghostBlueY, speedBlue)) {
            double distanceToPM2 = Math.sqrt(Math.pow(2 * pmx - ghostRedX - ghostBlueX, 2) + Math.pow(ghostBlueY + ghostRedY - 2 * pmy - speedBlue, 2));
            distance[0][k] = distanceToPM2;
            ghostUp = 1;
            distance[1][k] = ghostUp;
        }
        k++;
        if (PossibleMoveRight(ghostBlueX, ghostBlueY, speedBlue)) {
            double distanceToPM3 = Math.sqrt(Math.pow(2 * pmx - ghostRedX - ghostBlueX + speedBlue, 2) + Math.pow(ghostBlueY + ghostRedY - 2 * pmy, 2));
            distance[0][k] = distanceToPM3;
            ghostRight = 1;
            distance[1][k] = ghostRight;
        }
        k++;
        if (PossibleMoveDown(ghostBlueX, ghostBlueY, speedBlue)) {
            double distanceToPM4 = Math.sqrt(Math.pow(2 * pmx - ghostRedX - ghostBlueX, 2) + Math.pow(ghostBlueY + ghostRedY - 2 * pmy + speedBlue, 2));
            distance[0][k] = distanceToPM4;
            ghostDown = 1;
            distance[1][k] = ghostDown;
        }
        double distanceToPMMin = 999999;
        for (int i = 0; i < 4; i++) {
            if (distanceToPMMin > distance[0][i] && distance[1][i] == 1)
                distanceToPMMin = distance[0][i];
        }
        if (distanceToPMMin == distance[0][0] && distance[1][0] != 0) return 3;
        if (distanceToPMMin == distance[0][1] && distance[1][1] != 0) return 2;
        if (distanceToPMMin == distance[0][2] && distance[1][2] != 0) return 1;
        if (distanceToPMMin == distance[0][3] && distance[1][3] != 0) return 4;
        return 0;
    }
    public void moveGhostBlue() {// 1-left 2-up 3-right 4-down//
        if (distanceToPMFromBlue() == 1 && PossibleMoveLeft(ghostBlueX, ghostBlueY, speedBlue)) {
            ghostBlueX -= speedBlue;
            dotGhostBlue=loadImagesGhost(1, "ghostBlue");
        }
        if (distanceToPMFromBlue() == 2 && PossibleMoveUp(ghostBlueX, ghostBlueY, speedBlue)) {
            ghostBlueY -= speedBlue;
            dotGhostBlue=loadImagesGhost(2, "ghostBlue");
        }
        if (distanceToPMFromBlue() == 3 && PossibleMoveRight(ghostBlueX, ghostBlueY, speedBlue)) {
            ghostBlueX += speedBlue;
            dotGhostBlue=loadImagesGhost(3, "ghostBlue");
        }
        if (distanceToPMFromBlue() == 4 && PossibleMoveDown(ghostBlueX, ghostBlueY, speedBlue)) {
            ghostBlueY += speedBlue;
            dotGhostBlue=loadImagesGhost(4, "ghostBlue");
        }
    }

    public int distanceToPMFromPink() {// 1-left 2-up 3-right 4-down//10-вперед на 10 клеток//
        double ghostRight, ghostLeft, ghostDown, ghostUp;
        double[][] distance = new double[2][4];
        int k = 0;
        if (PossibleMoveLeft(ghostPinkX, ghostPinkY, speedPink)) {
            double distanceToPM1 = Math.sqrt(Math.pow((ghostPinkX - speedPink - pmx), 2) + Math.pow((ghostPinkY - pmy), 2)) + 5 * 16;
            distance[0][k] = distanceToPM1;
            ghostLeft = 1;
            distance[1][k] = ghostLeft;
        }
        k++;
        if (PossibleMoveUp(ghostPinkX, ghostPinkY, speedPink)) {
            double distanceToPM2 = Math.sqrt(Math.pow((ghostPinkX - pmx), 2) + Math.pow((ghostPinkY - speedPink - pmy), 2)) + 5 * 16;
            distance[0][k] = distanceToPM2;
            ghostUp = 1;
            distance[1][k] = ghostUp;

        }
        k++;
        if (PossibleMoveRight(ghostPinkX, ghostPinkY, speedPink)) {
            double distanceToPM3 = Math.sqrt(Math.pow((ghostPinkX + speedPink - pmx), 2) + Math.pow((ghostPinkY - pmy), 2)) + 5 * 16;
            distance[0][k] = distanceToPM3;
            ghostRight = 1;
            distance[1][k] = ghostRight;
        }
        k++;
        if (PossibleMoveDown(ghostPinkX, ghostPinkY, speedPink)) {
            double distanceToPM4 = Math.sqrt(Math.pow((ghostPinkX - pmx), 2) + Math.pow((ghostPinkY + speedPink - pmy), 2)) + 5 * 16;
            distance[0][k] = distanceToPM4;
            ghostDown = 1;
            distance[1][k] = ghostDown;
        }
        double distanceToPMMin = 999999;//минимальное расстояние до пакмена//
        for (int i = 0; i < 4; i++) {
            if (distanceToPMMin > distance[0][i] && distance[1][i] == 1)
                distanceToPMMin = distance[0][i];
        }
        if (distanceToPMMin == distance[0][0] && distance[1][1]!=0) return 1;
        if (distanceToPMMin == distance[0][1] && distance[1][2]!=0) return 2;
        if (distanceToPMMin == distance[0][2] && distance[1][2] != 0) return 3;
        if (distanceToPMMin == distance[0][3] && distance[1][3] != 0) return 4;
        return 0;
    }
    public void moveGhostPink() {// 1-left 2-up 3-right 4-down//
        if (distanceToPMFromPink() == 1) {
            ghostPinkX -= speedPink;
            dotGhostPink=loadImagesGhost(1, "ghostPink");
        }
        if (distanceToPMFromPink() == 2) {
            ghostPinkY -= speedPink;
            dotGhostPink=loadImagesGhost(2, "ghostPink");
        }
        if (distanceToPMFromPink() == 3) {
            ghostPinkX += speedPink;
            dotGhostPink=loadImagesGhost(3, "ghostPink");
        }
        if (distanceToPMFromPink() == 4) {
            ghostPinkY += speedPink;
            dotGhostPink=loadImagesGhost(4, "ghostPink");
        }
    }

    public int distanceToPMFromOrange() {
        double ghostRight, ghostLeft, ghostDown, ghostUp;
        double[][] distance = new double[2][4];
        int k = 0;
        if (PossibleMoveLeft(ghostOrangeX, ghostOrangeY, speedOrange)) {
            double distanceToPM1 = Math.sqrt(Math.pow((ghostOrangeX - speedOrange - pmx), 2) + Math.pow((ghostOrangeY - pmy), 2));
            distance[0][k] = distanceToPM1;
            ghostLeft = 1;
            distance[1][k] = ghostLeft;
        }
        k++;
        if (PossibleMoveUp(ghostOrangeX, ghostOrangeY, speedOrange)) {
            double distanceToPM2 = Math.sqrt(Math.pow((ghostOrangeX - pmx), 2) + Math.pow((ghostOrangeY - speedOrange - pmy), 2));
            distance[0][k] = distanceToPM2;
            ghostUp = 1;
            distance[1][k] = ghostUp;
        }
        k++;
        if (PossibleMoveRight(ghostOrangeX, ghostOrangeY, speedOrange)) {
            double distanceToPM3 = Math.sqrt(Math.pow((ghostOrangeX + speedOrange - pmx), 2) + Math.pow((ghostOrangeY - pmy), 2));
            distance[0][k] = distanceToPM3;
            ghostRight = 1;
            distance[1][k] = ghostRight;
        }
        k++;
        if (PossibleMoveDown(ghostOrangeX, ghostOrangeY, speedOrange)) {
            double distanceToPM4 = Math.sqrt(Math.pow((ghostOrangeX - pmx), 2) + Math.pow((ghostOrangeY + speedOrange - pmy), 2));
            distance[0][k] = distanceToPM4;
            ghostDown = 1;
            distance[1][k] = ghostDown;
        }
        double distanceToPMMin =999999;
        for (int i = 0; i < 4; i++) {
            if (distanceToPMMin >=distance[0][i] && distance[1][i] == 1)
                distanceToPMMin = distance[0][i];
        }
        if (distanceToPMMin == distance[0][0] && distance[1][0] != 0) return 1;
        if (distanceToPMMin == distance[0][1] && distance[1][1] != 0) return 2;
        if (distanceToPMMin == distance[0][2] && distance[1][2] != 0) return 3;
        if (distanceToPMMin == distance[0][3] && distance[1][3] != 0) return 4;
        return 0;
    }
    public void moveGhostOrange() {
        if (Math.sqrt(Math.pow(pmx - ghostOrangeX, 2) + Math.pow(pmy - ghostOrangeY, 2)) >= 8 * 16) {
            if (distanceToPMFromOrange() == 1) {
                ghostOrangeX -= speedOrange;
                dotGhostOrange=loadImagesGhost(1, "ghostOrange");
            }
            if (distanceToPMFromOrange() == 2) {
                ghostOrangeY -= speedOrange;
                dotGhostOrange=loadImagesGhost(2, "ghostOrange");
            }
            if (distanceToPMFromOrange() == 3) {
                ghostOrangeX += speedOrange;
                dotGhostOrange=loadImagesGhost(3, "ghostOrange");
            }
            if (distanceToPMFromOrange() == 4) {
                ghostOrangeY += speedOrange;
                dotGhostOrange=loadImagesGhost(4, "ghostOrange");
            }
        } else {
            double ghostRight, ghostLeft, ghostDown, ghostUp;
            double targetCellX = 0, targetCellY = h;
            double[][] distance = new double[2][4];
            int k = 0;
            if (PossibleMoveLeft(ghostOrangeX, ghostOrangeY, speedOrange)) {
                double distanceToPM1 = Math.sqrt(Math.pow((ghostOrangeX - speedOrange - targetCellX), 2) + Math.pow((ghostOrangeY - targetCellY), 2));
                distance[0][k] = distanceToPM1;
                ghostLeft = 1;
                distance[1][k] = ghostLeft;
            }
            k++;
            if (PossibleMoveUp(ghostOrangeX, ghostOrangeY, speedOrange)) {
                double distanceToPM2 = Math.sqrt(Math.pow((ghostOrangeX - targetCellX), 2) + Math.pow((ghostOrangeY - speedOrange - targetCellY), 2));
                distance[0][k] = distanceToPM2;
                ghostUp = 1;
                distance[1][k] = ghostUp;
            }
            k++;
            if (PossibleMoveRight(ghostOrangeX, ghostOrangeY, speedOrange)) {
                double distanceToPM3 = Math.sqrt(Math.pow((ghostOrangeX + speedOrange - targetCellX), 2) + Math.pow((ghostOrangeY - targetCellY), 2));
                distance[0][k] = distanceToPM3;
                ghostRight = 1;
                distance[1][k] = ghostRight;
            }
            k++;
            if (PossibleMoveDown(ghostOrangeX, ghostOrangeY, speedOrange)) {
                double distanceToPM4 = Math.sqrt(Math.pow((ghostOrangeX - targetCellX), 2) + Math.pow((ghostOrangeY + speedOrange - targetCellY), 2));
                distance[0][k] = distanceToPM4;
                ghostDown = 1;
                distance[1][k] = ghostDown;
            }
            double distanceToPMMin = 9999999;
            for (int i = 0; i < 4; i++) {
                if (distanceToPMMin >=distance[0][i] && distance[1][i] == 1)
                    distanceToPMMin = distance[0][i];
            }

            if (distanceToPMMin == distance[0][0] && distance[1][0] != 0) {
                ghostOrangeX -= speedOrange;
                dotGhostOrange=loadImagesGhost(1, "ghostOrange");
            }
            if (distanceToPMMin == distance[0][1] && distance[1][1] != 0) {
                ghostOrangeY -= speedOrange;
                dotGhostOrange=loadImagesGhost(2, "ghostOrange");
            }
            if (distanceToPMMin == distance[0][2] && distance[1][2] != 0) {
                ghostOrangeX += speedOrange;
                dotGhostOrange=loadImagesGhost(3, "ghostOrange");
            }
            if (distanceToPMMin == distance[0][3] && distance[1][3] != 0) {
                ghostOrangeY += speedOrange;
                dotGhostOrange=loadImagesGhost(4, "ghostOrange");
            }
        }
    }

    public void DistanceRunawayGhost(int x, int y, char colourGhost) {//o-orangeGhost r-redGhost b-blueGhost p-pinkGhost//
        int offset = 9;//смещение
        double ghostRight, ghostLeft, ghostDown, ghostUp;
        double targetCellX = 0, targetCellY = 0;
        double[][] distance = new double[2][4];
        if (colourGhost == 'o') {
            targetCellX = 0;
            targetCellY = h;
        }
        if (colourGhost == 'b') {
            targetCellX = w;
            targetCellY = h;
        }
        if (colourGhost == 'r') {
            targetCellX = w;
            targetCellY = 0;
        }
        if (colourGhost == 'p') {
            targetCellX = 0;
            targetCellY = 0;
        }

        int k = 0;
        if (PossibleMoveLeft(x, y, offset)) {
            double distanceToPM1 = Math.sqrt(Math.pow((x - offset - targetCellX), 2) + Math.pow((y - targetCellY), 2));
            distance[0][k] = distanceToPM1;
            ghostLeft = 1;
            distance[1][k] = ghostLeft;
        }
        k++;
        if (PossibleMoveUp(x, y, offset)) {
            double distanceToPM2 = Math.sqrt(Math.pow((x - targetCellX), 2) + Math.pow((y - offset - targetCellY), 2));
            distance[0][k] = distanceToPM2;
            ghostUp = 1;
            distance[1][k] = ghostUp;
        }
        k++;
        if (PossibleMoveRight(x, y, offset)) {
            double distanceToPM3 = Math.sqrt(Math.pow((x + offset - targetCellX), 2) + Math.pow((y - targetCellY), 2));
            distance[0][k] = distanceToPM3;
            ghostRight = 1;
            distance[1][k] = ghostRight;
        }
        k++;
        if (PossibleMoveDown(x, y, offset)) {
            double distanceToPM4 = Math.sqrt(Math.pow((x - targetCellX), 2) + Math.pow((y + offset - targetCellY), 2));
            distance[0][k] = distanceToPM4;
            ghostDown = 1;
            distance[1][k] = ghostDown;
        }
        double distanceToPMMin = 9999999;
        for (int i = 0; i < 4; i++) {
            if (distanceToPMMin > distance[0][i] && distance[1][i] == 1)
                distanceToPMMin = distance[0][i];
        }

        if (distanceToPMMin == distance[0][0] && distance[1][0] != 0) {
            x -= offset;
        }
        if (distanceToPMMin == distance[0][1] && distance[1][1] != 0) {
            y -= offset;
        }
        if (distanceToPMMin == distance[0][2] && distance[1][2] != 0) {
            x += offset;
        }
        if (distanceToPMMin == distance[0][3] && distance[1][3] != 0) {
            y += offset;
        }
        temp[0] = x;
        temp[1] = y;
    }

    public boolean PossibleMoveLeft(int x, int y, int offset){
        return x - offset > 0 && pmColl[y / (h / 32)][(x - (w - h / 8 * 7) / 2 - h / 64) / (h / 32)] != 0;
    }
    public boolean PossibleMoveRight(int x, int
            y, int offset){
        return x + offset > 0 && pmColl[y / (h / 32)][(x + h / 64 - (w - h / 8 * 7) / 2 + h / 64) / (h / 32)] != 0;
    }
    public boolean PossibleMoveUp(int x, int y, int offset){
        return y - offset > 0 && pmColl[(y - h / 32) / (h / 32)][(x + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] != 0;
    }
    public boolean PossibleMoveDown(int x, int y, int offset){
        return y + offset < h && pmColl[(y + h / 64) / (h / 32)][(x + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] != 0;
    }

    public Image loadImagesGhost(int direction, String color){
        if(direction==1) {
            ImageIcon iid = new ImageIcon(color+"left"+".gif");
            return iid.getImage();
        }
        if(direction==2) {
            ImageIcon iid = new ImageIcon(color+"up"+".gif");
            return iid.getImage();
        }
        if(direction==3) {
            ImageIcon iid = new ImageIcon(color+"right"+".gif");
            return iid.getImage();
        }
        if(direction==4) {
            ImageIcon iid = new ImageIcon(color+"down"+".gif");
            return iid.getImage();
        }
        return null;
    }

    public void restartAndOverGame(boolean over){
        if(!over){
            ghostRedX = 755;
            ghostRedY = 359;
            speedRed = 5;

            ghostBlueX = 755;
            ghostBlueY = 419;
            speedBlue = 5;

            ghostPinkX = 695;
            ghostPinkY = 359;
            speedPink = 5;

            ghostOrangeX = 815;
            ghostOrangeY = 359;
            speedOrange = 5;

            scoreForGhosts = 0;

            pmx = 755;
            pmy = 299;
            speedPM = 13;
            inGame=true;
            timerRunAway.restart();
            timerForTimerRunAway.restart();
            timer.restart();

        }else{
            ImageIcon iid=new ImageIcon("pacRip.gif");
            dot = iid.getImage();
            timerRunAway.stop();
            timerForTimerRunAway.stop();
            timer.stop();
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            speedBlue=0;
            speedOrange=0;
            speedPink=0;
            speedRed=0;
        }
    }

    public void DistanceToCenterRipGhost(int x, int y) {
        int offset = 32;//смещение
        double ghostRight, ghostLeft, ghostDown, ghostUp;
        int targetCellX = w/2, targetCellY = h/2;
        double[][] distance = new double[2][4];
        int k = 0;

        if (x - offset > 0 && pmColl[y / (h / 32)][(x - (w - h / 8 * 7) / 2 - h / 64) / (h / 32)] != 0) {
            double distanceToPM1 = Math.sqrt(Math.pow((x - offset - targetCellX), 2) + Math.pow((y - targetCellY), 2));
            distance[0][k] = distanceToPM1;
            ghostLeft = 1;
            distance[1][k] = ghostLeft;
        }
        k++;
        if (y - offset > 0 && pmColl[(y - h / 32) / (h / 32)][(x + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] != 0) {
            double distanceToPM2 = Math.sqrt(Math.pow((x - targetCellX), 2) + Math.pow((y - offset - targetCellY), 2));
            distance[0][k] = distanceToPM2;
            ghostUp = 1;
            distance[1][k] = ghostUp;
        }
        k++;
        if (x + offset < w && pmColl[y / (h / 32)][(x + h / 64 - (w - h / 8 * 7) / 2 + h / 64) / (h / 32)] != 0) {
            double distanceToPM3 = Math.sqrt(Math.pow((x + offset - targetCellX), 2) + Math.pow((y - targetCellY), 2));
            distance[0][k] = distanceToPM3;
            ghostRight = 1;
            distance[1][k] = ghostRight;
        }
        k++;
        if (y + offset < h && pmColl[(y + h / 64) / (h / 32)][(x + h / 64 - (w - h / 8 * 7) / 2) / (h / 32)] != 0) {
            double distanceToPM4 = Math.sqrt(Math.pow((x - targetCellX), 2) + Math.pow((y + offset - targetCellY), 2));
            distance[0][k] = distanceToPM4;
            ghostDown = 1;
            distance[1][k] = ghostDown;
        }
        double distanceToPMMin = 9999999;
        for (int i = 0; i < 4; i++) {
            if (distanceToPMMin >=distance[0][i] && distance[1][i] == 1)
                distanceToPMMin = distance[0][i];
        }
        if (distanceToPMMin == distance[0][0] && distance[1][0] != 0) {
            x -= offset;
        }
        if (distanceToPMMin == distance[0][1] && distance[1][1] != 0) {
            y -= offset;
        }
        if (distanceToPMMin == distance[0][2] && distance[1][2] != 0) {
            x += offset;
        }
        if (distanceToPMMin == distance[0][3] && distance[1][3] != 0) {
            y += offset;
        }
        temp[0] = x;
        temp[1] = y;
    }

    public void checkGhostToCentre(char color){
        if(color=='r') {
            redInCentre = false;
            DistanceToCenterRipGhost(ghostRedX, ghostRedY);
            ghostRedX = (temp[0] != 0) ? temp[0] : ghostRedX;
            ghostRedY = (temp[1] != 0) ? temp[1] : ghostRedY;
            ImageIcon iid = new ImageIcon("RipGhost.png");
            dotGhostRed = iid.getImage();
            if (checkCollisionObjects(ghostRedX, ghostRedY, w / 2, h / 2)) {
                redInCentre = true;
            }
        }
        if(color=='p') {
            DistanceToCenterRipGhost(ghostPinkX, ghostPinkY);
            pinkInCentre = false;
            ghostPinkX = (temp[0] != 0) ? temp[0] : ghostPinkX;
            ghostPinkY = (temp[1] != 0) ? temp[1] : ghostPinkY;
            ImageIcon iid = new ImageIcon("RipGhost.png");
            dotGhostPink = iid.getImage();
            if (checkCollisionObjects(ghostPinkX, ghostPinkY, w / 2, h / 2)) {
                pinkInCentre = true;
            }
        }
        if(color=='b') {
            DistanceToCenterRipGhost(ghostBlueX, ghostBlueY);
            blueInCentre=false;
            ghostBlueX = (temp[0] != 0) ? temp[0] : ghostBlueX;
            ghostBlueY = (temp[1] != 0) ? temp[1] : ghostBlueY;
            ImageIcon iid = new ImageIcon("RipGhost.png");
            dotGhostBlue = iid.getImage();
            if (checkCollisionObjects(ghostBlueX, ghostBlueY, w / 2, h / 2)) {
                blueInCentre = true;
            }
        }
        if(color=='o') {
            DistanceToCenterRipGhost(ghostOrangeX, ghostOrangeY);
            orangeInCentre=false;
            ghostOrangeX = (temp[0] != 0) ? temp[0] : ghostOrangeX;
            ghostOrangeY = (temp[1] != 0) ? temp[1] : ghostOrangeY;
            ImageIcon iid = new ImageIcon("RipGhost.png");
            dotGhostOrange = iid.getImage();
            if (checkCollisionObjects(ghostBlueX, ghostBlueY, w / 2, h / 2)) {
                orangeInCentre = true;
            }
        }
    }
}
