package config.frames;
import javax.swing.*;

public class GameConfigJFrame extends JFrame {

    private JPanel Main;
    private JLabel Subtitle;
    private JLabel Title;
    private JPanel Titlebox;
    private JCheckBox cardsRule;
    private JLabel gameboardHeight;
    private JLabel gameboardLenght;
    private JFormattedTextField gbHeightField;
    private JFormattedTextField gbLenghtField;
    private JFormattedTextField nPlayerField;
    private JLabel nPlayers;
    private JCheckBox rerollRule;
    private JCheckBox specialRulesField;
    private JButton startGameButton;
    private JCheckBox stopRule;
    private JCheckBox mollaRule;
    private JCheckBox oneDiceRule;
    private JCheckBox oneDiceEndRule;
    private JCheckBox wantToEditCheckBox;
  
    public GameConfigJFrame() {
        initComponents();
    }

                         
    private void initComponents() {

         Main = new javax.swing.JPanel();
         Subtitle = new javax.swing.JLabel();
         Titlebox = new javax.swing.JPanel();
         Title = new javax.swing.JLabel();
         gameboardLenght = new javax.swing.JLabel();
         gameboardHeight = new javax.swing.JLabel();
         gbLenghtField = new javax.swing.JFormattedTextField();
         gbHeightField = new javax.swing.JFormattedTextField();
         nPlayers = new javax.swing.JLabel();
         nPlayerField = new javax.swing.JFormattedTextField();
         cardsRule = new javax.swing.JCheckBox();
         specialRulesField = new javax.swing.JCheckBox();
         stopRule = new javax.swing.JCheckBox();
         rerollRule = new javax.swing.JCheckBox();
         mollaRule = new javax.swing.JCheckBox();
         oneDiceEndRule = new javax.swing.JCheckBox();
         oneDiceRule = new javax.swing.JCheckBox();
         startGameButton = new JButton();
         wantToEditCheckBox = new JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Subtitle.setText("Menù di configurazione");

        Titlebox.setBackground(new java.awt.Color(255, 255, 255));
        Titlebox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Title.setFont(new java.awt.Font("URW Gothic L", 1, 18)); // NOI18N
        Title.setText("SCALE&SERPENTI");

        javax.swing.GroupLayout TitleboxLayout = new javax.swing.GroupLayout(Titlebox);
        Titlebox.setLayout(TitleboxLayout);
        TitleboxLayout.setHorizontalGroup(
            TitleboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleboxLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(Title)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        TitleboxLayout.setVerticalGroup(
            TitleboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleboxLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(Title)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout MainLayout = new javax.swing.GroupLayout(Main);
        Main.setLayout(MainLayout);
        MainLayout.setHorizontalGroup(
            MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainLayout.createSequentialGroup()
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(Titlebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainLayout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(Subtitle)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MainLayout.setVerticalGroup(
            MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titlebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Subtitle)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        gameboardLenght.setText("Lunghezza board in Celle:");

        gameboardHeight.setText("Altezza board in Celle:");
        gbLenghtField.setColumns(4);
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

        gbHeightField.setColumns(4);
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

        nPlayers.setText("Numero Giocatori: ");
        nPlayerField.setColumns(4);
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

        cardsRule.setText("Cartefolli");
        specialRulesField.setText("Attiva Regole Speciali");
        stopRule.setText("Caselle-STOP");
        rerollRule.setText("Caselle-REROLL");
        mollaRule.setText("Caselle-MOLLA");
        oneDiceRule.setText("Dado Singolo");
        oneDiceEndRule.setText("Dado Singolo al lancio finale");
        startGameButton.setText("START!");
        cardsRule.setEnabled(false);
        stopRule.setEnabled(false);
        rerollRule.setEnabled(false);
        mollaRule.setEnabled(false);
        oneDiceRule.setEnabled(false);
        oneDiceEndRule.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gameboardLenght, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gameboardHeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gbLenghtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gbHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addComponent(nPlayers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nPlayerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(specialRulesField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardsRule)
                                .addGap(54, 54, 54)
                                .addComponent(stopRule)
                                .addGap(14, 14, 14)
                                .addComponent(rerollRule))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mollaRule)
                                .addGap(18, 18, 18)
                                .addComponent(oneDiceRule)
                                .addGap(18, 18, 18)
                                .addComponent(oneDiceEndRule)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startGameButton)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gameboardLenght)
                            .addComponent(gbLenghtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(gameboardHeight)
                            .addComponent(gbHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nPlayerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nPlayers))))
                .addGap(19, 19, 19)
                .addComponent(specialRulesField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardsRule)
                    .addComponent(stopRule)
                    .addComponent(rerollRule))
                    
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(mollaRule)
                    .addComponent(oneDiceRule)
                    .addComponent(oneDiceEndRule))

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startGameButton)
                .addContainerGap(23, Short.MAX_VALUE))
            );

        pack();
    }


    public JCheckBox getCardsRule() {
        return cardsRule;
    }
    public JFormattedTextField getGbHeightField() {
        return gbHeightField;
    }
    public JFormattedTextField getGbLenghtField() {
        return gbLenghtField;
    }
    public JFormattedTextField getnPlayerField() {
        return nPlayerField;
    }
    public JCheckBox getRerollRule() {
        return rerollRule;
    }
    public JCheckBox getSpecialRulesField() {
        return specialRulesField;
    }
    public JButton getStartGameButton() {
        return startGameButton;
    }
    public JCheckBox getStopRule() {
        return stopRule;
    }
    
    public JCheckBox getMollaRule() {
        return mollaRule;
    }

    public JCheckBox getOneDiceRule() {
        return oneDiceRule;
    }

    public JCheckBox getOneDiceEndRule() {
        return oneDiceEndRule;
    }
    public void disposeFrame(){
        dispose();
    }

    public JCheckBox getWantToEditCheckBox(){
        return wantToEditCheckBox;
    }
    

}