package config.configmodels;

// Design Pattern: Singleton Pattern
public class GameConfig {
    private static GameConfig instance;
    private int numberOfPlayers;
    private int gridSizeX;
    private int gridSizeY;
    private int nScale;
    private int nSerpenti;
    private boolean wantToEdit;
    private boolean enableSpecialRules = false;
    private boolean enableCards = false;
    private boolean enableCardsAddon = false;
    private boolean enableStopSquares = false;
    private boolean enablePrizeSquares = false;
    private boolean enableDoubleSixRule = false;
    private boolean enableOneDice = false;
    private boolean enableOneDiceEnd = false;

    private GameConfig() {
        // Inizializza le impostazioni predefinite del gioco
        numberOfPlayers = 2;
        gridSizeX = 10;
        gridSizeY = 10;
        nScale = Math.min(gridSizeX,gridSizeY);
        nSerpenti = Math.min(gridSizeX,gridSizeY);

        enableSpecialRules = false;
        wantToEdit = false;
        
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




    
    // Altre getter e setter per le impostazioni del gioco
}
