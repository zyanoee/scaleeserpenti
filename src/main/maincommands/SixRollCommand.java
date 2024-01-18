package main.maincommands;

import entities.concreteclass.concreteEvents.RerollEvent;
import entities.interfaces.Callback;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.mainview.GameView;

public class SixRollCommand implements Command{
    

    private GameInterface model;
    private GameView gw;
    private Command nextCommand;
    private RuleHandler ruler;


    public SixRollCommand(GameInterface model, RuleHandler ruler, GameView gw, Command nextCommand) {
        this.model = model;
        this.ruler = ruler;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
       Callback sixrollCallback = () -> {
            nextCommand.execute();
        };

        int[] dadi = model.getDadi();
        if(ruler.isDoubleSixEnabled() && dadi[0]==6 && dadi[1]==6){
            gw.printMessage("- Doppio Sei! -");
            RerollEvent rollEvent = new RerollEvent();
            rollEvent.execute(ruler, model, gw, sixrollCallback);
        } else {
            sixrollCallback.onComplete();
        }
    }

}
