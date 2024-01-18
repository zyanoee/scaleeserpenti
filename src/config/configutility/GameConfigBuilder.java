package config.configutility;

import config.configmodels.GameConfig;

public class GameConfigBuilder {
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

    public GameConfigBuilder(int numberOfPlayers, int gridSizeX, int gridSizeY) {
        this.numberOfPlayers = numberOfPlayers;
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
    }

    public GameConfigBuilder nScale(int nScale) {
        this.nScale = nScale;
        return this;
    }

    public GameConfigBuilder nSerpenti(int nSerpenti) {
        this.nSerpenti = nSerpenti;
        return this;
    }

    public GameConfigBuilder enableSpecialRules(boolean enableSpecialRules) {
        this.enableSpecialRules = enableSpecialRules;
        return this;
    }

    public GameConfigBuilder enableCards(boolean enableCards) {
        this.enableCards = enableCards;
        return this;
    }

    public GameConfigBuilder enableCardsAddon(boolean enableCardsAddon) {
        this.enableCardsAddon = enableCardsAddon;
        return this;
    }

    public GameConfigBuilder enableStopSquares(boolean enableStopSquares) {
        this.enableStopSquares = enableStopSquares;
        return this;
    }

    public GameConfigBuilder enablePrizeSquares(boolean enablePrizeSquares) {
        this.enablePrizeSquares = enablePrizeSquares;
        return this;
    }

    public GameConfigBuilder enableDoubleSixRule(boolean enableDoubleSixRule) {
        this.enableDoubleSixRule = enableDoubleSixRule;
        return this;
    }

    public GameConfigBuilder enableOneDice(boolean enableOneDice) {
        this.enableOneDice = enableOneDice;
        return this;
    }

    public GameConfigBuilder enableOneDiceEnd(boolean enableOneDiceEnd) {
        this.enableOneDiceEnd = enableOneDiceEnd;
        return this;
    }

    public GameConfigBuilder wantToEdit(boolean wantToEdit) {
        this.wantToEdit = wantToEdit;
        return this;
    }

    public GameConfigBuilder isAutomatic(boolean isAutomatic) {
        this.isAutomatic = isAutomatic;
        return this;
    }

    public GameConfig build() {
        GameConfig config = GameConfig.getInstance();
        config.setNumberOfPlayers(numberOfPlayers);
        config.setGridSizeX(gridSizeX);
        config.setGridSizeY(gridSizeY);
        config.setNScale(nScale);
        config.setNSerpenti(nSerpenti);
        config.setSpecialRules(enableSpecialRules);
        config.setCardsRule(enableCards);
        config.setCardsRuleAddon(enableCardsAddon);
        config.setBlockSquareRule(enableStopSquares);
        config.setPrizeSquareRule(enablePrizeSquares);
        config.setDoubleSixRule(enableDoubleSixRule);
        config.setOneDiceRule(enableOneDice);
        config.setOneDiceEndRule(enableOneDiceEnd);
        config.setEditing(wantToEdit);
        config.setAutomatic(isAutomatic);
        return config;
    }
}