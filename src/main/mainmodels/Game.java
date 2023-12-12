package main.mainmodels;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import entities.concreteclass.Pawn;
import entities.interfaces.Cell;
import entities.interfaces.Player;

public class Game {
    private Random scaleserpentiRNG;
    private GameBoard gboard;
    private int gboardSize;
    private int nPlayers;
    private List<Player> players;
    private int turnPlayerCounter;

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

        //USE FACTORY PER CREARE LE CARTE E AGGIUNGILE ALLA QUEUE;
    }

    public int getTurnPlayerCounter(){
        return this.turnPlayerCounter;
    }

    public Player getPawn(int i){
        return this.players.get(i);
    }


    public int[] lanciaDadi(){
        //if(Regola solo un dado)
        dado1 = scaleserpentiRNG.nextInt(7)+1;
        dado2 = scaleserpentiRNG.nextInt(7)+1;
        return new int[]{dado1,dado2};
    }

    public void movePosition(int[] positions){
        Player currentPlayer = players.get(turnPlayerCounter);
        currentPlayer.move(positions[0], positions[1]);
    }

    public int[] muovi(int[] dadi){
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

    public int handleEvent(){
        Player currentPlayer = players.get(turnPlayerCounter);
         int currentPosx = currentPlayer.getPositionX();
        int currentPosy = currentPlayer.getPositionY();
        Cell actualCell = gboard.getCell(currentPosx, currentPosy);
        return actualCell.apply(currentPlayer);
    }

    public int[] handleScaleSerpenti(){
        Player currentPlayer = players.get(turnPlayerCounter);
        return new int[]{ currentPlayer.getPositionX() , currentPlayer.getPositionY() };
    }

    public void handleReroll(){
        turnPlayerCounter = turnPlayerCounter!=0 ? turnPlayerCounter-- : players.size()-1;
    }

    public void handleNextTurn(){
        turnPlayerCounter = (turnPlayerCounter+1)%players.size();
    }

    //public void handleCard(); //TODO

    


    
}
