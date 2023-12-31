package entities.interfaces;

import java.awt.Color;

public interface Cell {
    
    int getPositionX();
    int getPositionY();
    boolean isSpecial();
    String getDescription();
    Color getColor();
    void setColor(Color c);
    Cell getSuccessivo();
    void setSuccessivo(Cell c);
    void setNumber(int n);
    int getNumber();
    Event generateEvent();

    
}
