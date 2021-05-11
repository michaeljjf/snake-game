package com.jiangjf.game;

import javax.swing.*;
import java.net.URL;

public class Images {
    // 首先将图片的路径进行封装为对象
    static URL bodyURL = Images.class.getResource("/images/body.png");
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);

    static URL downURL = Images.class.getResource("/images/down.png");
    public static ImageIcon downImg = new ImageIcon(downURL);

    static URL foodURL = Images.class.getResource("/images/food.png");
    public static ImageIcon foodImg = new ImageIcon(foodURL);

    static URL headerURL = Images.class.getResource("/images/header.png");
    public static ImageIcon headerImg = new ImageIcon(headerURL);

    static URL leftURL = Images.class.getResource("/images/left.png");
    public static ImageIcon leftImg = new ImageIcon(leftURL);

    static URL rightURL = Images.class.getResource("/images/right.png");
    public static ImageIcon rightImg = new ImageIcon(rightURL);

    static URL upURL = Images.class.getResource("/images/up.png");
    public static ImageIcon upImg = new ImageIcon(upURL);
}
