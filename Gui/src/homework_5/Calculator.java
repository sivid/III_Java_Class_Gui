package homework_5;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator {
	//private static final long serialVersionUID = 1L;
	//static TextField tf = new TextField();
	static JButton[] buttons = new JButton[20];
	static JLabel label = new JLabel("0",JLabel.RIGHT);	
	public Calculator(){}
	
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("CalcSwing");		// the big frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel topTextPanel = new JPanel();
		JPanel botButtonsPanel = new JPanel();		
		frame.add(topTextPanel, BorderLayout.NORTH);
		frame.add(botButtonsPanel, BorderLayout.CENTER);		
		topTextPanel.setLayout(new GridLayout(1,1));
		topTextPanel.add(label);
		botButtonsPanel.setLayout(new GridLayout(5,4));		
		
		String calcLayout = "789+456-123*0.=/";
		StringBuffer tempStr1 = new StringBuffer();
		StringBuffer tempStr2 = new StringBuffer();
		StringBuffer tempOperator = new StringBuffer();						// this should be a char, but conversion is such a bore
		buttons[0] = new JButton("\u2190");			// Question: can i add actionlisteners for each button here?
		buttons[1] = new JButton("CE");				// 			 if yes, i don't need a switch...
		buttons[2] = new JButton("C");
		buttons[3] = new JButton("+/-");
		for (int i=0; i < 4; i++){botButtonsPanel.add(buttons[i]);}
		for (int i=0; i < calcLayout.length(); i++){
			String buttonText = calcLayout.substring(i, i+1);
			buttons[i+4] = new JButton(buttonText);
			botButtonsPanel.add(buttons[i+4]);
		}
		for (int i=0; i < 20; i++){
			buttons[i].addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	            	String s = ((JButton)e.getSource()).getText();
	            	boolean str1isEmpty = (tempStr1.length() == 0 ? true : false );
	            	boolean str2isEmpty = (tempStr2.length() == 0 ? true : false );
	            	boolean operIsEmpty = (tempOperator.length() == 0 ? true : false );
	            	switch(s){
	            	case ".":									// truth table ftw
	            		if (!str1isEmpty && !operIsEmpty)
	            			setDots(s, tempStr2);
	            		else
	            			setDots(s, tempStr1);
	            		break;
	            	case "=":
	            		if ((!str1isEmpty && !str2isEmpty) && !operIsEmpty){
	            			//do arithmetic, output result, tempStr1 = result, clear tempStr2, clear tempOperator
	            			doArithmetic(new String(tempOperator), tempStr1, tempStr2, tempOperator);
	            		}
	            		seeValues(tempStr1, tempStr2, tempOperator);
	            		break;
	            	case "+": case "-": case "*": case "/":				// ...
	            		if (!str1isEmpty && str2isEmpty){
	            			tempOperator.setLength(0);
	            			tempOperator.append(s);
	            		}else if ((!str1isEmpty && !str2isEmpty) && !operIsEmpty){
	            			//do arithmetic, output result, tempStr1 = result, clear tempStr2, set tempO to new o
	            			doArithmetic(new String(tempOperator), tempStr1, tempStr2, tempOperator);
	            			tempOperator.append(s);
	            		}
	            		seeValues(tempStr1, tempStr2, tempOperator);
	            		break;
	            	case "\u2190":
	            		if (!operIsEmpty){
	            			if (tempStr2.length() > 0) {
	            				tempStr2.setLength(tempStr2.length() - 1);
	            				label.setText(new String(tempStr2));
	            			}
	            		}else{
	            			if (tempStr1.length() > 0) {
	            				tempStr1.setLength(tempStr1.length() - 1);
	            				label.setText(new String(tempStr1));
	            			}
	            		}
	            		seeValues(tempStr1, tempStr2, tempOperator);
	            		break;
	            	case "C":
	            		if (!operIsEmpty)
	            			tempStr2.setLength(0);
	            		else
	            			tempStr1.setLength(0);
	            		break;
	            	case "CE":
	            		tempStr1.setLength(0);
	            		tempStr2.setLength(0);
	            		tempOperator.setLength(0);
	            		label.setText("0");
	            		seeValues(tempStr1, tempStr2, tempOperator);
	            		break;
	            	case "+/-":
	            		if (str1isEmpty){
	            			inverseNum(tempStr1);
	            			label.setText(new String(tempStr1));
	            		}else if (!operIsEmpty){
	            			inverseNum(tempStr2);
	            		}else if(!str2isEmpty ){
	            			inverseNum(tempStr2);
	            			label.setText(new String(tempStr2));
	            		}else if (!str1isEmpty && str2isEmpty){
	            			inverseNum(tempStr1);
	            			label.setText(new String(tempStr1));
	            		}
	            		break;
	            	default:
	            		if (s.matches("[0-9]")){
		            		if ( str1isEmpty || (!str1isEmpty && operIsEmpty) ){
		            			setNumbers(s, tempStr1);
		            			label.setText(tempStr1.toString());
		            		}else if (!operIsEmpty){
		            			setNumbers(s, tempStr2);
		            			label.setText(tempStr2.toString());
		            		}
	            		}
	            	
	            	} // end switch
	            } // end actionPerformed()
	        });
		}	// end actionlistener for loop

		frame.setBounds(350,350,500,350);
		frame.setVisible(true);
	}
	
	private static void setNumbers(String s, StringBuffer str){
		str.append(s);
	}
	
	private static void setDots(String s, StringBuffer str){
		boolean haveDot = false;
		for (int i=0; i < str.length(); i++){
			if (str.charAt(i) == s.charAt(0))
				haveDot = true;
		}
		if (haveDot)
			return;
		else if (str.length() == 0)
			str.append("0.");
		else
			str.append(s);
	}
	
	private static void doArithmetic(String s, StringBuffer tempStr1, StringBuffer tempStr2, StringBuffer tempOperator){
		//do arithmetic, output result, tempStr1 = result, clear tempStr2, clear tempOperator
		double num1 = Double.parseDouble(tempStr1.toString());
		double num2 = Double.parseDouble(tempStr2.toString());
		double result = 0;
		switch (s){
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			result = num1 / num2;
			break;
		default: 
			System.out.println("it's not arithmetic?");
		} // end switch
		label.setText(Double.toString(result));				// basically a "="
		tempStr1.setLength(0);
		tempStr1.append(Double.toString(result));
		if ((tempStr1.charAt(tempStr1.length()-1) == ".".charAt(0)) ){
			tempStr1.setLength(tempStr1.length() -1);
		}		
		tempStr2.setLength(0);
		tempOperator.setLength(0);
		//return result;
	}
	
	private static void inverseNum(StringBuffer str){
		if (str.length() != 0){
			if (str.charAt(0) == "-".charAt(0))
				str.delete(0, 1);
			else
				str.insert(0, "-".charAt(0));
		}else{
			str.insert(0, "-".charAt(0));
		}		
	}
	
	private static void seeValues(StringBuffer str1, StringBuffer str2, StringBuffer oper){
		//stuff for validating values
		/*
		System.out.println("tempStr1 is :" + str1.toString());
		System.out.println("tempStr2 is :" + str2.toString());
		System.out.println("tempOperator is :" + oper.toString());
		System.out.println("==============");
		*/
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
}
