package entities.concreteclass.concreteEvents;

import entities.interfaces.Callback;
import entities.interfaces.Cell;
import entities.interfaces.Event;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class SerpenteEvent implements Event{

        Cell origin;
        Cell destinationCell;

    public SerpenteEvent(Cell origin, Cell destinationCell){
        this.origin = origin;
        this.destinationCell = destinationCell;
    }
    public void execute(RuleHandler model, GameInterface game, GameView gw, Callback callbackEvent) {

        int[] exPos = new int[]{origin.getPositionX(), origin.getPositionY()};
        int[] newPos =new int[]{destinationCell.getPositionX(), destinationCell.getPositionY() } ;
        gw.printMessage(" - Il Giocatore "+game.getTurnPlayerCounter()+" "+getDescription()+" -");
        game.movePosition(newPos);
        Callback callback = () -> {  
            gw.movePawnInstant(exPos, newPos, callbackEvent);
            
        };
        gw.animTimer(500, callback);
    }

    public Cell getCell(){
        return destinationCell;
    }

    public void accept(GameView gw){
        
    }

    public String getDescription(){
        return "non è stato molto fortunato, scivola via lungo il corpo del Sssssserpente";
    }
    
}
