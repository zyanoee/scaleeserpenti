package entities.concreteclass.concreteEvents;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class MollaEvent implements Event {


    public MollaEvent(){
    
    }
    public void execute(RuleHandler g, GameInterface game, GameView gw, Callback callbackEvent) {
        int[] dadi = game.getDadi();
        int[] exPos = new int[]{game.getCurrentPlayer().getPositionX(), game.getCurrentPlayer().getPositionY()};
        int[] newPos = game.muovi(dadi);
         Callback callback = () -> {  
            gw.printMessage(" - Il Giocatore "+game.getTurnPlayerCounter()+" "+getDescription()+" -");
            Callback callbackMovement = () -> {
                Event event = g.handleEvent();
                if(event!=null){
                    event.execute(g, game, gw, callbackEvent);
                } else {
                    callbackEvent.onComplete();
                }   
            };
            gw.movePawnInstant(exPos, newPos, callbackMovement);          
        };
        gw.animTimer(500, callback);
        
    }

    public void accept(GameView gw){
        gw.showCard(this);
    }

    public String getDescription(){
        return "ha fatto un bel balzo ed è avanzato dello stesso numero del lancio!";
    }

    
}