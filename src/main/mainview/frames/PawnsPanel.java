package main.mainview.frames;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import config.configmodels.GameBoard;
import entities.interfaces.Player;
import main.mainmodels.Game;

public class PawnsPanel extends JPanel{
    

    private GameBoard gb;
    private Game game;

    public PawnsPanel(GameBoard gb, Game game){
        this.gb = gb;
        this.game = game;
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<gb.getNumberOfPlayers();i++){
            paintPawn(i, g);
        }
    }

    public void paintPawn(int i, Graphics g){
        int cellWidth = getWidth() / gb.getGridSizeX();
        int cellHeight = getHeight() / gb.getGridSizeY();
        Player pawn = game.getPawn(i);
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

    public void movePawn(){
        repaint();
    }
}
