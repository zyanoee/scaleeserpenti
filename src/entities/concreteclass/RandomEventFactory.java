package entities.concreteclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.concreteclass.concreteEvents.MollaEvent;
import entities.concreteclass.concreteEvents.RerollEvent;
import entities.concreteclass.concreteEvents.StopEvent;
import entities.interfaces.Event;
import entities.interfaces.EventFactory;

public class RandomEventFactory {


    private List<EventFactory> factories;

    public RandomEventFactory(){
        factories = new ArrayList<>();
        factories.add(new MollaEventFactory());
        factories.add(new RerollEventFactory());
        factories.add(new StopEventFactory());
    }

    public Event createEvent(){
        Random random = new Random();
        int randomIndex = random.nextInt(factories.size());
        return factories.get(randomIndex).createEvent();
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

    class StopEventFactory implements EventFactory{
        public Event createEvent(){
            return new StopEvent();
        }
    }
}
