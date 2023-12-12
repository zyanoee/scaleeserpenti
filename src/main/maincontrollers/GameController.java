package main.maincontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.configview.GameBoardView;
import main.mainview.frames.MainframeJFrame;
import main.mainview.frames.PawnsPanel;
import main.mainmodels.Game;


public class GameController {

    private Game model;
    private MainframeJFrame view;
    private GameBoardView gbview;
    private PawnsPanel ppanel;

    

    public GameController(Game model, MainframeJFrame mainframe, GameBoardView gbview, PawnsPanel ppanel){

        this.model = model;
        this.view = mainframe;
        this.gbview = gbview;
        this.ppanel = ppanel;
        //view.showLanciaIDadi();
        mainframe.getDiceButton().addActionListener(new DiceRollListener());

    }

    class DiceRollListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] dadi = model.lanciaDadi();
            int sum = dadi[0]+dadi[1];
            model.muovi(dadi);
            //view.showDicesRolled(dadi); //TODO
            System.out.println("[DEBUG-CONTROLLER] Somma dadi: "+sum+"");
            ppanel.movePawn();
            int eventValue = model.handleEvent();
            controllerEvent(eventValue);
            model.handleNextTurn();
            //view.showLanciaIDadi()
           
        }

        public void controllerEvent(int i){
            System.out.println("[DEBUG-CONTROLLER] Player "+model.getTurnPlayerCounter()+" genera Evento "+i);
            try{
            switch (i) {
                case 1: //SCALE E SERPENTI
                case 2:
                    int[] newposition = model.handleScaleSerpenti();
                    model.movePosition(newposition);
                    Thread.sleep(1000);
                    ppanel.movePawn();
                    break;
                case 3:
                    //view.showStop() //TODO
                    break;
                case 4:
                    model.handleReroll();
                    //view.showReroll();
                    break;
                case 5:
                    //int cardid = model.handleCard()
                    //view.showCard(cardid)
                    //this.handleCardRule(cardid);
                    break;
                default:
                    break;
            }
        }catch (InterruptedException e ){
            e.printStackTrace();
        }
        }

    }
    
}
