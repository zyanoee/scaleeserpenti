package main.mainmodels;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import entities.concreteclass.Pawn;
import entities.concreteclass.RandomEventFactory;
import entities.interfaces.Cell;
import entities.interfaces.Event;
import entities.interfaces.Player;

public class Game {
    private Random scaleserpentiRNG;
    private GameBoard gboard;
    private boolean doubleSixRule;
    private boolean oneDiceRule;
    private boolean oneDiceEndRule;
    private boolean oneDiceActivated;
    private int gboardSize;
    private int nPlayers;
    private List<Player> players;
    private int turnPlayerCounter;
    private LinkedList<Event> cards;
    private Event lastCardDraw;

    private int dado1;
    private int dado2;

    //private List<Carta> carteFolli;


    

    public Game(GameConfig model, GameBoard gboard) {
        this.gboard = gboard;
        gboardSize = gboard.getGridSizeX()*gboard.getGridSizeY() - 1;
        nPlayers = model.getNumberOfPlayers();
        players = new ArrayList<>();
        for(int i = 0; i<nPlayers; i++){
            players.add(new Pawn(i));
        }
        turnPlayerCounter = 0;
        scaleserpentiRNG = new Random();
        cards = new LinkedList<>();
        if(model.isCardRuleEnabled()){
            RandomEventFactory ref = new RandomEventFactory();
            for(int i = 0; i<30;i++){
                cards.add(ref.createEvent());
            }
        }
        oneDiceRule = model.isOneDiceEnabled();
        oneDiceEndRule = model.isOneDiceEndEnabled();
        doubleSixRule = model.isDoubleSixEnabled();
        oneDiceActivated = false;

    }

    public int getTurnPlayerCounter(){
        return this.turnPlayerCounter;
    }

    public Player getPawn(int i){
        return this.players.get(i);
    }


    public int[] lanciaDadi(){
        dado1 = scaleserpentiRNG.nextInt(6)+1;
        dado2 = 0;
        if(!oneDiceRule && !oneDiceActivated){
            dado2 = scaleserpentiRNG.nextInt(6)+1;
        }
        return new int[]{dado1,dado2};
    }

    public void movePosition(int[] positions){
        Player currentPlayer = players.get(turnPlayerCounter);
        currentPlayer.move(positions[0], positions[1]);
    }


    public int[] muovi(int[] dadi){
        System.out.println(dadi[0]+","+dadi[1]);
        Player currentPlayer = players.get(turnPlayerCounter);
        int currentPosx = currentPlayer.getPositionX();
        int currentPosy = currentPlayer.getPositionY();
        Cell actualCell = gboard.getCell(currentPosx, currentPosy);
        int actualNumber = actualCell.getNumber();
        int midvalue = actualNumber + dadi[0] + dadi[1];
        int finalNumber = (midvalue <= gboardSize) ?  midvalue : gboardSize - (midvalue - gboardSize);
        Cell finalCell = gboard.getCellByNumber(finalNumber);
        currentPlayer.move(finalCell.getPositionX(), finalCell.getPositionY());
        return new int[]{currentPlayer.getPositionX(), currentPlayer.getPositionY()};
    }

    public Event handleEvent(){
        Player currentPlayer = players.get(turnPlayerCounter);
         int currentPosx = currentPlayer.getPositionX();
        int currentPosy = currentPlayer.getPositionY();
        Cell actualCell = gboard.getCell(currentPosx, currentPosy);
        return actualCell.generateEvent();
    }

    public int[] handleScaleSerpenti(){
        Player currentPlayer = players.get(turnPlayerCounter);
        return new int[]{ currentPlayer.getPositionX() , currentPlayer.getPositionY() };
    }

    public void handleReroll(){
        System.out.println("[DEBUG-GAME] Chiamato HandleReroll con valore player "+turnPlayerCounter);
        turnPlayerCounter = turnPlayerCounter!=0 ? turnPlayerCounter-1 : players.size()-1;
        System.out.println("[DEBUG-GAME] Nuovo valore player "+turnPlayerCounter);
    }

    public int handleNextTurn(){
        System.out.println("[DEBUG-GAME] Chiamato HandleNextTurn con valore player "+turnPlayerCounter);
        turnPlayerCounter = (turnPlayerCounter+1)%players.size();
        if(players.get(turnPlayerCounter).isBlocked()>0){
            players.get(turnPlayerCounter).setBlocked(players.get(turnPlayerCounter).isBlocked()-1);
            turnPlayerCounter = handleNextTurn();
        }
        System.out.println("[DEBUG-GAME] Nuovo valore player nel nuovo turno "+turnPlayerCounter);
        return turnPlayerCounter;
    }

    public Event handleCard(){
        Event randomCard = cards.pop();
        cards.addLast(randomCard);
        this.lastCardDraw = randomCard;
        return randomCard;
    }

    public void handleOneDiceEnd(boolean b){
        oneDiceActivated = b;

    }

     public int[] getDadi(){
        return new int[]{dado1, dado2};
    }

    public Player getPlayer(){
        return players.get(turnPlayerCounter);
    }

    public Event getLastCard(){
        return this.lastCardDraw;
    }

    public Cell getPlayerCell(){
        return gboard.getCell(getPlayer().getPositionX(), getPlayer().getPositionY());
    }

    public Cell getCell(int x, int y){
        return gboard.getCell(x,y);
    }

    public boolean checkDistance(){
        if(oneDiceEndRule){
            Player currentPlayer = players.get(turnPlayerCounter);
            if(gboardSize - getNumberFromPlayer(currentPlayer) <= 6){
                return true;
            }
        }
        return false;
    }

    public int getNumberFromPlayer(Player p){
        return gboard.getNumberByPos(p.getPositionX(), p.getPositionY());
    }

    public boolean isOneDiceEndEnabled(){
        return oneDiceEndRule;
    }

    public boolean isDoubleSixEnabled(){
        return doubleSixRule;
    }

    public Cell[] getPath(Cell start, Cell end, int lancio) {
        int nStart = start.getNumber();
        int nFinal = end.getNumber();
        int arrayDim = lancio;
    
        if (nStart + lancio > gboardSize) {
            Cell[] ret = new Cell[arrayDim];
            Cell currentCell = start;
    
            for (int i = 0; i < gboardSize - nStart; i++) {
                ret[i] = currentCell.getSuccessivo();
                currentCell = ret[i];
            }
    
            currentCell = end;

            for (int i = 0; i<gboardSize-nFinal;i++) {
                ret[2*gboardSize-nFinal-nStart-1-i] = currentCell;
                currentCell = currentCell.getSuccessivo();
            }
    
            return ret;
        } else {
            Cell[] ret = new Cell[arrayDim];
            Cell currentCell = start;
    
            for (int i = 0; i < arrayDim; i++) {
                ret[i] = currentCell.getSuccessivo();
                currentCell = ret[i];
            }
    
            return ret;
        }
    }

    

    
}
