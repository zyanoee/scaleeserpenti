package config.frames;

import javax.swing.InputVerifier;
import javax.swing.JComponent;

public class GameConfigJFrame2 extends javax.swing.JFrame {

    private javax.swing.JLabel Subtitle;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel Titlebox;
    private javax.swing.JCheckBox cardsRule;
    private javax.swing.JCheckBox cardsRuleAddon;
    private javax.swing.JCheckBox doubleSixRule;
    private javax.swing.JLabel gameboardHeight;
    private javax.swing.JLabel gameboardLenght;
    private javax.swing.JFormattedTextField gbHeightField;
    private javax.swing.JFormattedTextField gbLenghtField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loadOptionsButton;
    private javax.swing.JFormattedTextField nPlayerField;
    private javax.swing.JLabel nScale;
    private javax.swing.JFormattedTextField nScaleField;
    private javax.swing.JLabel nSerpenti;
    private javax.swing.JFormattedTextField nSerpentiField;
    private javax.swing.JCheckBox oneDiceEndRule;
    private javax.swing.JCheckBox oneDiceRule;
    private javax.swing.JCheckBox prizeRule;
    private javax.swing.JCheckBox specialRulesField;
    private javax.swing.JButton startGameButton;
    private javax.swing.JCheckBox stopRule;
    private javax.swing.JCheckBox wantToEditCheckbox;


    public GameConfigJFrame2() {
        initComponents();
    }

   
    private void initComponents() {

        nPlayerField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        gameboardLenght = new javax.swing.JLabel();
        gameboardHeight = new javax.swing.JLabel();
        gbLenghtField = new javax.swing.JFormattedTextField();
        gbHeightField = new javax.swing.JFormattedTextField();
        specialRulesField = new javax.swing.JCheckBox();
        oneDiceRule = new javax.swing.JCheckBox();
        oneDiceEndRule = new javax.swing.JCheckBox();
        doubleSixRule = new javax.swing.JCheckBox();
        cardsRule = new javax.swing.JCheckBox();
        stopRule = new javax.swing.JCheckBox();
        prizeRule = new javax.swing.JCheckBox();
        cardsRuleAddon = new javax.swing.JCheckBox();
        nScale = new javax.swing.JLabel();
        nSerpenti = new javax.swing.JLabel();
        nScaleField = new javax.swing.JFormattedTextField();
        nSerpentiField = new javax.swing.JFormattedTextField();
        wantToEditCheckbox = new javax.swing.JCheckBox();
        loadOptionsButton = new javax.swing.JButton();
        startGameButton = new javax.swing.JButton();
        Titlebox = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        Subtitle = new javax.swing.JLabel();



        cardsRule.setEnabled(false);
        stopRule.setEnabled(false);
        prizeRule.setEnabled(false);
        doubleSixRule.setEnabled(false);
        oneDiceRule.setEnabled(false);
        oneDiceEndRule.setEnabled(false);
        cardsRuleAddon.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        nPlayerField.setText("2");
        gbHeightField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        nPlayerField.setValue(2);
        nPlayerField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    int value = Integer.parseInt(nPlayerField.getText());
                    // Imposta il valore minimo a 1 e il valore massimo a 4
                    value = Math.min(Math.max(value, 1), 4);
                    nPlayerField.setValue(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });

        

        jLabel1.setText("Numero Players");

        gameboardLenght.setText("Lunghezza Board");

        gameboardHeight.setText("Altezza Board");

        gbLenghtField.setText("10");
        gbHeightField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        gbLenghtField.setValue(10);
        gbLenghtField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    int value = Integer.parseInt(gbLenghtField.getText());
                    value = Math.min(Math.max(value, 5), 15);
                    gbLenghtField.setValue(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });


        gbHeightField.setText("10");
        gbHeightField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        gbHeightField.setValue(10); // Imposta il valore predefinito
        gbHeightField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    int value = Integer.parseInt(gbHeightField.getText());
                    // Imposta il valore minimo a 5 e il valore massimo a 15
                    value = Math.min(Math.max(value, 5), 15);
                    gbHeightField.setValue(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });

        specialRulesField.setText("Abilita Regole Speciali");
 

        oneDiceRule.setText("Dado Singolo");

        oneDiceEndRule.setText("Dado Singolo All'ultimo lancio");

        doubleSixRule.setText("Doppio Sei");

        cardsRule.setText("Carte-Folli");

        stopRule.setText("Caselle Sosta");

        prizeRule.setText("Caselle Premio");

        cardsRuleAddon.setText("Più Carte!");

        nScale.setText("Numero Scale");
         

        nSerpenti.setText("Numero Serpenti");

        nScaleField.setText("10");

        nScaleField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        nScaleField.setValue(10);
        nScaleField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    int value = Integer.parseInt(nScaleField.getText());
                    value = Math.min(Math.max(value, Math.min(Integer.parseInt(gbHeightField.getText()), Integer.parseInt(gbHeightField.getText()))/2), Math.min(Integer.parseInt(gbHeightField.getText()), Integer.parseInt(gbHeightField.getText())));
                    nScaleField.setValue(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });


        nSerpentiField.setText("10");
        nSerpentiField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        nSerpentiField.setValue(10);
        nSerpentiField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    int value = Integer.parseInt(nSerpentiField.getText());
                    value = Math.min(Math.max(value, Math.min(Integer.parseInt(gbHeightField.getText()), Integer.parseInt(gbHeightField.getText()))/2), Math.min(Integer.parseInt(gbHeightField.getText()), Integer.parseInt(gbHeightField.getText())));
                    nSerpentiField.setValue(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });

        wantToEditCheckbox.setText("Editor Mappa");


        loadOptionsButton.setText("Load");

        startGameButton.setText("Start!");

        Titlebox.setBackground(new java.awt.Color(255, 153, 153));

        Title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Title.setText("SCALE & SERPENTI");

        javax.swing.GroupLayout TitleboxLayout = new javax.swing.GroupLayout(Titlebox);
        Titlebox.setLayout(TitleboxLayout);
        TitleboxLayout.setHorizontalGroup(
            TitleboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleboxLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(Title)
                .addGap(27, 27, 27))
        );
        TitleboxLayout.setVerticalGroup(
            TitleboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleboxLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(Title)
                .addGap(38, 38, 38))
        );

        Subtitle.setText("Menù di configurazione");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oneDiceRule)
                            .addComponent(cardsRule))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardsRuleAddon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stopRule))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(oneDiceEndRule)
                                .addGap(0, 13, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(doubleSixRule)
                                .addGap(69, 69, 69))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(prizeRule)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(loadOptionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startGameButton)
                                .addGap(27, 27, 27))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(specialRulesField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(gameboardLenght)
                                            .addComponent(gameboardHeight))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(gbLenghtField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(gbHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nPlayerField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(nSerpenti)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(nSerpentiField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(nScale)
                                                .addGap(26, 26, 26)
                                                .addComponent(nScaleField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Subtitle)
                                        .addGap(69, 69, 69)))
                                .addGap(18, 18, 18)
                                .addComponent(wantToEditCheckbox)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(Titlebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(Titlebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Subtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gameboardLenght)
                            .addComponent(gbLenghtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nScale)
                            .addComponent(nScaleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nPlayerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(gameboardHeight)
                            .addComponent(gbHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nSerpenti)
                            .addComponent(nSerpentiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(wantToEditCheckbox)
                        .addGap(27, 27, 27)))
                .addComponent(specialRulesField)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(oneDiceRule)
                            .addComponent(oneDiceEndRule)
                            .addComponent(doubleSixRule))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cardsRule)
                            .addComponent(stopRule)
                            .addComponent(prizeRule)
                            .addComponent(cardsRuleAddon))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loadOptionsButton)
                            .addComponent(startGameButton))
                        .addGap(24, 24, 24))))
        );

        pack();
    }


    public javax.swing.JCheckBox getCardsRule() {
        return cardsRule;
    }


    public javax.swing.JCheckBox getCardsRuleAddon() {
        return cardsRuleAddon;
    }


    public javax.swing.JCheckBox getDoubleSixRule() {
        return doubleSixRule;
    }


    public javax.swing.JFormattedTextField getGbHeightField() {
        return gbHeightField;
    }


    public javax.swing.JFormattedTextField getGbLenghtField() {
        return gbLenghtField;
    }


    public javax.swing.JButton getLoadOptionsButton() {
        return loadOptionsButton;
    }


    public javax.swing.JFormattedTextField getnPlayerField() {
        return nPlayerField;
    }


    public javax.swing.JFormattedTextField getnScaleField() {
        return nScaleField;
    }


    public javax.swing.JFormattedTextField getnSerpentiField() {
        return nSerpentiField;
    }


    public javax.swing.JCheckBox getOneDiceEndRule() {
        return oneDiceEndRule;
    }


    public javax.swing.JCheckBox getOneDiceRule() {
        return oneDiceRule;
    }


    public javax.swing.JCheckBox getPrizeRule() {
        return prizeRule;
    }


    public javax.swing.JCheckBox getSpecialRulesField() {
        return specialRulesField;
    }


    public javax.swing.JButton getStartGameButton() {
        return startGameButton;
    }


    public javax.swing.JCheckBox getStopRule() {
        return stopRule;
    }


    public javax.swing.JCheckBox getWantToEditCheckbox() {
        return wantToEditCheckbox;
    }
}
