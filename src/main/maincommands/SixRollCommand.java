package main.maincommands;

import entities.concreteclass.concreteEvents.RerollEvent;
import entities.interfaces.Callback;
import main.mainmodels.Game;
import main.mainview.GameView;

public class SixRollCommand implements Command{
    

    private Game model;
    private GameView gw;
    private Command nextCommand;


    public SixRollCommand(Game model, GameView gw, Command nextCommand) {
        this.model = model;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
       Callback sixrollCallback = () -> {
            nextCommand.execute();
        };

        int[] dadi = model.getDadi();
        if(model.isDoubleSixEnabled() && dadi[0]==6 && dadi[1]==6){
            gw.printMessage("- Doppio Sei! -");
            RerollEvent rollEvent = new RerollEvent();
            rollEvent.execute(model, gw, sixrollCallback);
        } else {
            sixrollCallback.onComplete();
        }
    }

}
