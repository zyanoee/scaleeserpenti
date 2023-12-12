package config.configcontroller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;

import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import config.configview.GameBoardView;
import config.configview.GameSetupView;
import main.maincontrollers.GameController;
import main.mainmodels.Game;
import main.mainview.frames.MainframeJFrame;
import main.mainview.frames.PawnsPanel;

public class GameSetupController {
    private GameSetupView view;
    private GameConfig model;
    private MainframeJFrame mainframe;

    public GameSetupController(GameSetupView view, GameConfig model, MainframeJFrame mainframe) {
        this.view = view;
        this.model = model;
        this.mainframe = mainframe;

        view.addStartGameButtonListener(new StartGameButtonListener());

        
    }

    

    class StartGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Informazioni dal form
            int numPlayers = view.getNumPlayers();
            int gridSizeX = view.getGridSizeX();
            int gridSizeY = view.getGridSizeY();
            boolean enableSpecialRules = view.isSpecialRulesEnabled();
            boolean enableStopSquares = view.isStopRuleEnabled();
            boolean enableReRollSquares = view.isRerollEnabled();
            boolean enableCards = view.isCardsEnabled();


            // Aggiorna il model del Gamecfg
            model.setNumberOfPlayers(numPlayers);
            model.setGridSize(gridSizeX, gridSizeY);
            model.setSpecialRules(enableSpecialRules);
            model.setBlockSquareRule(enableStopSquares);
            model.setRerollSquareRule(enableReRollSquares);
            model.setCardsRule(enableCards);
            
            SwingUtilities.invokeLater(() -> {
                initializeGameBoardView();
            });



        }

        private void initializeGameBoardView() {

            mainframe.getGameZonePanel().removeAll();
            GameBoard board = new GameBoard(model);
            GameBoardView gbview = new GameBoardView(board);
            gbview.setPreferredSize(new Dimension(500,500));

            mainframe.getGameZonePanel().setLayout(new OverlayLayout(mainframe.getGameZonePanel()));
    
            Game game = new Game(model, board);
            PawnsPanel ppanel = new PawnsPanel(board, game);
            ppanel.setPreferredSize(new Dimension(500,500));
            mainframe.getGameZonePanel().add(ppanel);
            mainframe.getGameZonePanel().revalidate();
            mainframe.getGameZonePanel().repaint();
            GameController gc = new GameController(game, mainframe, gbview, ppanel);
            mainframe.getGameZonePanel().add(gbview);

            gbview.validate();
            gbview.repaint();
            ppanel.validate();
            ppanel.repaint();
            mainframe.getGameZonePanel().revalidate();
            mainframe.getGameZonePanel().repaint();
            mainframe.revalidate();
            mainframe.repaint();

            
        }
    }
}