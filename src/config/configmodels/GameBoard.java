package config.configmodels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entities.concreteclass.concreteCells.CardCell;
import entities.concreteclass.concreteCells.LocandaCell;
import entities.concreteclass.concreteCells.MollaCell;
import entities.concreteclass.concreteCells.NormalCell;
import entities.concreteclass.concreteCells.RerollCell;
import entities.concreteclass.concreteCells.ScalaCell;
import entities.concreteclass.concreteCells.SerpenteCell;
import entities.concreteclass.concreteCells.StopCell;
import entities.concreteclass.concreteCells.WinCell;
import entities.interfaces.Cell;

public class GameBoard {
    private Cell[][] grid;
    List<Cell> normalCells;
    List<ScalaCell> scale;
    List<SerpenteCell> serpenti;
    private Random scaleserpentiRNG;
    private Random normalcellRNG = new Random();
    private GameConfig config;
    private int numPlayers;
    private int nScale;
    private int nSerpenti;



    public GameBoard(GameConfig model) {

        config = model;
        grid = new Cell[model.getGridSizeX()][model.getGridSizeY()];
        normalCells = new ArrayList<>();
        scale = new ArrayList<>();
        serpenti = new ArrayList<>();
        scaleserpentiRNG = new Random();
        numPlayers = model.getNumberOfPlayers();
        nScale = model.getNScale();
        nSerpenti = model.getNSerpenti();
        initializeGrid();
    }

    private void initializeGrid() {
        System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre NormalCell: "+normalCells.size());
        generateNormalCell();
        System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Win: "+normalCells.size());
        generateWinCell();
        setSuccessivi();
    }

    public void generateElements(){
        System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Scala: "+normalCells.size());
        generateScalaCell();
        System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Serpente: "+normalCells.size());
        generateSerpenteCell();
        System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Speciali: "+normalCells.size());
        generateSpecialCell();
        setSuccessivi();
    }

    public void generateSpecialElements(){
        generateSpecialCell();
        setSuccessivi();
    }

    public int getNumberOfPlayers(){
        return this.numPlayers;
    }

    public Cell getCellValue(int x, int y) {
        return grid[x][y];
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

    

    private void generateScalaCell(){
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
            scale.add(scalaCell);

            
        }
    }


    private void generateSerpenteCell(){
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
            serpenti.add(scalaCell);     
        } 
    }

    public void generateSpecialCell(){
        if(config.isSpecialRulesEnabled()){
            System.out.println(config.isCardRuleEnabled()+", "+config.isPrizeEnabled()+", "+config.isStopRuleEnabled());
            if(config.isCardRuleEnabled()){
                System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Carte: "+normalCells.size());
                generateCardCell();
            }
            if(config.isPrizeEnabled()){
                System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Reroll: "+normalCells.size());
                generateRerollCell();
                System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Molla: "+normalCells.size());
                generateMollaCell();
            }
            if(config.isStopRuleEnabled()){
                System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Stop: "+normalCells.size());
                generateStopCell();
                System.out.println("[DEBUG-GAMEBOARD] Size delle normal cell pre Locanda: "+normalCells.size());
                generateLocandaCell();
            }
        }
    }

    private void generateCardCell(){
        int nCardCell = scaleserpentiRNG.nextInt((grid.length/3))+1;
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
        int nRerollCell = scaleserpentiRNG.nextInt((grid.length/3))+1;
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
        int nStopCell = scaleserpentiRNG.nextInt((grid.length/3))+1;
        for(int i = 0; i<nStopCell;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            normalCells.remove(randomIndex);
            StopCell stopCell;
            stopCell = new StopCell(normalCell1.getPositionX(), normalCell1.getPositionY());

            grid[stopCell.getPositionX()][stopCell.getPositionY()] = stopCell;
        } 
    }

    private void generateLocandaCell(){
        int nStopCell = scaleserpentiRNG.nextInt((grid.length/4))+1;
        for(int i = 0; i<nStopCell;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            normalCells.remove(randomIndex);
            LocandaCell stopCell;
            stopCell = new LocandaCell(normalCell1.getPositionX(), normalCell1.getPositionY());

            grid[stopCell.getPositionX()][stopCell.getPositionY()] = stopCell;
        } 
    }

    private void generateMollaCell(){
        int nMollaCell = scaleserpentiRNG.nextInt((grid.length/3))+1;
        for(int i = 0; i<nMollaCell;i++){
            int randomIndex = normalcellRNG.nextInt(normalCells.size());
            Cell normalCell1 = normalCells.get(randomIndex);
            normalCells.remove(randomIndex);
            MollaCell mollaCell;
            mollaCell = new MollaCell(normalCell1.getPositionX(), normalCell1.getPositionY());

            grid[mollaCell.getPositionX()][mollaCell.getPositionY()] = mollaCell;
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

    public boolean addScalaSerpente(int x, int y, int x2, int y2, boolean isScala){
        if(isScala){
            return addScala(x,y,x2,y2);
        } else {
            return addSerpente(x,y,x2,y2);
        }
    }

    public boolean selectScalaSerpente(int x, int y, boolean isScala){
        if(isScala){
            return selectScala(x,y);
        } else {
            return selectSerpente(x,y);
        }
    }

    private boolean selectScala(int x, int y){
        return isNormal(x, y) && y!=grid.length-1 && normalCells.contains(grid[x][y]);
    }

    private boolean selectSerpente(int x, int y){
        return isNormal(x,y) && y!=0 && normalCells.contains(grid[x][y]);
    }

    private boolean addScala(int x, int y, int x2, int y2){
        Cell cell = grid[x][y];
        Cell cellCima = grid[x2][y2];
        if((!cellCima.isSpecial()) && normalCells.contains(cellCima) && y<y2){
            System.out.println("SCALA Y1 = "+y+" , Y2 = "+y2);
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

    private boolean addSerpente(int x, int y, int x2, int y2){
        Cell cell = grid[x][y];
        Cell cellCoda = grid[x2][y2];
        if((!cellCoda.isSpecial()) && normalCells.contains(cellCoda) && y>y2){
            System.out.println("SERPENTE Y1 = "+y+" , Y2 = "+y2);
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
        return !grid[x][y].isSpecial();
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

    public int getNumberByPos(int x, int y){
        Cell cell = grid[x][y];
        return cell.getNumber();
    }

    public int getNScale(){
        return nScale;
    }

    public int getNSerpenti(){
        return nSerpenti;
    }

    public List<ScalaCell> getScale(){
        return this.scale;
    }

    public List<SerpenteCell> getSerpenti(){
        return this.serpenti;
    }
}
