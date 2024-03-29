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
import config.configutility.GameFactory;
import config.configutility.GameFactoryConcrete;
import config.configview.EditBoardView;
import config.configview.GameSetupView;
import entities.interfaces.EditBoardInterface;
import entities.interfaces.GameBoardInterface;
import entities.interfaces.GameInterface;
import entities.interfaces.RuleHandler;
import main.maincontrollers.GameController;
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

    public void initializeGameBoardFromFile(GameBoardInterface gboard){
            int choice = JOptionPane.showConfirmDialog(mainframe,
            "Vuoi abilitare il gioco automatico?", "Gioco Automatico",
            JOptionPane.YES_NO_OPTION);
            model.setAutomatic(choice==JOptionPane.YES_OPTION);
            GameFactory gf = new GameFactoryConcrete(model, gboard);
            GameInterface game = gf.getGameInterfaceI();
            RuleHandler ruler = gf.getRuleHandler();
            GameView gw = new GameView(mainframe, gboard, model, game);
            GameControllerFactory gcf = new GameControllerFactory(game, ruler, gw, model.isAutomatic());
            GameController gc = gcf.create();
            gc.startListener();
            view.disposeFrame();

        }

    

    class StartGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            int numPlayers = view.getNumPlayers();
            int gridSizeX = view.getGridSizeX();
            int gridSizeY = view.getGridSizeY();
            int nScale = view.getNScale();
            int nSerpenti = view.getNSerpenti();
            
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
            ConfigFileHandler.saveConfiguration(mainframe, null);
            int choice = JOptionPane.showConfirmDialog(mainframe,
            "Vuoi abilitare il gioco automatico?", "Gioco Automatico",
            JOptionPane.YES_NO_OPTION);
            model.setAutomatic(choice==JOptionPane.YES_OPTION);
            GameBoardInterface board = new GameBoard(model);
            board.generateElements();
            GameFactory gf = new GameFactoryConcrete(model, board);
            GameInterface game = gf.getGameInterfaceI();
            RuleHandler ruler = gf.getRuleHandler();
            GameView gw = new GameView(mainframe, board, model, game);
            GameControllerFactory gcf = new GameControllerFactory(game, ruler, gw, model.isAutomatic());
            GameController gc = gcf.create();
            gc.startListener();
            
 
        }

        private void initializeEditBoardView(){
            int choice = JOptionPane.showConfirmDialog(mainframe,
            "Vuoi abilitare il gioco automatico?", "Gioco Automatico",
            JOptionPane.YES_NO_OPTION);
            model.setAutomatic(choice==JOptionPane.YES_OPTION);
            EditBoardInterface editBoard = new GameBoard(model);
            EditBoardView ebview = new EditBoardView(editBoard);
            EditBoardController ebcontroller = new EditBoardController(ebview, editBoard, model, mainframe);
            ebcontroller.startListener();
            
        }

        
    }

    class LoadConfigListener implements ActionListener{
         public void actionPerformed(ActionEvent e) {
            model = GameConfig.getInstance();
            GameBoardInterface loadedBoard = ConfigFileHandler.loadConfiguration(view.getFrame());
            updateView();
            if(loadedBoard!=null){
                initializeGameBoardFromFile(loadedBoard);
            }
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