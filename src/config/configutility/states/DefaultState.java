package config.configutility.states;

import java.awt.Color;
import java.awt.event.MouseEvent;


import config.configutility.EditBoardState;
import config.configview.EditBoardView;
import entities.interfaces.EditBoardInterface;

public class DefaultState implements EditBoardState {

    EditBoardView ebview;
    EditBoardInterface eboard;
    Color color;
    Color colorHover;
    boolean available;

    public DefaultState(){
        available = true;
        color = Color.decode("000000");
        colorHover = Color.decode("000000");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       
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

    
     
