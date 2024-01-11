package main.mainview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import entities.interfaces.Callback;
import entities.interfaces.Cell;

public class AnimationManager {

    private JPanel panelToAnimate;
    private int animationIndex;
    private int animPosX;
    private int animPosY;
    private boolean moving;

    public AnimationManager(JPanel panel){
        this.animationIndex=0;
        this.animPosX=0;
        this.animPosY=0;
        this.moving=false;
        this.panelToAnimate=panel;
    }



    public void startAnimation(int endPosX, int endPosY, Cell[] path){
        if ((animPosX != endPosX || animPosY != endPosY) || animationIndex < path.length -1) {
            animationIndex = animationIndex + 1;
            animPosX = path[animationIndex].getPositionX();
            animPosY = path[animationIndex].getPositionY();
            Callback animCallback = () -> {
                panelToAnimate.repaint();
            };
            animTimer(150, animCallback);
        } 
    }

    public void animTimer(int millis, Callback callback) {
        Timer timer = new Timer(millis, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                SwingUtilities.invokeLater(() -> {
                    callback.onComplete();
                });
            }
        });
        timer.start();
    }

    public void setMoving(boolean b){
        this.moving = b;
    }

    public boolean getMoving(){
        return this.moving;
    }

    public int getAnimationIndex(){
        return this.animationIndex;
    }

    public int getAnimationPosX(){
        return this.animPosX;
    }

    public int getAnimationPosY(){
        return this.animPosY;
    }

    public void setPosition(int x, int y){
        this.animPosX=x;
        this.animPosY=y;
    }

    public void reset(){
        this.moving=false;
        this.animationIndex=0;
    }
    
}
