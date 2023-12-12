package entities.concreteclass;

import java.awt.Color;

import entities.ColorPawnType;
import entities.interfaces.Cell;
import entities.interfaces.Player;

public class Pawn implements Player {

    int posx;
    int posy;
    int id;
    Color color;
    boolean blocked;

    public Pawn(int i){
        posx = 0;
        posy = 0;
        id = i;
        color = Color.decode(ColorPawnType.values()[id].getHexCode());
        blocked = false;
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
    public void move(int x, int y) {
        posx = x;
        posy = y;
    }

    @Override
    public int applyEffect(Cell cell) {
        return 0;
    }

    @Override
    public int applyEffect(NormalCell cell){
        return 0;
    }

    @Override
    public int applyEffect(ScalaCell cell) {
        move(cell.getUpperCell().getPositionX(), cell.getUpperCell().getPositionY());
        return 1;
    }

    @Override
    public int applyEffect(SerpenteCell cell) {
        move(cell.getLowerCell().getPositionX(), cell.getLowerCell().getPositionY());
        return 2;
    }

    @Override
    public int applyEffect(StopCell cell) {
        blocked = true;
        return 3;
    }

    @Override
    public int applyEffect(RerollCell cell){
        return 4;
    }

    @Override
    public int applyEffect(CardCell cell){
        return 5;
    }

    @Override
    public int applyEffect(WinCell cell){
        return 6;
    }




    
}
