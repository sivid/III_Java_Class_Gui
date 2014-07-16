import homework_5.SimpleEditor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
public class JSliderDemo {
	JSlider slider;
	JFrame frame;
	public JSliderDemo() {
		// TODO Auto-generated constructor stub
	}
	
	public Container createContentPane() {
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);
		
		slider = new JSlider(SwingConstants.VERTICAL);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		contentPane.add(slider, BorderLayout.CENTER);
		return contentPane;
	} // end createContentPane()

	private void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Editor");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Create and set up the content pane.
		JSliderDemo sliderDemo = new JSliderDemo();
		frame.setContentPane(sliderDemo.createContentPane());
		// frame.addWindowListener(new AppCloser());
		frame.setTitle("yooo");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// Display the window.
		frame.setSize(1280, 720);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				new JSliderDemo().createAndShowGUI();
			}
		});
	}

}
