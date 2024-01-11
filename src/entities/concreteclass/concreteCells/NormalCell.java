package entities.concreteclass.concreteCells;

import java.awt.Color;

import entities.SpecialCellType;
import entities.interfaces.Cell;
import entities.interfaces.Event;

public class NormalCell implements Cell{

    private Cell successivo;
    private int number;
    private int positionX;
    private int positionY;
    private Color color;

    public NormalCell(int x, int y){
        this.positionX = x;
        this.positionY = y;
        this.color = Color.WHITE;
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
        return false;
    }

    @Override
    public String getDescription(){
        return "Cella base";
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

    public Event generateEvent(){
        return null;
    }

    public String toString(){
        return "["+getNumber()+"] ";
    }

    @Override
    public SpecialCellType getType() {
        return SpecialCellType.NORMAL;
    }

    
}
