package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class newFrame extends JFrame {

    public static void main(String[] args) throws FontFormatException {
        JFrame jFrame = new JFrame();
        jFrame.setSize(500, 250);
        jFrame.setLocation(500,250);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);//для изм в самой программе расположения

        JButton jButtonStart = new JButton("Start");
        JButton jButtonOut = new JButton("Exit");
        jButtonStart.setBounds(0, 0, 250, 250);
        jButtonOut.setBounds(250, 0, 250, 250);

        jPanel.add(jButtonStart);
        jPanel.add(jButtonOut);

        Font font=new Font("Arial", Font.BOLD,42 );

        jButtonStart.setBackground(Color.BLACK);
        jButtonStart.setForeground(Color.YELLOW);
        jButtonStart.setFont(font);
        jButtonOut.setBackground(Color.BLACK);
        jButtonOut.setForeground(Color.YELLOW);
        jButtonOut.setFont(font);
        jFrame.setUndecorated(true);//можно изм окно

        jButtonStart.addActionListener(new ActionListener() {//обработка нажатия
            @Override
            public void actionPerformed(ActionEvent e) {
                Display mw = new Display();
                jFrame.setVisible(false);
            }
        });
        jButtonOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonOut.setEnabled(false);
                System.exit(0);
            }
        });


        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
}
