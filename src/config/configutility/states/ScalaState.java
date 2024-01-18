package config.configutility.states;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import config.configcontroller.EditBoardController;
import config.configcontroller.EditViewState;
import config.configutility.EditBoardState;
import config.configview.EditBoardView;
import entities.interfaces.EditBoardInterface;

public class ScalaState implements EditBoardState {

    EditBoardView ebview;
    EditBoardInterface eboard;
    EditBoardController ebcontroller;
    Color color;
    Color colorHover;
    boolean available;

    public ScalaState(EditBoardView ebview, EditBoardInterface eboard, EditBoardController ebcontroller){
        this.ebview = ebview;
        this.eboard = eboard;
        this.ebcontroller = ebcontroller;
        available = true;
        color = Color.BLUE;
        colorHover = Color.decode("#477bf5");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!ebview.getEditButton().isEnabled()){
        switch(ebview.getState()){
            case NO_SELECTED:
                ebview.setAllRadio(false);
                ebcontroller.checkButtons();
                int x = e.getX();
                int y = e.getY();
                x = x/(ebview.getGameBoardView().getWidth() / eboard.getGridSizeX());
                y = y/(ebview.getGameBoardView().getHeight() / eboard.getGridSizeY());
                int inverseY = eboard.getGridSizeY() - y - 1;
                boolean esit = eboard.selectScala(x,inverseY);
                if(esit){
                    ebview.highlightPermanent(e.getX(), e.getY(), this);
                    SwingUtilities.invokeLater(() -> {
                    ebview.highlightSetNull();
                    ebview.setState(EditViewState.ONE_SELECTED);
                });
                }
                break;
                
            case ONE_SELECTED:
                ebview.setAllRadio(true);
                ebcontroller.enableRuleButtons();
                ebcontroller.checkButtons();
                x = e.getX();
                y = e.getY();
                x = x/(ebview.getGameBoardView().getWidth() / eboard.getGridSizeX());
                y = y/(ebview.getGameBoardView().getHeight() / eboard.getGridSizeY());
                inverseY = eboard.getGridSizeY() - y - 1;   
                int[] selected = ebview.getSelected();
                int inverseSelectedY = eboard.getGridSizeY() - selected[1] - 1;
                esit = eboard.addScala(selected[0],inverseSelectedY,x,inverseY);
                if(esit){
                    ebview.highlightSetNullPermanent();
                    SwingUtilities.invokeLater(() -> {
                        ebview.setState(EditViewState.NO_SELECTED);
                    });
                    SwingUtilities.invokeLater(() -> {
                        if(eboard.getScale().size() == eboard.getNScale()){
                            available=false;
                            JRadioButton sourceButton = ebview.getScalaButton();
                            sourceButton.setEnabled(false);
                            sourceButton.setSelected(false);
                            ebcontroller.setDefaultState();
                            ebcontroller.checkTerminable();
                            ebcontroller.enableRuleButtons();
                        }
                    });
                    ebview.getGameBoardView().repaint();
                }
                break;
         }
     }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       if(!ebview.getEditButton().isEnabled()){
        switch(ebview.getState()){
            case NO_SELECTED:
                int posx = e.getX();
                int posy = e.getY();
                ebview.highlightCell(posx,posy,this);
                break;
                
            case ONE_SELECTED:
                posx = e.getX();
                posy = e.getY();
                ebview.highlightCell2(posx,posy,this);
                break;
        }
        
       }
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

    
     
