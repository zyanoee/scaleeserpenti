package entities.concreteclass.concreteEvents;



import entities.interfaces.Callback;
import entities.interfaces.Event;
import main.mainmodels.Game;
import main.mainview.GameView;

public class CardEvent implements Event{


    private static CardEvent instance;

    public CardEvent(){

    }
    public void execute(Game g, GameView gw, Callback callback) {
        System.out.println("[DEBUG-EVENTO] Execute di CardEvent Avviato");
        Callback animationCallback = () -> {
            g.handleCard();
            gw.showDeck(callback);
        };
        gw.animTimer(500, animationCallback);
         
    }

    public static synchronized CardEvent getInstance() {
        if (instance == null) {
            instance = new CardEvent();
        }
        return instance;
    }

    public void accept(GameView gw){

    }

    
}
