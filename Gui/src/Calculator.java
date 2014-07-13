import java.awt.*;
public class Calculator extends Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1736169538384358001L;

	public Calculator() {
		//Frame f = new Frame("Calculator");
		super("Calculator");
		add(new TextField(""), BorderLayout.NORTH);
		Panel p = new Panel();
		add(p, BorderLayout.CENTER);
				
		p.setLayout(new GridLayout(4,5));
		String calcLayout = "789 +456 -123 *0.= /";
		for (int i=0; i<calcLayout.length(); i++){
			String buttonText = calcLayout.substring(i, i+1);
			// p.add(new Button(buttonText));
			Button b = new Button(buttonText);
			p.add(b);
			if (buttonText.equals(" "))
				b.setEnabled(false);
		} // end calculator buttons layout for loop
		
		setBounds(50,50,500,350);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Calculator();
	}
}
