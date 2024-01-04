package entities.concreteclass;

import java.awt.Color;

import entities.ColorPawnType;
import entities.interfaces.Player;

public class Pawn implements Player {

    int posx;
    int posy;
    int id;
    Color color;
    int blocked;

    public Pawn(int i){
        posx = 0;
        posy = 0;
        id = i;
        color = Color.decode(ColorPawnType.values()[id].getHexCode());
        blocked = 0;
    }

    @Override
    public int getPositionX() {
        return posx;
    }

    @Override
    public int getPositionY() {
        return posy;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Color getColor() {
       return color;
    }

    @Override
    public int isBlocked(){
        return this.blocked;
    }

    @Override
    public void setBlocked(int n){
        this.blocked = n;
    }

    @Override
    public void move(int x, int y) {
        posx = x;
        posy = y;
    }

    
}
