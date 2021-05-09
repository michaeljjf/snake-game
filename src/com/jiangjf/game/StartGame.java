package com.jiangjf.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        // 设置窗体的标题
        jFrame.setTitle("贪吃蛇 by jiangjf");
        // 设置窗体的位置和大小
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = dimension.width;
        int height = dimension.height;
        int jFrameWidth = 800;
        int jFrameHeight = 800;
        int x = (width - jFrameWidth) / 2;
        int y = (height - jFrameHeight) / 2;
        jFrame.setBounds(x, y, jFrameWidth, jFrameHeight);
        // 关闭窗口，退出程序
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置窗体大小不可调
        jFrame.setResizable(false);
        // 默认窗体隐藏，需要将窗体显示出来
        jFrame.setVisible(true);
    }
}
