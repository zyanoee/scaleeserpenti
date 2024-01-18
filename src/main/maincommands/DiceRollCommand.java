package main.maincommands;

import entities.interfaces.Callback;
import entities.interfaces.GameInterface;
import main.mainview.GameView;

public class DiceRollCommand implements Command {
    private GameInterface model;
    private GameView gw;
    private Command nextCommand;

    public DiceRollCommand(GameInterface model, GameView gw, Command nextCommand) {
        this.model = model;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
        gw.getDiceButton().setEnabled(false);
        gw.getDiceEndCheckbox().setEnabled(false);

        int[] dadi;
        dadi = model.lanciaDadi();
        int sum = dadi[0] + dadi[1];

        Callback callbackDices = () -> {
            gw.printMessage("- Il Giocatore " + model.getTurnPlayerCounter() + " Ha totalizzato un lancio di " + sum + " -");
            nextCommand.execute();
        };

        gw.showDicesRolled(dadi, callbackDices);
        
    }
}
