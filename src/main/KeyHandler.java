package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
        //We won't be using keyTyped
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //returns a number of the key that was pressed
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}

/*
* Examples of associated keycode
* 8 = Backspace
* 10 = Enter
* 16 = Shift
* 18 = Alt
* 65 = A
* 67 = C
* 69 = E
* 71  = G
* 73 = I
* 9 = Tab
* 12 = Clear
* 17 = Ctrl
* 66 = B
* 68 = D
* 70 = F
* 72 = H
* 74 = J
* */
