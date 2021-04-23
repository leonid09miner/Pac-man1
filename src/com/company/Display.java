package com.company;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pac-Man");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        frame.setUndecorated(true);
        frame.setSize(1536, 832);
        frame.add(new GAmeField(frame, frame.getHeight(), frame.getWidth()));
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
