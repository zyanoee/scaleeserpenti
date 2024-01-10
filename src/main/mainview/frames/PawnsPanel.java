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

import config.configmodels.GameBoard;
import entities.interfaces.Callback;
import entities.interfaces.Cell;
import entities.interfaces.Player;
import main.mainmodels.Game;

public class PawnsPanel extends JPanel{
    

    private GameBoard gb;
    private Game game;
    private int[] startPlayerPos;
    private int[] endPlayerPos;
    private Cell[] path;
    private Callback eventCallback;
    private int animPosX;
    private int animPosY;
    private static int animationIndex = 0;
    private static boolean moving = true;

    public PawnsPanel(GameBoard gb, Game game){
        this.gb = gb;
        this.game = game;
        setOpaque(false);
        this.startPlayerPos = new int[]{0,0};
        this.endPlayerPos = new int[]{0,0};
        this.path = new Cell[]{game.getCell(0, 0)};
        this.animPosX = 0;
        this.animPosY = 0;

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
            
         if(eventCallback!=null && animationIndex == path.length -1){
            SwingUtilities.invokeLater(() -> {
                animationIndex = 0;
                eventCallback.onComplete();
                moving=false;
                this.eventCallback=null;
                return;  });
        }
        for(int i=0;i<gb.getNumberOfPlayers();i++){
            paintPawn(i, g);
        }

       

    }

    public void paintPawn(int i, Graphics g){
        Player pawn = game.getPawn(i);
        if(i == game.getTurnPlayerCounter() && moving){
                animationMovement(g);
        }else {
            int cellWidth = getWidth() / gb.getGridSizeX();
            int cellHeight = getHeight() / gb.getGridSizeY();
            int posx = pawn.getPositionX();
            int posy = pawn.getPositionY();
            int rotatedX = posx;
            int rotatedY = (gb.getGridSizeY() - posy - 1);
            g.setColor(pawn.getColor());
            g.fillOval( rotatedX*cellWidth + cellWidth/10 , rotatedY*cellHeight + cellHeight/10 ,cellWidth - 2*cellWidth/10,cellHeight - 2*cellHeight/10);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            float strokeWidth = 3.0f; // Modifica il valore a tuo piacimento
            g2d.setStroke(new BasicStroke(strokeWidth));
            g2d.drawOval( rotatedX*cellWidth + cellWidth/10 , rotatedY*cellHeight + cellHeight/10 ,cellWidth - cellWidth/5,cellHeight - cellHeight/5);
        }
        
    }

    public void animationMovement(Graphics g){
        int cellWidth = getWidth() / gb.getGridSizeX();
        int cellHeight = getHeight() / gb.getGridSizeY();
        int posx = animPosX;
        int posy = animPosY;
        int rotatedX = posx;
        int rotatedY = (gb.getGridSizeY() - posy - 1);
        g.setColor(game.getPlayer().getColor());
        g.fillOval( rotatedX*cellWidth + cellWidth/10 , rotatedY*cellHeight + cellHeight/10 ,cellWidth - 2*cellWidth/10,cellHeight - 2*cellHeight/10);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        float strokeWidth = 3.0f; // Modifica il valore a tuo piacimento
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawOval( rotatedX*cellWidth + cellWidth/10 , rotatedY*cellHeight + cellHeight/10 ,cellWidth - cellWidth/5,cellHeight - cellHeight/5);

        if ((posx != endPlayerPos[0] || posy != endPlayerPos[1]) || animationIndex < path.length -1) {
            animationIndex = animationIndex + 1;
            animPosX = path[animationIndex].getPositionX();
            animPosY = path[animationIndex].getPositionY();
            Callback animCallback = () -> {
                repaint();
            };
            animTimer(150, animCallback);
        } 


}


        
    

    public void movePawn(int[] startPlayerPos, int[] newPosition, Callback callback) {
        this.eventCallback = callback;
        this.startPlayerPos = startPlayerPos;
        this.endPlayerPos = newPosition;
        this.path = game.getPath(game.getCell(startPlayerPos[0], startPlayerPos[1]), game.getCell(endPlayerPos[0], endPlayerPos[1]), game.getDadi()[0] + game.getDadi()[1]);
        this.animPosX = path[0].getPositionX();
        this.animPosY = path[0].getPositionY();
        moving = true;
        repaint();
    }

    public void movePawnInstant(int[] startPlayerPos, int[] newPosition, Callback callback){
        this.eventCallback = callback;
        this.startPlayerPos = startPlayerPos;
        this.endPlayerPos = newPosition;
        this.path = new Cell[]{game.getCell(this.startPlayerPos[0],this.startPlayerPos[1]),game.getCell(endPlayerPos[0],endPlayerPos[1])};
        moving = true;
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
