package main.mainview.frames;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import entities.interfaces.Callback;
import entities.interfaces.Cell;
import entities.interfaces.GameBoardInterface;
import entities.interfaces.GameInterface;
import entities.interfaces.Player;
import main.mainview.AnimationManager;

public class PawnsPanel extends JPanel{
    

    private GameBoardInterface gb;
    private GameInterface game;
    private int[] startPlayerPos;
    private int[] endPlayerPos;
    private Cell[] path;
    private Callback eventCallback;
    private AnimationManager animationManager;

    public PawnsPanel(GameBoardInterface gb, GameInterface game) {
        this.gb = gb;
        this.game = game;
        setOpaque(false);
        this.startPlayerPos = new int[]{0,0};
        this.endPlayerPos = new int[]{0,0};
        this.path = new Cell[]{game.getCell(0, 0)};
        animationManager = new AnimationManager(this);

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
            
        if(eventCallback!=null && animationManager.getAnimationIndex() == path.length -1){
            SwingUtilities.invokeLater(() -> {
                animationManager.reset();
                eventCallback.onComplete();
                this.eventCallback=null;
                return;  });
        }
        for(int i=0;i<game.getNumberOfPlayers();i++){
            paintPawn(i, g);
        }

       

    }


    public void paintPawn(int i, Graphics g){
        Player pawn = game.getPawn(i);
        if(i == game.getTurnPlayerCounter() && animationManager.getMoving()){
                animationMovement(g);
        }else {
            renderPawn(pawn,pawn.getPositionX(), pawn.getPositionY(), g);
        }
        
    }

    public void animationMovement(Graphics g){
        renderPawn(game.getCurrentPlayer(), animationManager.getAnimationPosX(), animationManager.getAnimationPosY(), g);
        animationManager.startAnimation(endPlayerPos[0], endPlayerPos[1], path);
    }

    public void renderPawn(Player pawn, int posx1, int posy1, Graphics g){
        int cellWidth = getWidth() / gb.getGridSizeX();
            int cellHeight = getHeight() / gb.getGridSizeY();
            int posx = posx1;
            int posy = posy1;
            int rotatedX = posx;
            int rotatedY = (gb.getGridSizeY() - posy - 1);
            g.setColor(pawn.getColor());
            g.fillOval( rotatedX*cellWidth + cellWidth/10 , rotatedY*cellHeight + cellHeight/10 ,cellWidth - 2*cellWidth/10,cellHeight - 2*cellHeight/10);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            float strokeWidth = 3.0f;
            g2d.setStroke(new BasicStroke(strokeWidth));
            g2d.drawOval( rotatedX*cellWidth + cellWidth/10 , rotatedY*cellHeight + cellHeight/10 ,cellWidth - cellWidth/5,cellHeight - cellHeight/5);

    }


        
    

    public void movePawn(int[] startPlayerPos, int[] newPosition, Callback callback) {
        this.eventCallback = callback;
        this.startPlayerPos = startPlayerPos;
        this.endPlayerPos = newPosition;
        this.path = game.getPath(game.getCell(startPlayerPos[0], startPlayerPos[1]), game.getCell(endPlayerPos[0], endPlayerPos[1]), game.getDadi()[0] + game.getDadi()[1]);
        animationManager.setPosition(path[0].getPositionX(), path[0].getPositionY());
        animationManager.setMoving(true);
        repaint();
    }

    public void movePawnInstant(int[] startPlayerPos, int[] newPosition, Callback callback){
        this.eventCallback = callback;
        this.startPlayerPos = startPlayerPos;
        this.endPlayerPos = newPosition;
        this.path = new Cell[]{game.getCell(this.startPlayerPos[0],this.startPlayerPos[1]),game.getCell(endPlayerPos[0],endPlayerPos[1])};
        animationManager.setMoving(true);
        repaint();
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
}
