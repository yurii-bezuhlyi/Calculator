package com.teamdev.bezugliy.calculator.impl.swingClass;

import javax.swing.*;

public class Dialog extends JFrame {
    public Dialog(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHE);
        setTitle("Calculator");
        ButtonPanel panel=new ButtonPanel();
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static final  int DEFAULT_WIDTH=500;
    public static final  int DEFAULT_HEIGHE=200;
}
