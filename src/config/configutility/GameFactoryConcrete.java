package config.configutility;

import config.configmodels.GameConfig;
import entities.interfaces.GameBoardInterface;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainmodels.Game;

public class GameFactoryConcrete implements GameFactory {

    //GameFactory specifico del gioco dove Game implementa sia GameInterface sia RuleHandler
    Game instance;

    public GameFactoryConcrete(GameConfig gconfig, GameBoardInterface gBoardInterface){
        instance = new Game(gconfig, gBoardInterface);
    }

    @Override
    public GameInterface getGameInterfaceI() {
        return instance;
    }

    @Override
    public RuleHandler getRuleHandler() {
        return instance;
    }
    
}
