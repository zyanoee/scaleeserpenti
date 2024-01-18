package entities.concreteclass.concreteCells;

import java.awt.Color;

import entities.SpecialCellType;
import entities.concreteclass.concreteEvents.StopEvent;
import entities.interfaces.Cell;
import entities.interfaces.Event;


public class StopCell implements Cell {

    private int number;
    private int positionX;
    private int positionY;
    private SpecialCellType type;
    private Color color;
    private Cell successivo;
    private final int STOP_TURN=1;

    public StopCell(int x, int y){
        this.positionX = x;
        this.positionY = y;
        this.type = SpecialCellType.BLOCCO;
        this.color = Color.MAGENTA;
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
        return "Stop! Fermo un turno";
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
        return new StopEvent(STOP_TURN);
    }

    public String toString(){
        return "["+getNumber()+"] ";
    }
}
