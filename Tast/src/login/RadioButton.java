package login;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButton extends JFrame{
    JPanel panel = new JPanel();
    
    JRadioButton radio[] = new JRadioButton[5];
    
    String radio_name[] = {"1점", "2점", "3점", "4점", "5점"}; 
    
    public RadioButton(){
        this.setTitle("RadioButton Example");
        this.setVisible(true);
        this.setSize(400, 80);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ButtonGroup group = new ButtonGroup();
        
        for(int i=0; i<radio.length; i++){
            radio[i] = new JRadioButton(radio_name[i]);
            group.add(radio[i]);
            panel.add(radio[i]);
        }
    
        radio[0].setSelected(false);
        radio[1].setSelected(true);
        
        this.add(panel);
    }
}
