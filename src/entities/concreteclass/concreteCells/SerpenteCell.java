package entities.concreteclass.concreteCells;

import java.awt.Color;

import entities.SpecialCellType;
import entities.concreteclass.concreteEvents.SerpenteEvent;
import entities.interfaces.Cell;
import entities.interfaces.Event;


public class SerpenteCell implements Cell {

    private int number;
    private int positionX;
    private int positionY;
    private SpecialCellType type;
    private Color color;
    private Cell lowerCell;
    private Cell successivo;

    public SerpenteCell(int x, int y){
        this.positionX = x;
        this.positionY = y;
        this.type = SpecialCellType.SERPENTE;
        this.lowerCell = null;
        this.color = Color.RED;
    }

    @Override
    public SpecialCellType getType() {
        return type;
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }


    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Serpente";
    }

    public void setLowerCell(Cell lowerCell) {
        this.lowerCell = lowerCell;
    }

    public Cell getLowerCell(){
        return this.lowerCell;
    }

    public void setColor(Color c){

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
        return new SerpenteEvent(this, lowerCell);
    }

    public String toString(){
        return "["+getNumber()+"] ";
    }

}
    

