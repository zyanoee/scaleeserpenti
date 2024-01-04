package config.configview;


import javax.swing.*;

//import config.frames.GameConfigJFrame;
import config.frames.GameConfigJFrame2;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// Classe di View per l'interfaccia utente di configurazione
public class GameSetupView {
    private GameConfigJFrame2 frame;
    private JFormattedTextField numPlayersField;
    private JFormattedTextField gridSizeXField;
    private JFormattedTextField gridSizeYField;
    private JFormattedTextField nScale;
    private JFormattedTextField nSerpenti;
    private JCheckBox specialRulesCheckbox;
    private JCheckBox cardsCheckbox;
    private JCheckBox stopSquaresCheckbox;  
    private JCheckBox prizeSquaresCheckbox;
    private JCheckBox doubleSixCheckbox;
    private JCheckBox cardsAddonCheckbox;
    private JCheckBox oneDiceCheckbox;  
    private JCheckBox oneDiceEndCheckbox;
    private JCheckBox wantToEditCheckBox;
    private JButton startGameButton;
    private JButton loadButton;
    
    public GameSetupView() {
        frame = new GameConfigJFrame2();
        numPlayersField = frame.getnPlayerField();
        gridSizeXField = frame.getGbLenghtField();
        gridSizeYField = frame.getGbHeightField();
        nScale = frame.getnScaleField();
        nSerpenti = frame.getnSerpentiField();
        specialRulesCheckbox = frame.getSpecialRulesField();
        cardsCheckbox = frame.getCardsRule();
        stopSquaresCheckbox = frame.getStopRule();
        prizeSquaresCheckbox = frame.getPrizeRule();
        startGameButton = frame.getStartGameButton();
        doubleSixCheckbox = frame.getDoubleSixRule();
        oneDiceCheckbox = frame.getOneDiceRule();
        oneDiceEndCheckbox = frame.getOneDiceEndRule();
        wantToEditCheckBox = frame.getWantToEditCheckbox();
        cardsAddonCheckbox = frame.getCardsRuleAddon();
        loadButton = frame.getLoadOptionsButton();
        

        specialRulesCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (specialRulesCheckbox.isSelected()) {
                    stopSquaresCheckbox.setEnabled(true);
                    cardsCheckbox.setEnabled(true);
                    prizeSquaresCheckbox.setEnabled(true);
                    doubleSixCheckbox.setEnabled(true);
                    oneDiceCheckbox.setEnabled(true);
                    oneDiceEndCheckbox.setEnabled(true);
                } else {
                    stopSquaresCheckbox.setEnabled(false);
                    cardsCheckbox.setEnabled(false);
                    prizeSquaresCheckbox.setEnabled(false);
                    doubleSixCheckbox.setEnabled(false);
                    oneDiceCheckbox.setEnabled(false);
                    oneDiceEndCheckbox.setEnabled(false);
                    cardsAddonCheckbox.setEnabled(false);
                }
            }
        });

        cardsCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if(cardsCheckbox.isSelected()){
                    cardsAddonCheckbox.setEnabled(true);
                } else {
                    cardsAddonCheckbox.setSelected(false);
                    cardsAddonCheckbox.setEnabled(false);
                }
            }
        });

        oneDiceCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if(oneDiceCheckbox.isSelected()){
                    oneDiceEndCheckbox.setSelected(false);
                    doubleSixCheckbox.setSelected(false);
                    oneDiceEndCheckbox.setEnabled(false);
                    doubleSixCheckbox.setEnabled(false);
                } else {
                    oneDiceEndCheckbox.setEnabled(true);
                    doubleSixCheckbox.setEnabled(true);
                }
            }
        });

         oneDiceEndCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if(oneDiceEndCheckbox.isSelected()){
                    oneDiceCheckbox.setSelected(false);
                    oneDiceCheckbox.setEnabled(false);
                } else {
                    oneDiceCheckbox.setEnabled(true);
                }
            }
        });

        doubleSixCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if(doubleSixCheckbox.isSelected()){
                    oneDiceCheckbox.setSelected(false);
                    oneDiceCheckbox.setEnabled(false);
                } else {
                    oneDiceCheckbox.setEnabled(true);
                }
            }
        });

    }
    
    public void addStartGameButtonListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }

    public void addLoadButtonListener(ActionListener listener){
        loadButton.addActionListener(listener);
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
    public boolean isPrizeEnabled() {
        if(prizeSquaresCheckbox.isEnabled()){
        return prizeSquaresCheckbox.isSelected();
        } else return false;
    }
    public boolean isDoubleSixEnabled() {
        if(doubleSixCheckbox.isEnabled()){
        return doubleSixCheckbox.isSelected();
        } else return false;
    }
    public boolean isOneDiceEnabled() {
        if(oneDiceCheckbox.isEnabled()){
        return oneDiceCheckbox.isSelected();
        } else return false;
    }
    public boolean isOneDiceEndEnabled() {
        if(oneDiceEndCheckbox.isEnabled()){
        return oneDiceEndCheckbox.isSelected();
        } else return false;
    }

    public boolean isCardsAddonEnabled(){
        if(cardsAddonCheckbox.isEnabled()){
            return cardsAddonCheckbox.isSelected();
        }else return false;
    }

    private boolean checkSpecials(){
        return (!cardsCheckbox.isSelected())&&(!stopSquaresCheckbox.isSelected())&&(!prizeSquaresCheckbox.isSelected())
        &&(!doubleSixCheckbox.isSelected())&&(!oneDiceCheckbox.isSelected())&&(!oneDiceEndCheckbox.isSelected());
    }

    public boolean wantToEdit(){
        return wantToEditCheckBox.isSelected();
    }

    public void showSetupScreen() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    public void disposeFrame(){
        frame.dispose();
    }
    
    public int getNScale(){
        return Integer.parseInt(frame.getnScaleField().getText());
    }

    public int getNSerpenti(){
        return Integer.parseInt(frame.getnSerpentiField().getText());
    }

    public void setNumberOfPlayers(int n){
        numPlayersField.setValue(n);
    }

    public void setGridSizeX(int n){
        gridSizeXField.setValue(n);
    }

    public void setGridSizeY(int n){
        gridSizeYField.setValue(n);
    }

    public void setNScale(int n){
        nScale.setValue(n);
    }

    public void setNSerpenti(int n){
        nSerpenti.setValue(n);
    }

    public void setSpecialRules(boolean b){
        specialRulesCheckbox.setSelected(b);
    }

    public void setCardsRule(boolean b){
        cardsCheckbox.setSelected(b);
    }

    public void setCardsRuleAddon(boolean b){
        cardsAddonCheckbox.setSelected(b);
    }

    public void setBlockSquareRule(boolean b){
        stopSquaresCheckbox.setSelected(b);
    }

    public void setPrizeSquareRule(boolean b){
        prizeSquaresCheckbox.setSelected(b);
    }

    public void setDoubleSixRule(boolean b){
        doubleSixCheckbox.setSelected(b);
    }

    public void setOneDiceRule(boolean b){
        oneDiceCheckbox.setSelected(b);
    }

    public void setOneDiceEndRule(boolean b){
        oneDiceEndCheckbox.setSelected(b);

    }

    public JFrame getFrame(){
        return frame;
    }

}