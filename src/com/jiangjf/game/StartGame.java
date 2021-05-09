package com.jiangjf.game;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        // 设置窗体的标题
        jFrame.setTitle("贪吃蛇 by jiangjf");
        // 设置窗体的位置和大小
        jFrame.setBounds(10, 20, 800, 800);
        // 关闭窗口，退出程序
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置窗体大小不可调
        jFrame.setResizable(false);
        // 默认窗体隐藏，需要将窗体显示出来
        jFrame.setVisible(true);
    }
}
