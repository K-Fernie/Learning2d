package main;

import javax.swing.*;

public class main {
    public static void main(String[] args) {

        //Creating new window with swing
        JFrame window = new JFrame();
        //Setting the frame to close when user clicks "x"
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        //pack causes this window to be sized to fit the preferred size and layouts of its subcomponents(GamePanel)
        window.pack();
        //Sets display location to the center of the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
