package entities.concreteclass;

import java.awt.Color;

import entities.SpecialCellType;
import entities.interfaces.Cell;
import entities.interfaces.Player;
import entities.interfaces.SpecialCell;

public class StopCell implements SpecialCell {

    private int number;
    private int positionX;
    private int positionY;
    private SpecialCellType type;
    private Color color;
    private Cell successivo;

    public StopCell(int x, int y){
        this.positionX = x;
        this.positionY = y;
        this.type = SpecialCellType.BLOCCO;
        this.color = Color.MAGENTA;
    }

    @Override
    public SpecialCellType getSpecialCellType() {
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
    public void onPlayerEnter(Player player) {
        //PLAYER.APPLY EFFETTO DELLA CARTA
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

    @Override
    public int apply(Player p) {
        return p.applyEffect(this);
    }
}
