package config.configcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import config.configmodels.GameConfig;
import config.configutility.EditBoardState;
import config.configutility.GameControllerFactory;
import config.configutility.states.ScalaState;
import config.configutility.states.SerpenteState;
import config.configutility.states.StopState;
import config.configutility.states.CardState;
import config.configutility.states.DefaultState;
import config.configutility.states.LocandaState;
import config.configutility.states.MollaState;
import config.configutility.states.RerollState;
import config.configview.EditBoardView;
import entities.interfaces.EditBoardInterface;
import main.maincontrollers.GameController;
import main.mainmodels.Game;
import main.mainview.GameView;
import main.mainview.frames.MainframeJFrame;

public class EditBoardController {
    
    private EditBoardView ebview;
    private EditBoardInterface eboard;
    private GameConfig gconfig;
    private MainframeJFrame mainframe;
    private EditBoardState currentState;


    public EditBoardController(EditBoardView view, EditBoardInterface editBoard, GameConfig gconfig, MainframeJFrame mainframe){
        this.ebview = view;
        this.eboard = editBoard;
        this.gconfig = gconfig;
        this.mainframe = mainframe;
        ebview.getEditButton().setEnabled(false);
        ebview.setAllRuleButtons(false);
        this.currentState = new DefaultState();

    }

    public void startListener(){
        ebview.getEditButton().addActionListener(new EditButtonListener());
        ebview.getHighlightOverlayJPanel().addMouseListener(new EditBoardMouseListener());
        ebview.getHighlightOverlayJPanel().addMouseMotionListener(new EditBoardMouseMotionListener());
        ebview.getScalaButton().addActionListener(e -> {
            if(ebview.getScalaButton().isEnabled()){currentState = new ScalaState(ebview, eboard, this);}
        });
        ebview.getSerpenteButton().addActionListener(e -> {
            if(ebview.getSerpenteButton().isEnabled()){currentState = new SerpenteState(ebview, eboard, this);}
        });    
        ebview.getCardButton().addActionListener(e->{
             if(ebview.getCardButton().isEnabled()){currentState = new CardState(ebview, eboard, this);}
        }) ;
        ebview.getStopButton().addActionListener(e->{
             if(ebview.getStopButton().isEnabled()){currentState = new StopState(ebview, eboard, this);}
        }) ;
        ebview.getLocandaButton().addActionListener(e->{
             if(ebview.getLocandaButton().isEnabled()){currentState = new LocandaState(ebview, eboard, this);}
        }) ;
        ebview.getRerollButton().addActionListener(e->{
             if(ebview.getRerollButton().isEnabled()){currentState = new RerollState(ebview, eboard, this);}
        }) ;
        ebview.getMollaButton().addActionListener(e->{
             if(ebview.getMollaButton().isEnabled()){currentState = new MollaState(ebview, eboard, this);}
        }) ;
    }

    public void checkTerminable(){
        if(eboard.getNScale() == eboard.getScale().size() && eboard.getNSerpenti() == eboard.getSerpenti().size()){
            ebview.getEditButton().setEnabled(true);
        }
    }

    public void setDefaultState(){
        this.currentState = new DefaultState();
    }

    public void checkButtons() {
        if(eboard.getNScale()==eboard.getScale().size()){ebview.getScalaButton().setEnabled(false);}
        if(eboard.getNSerpenti()==eboard.getSerpenti().size()){ebview.getSerpenteButton().setEnabled(false);}
    }

    public void enableRuleButtons(){
        if(eboard.getNScale() == eboard.getScale().size() && eboard.getNSerpenti() == eboard.getSerpenti().size()){
        ebview.getCardButton().setEnabled(gconfig.isCardRuleEnabled());
        ebview.getStopButton().setEnabled(gconfig.isStopRuleEnabled());
        ebview.getLocandaButton().setEnabled(gconfig.isStopRuleEnabled());
        ebview.getRerollButton().setEnabled(gconfig.isPrizeEnabled());
        ebview.getMollaButton().setEnabled(gconfig.isPrizeEnabled());
        }else{
            ebview.setAllRuleButtons(false);
        }
    }

    class EditButtonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            eboard.generateSpecialElements();
            Game game = new Game(gconfig, eboard);
            GameView gw = new GameView(mainframe, eboard, gconfig, game);
            GameControllerFactory gcf = new GameControllerFactory(game, gw, gconfig.isAutomatic());
            GameController gc = gcf.create();
            gc.startListener();
            ebview.disposeFrame();
    }
    
}

class EditBoardMouseListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        currentState.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

class EditBoardMouseMotionListener implements MouseMotionListener{

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       currentState.mouseMoved(e);
    }
    
}


}


