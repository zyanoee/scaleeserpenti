package entities.interfaces;

import java.util.List;

public interface GameBoardInterface {

    public void initializeGrid();
    public void generateElements();
    public void generateSpecialElements();
    public void setSuccessivi();
    public Cell[][] getGrid();
    public int getGridSizeX();
    public int getGridSizeY();
    public int getNScale();
    public int getNSerpenti();
    public List<Cell> getScale();
    public List<Cell> getSerpenti();
    public Cell getCell(int x, int y);
    public Cell getCellByNumber(int n);

    
}
