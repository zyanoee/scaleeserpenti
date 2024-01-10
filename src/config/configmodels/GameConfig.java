package config.configmodels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;




public class GameConfig {
    private static GameConfig instance;
    private int numberOfPlayers;
    private int gridSizeX;
    private int gridSizeY;
    private int nScale;
    private int nSerpenti;
    private boolean enableSpecialRules = false;
    private boolean enableCards = false;
    private boolean enableCardsAddon = false;
    private boolean enableStopSquares = false;
    private boolean enablePrizeSquares = false;
    private boolean enableDoubleSixRule = false;
    private boolean enableOneDice = false;
    private boolean enableOneDiceEnd = false;
    private boolean wantToEdit;
    private boolean isAutomatic;


    private GameConfig() {
        
    }

    public static synchronized GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getGridSizeX() {
        return gridSizeX;
    }

    public int getGridSizeY() {
        return gridSizeY;
    }

    public void setEditing(boolean n){
        this.wantToEdit = n;
    }

    public boolean wantToEdit(){
        return this.wantToEdit;
    }

    
    public void setNumberOfPlayers(int n){
        this.numberOfPlayers = n;
    }

    public void setGridSizeX(int x){
        this.gridSizeX = x;
    }

    public void setGridSizeY(int y){
        this.gridSizeY = y;
    }

    public void setGridSize(int x, int y){
        this.gridSizeX = x;
        this.gridSizeY = y;
    }

    public void setNScale(int n){
        nScale = n;
    }

    public void setNSerpenti(int n){
        nSerpenti = n;
    }

    public int getNScale(){
        return nScale;
    }

    public int getNSerpenti(){
        return nSerpenti;
    }

    public boolean isAutomatic(){
        return isAutomatic;
    }

    public void setAutomatic(boolean b){
        isAutomatic = b;
    }
    


    //REGOLE SPECIALI
    public void setSpecialRules(boolean b){
        this.enableSpecialRules = b;
    }
    public void setCardsRule(boolean b){
        this.enableCards = b;
    }
    public void setBlockSquareRule(boolean b){
        this.enableStopSquares = b;
    }
    public void setPrizeSquareRule(boolean b){
        this.enablePrizeSquares = b;
    }
    public void setDoubleSixRule(boolean b){
        this.enableDoubleSixRule = b;
    }
    public void setOneDiceRule(boolean b){
        this.enableOneDice = b;
    }
    public void setOneDiceEndRule(boolean b){
        this.enableOneDiceEnd = b;
    }

    public void setCardsRuleAddon(boolean b){
        this.enableCardsAddon = b;
    }
    public boolean isSpecialRulesEnabled() {
        return enableSpecialRules;
    }
    public boolean isCardRuleEnabled(){
        return enableCards;
    }
    public boolean isStopRuleEnabled(){
        return enableStopSquares;
    }
    public boolean isPrizeEnabled(){
        return enablePrizeSquares;
    }
    public boolean isDoubleSixEnabled(){
        return enableDoubleSixRule;
    }

    public boolean isCardsAddonEnabled(){
        return enableCardsAddon;
    }
    public boolean isOneDiceEnabled(){
        return enableOneDice;
    }
    public boolean isOneDiceEndEnabled(){
        return enableOneDiceEnd;
    }


    public void writeConfiguration(PrintWriter writer){
        writer.println("numberOfPlayers="+numberOfPlayers);
        writer.println("gridSizeX="+gridSizeX);
        writer.println("gridSizeY="+gridSizeY);
        writer.println("nScale="+nScale);
        writer.println("nSerpenti="+nSerpenti);
        writer.println("enableSpecialRules="+enableSpecialRules);
        writer.println("enableCards="+enableCards);
        writer.println("enableCardsAddon="+enableCardsAddon);
        writer.println("enableStopSquares="+enableStopSquares);
        writer.println("enablePrizeSquares="+enablePrizeSquares);
        writer.println("enableDoubleSixRule="+enableDoubleSixRule);
        writer.println("enableOneDice="+enableOneDice);
        writer.println("enableOneDiceEnd="+enableOneDiceEnd);
    }

    public void readConfiguration(BufferedReader reader) throws IOException{
        Properties prop = new Properties();
        prop.load(reader);
        this.numberOfPlayers = Integer.parseInt(prop.getProperty("numberOfPlayers"));
        this.gridSizeX = Integer.parseInt(prop.getProperty("gridSizeX"));
        this.gridSizeY = Integer.parseInt(prop.getProperty("gridSizeY"));
        this.nScale = Integer.parseInt(prop.getProperty("nScale"));
        this.nSerpenti = Integer.parseInt(prop.getProperty("nSerpenti"));
        this.enableSpecialRules = Boolean.parseBoolean(prop.getProperty("enableSpecialRules"));
        this.enableCards = Boolean.parseBoolean(prop.getProperty("enableCards"));
        this.enableCardsAddon = Boolean.parseBoolean(prop.getProperty("enableCardsAddon"));
        this.enableStopSquares = Boolean.parseBoolean(prop.getProperty("enableStopSquares"));
        this.enablePrizeSquares = Boolean.parseBoolean(prop.getProperty("enablePrizeSquares"));
        this.enableDoubleSixRule = Boolean.parseBoolean(prop.getProperty("enableDoubleSixRule"));
        this.enableOneDice = Boolean.parseBoolean(prop.getProperty("enableOneDice"));
        this.enableOneDiceEnd = Boolean.parseBoolean(prop.getProperty("enableOneDiceEnd"));
    }

}
