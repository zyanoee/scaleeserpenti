package entities.concreteclass.concreteEvents;

import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class MollaEvent implements Event {


    public MollaEvent(){
    
    }
    public void execute(Game g, GameView gw, Callback callbackEvent) {
        int[] dadi = g.getDadi();
        int[] exPos = new int[]{g.getPlayer().getPositionX(), g.getPlayer().getPositionY()};
        int[] newPos = g.muovi(dadi);
         Callback callback = () -> {  
            System.out.println("[DEBUG-EVENTO] Repainting pedina post Scala/Serpente");
            Callback callbackMovement = () -> {
                Event event = g.handleEvent();
                if(event!=null){
                    event.execute(g, gw, callbackEvent);
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

    
}