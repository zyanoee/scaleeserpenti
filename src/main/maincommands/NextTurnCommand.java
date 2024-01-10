package main.maincommands;

import javax.swing.SwingUtilities;

import main.mainmodels.Game;
import main.mainview.GameView;

public class NextTurnCommand implements Command{

    private Game model;
    private GameView gw;
    private Command nextCommand;


    public NextTurnCommand(Game model, GameView gw, Command nextCommand) {
        this.model = model;
        this.gw = gw;
        this.nextCommand = nextCommand;
    }

    @Override
    public void execute() {
            
        SwingUtilities.invokeLater(() -> {
                int turnPlayer = model.handleNextTurn();
                gw.showLanciaIDadi(turnPlayer);
                gw.getDiceButton().setEnabled(true);
                SwingUtilities.invokeLater(() -> {
                    gw.getDiceEndCheckbox().setSelected(false);
                    if(model.checkDistance()){
                        gw.getDiceEndCheckbox().setEnabled(true);                                     
                    }
                    if(nextCommand!=null){
                        nextCommand.execute();
                    }
                });
            });
    }

}
