package config.configutility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import config.configmodels.GameConfig;
import entities.concreteclass.concreteCells.CardCell;
import entities.concreteclass.concreteCells.LocandaCell;
import entities.concreteclass.concreteCells.MollaCell;
import entities.concreteclass.concreteCells.NormalCell;
import entities.concreteclass.concreteCells.RerollCell;
import entities.concreteclass.concreteCells.ScalaCell;
import entities.concreteclass.concreteCells.SerpenteCell;
import entities.concreteclass.concreteCells.StopCell;
import entities.interfaces.Cell;
import entities.interfaces.CellFactoryInterface;

public class CellFactory {

    private static int nScale;
    private static int nSerpenti;
    private static int nPrize;
    private static int nStop;
    private static int nCards;
    private static List<Cell> normalCells;
    private static List<CellFactoryInterface> scalaserpentefactories;
    private static List<CellFactoryInterface> eventcellfactories;
    private static Cell[][] grid;
    private static Random rngCelle = new Random();

    public CellFactory(GameConfig gc, List<Cell> normalCells, Cell[][] grid){
        CellFactory.normalCells = normalCells;
        CellFactory.grid = grid;
        CellFactory.nScale = gc.getNScale();
        CellFactory.nSerpenti = gc.getNSerpenti();
        CellFactory.nPrize = gc.isPrizeEnabled() ? gc.getGridSizeX()/3 : 0;
        CellFactory.nStop = gc.isStopRuleEnabled() ? gc.getGridSizeX()/3 : 0;
        CellFactory.nCards = gc.isCardRuleEnabled() ? gc.getGridSizeX()/3 : 0;
        scalaserpentefactories = new ArrayList<>();
        eventcellfactories = new ArrayList<>();
        scalaserpentefactories.add(ScalaCellFactory.getInstance());
        scalaserpentefactories.add(SerpenteCellFactory.getInstance());
        if(gc.isCardRuleEnabled()){eventcellfactories.add(CardCellFactory.getInstance());};
        if(gc.isStopRuleEnabled()){eventcellfactories.add(StopCellFactory.getInstance()); eventcellfactories.add(new LocandaCellFactory());};
        if(gc.isPrizeEnabled()){eventcellfactories.add(MollaCellFactory.getInstance()); eventcellfactories.add(new RerollCellFactory());};  
    }

    public Cell[][] populateGrid(){
        populateScalaSerpentiCells();
        populateEventCells();
        return grid;
    }

    public void populateScalaSerpentiCells(){
        Random rng = new Random();
        
        while(nScale > 0 || nSerpenti > 0){
            int randomIndex = rng.nextInt(scalaserpentefactories.size());
            int randomIndexCelle = rngCelle.nextInt(normalCells.size());
            Cell cell = scalaserpentefactories.get(randomIndex).createCell(normalCells.get(randomIndexCelle));
            grid[cell.getPositionX()][cell.getPositionY()] = cell;
        }
    }

    public Cell[][] populateEventCells(){
        Random rng = new Random();

        while(nCards > 0 || nPrize > 0 || nStop > 0){
            int randomIndex = rng.nextInt(eventcellfactories.size());
            int randomIndexCelle = rngCelle.nextInt(normalCells.size());
            Cell cell = eventcellfactories.get(randomIndex).createCell(normalCells.get(randomIndexCelle));
            grid[cell.getPositionX()][cell.getPositionY()] = cell;    
        }
        return grid;
    }

    

    static class ScalaCellFactory implements CellFactoryInterface{

        private static CellFactoryInterface instance;
        private ScalaCellFactory(){}

        public static CellFactoryInterface getInstance(){
            if(instance==null){
                instance = new ScalaCellFactory();
            }
            return instance;
        }

        @Override
        public Cell createCell(Cell cell) {
            if(cell.getPositionY()==grid.length-1){
                if(nSerpenti > 0){
                    return scalaserpentefactories.get(scalaserpentefactories.indexOf(SerpenteCellFactory.getInstance())).createCell(cell);
                } else {
                    return new NormalCell(cell.getPositionX(), cell.getPositionY());
                }
            }
            normalCells.remove(cell);
            Cell cell2 = cell;
            while(cell2.getPositionY() == cell.getPositionY()){
                int randomIndexCelle = rngCelle.nextInt(normalCells.size());
                cell2 = normalCells.get(randomIndexCelle);
            }
            normalCells.remove(cell2);
            Cell base;
            Cell cima;
            base = cell.getPositionY()<cell2.getPositionY() ? cell : cell2;
            cima = cell.getPositionY()<cell2.getPositionY() ? cell2 : cell;
            ScalaCell scalaCell = new ScalaCell(base.getPositionX(), base.getPositionY());
            scalaCell.setUpperCell(cima);
            nScale = nScale -1;
            if(nScale == 0){
                scalaserpentefactories.remove(ScalaCellFactory.getInstance());
            }
            return scalaCell;    
        }
    }

    static class SerpenteCellFactory implements CellFactoryInterface{

        private static CellFactoryInterface instance;
        private SerpenteCellFactory(){}

        public static CellFactoryInterface getInstance(){
            if(instance==null){
                instance = new SerpenteCellFactory();
            }
            return instance;
        }

        @Override
        public Cell createCell(Cell cell) {
            if(cell.getPositionY()==0){
                if(nScale > 0){
                    return scalaserpentefactories.get(scalaserpentefactories.indexOf(ScalaCellFactory.getInstance())).createCell(cell);
                } else {
                    return new NormalCell(cell.getPositionX(), cell.getPositionY());
                }
            }
            normalCells.remove(cell);
            Cell cell2 = cell;
            while(cell2.getPositionY() == cell.getPositionY()){
                int randomIndexCelle = rngCelle.nextInt(normalCells.size());
                cell2 = normalCells.get(randomIndexCelle);
            }
            normalCells.remove(cell2);
            Cell testa;
            Cell coda;
            coda = cell.getPositionY()<cell2.getPositionY() ? cell : cell2;
            testa = cell.getPositionY()<cell2.getPositionY() ? cell2 : cell;
            SerpenteCell serpenteCell = new SerpenteCell(testa.getPositionX(), testa.getPositionY());
            serpenteCell.setLowerCell(coda);
            nSerpenti = nSerpenti-1;
            if(nSerpenti == 0){
                scalaserpentefactories.remove(SerpenteCellFactory.getInstance());
            }
            return serpenteCell;    
        }
    }
    

    static class StopCellFactory implements CellFactoryInterface{

        private static CellFactoryInterface instance;
        private StopCellFactory(){}

        public static CellFactoryInterface getInstance(){
            if(instance==null){
                instance = new StopCellFactory();
            }
            return instance;
        }

        @Override
        public Cell createCell(Cell cell) {
            StopCell stopCell = new StopCell(cell.getPositionX(), cell.getPositionY());
            normalCells.remove(cell);
            nStop = nStop-1;
            if(nStop == 0){
                eventcellfactories.remove(StopCellFactory.getInstance());
                eventcellfactories.remove(LocandaCellFactory.getInstance());
            }
            return stopCell;

        }
    }

    static class LocandaCellFactory implements CellFactoryInterface{

        private static CellFactoryInterface instance;
        private LocandaCellFactory(){}

        public static CellFactoryInterface getInstance(){
            if(instance==null){
                instance = new LocandaCellFactory();
            }
            return instance;
        }

        @Override
        public Cell createCell(Cell cell) {
            LocandaCell locandaCell = new LocandaCell(cell.getPositionX(), cell.getPositionY());
            normalCells.remove(cell);
            nStop = nStop-1;
            if(nStop == 0){
                eventcellfactories.remove(StopCellFactory.getInstance());
                eventcellfactories.remove(LocandaCellFactory.getInstance());
            }
            return locandaCell;
        }
    }

    static class MollaCellFactory implements CellFactoryInterface{

        private static CellFactoryInterface instance;
        private MollaCellFactory(){}

        public static CellFactoryInterface getInstance(){
            if(instance==null){
                instance = new MollaCellFactory();
            }
            return instance;
        }

        @Override
        public Cell createCell(Cell cell) {
            MollaCell mollaCell = new MollaCell(cell.getPositionX(), cell.getPositionY());
            normalCells.remove(cell);
            nPrize = nPrize-1;
            if(nPrize == 0){
                eventcellfactories.remove(MollaCellFactory.getInstance());
                eventcellfactories.remove(RerollCellFactory.getInstance());
            }
            return mollaCell;
        }
    }

    static class RerollCellFactory implements CellFactoryInterface{

        private static CellFactoryInterface instance;
        private RerollCellFactory(){}

        public static CellFactoryInterface getInstance(){
            if(instance==null){
                instance = new RerollCellFactory();
            }
            return instance;
        }

        @Override
        public Cell createCell(Cell cell) {
            
            RerollCell rerollCell = new RerollCell(cell.getPositionX(), cell.getPositionY());
            normalCells.remove(cell);
            nPrize = nPrize-1;
            if(nPrize == 0){
                eventcellfactories.remove(MollaCellFactory.getInstance());
                eventcellfactories.remove(RerollCellFactory.getInstance());
            }
            return rerollCell;
        
        }
    }

    static class CardCellFactory implements CellFactoryInterface{

        private static final CellFactoryInterface INSTANCE = new CardCellFactory();
        private CardCellFactory(){}

        public static CellFactoryInterface getInstance(){
            return INSTANCE;
        }

        @Override
        public Cell createCell(Cell cell) {
            CardCell cardCell = new CardCell(cell.getPositionX(), cell.getPositionY());
            normalCells.remove(cell);
            nCards = nCards-1;
            if(nCards == 0){
                eventcellfactories.remove(CardCellFactory.getInstance());
            }
            return cardCell;
        }
    }


    
}


