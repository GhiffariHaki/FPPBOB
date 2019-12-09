package TestingMove;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainEXE extends JFrame {
    
	public MainEXE() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(400, 300);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MainEXE ex = new MainEXE();
            ex.setVisible(true);
        });
    }
    
}
