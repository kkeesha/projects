package assign2;

public class Main {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*
                 the following method will start a game and display it 
                 */
                   new gridNumber().setVisible(true);
            }
        });
    }
}
