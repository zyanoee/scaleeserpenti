package entities.concreteclass.concreteEvents;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class WinEvent implements Event{

    public WinEvent(){

    }
    public void execute(Game g, GameView gw, Callback callback) {
        //g.handleWin();
        callback.onComplete();
    }

    public void accept(GameView gw){
        
    }

    
}
