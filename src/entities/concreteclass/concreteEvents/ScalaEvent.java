package entities.concreteclass.concreteEvents;

import entities.interfaces.Callback;
import entities.interfaces.Cell;
import entities.interfaces.Event;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class ScalaEvent implements Event {

    Cell origin;
    Cell destinationCell;

    public ScalaEvent(Cell origin, Cell destinationCell){
        this.destinationCell = destinationCell;
        this.origin = origin;
    }
    public void execute(RuleHandler model, GameInterface game, GameView gw, Callback callbackEvent) {
        int[] exPos =new int[]{origin.getPositionX(), origin.getPositionY() } ;
        int[] newPos =new int[]{destinationCell.getPositionX(), destinationCell.getPositionY() } ;
        gw.printMessage(" - Il Giocatore "+game.getTurnPlayerCounter()+" "+getDescription()+" -");
        game.movePosition(newPos);
        Callback callback = () -> {  
            gw.movePawnInstant(exPos, newPos,callbackEvent);
            
        };
        gw.animTimer(500, callback);
    }

    public Cell getCell(){
        return destinationCell;
    }

    public void accept(GameView gw){
        
    }

    public String getDescription(){
        return "ha raggiunto una Scala! Sale fino alla sua cima";
    }

    
}
