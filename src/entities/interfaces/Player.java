package entities.interfaces;

import java.awt.Color;


public interface Player {
    int getPositionX();
    int getPositionY();
    int getId();
    Color getColor();

    void move(int x, int y);
    int isBlocked();
    void setBlocked(int n);
}
    
