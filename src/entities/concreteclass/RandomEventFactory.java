package entities.concreteclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.concreteclass.concreteEvents.FugaEvent;
import entities.concreteclass.concreteEvents.MollaEvent;
import entities.concreteclass.concreteEvents.RerollEvent;
import entities.concreteclass.concreteEvents.StopEvent;
import entities.interfaces.Event;
import entities.interfaces.EventFactory;

public class RandomEventFactory {


    private List<EventFactory> factories;
    private final int STOP_TURN=1;
    private final int LOCANDA_TURN=3;

    public RandomEventFactory(){
        factories = new ArrayList<>();
        factories.add(new MollaEventFactory());
        factories.add(new RerollEventFactory());
        factories.add(new StopEventFactory());
        factories.add(new LocandaEventFactory());
    }

    public Event createEvent(){
        Random random = new Random();
        int randomIndex = random.nextInt(factories.size());
        return factories.get(randomIndex).createEvent();
    }

    public void addFugaCards(){
        factories.add(new FugaEventFactory());
    }
    
    

    class MollaEventFactory implements EventFactory{
        public Event createEvent(){
            return new MollaEvent();
        }
    }

    class RerollEventFactory implements EventFactory{
        public Event createEvent(){
            return new RerollEvent();
        }
    }

    class FugaEventFactory implements EventFactory{
        public Event createEvent(){
            return new FugaEvent();
        }
    }

    class StopEventFactory implements EventFactory{
        public Event createEvent(){
            return new StopEvent(STOP_TURN);
        }
    }

    class LocandaEventFactory implements EventFactory{
        public Event createEvent(){
            return new StopEvent(LOCANDA_TURN);
        }
    }
}
