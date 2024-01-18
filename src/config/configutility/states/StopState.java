package config.configutility.states;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;



import config.configcontroller.EditBoardController;
import config.configutility.EditBoardState;
import config.configview.EditBoardView;
import entities.concreteclass.concreteCells.StopCell;
import entities.interfaces.EditBoardInterface;

public class StopState implements EditBoardState{

    EditBoardView ebview;
    EditBoardInterface eboard;
    EditBoardController ebcontroller;
    Color color;
    Color colorHover;
    boolean available;

    public StopState(EditBoardView ebview, EditBoardInterface eboard, EditBoardController ebcontroller){
        this.ebview = ebview;
        this.eboard = eboard;
        this.ebcontroller = ebcontroller;
        available = true;
        color = Color.decode("#ff00ff");
        colorHover = Color.decode("#fc90fc");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ebcontroller.checkButtons();
        int x = e.getX();
        int y = e.getY();
        x = x/(ebview.getGameBoardView().getWidth() / eboard.getGridSizeX());
        y = y/(ebview.getGameBoardView().getHeight() / eboard.getGridSizeY());
        int inverseY = eboard.getGridSizeY() - y - 1;
        boolean esit = eboard.isNormal(x,inverseY);
        if(esit){
            eboard.addRuleCell(new StopCell(x, inverseY));
            ebview.highlightPermanent(e.getX(), e.getY(), this);
            SwingUtilities.invokeLater(() -> {
            ebview.highlightSetNull();
        });
        }
        ebview.getGameBoardView().repaint();            
    }
       
    

    @Override
    public void mouseMoved(MouseEvent e) {
        int posx = e.getX();
        int posy = e.getY();
        ebview.highlightCell(posx,posy,this);
    }


    public boolean isAvailable(){
        return available;
    }

    public Color getColor(){
        return color;
    }

    public Color getColorHover(){
        return colorHover;
    }

}

    

