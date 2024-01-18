package entities.interfaces;

import java.awt.Color;
import java.io.Serializable;

import entities.SpecialCellType;

public interface Cell extends Serializable {
    
    int getPositionX();
    int getPositionY();
    boolean isSpecial();
    SpecialCellType getType();
    String getDescription();
    Color getColor();
    void setColor(Color c);
    Cell getSuccessivo();
    void setSuccessivo(Cell c);
    void setNumber(int n);
    int getNumber();
    Event generateEvent();

    
}
