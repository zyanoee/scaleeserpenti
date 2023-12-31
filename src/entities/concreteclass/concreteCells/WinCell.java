package entities.concreteclass.concreteCells;

import java.awt.Color;

import entities.SpecialCellType;
import entities.concreteclass.concreteEvents.WinEvent;
import entities.interfaces.Cell;
import entities.interfaces.Event;
import entities.interfaces.SpecialCell;

public class WinCell implements SpecialCell{

    private Cell successivo;
    private int number;
    private int positionX;
    private int positionY;
    private Color color;
    private SpecialCellType type;

    public WinCell(int x, int y){
        this.positionX = x;
        this.positionY = y;
        this.color = Color.ORANGE;
        this.type = SpecialCellType.WIN;
    }

    @Override
    public int getPositionX(){
        return this.positionX;
    }

    @Override
    public int getPositionY(){
        return this.positionY;
    }

    @Override
    public boolean isSpecial(){
        return true;
    }

    @Override
    public String getDescription(){
        return "Vittoria!";
    }
    
    public void setColor(Color c){
        this.color = c;
    }

    public Color getColor(){
        return this.color;
    }

    public Cell getSuccessivo(){
        return this.successivo;
    }

    public void setSuccessivo(Cell c){
        this.successivo = c;
    }

    public void setNumber(int n){
        this.number = n;
    }

    public int getNumber(){
        return this.number;
    }

    @Override
    public SpecialCellType getSpecialCellType() {
        return this.type;
    }

    public Event generateEvent(){
        return new WinEvent();
    }

    public String toString(){
        return "["+getNumber()+"] ";
    }


    
}
