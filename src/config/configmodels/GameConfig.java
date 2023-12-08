package config.configmodels;

// Design Pattern: Singleton Pattern
public class GameConfig {
    private static GameConfig instance;
    private int numberOfPlayers;
    private int gridSizeX;
    private int gridSizeY;
    private boolean enableSpecialRules = false;
    private boolean enableCards = false;
    private boolean enableStopSquares = false;
    private boolean enableReRollSquares = false;
    private GameConfig() {
        // Inizializza le impostazioni predefinite del gioco
        numberOfPlayers = 2;
        gridSizeX = 10;
        gridSizeY = 10;
        enableSpecialRules = false;
        
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
    public void setRerollSquareRule(boolean b){
        this.enableReRollSquares = b;
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
    public boolean isRerollEnabled(){
        return enableReRollSquares;
    }




    
    // Altre getter e setter per le impostazioni del gioco
}
