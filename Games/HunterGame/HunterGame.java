package assign2;

import java.awt.Color;
import java.awt.Font;

class Player {
    public Player() {}
    public String namePlayer;
    public String name;
    public int columns;
    public int rows;
}
public class HunterGame extends javax.swing.JFrame {
    /**
     * 
     * @param gridSize
     * @param player1
     * @param player2 
     * the following constructor gets three parameters as grid size and names for players
     * sets the final int just to make sure the player will have limited steps as allMoves variable
     * then adds to an array of Players, sets the text inside the text box where the turn of a player is visible 
     * so in the end it invokes another methods such as createGrid
     */
    public HunterGame(int gridSize, String player1, String player2) {
        initComponents();
        allMoves = 4 * gridSize;
        hunters = new Player[4];
        for(int i=0;i<4;++i) {
            hunters[i] = new Player();
            if(!player1.isEmpty())
                hunters[i].namePlayer = player1;
        }
        hunterButton[0] = hunter1;
        hunterButton[1] = hunter2;
        hunterButton[2] = hunter3;
        hunterButton[3] = hunter4;
        
        fugitivePlayer = new Player();
        fugitivePlayer.name = "F";
        if(!player2.isEmpty())
            fugitivePlayer.namePlayer = player2;
        
        textField.setFont(new Font("Dialog", Font.BOLD, 16));
        textField.setText("Turn: " + fugitivePlayer.namePlayer + "'s");
        size = gridSize;
        createGrid();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hunterButtons = new javax.swing.ButtonGroup();
        gridPanel = new javax.swing.JPanel();
        upKey = new javax.swing.JButton();
        leftKey = new javax.swing.JButton();
        rightKey = new javax.swing.JButton();
        downKey = new javax.swing.JButton();
        hunter1 = new javax.swing.JRadioButton();
        hunter2 = new javax.swing.JRadioButton();
        hunter3 = new javax.swing.JRadioButton();
        hunter4 = new javax.swing.JRadioButton();
        textField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gridPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout gridPanelLayout = new javax.swing.GroupLayout(gridPanel);
        gridPanel.setLayout(gridPanelLayout);
        gridPanelLayout.setHorizontalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        gridPanelLayout.setVerticalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        upKey.setText("UP");
        upKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upKeyActionPerformed(evt);
            }
        });

        leftKey.setText("LEFT");
        leftKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftKeyActionPerformed(evt);
            }
        });

        rightKey.setText("RIGHT");
        rightKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightKeyActionPerformed(evt);
            }
        });

        downKey.setText("DOWN");
        downKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downKeyActionPerformed(evt);
            }
        });

        hunterButtons.add(hunter1);
        hunter1.setText("1H");
        hunterButtons.add(hunter2);
        hunter2.setText("2H");
        hunterButtons.add(hunter3);
        hunter3.setText("3H");
        hunterButtons.add(hunter4);
        hunter4.setText("4H");

        textField.setEditable(false);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(gridPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(hunter1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hunter2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hunter3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hunter4)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(leftKey, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(rightKey, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(downKey, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(upKey, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gridPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(upKey, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftKey, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rightKey, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(downKey, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hunter1)
                            .addComponent(hunter2)
                            .addComponent(hunter3)
                            .addComponent(hunter4))
                        .addGap(16, 16, 16)))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    there are 4 events where you can find the explanation for them in the documentation
    */
    private void upKeyActionPerformed(java.awt.event.ActionEvent evt) {                                      
        endGame("UP");
    }
    private void leftKeyActionPerformed(java.awt.event.ActionEvent evt) {                                        
        endGame("LEFT");
    }
    private void rightKeyActionPerformed(java.awt.event.ActionEvent evt) {                                         
        endGame("RIGHT");
    }
    private void downKeyActionPerformed(java.awt.event.ActionEvent evt) {                                        
        endGame("DOWN");
    }

//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(gridNumOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(gridNumOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(gridNumOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(gridNumOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HunterGame(5, "player1", "player2").setVisible(true);
//            }
//        });
//    }
    
    /*
      create a grid with a given size  and the players` name given by the user
    and setting up the initial postion for the hunters and fugicitve 
    as each hunter in the each corner and a fugitive in the very middle of the grid
     */
    private void createGrid() {
       grid = new javax.swing.JButton[size][size];
        gridPanel.setLayout(new java.awt.GridLayout(size, size));
        for(int i=0;i<size;++i) {
            for(int j=0;j<size;++j) {
                grid[i][j] = new javax.swing.JButton(" ");
                gridPanel.add(grid[i][j]); 
            }
        }
        
        hunters[0].name = "1H";
        hunters[0].columns = 0;
        hunters[0].rows = 0;
        grid[0][0].setText("1H");
        
        int half = (int) grid.length / 2;
        fugitivePlayer.columns = half;
        fugitivePlayer.rows = half;
        grid[half][half].setText("F");
        
        hunters[1].name = "2H";
        hunters[1].columns = size-1;
        hunters[1].rows = 0;
        grid[0][size-1].setText("2H");
        
        hunters[2].name = "3H";
        hunters[2].columns = 0;
        hunters[2].rows = size-1;
        grid[size-1][0].setText("3H");
        
        hunters[3].name = "4H";
        hunters[3].columns = size-1;
        hunters[3].rows = size-1;
        grid[size-1][size-1].setText("4H"); 
    }
    
    /**
     movePlayer will take parameters as Player and String
     * the Player object is needed which we want to move and a string key to give a direction to move.
     * if the key is up it moves the player one step upwards and this applies for each key.
     * and it changes the text box and says if the turn is fugitive`s or hunter`s.
     * @param key Direction to move
     */
    private void movePlayer(Player player, String key) {
        boolean isMoved = false;
        switch(key) {
            case "UP": 
                if(player.rows-1 >= 0 && grid[player.rows-1][player.columns].getText().equals(" ")) {
                    grid[player.rows][player.columns].setText(" ");
                    player.rows--;
                    grid[player.rows][player.columns].setText(player.name);
                    isMoved = true;
                }
                break;
            case "LEFT": 
                if(player.columns-1 >= 0 && grid[player.rows][player.columns-1].getText().equals(" ")) {
                    grid[player.rows][player.columns].setText(" ");
                    player.columns--;
                    grid[player.rows][player.columns].setText(player.name);
                    isMoved = true;
                }
                break;
            case "RIGHT":
                if(player.columns+1 <= grid[0].length-1 && grid[player.rows][player.columns+1].getText().equals(" ")) {
                    grid[player.rows][player.columns].setText(" ");
                    player.columns++;
                    grid[player.rows][player.columns].setText(player.name);
                    isMoved = true;
                }
                break;
            case "DOWN": 
                if(player.rows+1 <= grid.length-1 && grid[player.rows+1][player.columns].getText().equals(" ")) {
                    grid[player.rows][player.columns].setText(" ");
                    player.rows++;
                    grid[player.rows][player.columns].setText(player.name);
                    isMoved = true;
                }
                break;
        }
        boolean isFugitive = player.name.equals("F");
        if(isMoved) {
            if(!player.name.equals(" ") && !player.name.equals("F")) //do comment this line in order to count steps only when hunter is moved and uncomment if count when anyone makes step
                cnt++;
            textField.setForeground(Color.black);
            if(isFugitive) {
                textField.setText("Turn: " + hunters[0].namePlayer + "'s");
                isHunterTurn = true;
            }
            else {
               textField.setText("Turn: " + fugitivePlayer.namePlayer + "'s");
               isHunterTurn = false; 
            }
                
        } else {
            if(isFugitive) isHunterTurn = false;
            else isHunterTurn = true;
        }
    }
    
    /*
    chooseHunter will select a hunter among other 4 and returns a Player type
    */
    private Player chosenHunter() {
        for(int i=0;i<4;++i) {
            if(hunterButton[i].isSelected()) return hunters[i];
        }
        return null;
    }
    
    /*
    isFugitiveLost is a boolean type of methods which indicates it the game is over with fugutive being caught.
    if there is no option for fugitive player to move to any direction then it will return false and we can keep playtin
    otherwise it is true which is set by default
    */
    private boolean isFugitiveLost() {
        boolean isLost = true;
        if(fugitivePlayer.rows-1 >= 0 && grid[fugitivePlayer.rows-1][fugitivePlayer.columns].getText().equals(" ")) isLost = false;
        if(fugitivePlayer.columns-1 >= 0 && grid[fugitivePlayer.rows][fugitivePlayer.columns-1].getText().equals(" "))  isLost = false;
        if(fugitivePlayer.columns+1 <= grid[0].length-1 && grid[fugitivePlayer.rows][fugitivePlayer.columns+1].getText().equals(" ")) isLost = false;
        if(fugitivePlayer.rows+1 <= grid.length-1 && grid[fugitivePlayer.rows+1][fugitivePlayer.columns].getText().equals(" ")) isLost = false;
        return isLost;
    }

    /**
     * endGame method takes a key string type and checks if the steps number exceeds the number of allowed steps 
     * or if the fugitive is captured by hunters and in case it is true the new window will appear to tell the user to
     * either restart the game or close it
     * if the res is 0 which is yes then new window will appear and close if it is 1 which is no
   * @param Direcetion to move 
     */
    private void endGame(String key) {
        
        if(isFugitiveLost() || cnt >= allMoves) {
            int res;
            if(isFugitiveLost())
            res = javax.swing.JOptionPane.showConfirmDialog(null, "Restart the game?", hunters[0].namePlayer + " wins!", javax.swing.JOptionPane.YES_NO_OPTION);
            else
            res = javax.swing.JOptionPane.showConfirmDialog(null, "Restart the game?", fugitivePlayer.namePlayer + " wins!", javax.swing.JOptionPane.YES_NO_OPTION);

                if(res == 1) System.exit(0);
                else restartGame();
        }
        
        if(isHunterTurn) {
            Player hunter = chosenHunter();
            if(hunter != null) {
                System.out.println(hunter.name);
                movePlayer(hunter, key);
            } else {
                textField.setText("Sorry, you DID NOT select the hunter!");
                textField.setForeground(Color.red);
            }
        }  
        else
            movePlayer(fugitivePlayer, key);
    }

    /**
     * Restart the game with initial set up parameters
     * with grid size and players names
     */
    private void restartGame() {
        new gridNumber().setVisible(true);
        this.dispose();
    }
    
    private javax.swing.JButton grid[][];
    private final Player hunters[];
    private final Player fugitivePlayer;
    private boolean isHunterTurn = false;
    private final int allMoves;
    private int cnt = 0;
    private final int size;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downKey;
    private javax.swing.JPanel gridPanel;
    private javax.swing.JRadioButton hunter1;
    private javax.swing.JRadioButton hunter2;
    private javax.swing.JRadioButton hunter3;
    private javax.swing.JRadioButton hunter4;
    private javax.swing.ButtonGroup hunterButtons;
    private javax.swing.JButton leftKey;
    private javax.swing.JButton rightKey;
    private javax.swing.JTextField textField;
    private javax.swing.JButton upKey;
    // End of variables declaration//GEN-END:variables
    private final javax.swing.JRadioButton hunterButton[] = new javax.swing.JRadioButton[4];
}
