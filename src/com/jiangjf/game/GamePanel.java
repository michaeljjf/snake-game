package com.jiangjf.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    }

    /**
     * 获取蛇头方向的图片对象
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
                    isStart = !isStart;
                    repaint();// 重新绘制
                }
            }
        });
        timer = new Timer(100, new ActionListener() {
            /**
             * 事件监听，每100ms监听是否发生了一个动作
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart) {//游戏开始时蛇才动
                    // 后一节身子走到前一节身子的位置上
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    // 动头
                    snakeX[0] += 25;
                    // 防止蛇超出边界
                    if (snakeX[0] > 750) {
                        snakeX[0] = 25;
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
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 设置面板的背景色
        this.setBackground(new Color(174, 207, 174));
        // 画一个图片 四个参数：this指代当前面板 g画笔 xy是画图片的坐标
        Images.headerImg.paintIcon(this, g, 10, 10);
        // 调整画笔颜色
        g.setColor(new Color(190, 179, 201));
        // 画一个游戏区域的矩形
        g.fillRect(10, 70, 770, 685);

        // 画蛇头
        getDirectionImg().paintIcon(this, g, snakeX[0], snakeY[0]);
        // 画蛇身
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 暂停时画提示语
        if (isStart == false) {
            g.setColor(new Color(204, 2, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("点击空格开始游戏", 250, 330);
        }
    }
}
