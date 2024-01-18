package config.configutility;

import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.maincontrollers.GameController;
import main.maincontrollers.GameControllerAutomatic;
import main.mainview.GameView;

public class GameControllerFactory {

    private boolean automatic;
    private GameInterface model;
    private GameView view;
    private RuleHandler ruler;

    public GameControllerFactory(GameInterface model, RuleHandler ruler, GameView view, boolean automatic){
        this.automatic = automatic;
        this.model = model;
        this.ruler = ruler;
        this.view = view;
    }

    public GameController create(){
        if(automatic){
            return new GameControllerAutomatic(model,ruler, view);
        } else {
            return new GameController(model,ruler, view);
        }
    }
    
}
