package com.jiangjf.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(197, 183, 113, 109));
        Images.headerImg.paintIcon(this, g, 10, 10);
    }
}
