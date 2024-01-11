package main.mainview.frames;

import java.awt.event.WindowEvent;

public class MainframeJFrame extends javax.swing.JFrame {
                
    private javax.swing.JButton diceButton;
    private javax.swing.JPanel diceZonePanel;
    private javax.swing.JLabel eventText;
    private javax.swing.JPanel eventZonePanel;
    private javax.swing.JPanel gameZonePanel;
    private javax.swing.JLabel titleGameLabel;
    private javax.swing.JLabel turnNameLabel;
    private javax.swing.JCheckBox oneDiceButton;
              

    public MainframeJFrame() {
        initComponents();
    }

          
    private void initComponents() {

        gameZonePanel = new javax.swing.JPanel();
        titleGameLabel = new javax.swing.JLabel();
        turnNameLabel = new javax.swing.JLabel();
        diceZonePanel = new javax.swing.JPanel();
        eventZonePanel = new javax.swing.JPanel();
        eventText = new javax.swing.JLabel();
        diceButton = new javax.swing.JButton();
        oneDiceButton = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gameZonePanel.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout gameZonePanelLayout = new javax.swing.GroupLayout(gameZonePanel);
        gameZonePanel.setLayout(gameZonePanelLayout);
        gameZonePanelLayout.setHorizontalGroup(
            gameZonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );
        gameZonePanelLayout.setVerticalGroup(
            gameZonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        titleGameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titleGameLabel.setText("SCALE E SERPENTI");

        turnNameLabel.setText("TOCCA A X");

        diceZonePanel.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout diceZonePanelLayout = new javax.swing.GroupLayout(diceZonePanel);
        diceZonePanel.setLayout(diceZonePanelLayout);
        diceZonePanelLayout.setHorizontalGroup(
            diceZonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        diceZonePanelLayout.setVerticalGroup(
            diceZonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        eventZonePanel.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout eventZonePanelLayout = new javax.swing.GroupLayout(eventZonePanel);
        eventZonePanel.setLayout(eventZonePanelLayout);
        eventZonePanelLayout.setHorizontalGroup(
            eventZonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        eventZonePanelLayout.setVerticalGroup(
            eventZonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        eventText.setText("Eventi");

        diceButton.setText("LANCIA I DADI");
        diceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diceButtonActionPerformed(evt);
            }
        });

        oneDiceButton.setText("Usa un solo Dado!");
        oneDiceButton.setEnabled(false);
        oneDiceButton.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(titleGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(gameZonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(diceZonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eventZonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(eventText))
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(turnNameLabel)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(diceButton)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(oneDiceButton)
                        .addGap(52,52,52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addComponent(titleGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gameZonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(diceButton)
                        .addGap(20, 20, 20)
                        .addComponent(oneDiceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(turnNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(diceZonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eventText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventZonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>                        

    private void diceButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    
    }                                          

    public javax.swing.JPanel getGameZonePanel(){
        return this.gameZonePanel;
    }

    public javax.swing.JButton getDiceButton(){
        return this.diceButton;
    }

    public javax.swing.JPanel getDiceZonePanel(){
        return this.diceZonePanel;
    }

    public javax.swing.JLabel getTurnNameLabel(){
        return this.turnNameLabel;
    }
    
    public javax.swing.JCheckBox getOneDiceEndCheckBox(){
        return this.oneDiceButton;
    }

    public javax.swing.JPanel getEventZonePanel(){
        return this.eventZonePanel;
    }

    public void close(){
        WindowEvent windowClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        this.dispatchEvent(windowClosing);
    }
}
