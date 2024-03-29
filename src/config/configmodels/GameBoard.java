package config.configmodels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import config.configutility.CellFactory;
import entities.concreteclass.concreteCells.NormalCell;
import entities.concreteclass.concreteCells.ScalaCell;
import entities.concreteclass.concreteCells.SerpenteCell;
import entities.concreteclass.concreteCells.WinCell;
import entities.interfaces.Cell;
import entities.interfaces.EditBoardInterface;


public class GameBoard implements EditBoardInterface{
    private Cell[][] grid;
    List<Cell> normalCells;
    List<Cell> scale;
    List<Cell> serpenti;
    private GameConfig config;
    private int nScale;
    private int nSerpenti;



    public GameBoard(GameConfig model) {

        config = model;
        grid = new Cell[model.getGridSizeX()][model.getGridSizeY()];
        normalCells = new ArrayList<>();
        scale = new ArrayList<>();
        serpenti = new ArrayList<>();
        nScale = model.getNScale();
        nSerpenti = model.getNSerpenti();
        initializeGrid();
    }

    public void initializeGrid() {
        generateNormalCell();
        generateWinCell();
        setSuccessivi();
    }

    public void generateElements(){
        CellFactory cFactory = new CellFactory(config, normalCells, grid);
        grid = cFactory.populateGrid();
        setSuccessivi();
    }

    public void generateSpecialElements(){
        CellFactory cFactory = new CellFactory(config, normalCells, grid);
        grid = cFactory.populateEventCells();
        setSuccessivi();
    }


    private void generateNormalCell(){
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                grid[i][j] = new NormalCell(i,j);
                if(i==0&&j==0){
                    continue;
                }
                normalCells.add(grid[i][j]);
            }
        }
    }

    private void generateWinCell(){
        int gridSizeX = config.getGridSizeX();
        int gridSizeY = config.getGridSizeY();
        if((gridSizeY - 1)%2==0){
            normalCells.removeIf(cella -> (cella.getPositionX() == (gridSizeX -1)) && (cella.getPositionY() == (gridSizeY -1))); 
            grid[gridSizeX -1][gridSizeY -1] = new WinCell(gridSizeX -1 , gridSizeY -1);
        } else {

            normalCells.removeIf(cella -> (cella.getPositionX() == 0) && (cella.getPositionY() == (gridSizeY -1))); 
            grid[0][gridSizeY - 1] = new WinCell(0, gridSizeY - 1);
        }
    }

    public void setSuccessivi(){

        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[i].length;j++){
                if(i==0&j==0){
                    grid[i][j].setNumber(j);
                    continue;
                }
                if(j%2==0){
                    if(i==0){
                        grid[0][j-1].setSuccessivo(grid[i][j]);
                    }else{
                        grid[i-1][j].setSuccessivo(grid[i][j]);
                    }
                    grid[i][j].setNumber(i+j*grid.length);
                } else {
                    if(i==grid.length-1){
                        grid[i][j-1].setSuccessivo(grid[i][j]);
                    }else{
                        grid[i+1][j].setSuccessivo(grid[i][j]);
                    }
                    grid[i][j].setNumber((grid.length - i)+j*grid.length - 1);
                }
            } 
        }
    }
    



    public boolean selectScala(int x, int y){
        return isNormal(x, y) && y!=grid.length-1 && normalCells.contains(grid[x][y]);
    }

    public boolean selectSerpente(int x, int y){
        return isNormal(x,y) && y!=0 && normalCells.contains(grid[x][y]);
    }

    public boolean addScala(int x, int y, int x2, int y2){
        Cell cell = grid[x][y];
        Cell cellCima = grid[x2][y2];
        if((!cellCima.isSpecial()) && normalCells.contains(cellCima) && y<y2){
            normalCells.remove(cellCima);
            normalCells.remove(cell);
            ScalaCell scalaCell = new ScalaCell(x,y);
            scalaCell.setUpperCell(cellCima);
            scale.add(scalaCell);
            grid[x][y]= scalaCell;
            return true;
        }
        return false;
    }

    public boolean addSerpente(int x, int y, int x2, int y2){
        Cell cell = grid[x][y];
        Cell cellCoda = grid[x2][y2];
        if((!cellCoda.isSpecial()) && normalCells.contains(cellCoda) && y>y2){
            normalCells.remove(cellCoda);
            normalCells.remove(cell);
            SerpenteCell serpenteCell = new SerpenteCell(x,y);
            serpenteCell.setLowerCell(cellCoda);
            serpenti.add(serpenteCell);
            grid[x][y]=serpenteCell;
            return true;
        }
        return false;

    }

    public boolean isNormal(int x, int y){
        return (!grid[x][y].isSpecial())&&normalCells.contains(grid[x][y]);
    }

    public boolean addRuleCell(Cell cell){
        grid[cell.getPositionX()][cell.getPositionY()]=cell;
        return true;
    }

    

    public Cell[][] getGrid() {
        return grid;
    }

    public int getGridSizeX(){
        return this.grid.length;
    }
    
    public int getGridSizeY(){
        return this.grid[0].length;
    }

    public Cell getCell(int x, int y){
        return this.grid[x][y];
    }

    public Cell getCellByNumber(int n){
        return Arrays.stream(this.grid)
        .flatMap(Arrays::stream)
        .filter(cell -> cell.getNumber() == n)
        .findFirst()
        .orElse(null);
    }

    public int getNScale(){
        return nScale;
    }

    public int getNSerpenti(){
        return nSerpenti;
    }

    public List<Cell> getScale(){
        return this.scale;
    }

    public List<Cell> getSerpenti(){
        return this.serpenti;
    }
}
