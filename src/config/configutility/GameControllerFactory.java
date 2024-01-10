package config.configutility;

import main.maincontrollers.GameController;
import main.maincontrollers.GameControllerAutomatic;
import main.mainmodels.Game;
import main.mainview.GameView;

public class GameControllerFactory {

    private boolean automatic;
    private Game model;
    private GameView view;

    public GameControllerFactory(Game model, GameView view, boolean automatic){
        this.automatic = automatic;
        this.model = model;
        this.view = view;
    }

    public GameController create(){
        if(automatic){
            return new GameControllerAutomatic(model, view);
        } else {
            return new GameController(model, view);
        }
    }
    
}
