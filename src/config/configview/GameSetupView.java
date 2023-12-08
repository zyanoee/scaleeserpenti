package config.configview;


import javax.swing.*;

import config.frames.GameConfigJFrame;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// Classe di View per l'interfaccia utente di configurazione
public class GameSetupView {
    private GameConfigJFrame frame;
    private JFormattedTextField numPlayersField;
    private JFormattedTextField gridSizeXField;
    private JFormattedTextField gridSizeYField;
    private JCheckBox specialRulesCheckbox;
    private JCheckBox cardsCheckbox;
    private JCheckBox stopSquaresCheckbox;  
    private JCheckBox reRollSquaresCheckbox;
    private JButton startGameButton;
    
    public GameSetupView() {
        frame = new GameConfigJFrame();
        numPlayersField = frame.getnPlayerField();
        gridSizeXField = frame.getGbLenghtField();
        gridSizeYField = frame.getGbHeightField();
        specialRulesCheckbox = frame.getSpecialRulesField();
        cardsCheckbox = frame.getCardsRule();
        stopSquaresCheckbox = frame.getStopRule();
        reRollSquaresCheckbox = frame.getRerollRule();
        startGameButton = frame.getStartGameButton();
        

        specialRulesCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (specialRulesCheckbox.isSelected()) {
                    stopSquaresCheckbox.setEnabled(true);
                    cardsCheckbox.setEnabled(true);
                    reRollSquaresCheckbox.setEnabled(true);
                } else {
                    stopSquaresCheckbox.setEnabled(false);
                    cardsCheckbox.setEnabled(false);
                    reRollSquaresCheckbox.setEnabled(false);
                }
            }
        });

    }
    
    public void addStartGameButtonListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }
    
    public int getNumPlayers() {
        return Integer.parseInt(numPlayersField.getText());
    }
    
    public int getGridSizeX() {
        return Integer.parseInt(gridSizeXField.getText());
    }
    
    public int getGridSizeY() {
        return Integer.parseInt(gridSizeYField.getText());
    }
    
    public boolean isSpecialRulesEnabled() {
        if(checkSpecials()){
            return false;
        }
        else return specialRulesCheckbox.isSelected();
    }
    public boolean isCardsEnabled() {
        if(cardsCheckbox.isEnabled()){
            return cardsCheckbox.isSelected();
        } else return false;
    }
    public boolean isStopRuleEnabled() {
        if(stopSquaresCheckbox.isEnabled()){
        return stopSquaresCheckbox.isSelected();
        } else return false;
    }
    public boolean isRerollEnabled() {
        if(reRollSquaresCheckbox.isEnabled()){
        return reRollSquaresCheckbox.isSelected();
        } else return false;
    }

    private boolean checkSpecials(){
        return (!cardsCheckbox.isSelected())&&(!stopSquaresCheckbox.isSelected())&&(!reRollSquaresCheckbox.isSelected());
    }

    public void showSetupScreen() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

}