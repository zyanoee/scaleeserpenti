package main.mainview;

import javax.swing.*;

import config.configview.GameBoardView;
import entities.ColorPawnType;

import java.awt.*;

public class PawnPanel extends JPanel {
    private Color colore;
    private int posy, posx; // Coordinate della pedina
    private int gridSizeX;
    private int gridSizeY;
    private int gridWidth;
    private int gridHeight;
    private GameBoardView gb;


    public PawnPanel(int id, int x, int y, GameBoardView gb) {
        this.colore = Color.decode(ColorPawnType.values()[id].getHexCode());
        this.gb = gb;
        gridSizeX = gb.getGridSizeX();
        gridSizeY = gb.getGridSizeY();
        gridWidth = gb.getWidth();
        gridHeight = gb.getHeight();
        this.posx = x;
        this.posy = y;


        // Imposta le dimensioni della pedina
        setPreferredSize(new Dimension(500/gridSizeX, 500/gridSizeY));
        setOpaque(false);
    }

    public int getX(){
        return this.posx;
    }

    public int getY(){
        return this.posy;
    }

    public void muovi(int x, int y) {
        gridWidth = gb.getWidth();
        gridHeight = gb.getHeight();
        this.posx = x*(gridWidth/gridSizeX);
        this.posy = y*(gridHeight/gridSizeY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna il cerchio riempito con il colore del giocatore
        g.setColor(colore);
        g.fillOval( this.getWidth()/10, this.getHeight()/10, this.getWidth() - 2*this.getWidth()/10, this.getHeight() - 2*this.getHeight()/10);

        // Disegna il bordo nero
        g.setColor(Color.BLACK);
        g.drawOval(this.getWidth()/10, this.getHeight()/10, this.getWidth() - 2*this.getWidth()/10, this.getHeight() - 2*this.getHeight()/10);
    }


  
}