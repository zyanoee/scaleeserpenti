package config.configcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import config.configview.EditBoardView;
import main.maincontrollers.GameController;
import main.mainmodels.Game;
import main.mainview.GameView;
import main.mainview.frames.MainframeJFrame;

public class EditBoardController {
    
    private EditBoardView ebview;
    private GameBoard eboard;
    private GameConfig gconfig;
    private MainframeJFrame mainframe;
    private boolean isScala;


    public EditBoardController(EditBoardView view, GameBoard editBoard, GameConfig gconfig, MainframeJFrame mainframe){
        this.ebview = view;
        this.eboard = editBoard;
        this.gconfig = gconfig;
        this.mainframe = mainframe;
        this.isScala=true;
    }

    public void startListener(){
        ebview.getEditButton().addActionListener(new EditButtonListener());
        ebview.getHighlightOverlayJPanel().addMouseListener(new EditBoardMouseListener());
        ebview.getHighlightOverlayJPanel().addMouseMotionListener(new EditBoardMouseMotionListener());
    }

    class EditButtonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            eboard.generateSpecialElements();
            Game game = new Game(gconfig, eboard);
            GameView gw = new GameView(mainframe, eboard, gconfig, game);
            GameController gc = new GameController(game, gw);
            gc.startListener();
            ebview.disposeFrame();
    }
    
}

class EditBoardMouseListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!ebview.getEditButton().isEnabled()){
        switch(ebview.getState()){
            case NO_SELECTED:
                int x = e.getX();
                int y = e.getY();
                x = x/(ebview.getGameBoardView().getWidth() / eboard.getGridSizeX());
                y = y/(ebview.getGameBoardView().getHeight() / eboard.getGridSizeY());
                int inverseY = eboard.getGridSizeY() - y - 1;
                boolean esit = eboard.selectScalaSerpente(x,inverseY,isScala);
                if(esit){
                    ebview.highlightPermanent(e.getX(), e.getY());
                    SwingUtilities.invokeLater(() -> {
                    ebview.highlightSetNull();
                    ebview.setState(EditViewState.ONE_SELECTED);
                });
                }
                break;
                
            case ONE_SELECTED:
                x = e.getX();
                y = e.getY();
                x = x/(ebview.getGameBoardView().getWidth() / eboard.getGridSizeX());
                y = y/(ebview.getGameBoardView().getHeight() / eboard.getGridSizeY());
                inverseY = eboard.getGridSizeY() - y - 1;   
                int[] selected = ebview.getSelected();
                int inverseSelectedY = eboard.getGridSizeY() - selected[1] - 1;
                System.out.println("[DEBUG-CONTROLLEREDIT] Selezionati: "+selected[0]+","+inverseSelectedY+" Nuovi: "+x+","+inverseY);
                esit = eboard.addScalaSerpente(selected[0],inverseSelectedY,x,inverseY,isScala);
                if(esit){
                    ebview.highlightSetNullPermanent();
                    SwingUtilities.invokeLater(() -> {
                        ebview.setState(EditViewState.NO_SELECTED);
                    });
                    SwingUtilities.invokeLater(() -> {
                        if(eboard.getScale().size() == eboard.getNScale()){
                            isScala = false;
                        }
                        if(eboard.getSerpenti().size() == eboard.getNSerpenti()){
                            ebview.getEditButton().setEnabled(true);
                        }
                    });
                    ebview.getGameBoardView().showScalaSerpente();
                }
                break;
         }
     }
        

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
       if(!ebview.getEditButton().isEnabled()){
        switch(ebview.getState()){
            case NO_SELECTED:
                int posx = e.getX();
                int posy = e.getY();
                ebview.highlightCell(posx,posy,isScala);
                break;
                
            case ONE_SELECTED:
                posx = e.getX();
                posy = e.getY();
                ebview.highlightCell2(posx,posy,isScala);
                break;


        }
        
       }
    }
    
}
}


