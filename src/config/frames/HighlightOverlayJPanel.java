package config.frames;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import config.configutility.EditBoardState;
import entities.interfaces.EditBoardInterface;

public class HighlightOverlayJPanel extends JPanel {

    private EditBoardInterface editBoard;
    private int cellSizeX;
    private int cellSizeY;
    private int[] highlightedCell;
    private int[] secondHighlightedCell;
    private Color color;
    private Color colorEnlighted;

    public HighlightOverlayJPanel(EditBoardInterface editBoard){
        this.editBoard = editBoard;

        setOpaque(false);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintCell(g);
        paintEnd(g);
        
    }

    public void paintCell(Graphics g){
        if(highlightedCell!=null){
            
            int posx = highlightedCell[0];
            int posy = highlightedCell[1];
            this.cellSizeX = getWidth()/editBoard.getGridSizeX();
            this.cellSizeY = getHeight()/editBoard.getGridSizeY();
            g.setColor(color);
            g.fillRect(posx*cellSizeX, posy*cellSizeY, cellSizeX, cellSizeY);
        }
    }

    public void paintEnd(Graphics g){
        if(secondHighlightedCell!=null){
            int posx = secondHighlightedCell[0];
            int posy = secondHighlightedCell[1];
            this.cellSizeX = getWidth()/editBoard.getGridSizeX();
            this.cellSizeY = getHeight()/editBoard.getGridSizeY();
            g.setColor(colorEnlighted);
            g.fillRect(posx*cellSizeX, posy*cellSizeY, cellSizeX, cellSizeY);
        }
    }

    public void highlightCell(int x, int y, EditBoardState ebstate){
        
        highlightedCell = new int[]{x,y};
        color = ebstate.getColorHover();

        repaint();
    }

    public void highlightEnd(int x, int y, EditBoardState ebstate){
        secondHighlightedCell = new int[]{x,y};
        colorEnlighted = ebstate.getColor();
        
        repaint();

    }

    public void setNull(){
        this.highlightedCell=null;
    }

    public void setNullPermanent(){
        this.secondHighlightedCell = null;
    }

    public int[] getSelected(){
        return this.secondHighlightedCell;
    }
    
}
