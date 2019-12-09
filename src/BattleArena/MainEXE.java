package BattleArena;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainEXE extends JFrame {

    public MainEXE() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Battle Arena");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            MainEXE ex = new MainEXE();
            ex.setVisible(true);
        });
    }
}