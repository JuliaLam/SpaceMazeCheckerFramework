package main;

import javax.swing.JFrame;

public class MainFrame{
    public static void main(String[] args){

        JFrame screen = new JFrame();
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screen.setResizable(false);
        screen.setTitle("Space Maze");

        MainPanel mainPanel = new MainPanel();
        screen.add(mainPanel);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);


        mainPanel.startThread();


    }


}
