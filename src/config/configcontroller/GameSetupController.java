package config.configcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import config.configmodels.GameBoard;
import config.configmodels.GameConfig;
import config.configutility.ConfigFileHandler;
import config.configutility.GameConfigBuilder;
import config.configutility.GameControllerFactory;
import config.configview.EditBoardView;
import config.configview.GameSetupView;
import main.maincontrollers.GameController;
import main.mainmodels.Game;
import main.mainview.GameView;
import main.mainview.frames.MainframeJFrame;


public class GameSetupController {
    private GameSetupView view;
    private GameConfig model;
    private MainframeJFrame mainframe;

    public GameSetupController(GameSetupView view, MainframeJFrame mainframe) {
        this.view = view;
        this.mainframe = mainframe;
    }

    public void startListener(){
        view.addStartGameButtonListener(new StartGameButtonListener());
        view.addLoadButtonListener(new LoadConfigListener());
    }

    

    class StartGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Informazioni dal form
            int numPlayers = view.getNumPlayers();
            int gridSizeX = view.getGridSizeX();
            int gridSizeY = view.getGridSizeY();
            int nScale = view.getNScale();
            int nSerpenti = view.getNSerpenti();
            
            // Aggiorna il model del Gamecfg
            model = new GameConfigBuilder(numPlayers, gridSizeX, gridSizeY)
                .nScale(nScale)
                .nSerpenti(nSerpenti)
                .enableSpecialRules(view.isSpecialRulesEnabled())
                .enableCards(view.isCardsEnabled())
                .enableCardsAddon(view.isCardsAddonEnabled())
                .enableStopSquares(view.isStopRuleEnabled())
                .enablePrizeSquares(view.isPrizeEnabled())
                .enableDoubleSixRule(view.isDoubleSixEnabled())
                .enableOneDice(view.isOneDiceEnabled())
                .enableOneDiceEnd(view.isOneDiceEndEnabled())
                .wantToEdit(view.wantToEdit())
                .isAutomatic(false)
                .build();
            
            SwingUtilities.invokeLater(() -> {
                if(model.wantToEdit()){
                    initializeEditBoardView();
                }else{
                    initializeGameBoardView();
                }
                
                closeConfigFrame();
            });



        }

        private void closeConfigFrame(){
            view.disposeFrame();
        }

        private void initializeGameBoardView() {
            ConfigFileHandler.saveConfiguration(mainframe);
            int choice = JOptionPane.showConfirmDialog(mainframe,
            "Vuoi abilitare il gioco automatico?", "Gioco Automatico",
            JOptionPane.YES_NO_OPTION);
            model.setAutomatic(choice==JOptionPane.YES_OPTION);
            GameBoard board = new GameBoard(model);
            board.generateElements();
            Game game = new Game(model, board);
            GameView gw = new GameView(mainframe, board, model, game);
            GameControllerFactory gcf = new GameControllerFactory(game, gw, model.isAutomatic());
            GameController gc = gcf.create();
            gc.startListener();
            
 
        }



        private void initializeEditBoardView(){
            ConfigFileHandler.saveConfiguration(mainframe);
            int choice = JOptionPane.showConfirmDialog(mainframe,
            "Vuoi abilitare il gioco automatico?", "Gioco Automatico",
            JOptionPane.YES_NO_OPTION);
            model.setAutomatic(choice==JOptionPane.YES_OPTION);
            GameBoard editBoard = new GameBoard(model);
            EditBoardView ebview = new EditBoardView(editBoard);
            EditBoardController ebcontroller = new EditBoardController(ebview, editBoard, model, mainframe);
            ebcontroller.startListener();
            


        }
    }

    class LoadConfigListener implements ActionListener{
         public void actionPerformed(ActionEvent e) {
            ConfigFileHandler.loadConfiguration(view.getFrame());
            updateView();
         }

         public void updateView(){
            view.setNumberOfPlayers(model.getNumberOfPlayers());
            view.setGridSizeX(model.getGridSizeX());
            view.setGridSizeY(model.getGridSizeY());
            view.setNScale(model.getNScale());
            view.setNSerpenti(model.getNSerpenti());
            view.setSpecialRules(model.isSpecialRulesEnabled());
            view.setCardsRule(model.isCardRuleEnabled());
            view.setCardsRuleAddon(model.isCardsAddonEnabled());
            view.setBlockSquareRule(model.isStopRuleEnabled());
            view.setPrizeSquareRule(model.isPrizeEnabled());
            view.setDoubleSixRule(model.isDoubleSixEnabled());
            view.setOneDiceRule(model.isOneDiceEnabled());
            view.setOneDiceEndRule(model.isOneDiceEndEnabled());
         }
  
    }



   
}