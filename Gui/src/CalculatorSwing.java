import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class CalculatorSwing extends JPanel {
	private static final long serialVersionUID = -1736169538384358001L;
	static TextField tf = new TextField();
	static JButton[] buttons = new JButton[16];
	
	public CalculatorSwing(){
	}
	
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("CalcSwing");		// the big frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topTextPanel = new JPanel();
		JPanel botButtonsPanel = new JPanel();
		
		frame.add(topTextPanel, BorderLayout.NORTH);
		frame.add(botButtonsPanel, BorderLayout.CENTER);
		
		topTextPanel.setLayout(new GridLayout(1,1));
		topTextPanel.add(tf);
		botButtonsPanel.setLayout(new GridLayout(4,5));		
		
		String calcLayout = "789+456-123*0.=/";
		for (int i=0; i < calcLayout.length(); i++){
			String buttonText = calcLayout.substring(i, i+1);
			buttons[i] = new JButton(buttonText);
			botButtonsPanel.add(buttons[i]);
			buttons[i].addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	            	String s = ((JButton)e.getSource()).getLabel();
	        		tf.setText(s);
	            }
	        });
		}
		frame.setBounds(50,50,500,350);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
}
