import java.awt.*;
import java.awt.event.*;
public class CalculatorGUI extends Frame implements ActionListener, WindowListener{
	private static final long serialVersionUID = -1736169538384358001L;
	TextField tf = new TextField();
	Button[] buttons = new Button[16];
	
	public CalculatorGUI() {
		super("Calculator");
		add(tf, BorderLayout.NORTH);
		Panel p = new Panel();
		add(p, BorderLayout.CENTER);		
		p.setLayout(new GridLayout(4,5));
		String calcLayout = "789+456-123*0.=/";
		for (int i=0; i < calcLayout.length(); i++){
			String buttonText = calcLayout.substring(i, i+1);
			buttons[i] = new Button(buttonText);
			p.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		this.addWindowListener(this);
		setBounds(50,50,500,350);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CalculatorGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = ((Button)e.getSource()).getLabel();
		tf.setText(s);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
