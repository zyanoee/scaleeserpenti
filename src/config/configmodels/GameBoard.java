package config.configmodels;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.concreteclass.NormalCell;
import entities.concreteclass.ScalaCell;
import entities.concreteclass.SerpenteCell;
import entities.interfaces.Cell;

public class GameBoard {
    private Cell[][] grid;
    List<Cell> normalCells;
    private Random scaleserpentiRNG;
    private Random normalcellRNG = new Random();

    public GameBoard(int gridSizeX, int gridSizeY) {
        grid = new Cell[gridSizeX][gridSizeY];
        normalCells = new ArrayList<>();
        scaleserpentiRNG = new Random();
        initializeGrid();
    }

    private void initializeGrid() {
        generateNormalCell();
        generateScalaCell();
        generateSerpenteCell();
        setSuccessivi();

    }

    public Cell getCellValue(int x, int y) {
        return grid[x][y];
    }

    public void generateNormalCell(){
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                grid[i][j] = new NormalCell(i,j);
                normalCells.add(grid[i][j]);
            }
        }
    }

    

    public void generateScalaCell(){
        int nScale = scaleserpentiRNG.nextInt((grid.length/2)-(grid.length/4)) + grid.length/4;
        for(int i = 0; i<nScale;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            while(normalCell1.getPositionY()==grid.length-1){
                randomIndex = normalcellRNG.nextInt(normalCells.size());
                normalCell1 = normalCells.get(randomIndex);
            }
            normalCells.remove(randomIndex);
            Cell normalCell2 = normalCell1;
            while(normalCell1.getPositionY()==normalCell2.getPositionY()){
                randomIndex = normalcellRNG.nextInt(normalCells.size());
                normalCell2 = normalCells.get(randomIndex);
            }
            normalCells.remove(randomIndex);
            ScalaCell scalaCell;
            if(normalCell1.getPositionY()>normalCell2.getPositionY()){
                scalaCell = new ScalaCell(normalCell1.getPositionX(), normalCell1.getPositionY());
                scalaCell.setUpperCell(normalCell2);
            } else {
                scalaCell = new ScalaCell(normalCell2.getPositionX(), normalCell2.getPositionY());
                scalaCell.setUpperCell(normalCell1);
            }
            System.out.println("Creata scala in posizione "+scalaCell.getPositionX()+" "+scalaCell.getPositionY());
            grid[scalaCell.getPositionX()][scalaCell.getPositionY()]= scalaCell;

            
        }
    }

    public void generateSerpenteCell(){
       int nSerpenti = scaleserpentiRNG.nextInt((grid.length/2)-(grid.length/4)) + grid.length/4;
        for(int i = 0; i<nSerpenti;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            while(normalCell1.getPositionY()==0){
                randomIndex = normalcellRNG.nextInt(normalCells.size());
                normalCell1 = normalCells.get(randomIndex);
            }
            normalCells.remove(randomIndex);
            Cell normalCell2 = normalCell1;
            while(normalCell1.getPositionY()==normalCell2.getPositionY()){
                randomIndex = normalcellRNG.nextInt(normalCells.size());
                normalCell2 = normalCells.get(randomIndex);
            }
            normalCells.remove(randomIndex);
            SerpenteCell scalaCell;
            if(normalCell1.getPositionY()<normalCell2.getPositionY()){
                scalaCell = new SerpenteCell(normalCell1.getPositionX(), normalCell1.getPositionY());
                scalaCell.setLowerCell(normalCell2);
            } else {
                scalaCell = new SerpenteCell(normalCell2.getPositionX(), normalCell2.getPositionY());
                scalaCell.setLowerCell(normalCell1);
            }

            grid[scalaCell.getPositionX()][scalaCell.getPositionY()] = scalaCell;

            
        } 
    }

    public void setSuccessivi(){

        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[i].length;j++){
                if(i==0&j==0){
                    grid[i][j].setNumber(j);
                    continue;
                }
                if(i%2==0){
                    if(j==0){
                        grid[i-1][0].setSuccessivo(grid[i][j]);
                    }else{
                        grid[i][j-1].setSuccessivo(grid[i][j]);
                    }
                    grid[i][j].setNumber(j+i*grid[i].length);
                } else {
                    if(j==grid[i].length-1){
                        grid[i-1][grid[i].length-1].setSuccessivo(grid[i][j]);
                    }else{
                        grid[i][j+1].setSuccessivo(grid[i][j]);
                    }
                    grid[i][j].setNumber(grid[i].length-1-j+i*grid[i].length);
                }
            } 
        }
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
}
