package entities.interfaces;
import entities.SpecialCellType;

public interface SpecialCell extends Cell{
    SpecialCellType getSpecialCellType();  
    void onPlayerEnter(Player player);
}
