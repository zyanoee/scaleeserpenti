package entities.concreteclass;

import java.awt.Color;

import entities.SpecialCellType;
import entities.interfaces.Cell;
import entities.interfaces.Player;
import entities.interfaces.SpecialCell;

public class ScalaCell implements SpecialCell {

    private int number;
    private int positionX;
    private int positionY;
    private SpecialCellType type;
    private Cell upperCell;
    private Color color;
    private Cell successivo;

    public ScalaCell(int x, int y){
        this.positionX = x;
        this.positionY = y;
        this.type = SpecialCellType.SCALA;
        this.upperCell = null;
        this.color = Color.BLUE;
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
        player.move(upperCell.getPositionX(), upperCell.getPositionY());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Scala";
    }

    public void setUpperCell(Cell upperCell) {
        this.upperCell = upperCell;
    }

    public Cell getUpperCell(){
        return this.upperCell;
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
    

