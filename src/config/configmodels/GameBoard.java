package config.configmodels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entities.concreteclass.CardCell;
import entities.concreteclass.NormalCell;
import entities.concreteclass.RerollCell;
import entities.concreteclass.ScalaCell;
import entities.concreteclass.SerpenteCell;
import entities.concreteclass.StopCell;
import entities.concreteclass.WinCell;
import entities.interfaces.Cell;

public class GameBoard {
    private Cell[][] grid;
    List<Cell> normalCells;
    private Random scaleserpentiRNG;
    private Random normalcellRNG = new Random();
    private GameConfig config;
    private int numPlayers;



    public GameBoard(GameConfig model) {

        config = model;
        grid = new Cell[model.getGridSizeX()][model.getGridSizeY()];
        normalCells = new ArrayList<>();
        scaleserpentiRNG = new Random();
        numPlayers = model.getNumberOfPlayers();
        initializeGrid();
    }

    private void initializeGrid() {
        generateNormalCell();
        generateWinCell();
        generateScalaCell();
        generateSerpenteCell();
        generateSpecialCell();
        setSuccessivi();
    }

    public int getNumberOfPlayers(){
        return this.numPlayers;
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

    public void generateWinCell(){
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
            if(normalCell1.getPositionY()<normalCell2.getPositionY()){
                scalaCell = new ScalaCell(normalCell1.getPositionX(), normalCell1.getPositionY());
                scalaCell.setUpperCell(normalCell2);
            } else {
                scalaCell = new ScalaCell(normalCell2.getPositionX(), normalCell2.getPositionY());
                scalaCell.setUpperCell(normalCell1);
            }
    
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
            if(normalCell1.getPositionY()>normalCell2.getPositionY()){
                scalaCell = new SerpenteCell(normalCell1.getPositionX(), normalCell1.getPositionY());
                scalaCell.setLowerCell(normalCell2);
            } else {
                scalaCell = new SerpenteCell(normalCell2.getPositionX(), normalCell2.getPositionY());
                scalaCell.setLowerCell(normalCell1);
            }

            grid[scalaCell.getPositionX()][scalaCell.getPositionY()] = scalaCell;

            
        } 
    }

    public void generateSpecialCell(){
        if(config.isSpecialRulesEnabled()){
            System.out.println(config.isCardRuleEnabled()+", "+config.isRerollEnabled()+", "+config.isStopRuleEnabled());
            if(config.isCardRuleEnabled()){
                generateCardCell();
            }
            if(config.isRerollEnabled()){
                generateRerollCell();
            }
            if(config.isStopRuleEnabled()){
                generateStopCell();
            }
        }
    }

    private void generateCardCell(){
        int nCardCell = scaleserpentiRNG.nextInt((grid.length/2)-(grid.length/4)) + grid.length/4;
        for(int i = 0; i<nCardCell;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            normalCells.remove(randomIndex);
            CardCell cardCell;
            cardCell = new CardCell(normalCell1.getPositionX(), normalCell1.getPositionY());

            grid[cardCell.getPositionX()][cardCell.getPositionY()] = cardCell;
        } 

    }

    private void generateRerollCell(){
        int nRerollCell = scaleserpentiRNG.nextInt((grid.length/2)-(grid.length/4)) + grid.length/4;
        for(int i = 0; i<nRerollCell;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            normalCells.remove(randomIndex);
            RerollCell rerollCell;
            rerollCell = new RerollCell(normalCell1.getPositionX(), normalCell1.getPositionY());

            grid[rerollCell.getPositionX()][rerollCell.getPositionY()] = rerollCell;
        } 

    }

    private void generateStopCell(){
        int nStopCell = scaleserpentiRNG.nextInt((grid.length/2)-(grid.length/4)) + grid.length/4;
        for(int i = 0; i<nStopCell;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            normalCells.remove(randomIndex);
            StopCell stopCell;
            stopCell = new StopCell(normalCell1.getPositionX(), normalCell1.getPositionY());

            grid[stopCell.getPositionX()][stopCell.getPositionY()] = stopCell;
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
}
