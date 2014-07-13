package homework_5;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Calculator extends JPanel {
	private static final long serialVersionUID = 1L;
	static TextField tf = new TextField();
	static JButton[] buttons = new JButton[16];
	
	public Calculator(){
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
		botButtonsPanel.setLayout(new GridLayout(4,4));		
		
		String calcLayout = "789+456-123*0.=/";
		// append to tempStr1 right after '=' is pressed
		StringBuffer tempStr1 = new StringBuffer();
		StringBuffer tempStr2 = new StringBuffer();
		char tempOperator;				// clear this value whenever '=' is pressed
		//tempNum1.
		for (int i=0; i < calcLayout.length(); i++){
			String buttonText = calcLayout.substring(i, i+1);
			buttons[i] = new JButton(buttonText);
			botButtonsPanel.add(buttons[i]);
			buttons[i].addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	            	String s = ((JButton)e.getSource()).getText();
	            	if (s.matches("[0-9]")){
	            		//System.out.println("yes");
	            		tempStr1.append(s);
	            	}
	        		tf.setText(s);
	            }
	        });
		}
		frame.setBounds(350,350,500,350);
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
