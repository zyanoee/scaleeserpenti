package entities.concreteclass.concreteEvents;

import entities.interfaces.Callback;
import entities.interfaces.Cell;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class SerpenteEvent implements Event{

        Cell origin;
        Cell destinationCell;

    public SerpenteEvent(Cell origin, Cell destinationCell){
        this.origin = origin;
        this.destinationCell = destinationCell;
    }
    public void execute(Game model, GameView gw, Callback callbackEvent) {

        int[] exPos = new int[]{origin.getPositionX(), origin.getPositionY()};
        int[] newPos =new int[]{destinationCell.getPositionX(), destinationCell.getPositionY() } ;
        model.movePosition(newPos);
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
    
}
