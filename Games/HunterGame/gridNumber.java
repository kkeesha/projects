package assign2;
import javax.swing.JSpinner;


public class gridNumber extends javax.swing.JFrame {
    /*
    initComponents() method will initiate the components in the poject such a button,spinner, textfields and actionListeners ob buttons
    */
    public gridNumber() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameName = new javax.swing.JLabel();
        gridSize = new javax.swing.JLabel();
        gridNum = new javax.swing.JSpinner();
        hunterLabel = new javax.swing.JLabel();
        fugitiveLabel = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        fugitivePlayer = new javax.swing.JTextField();
        hunterPlayer = new javax.swing.JTextField();
	gridNum.setEditor(new JSpinner.DefaultEditor(gridNum));
	gridNum.setValue(3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	
	

        gameName.setBackground(new java.awt.Color(255, 255, 255));
        gameName.setForeground(new java.awt.Color(0, 0, 0));
        gameName.setText("Hunting Game");

        gridSize.setForeground(new java.awt.Color(0, 0, 0));
        gridSize.setText("Grid size(NxN): ");

        gridNum.setModel(new javax.swing.SpinnerNumberModel(3, 3, 7, 2));

        hunterLabel.setForeground(new java.awt.Color(0, 0, 0));
        hunterLabel.setText("Player1(Hunter): ");

        fugitiveLabel.setForeground(new java.awt.Color(0, 0, 0));
        fugitiveLabel.setText("Player2(Fugitive): ");

        start.setForeground(new java.awt.Color(0, 0, 0));
        start.setText("Start Game");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(gameName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(start))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fugitiveLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fugitivePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(gridSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(gridNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hunterLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hunterPlayer)))))
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(gameName)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gridSize)
                    .addComponent(gridNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hunterLabel)
                    .addComponent(hunterPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fugitiveLabel)
                    .addComponent(fugitivePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(start)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    startActionPerformed() method will store the given data such as grid num and names of hunter and fugitive and pass it to next method which is openGameWindow
    and id the names for players were not givnen then the method will set defaulf names to them as Hunter and Fugitive respectively to each of them.
    */
    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        this.dispose();
        int size = (int) (gridNum.getValue());
        String hunterName = hunterPlayer.getText().isEmpty() ? "Hunter" : hunterPlayer.getText();
        String fugitiveName = fugitivePlayer.getText().isEmpty() ? "Fugitive" : fugitivePlayer.getText();

        openGameWindow(size, hunterName, fugitiveName);
    }                                     
//GEN-LAST:event_startActionPerformed
    /*
    openGameWindow() method will take three parameters as grid size, and names for hunter and fugitive
    and open a new window which will be a game window where the player actually start playing
    */
    private void openGameWindow(int size, String hunterName, String fugitiveName) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HunterGame(size, hunterName, fugitiveName).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fugitiveLabel;
    private javax.swing.JTextField fugitivePlayer;
    private javax.swing.JLabel gameName;
    private javax.swing.JSpinner gridNum;
    private javax.swing.JLabel gridSize;
    private javax.swing.JLabel hunterLabel;
    private javax.swing.JTextField hunterPlayer;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
}



























