package config.configutility;

import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;

public interface GameFactory {

    public GameInterface getGameInterfaceI();
    public RuleHandler getRuleHandler();

}
