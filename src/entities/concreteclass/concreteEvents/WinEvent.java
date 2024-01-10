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
        gw.printMessage(" - Il Giocatore "+g.getTurnPlayerCounter()+" "+getDescription()+" -");
        
        callback.onComplete();
    }

    public void accept(GameView gw){
        
    }

    public String getDescription(){
        return "HA VINTO! Lunga vita al nostro campione!";
    }

    
}
