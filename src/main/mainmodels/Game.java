package main.mainmodels;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


import config.configmodels.GameConfig;
import entities.concreteclass.Pawn;
import entities.concreteclass.RandomEventFactory;
import entities.interfaces.Cell;
import entities.interfaces.Event;
import entities.interfaces.GameBoardInterface;
import entities.interfaces.Player;


public class Game {
    private Random scaleserpentiRNG;
    private GameBoardInterface gboard;
    private boolean doubleSixRule;
    private boolean oneDiceRule;
    private boolean oneDiceEndRule;
    private boolean oneDiceActivated;
    private boolean moreCardsRule;
    private int gboardSize;
    private int nPlayers;
    private List<Player> players;
    private Map<Player, Integer> nCarteFuga;
    private int turnPlayerCounter;
    private LinkedList<Event> cards;
    private Event lastCardDraw;

    private int dado1;
    private int dado2;




    

    public Game(GameConfig model, GameBoardInterface gboard) {
        this.gboard = gboard;
        gboardSize = gboard.getGridSizeX()*gboard.getGridSizeY() - 1;
        nPlayers = model.getNumberOfPlayers();
        players = new ArrayList<>();
        nCarteFuga = new HashMap<>();

        for(int i = 0; i<nPlayers; i++){
            players.add(new Pawn(i));
            nCarteFuga.put(players.get(i), 0);
        }

        turnPlayerCounter = 0;
        scaleserpentiRNG = new Random();
        cards = new LinkedList<>();

        if(model.isCardRuleEnabled()){
            RandomEventFactory ref = new RandomEventFactory();
            if(model.isCardsAddonEnabled()){
                ref.addFugaCards();
            }
            for(int i = 0; i<30;i++){
                cards.add(ref.createEvent());
            }
        }

        oneDiceRule = model.isOneDiceEnabled();
        oneDiceEndRule = model.isOneDiceEndEnabled();
        doubleSixRule = model.isDoubleSixEnabled();
        moreCardsRule = model.isCardsAddonEnabled();
        oneDiceActivated = false;

    }


    public int[] lanciaDadi(){
        dado1 = scaleserpentiRNG.nextInt(6)+1;
        dado2 = 0;
        if(!oneDiceRule && !oneDiceActivated){
            dado2 = scaleserpentiRNG.nextInt(6)+1;
        }
        return new int[]{dado1,dado2};
    }

    public void movePosition(int[] positions){ //Muove in posizione specifica
        Player currentPlayer = players.get(turnPlayerCounter);
        currentPlayer.move(positions[0], positions[1]);
    }


    public int[] muovi(int[] dadi){ //Muove per lancio di dadi
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


    public int handleNextTurn(){
        turnPlayerCounter = (turnPlayerCounter+1)%players.size();
        if(players.get(turnPlayerCounter).isBlocked()>0){
            players.get(turnPlayerCounter).setBlocked(players.get(turnPlayerCounter).isBlocked()-1);
            turnPlayerCounter = handleNextTurn();
        }
        return turnPlayerCounter;
    }

     public void increaseFugaCard(Player p){
        nCarteFuga.put(p, nCarteFuga.get(p)+1);
    }

    public boolean handleFugaUsage(Player p, boolean playerChoice){
        if(playerChoice){
            int ncardsprevious = nCarteFuga.get(players.get(turnPlayerCounter));
            nCarteFuga.put(players.get(turnPlayerCounter), ncardsprevious-1);
            return true;
        }
        return false;
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


    //GETTER SETTERS & UTIL

    public int getTurnPlayerCounter(){
        return this.turnPlayerCounter;
    }

    public void setTurnPlayerCounter(int turn){
        this.turnPlayerCounter = turn;
    }

    public Player getPawn(int i){
        return this.players.get(i);
    }

    public int getNCarteFuga(Player p){
        return nCarteFuga.get(getPlayer());
    }

     public int[] getDadi(){
        return new int[]{dado1, dado2};
    }

    public int getNumberOfPlayers(){
        return players.size();
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

    public int getNumberFromPlayer(Player p){
        return gboard.getCell(p.getPositionX(), p.getPositionY()).getNumber();
    }

    public boolean isOneDiceEndEnabled(){
        return oneDiceEndRule;
    }

    public boolean isDoubleSixEnabled(){
        return doubleSixRule;
    }

    public boolean isMoreCardEnabled(){
        return moreCardsRule;
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

    public boolean checkDistance(){
        if(oneDiceEndRule){
            Player currentPlayer = players.get(turnPlayerCounter);
            if(gboardSize - getNumberFromPlayer(currentPlayer) <= 6){
                return true;
            }
        }
        return false;
    }

    

    
}
