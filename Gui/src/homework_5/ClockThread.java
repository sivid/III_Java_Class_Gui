package homework_5;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;

public class ClockThread extends JPanel{
	private static final long serialVersionUID = 1L;
    static private JTextField tf;
	public ClockThread() {
		
	}
	protected static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Hi Clock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel newPanel = new ClockThread();
		newPanel.setLayout(new FlowLayout());
		frame.setContentPane(newPanel);
		
		tf = new JTextField(5);
        tf.setEditable(false);
        tf.setFont(new Font("arial", Font.PLAIN, 72));
        myClock();
        
		new javax.swing.Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				myClock();
			}
		}).start(); // «Û§Ê­ô
        newPanel.add(tf);
		//frame.setBounds(50,50,500,350);
		frame.pack();
		frame.setVisible(true);
	}	
	
	public static void myClock(){
		Calendar now = Calendar.getInstance();
        int h = now.get(Calendar.HOUR_OF_DAY);
        int m = now.get(Calendar.MINUTE);
        int s = now.get(Calendar.SECOND);
        tf.setText("" + h + ":" + m + ":" + s);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
}
