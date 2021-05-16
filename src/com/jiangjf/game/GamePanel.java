package com.jiangjf.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    // 蛇的长度
    int length;
    // 存储蛇的x轴坐标，数组200的长度足够了
    int[] snakeX = new int[200];
    // 存储蛇的y轴坐标
    int[] snakeY = new int[200];
    // 蛇头的方向
    String direction;
    // 游戏的状态：开始 暂停，默认暂停
    boolean isStart = false;
    // 定时器，移动小蛇用
    Timer timer;
    // 食物的位置
    int foodX;
    int foodY;
    // 积分
    int score;
    // 小蛇是否死亡的判断，默认活着
    boolean isDie = false;

    /**
     * 初始化动作
     */
    public void init() {
        // 初始化蛇的长度
        length = 3;
        // 初始化蛇头坐标
        snakeX[0] = 175;
        snakeY[0] = 225;
        // 初始化第一节身子的坐标
        snakeX[1] = 150;
        snakeY[1] = 225;
        // 初始化第二节身子的坐标
        snakeX[2] = 125;
        snakeY[2] = 225;

        // 初始化蛇头的方向 L R U D
        direction = "R";

        // 初始化食物的位置
        foodX = 375;
        foodY = 475;

        // 初始化积分
        score = 0;
    }

    /**
     * 获取蛇头方向的图片对象
     *
     * @return
     */
    public ImageIcon getDirectionImg() {
        ImageIcon imageIcon = Images.rightImg;// 默认向右
        if ("U".equals(direction)) {// 上
            imageIcon = Images.upImg;
        }
        if ("D".equals(direction)) {// 下
            imageIcon = Images.downImg;
        }
        if ("L".equals(direction)) {// 左
            imageIcon = Images.leftImg;
        }
        return imageIcon;
    }

    public GamePanel() {
        init();
        // 设置窗体获得焦点
        this.setFocusable(true);
        // 监听键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {// 按下了空格
                    if (isDie) {
                        isDie = false;
                        init();
                    } else {
                        isStart = !isStart;
                    }
                    repaint();// 重新绘制
                }
                if (keyCode == KeyEvent.VK_UP) {// 向上
                    direction = "U";
                }
                if (keyCode == KeyEvent.VK_DOWN) {// 向下
                    direction = "D";
                }
                if (keyCode == KeyEvent.VK_LEFT) {// 向左
                    direction = "L";
                }
                if (keyCode == KeyEvent.VK_RIGHT) {// 向右
                    direction = "R";
                }
            }
        });
        timer = new Timer(130, new ActionListener() {
            /**
             * 事件监听，每100ms监听是否发生了一个动作
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart && !isDie) {//游戏开始时并且活着，蛇才动
                    // 后一节身子走到前一节身子的位置上
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    // 动头
                    if ("R".equals(direction)) {
                        snakeX[0] += 25;
                    }
                    if ("L".equals(direction)) {
                        snakeX[0] -= 25;
                    }
                    if ("U".equals(direction)) {
                        snakeY[0] -= 25;
                    }
                    if ("D".equals(direction)) {
                        snakeY[0] += 25;
                    }
                    // 防止蛇超出边界
                    if (snakeX[0] > 750) {
                        snakeX[0] = 25;
                    }
                    if (snakeX[0] < 25) {
                        snakeX[0] = 750;
                    }
                    if (snakeY[0] > 725) {
                        snakeY[0] = 100;
                    }
                    if (snakeY[0] < 100) {
                        snakeY[0] = 725;
                    }

                    // 吃到食物
                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
                        length++;

                        /*
                         随机下一个食物的位置
                         x[25,750]：转换：除以25 -> [1,30] -> -1 -> [0,29]
                         y[100,725] 转换：除以25 -> [4,29] -> -4 -> [0,25]
                         */
                        foodX = (new Random().nextInt(30) + 1) * 25;
                        foodY = (new Random().nextInt(26) + 4) * 25;

                        // 每吃一个食物，积分加10
                        score += 10;
                    }

                    // 判断是否死亡
                    for (int i = 1; i < length; i++) {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                            isDie = true;
                            break;
                        }
                    }

                    // 重绘
                    repaint();
                }
            }
        });
        // 启动定时器
        timer.start();
    }

    /**
     * 图形版的main方法
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 设置面板的背景色
        this.setBackground(new Color(174, 207, 174));
        // 画一个图片 四个参数：this指代当前面板 g画笔 xy是画图片的坐标
        Images.headerImg.paintIcon(this, g, 8, 10);
        // 调整画笔颜色
        g.setColor(new Color(26, 73, 66, 255));
        // 画一个游戏区域的矩形
        g.fillRect(8, 70, 770, 685);

        // 画蛇头
        getDirectionImg().paintIcon(this, g, snakeX[0], snakeY[0]);
        // 画蛇身
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 画食物
        Images.foodImg.paintIcon(this, g, foodX, foodY);

        // 暂停时画提示语
        if (!isStart) {
            g.setColor(new Color(204, 2, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("点击空格开始游戏", 250, 330);
        }

        // 画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("积分：" + score, 640, 40);

        // 画入死亡状态
        if (isDie) {
            g.setColor(new Color(204, 2, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.drawString("小蛇死亡，点击空格重新开始游戏", 250, 330);
        }
    }
}
