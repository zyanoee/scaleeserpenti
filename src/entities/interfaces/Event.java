package entities.interfaces;

import main.mainmodels.Game;
import main.mainview.GameView;

public interface Event{
    public void execute(Game model, GameView gw, Callback callback);
    public void accept(GameView gw);
    public String getDescription();
}
