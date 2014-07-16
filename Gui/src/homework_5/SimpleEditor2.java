package homework_5;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class SimpleEditor2 {
	JFrame frame;
	JMenuItem menuItemNew;
	JMenuItem menuItemOpen;
	JMenuItem menuItemSave;
	JMenuItem menuItemSaveAs;
	JMenuItem menuItemExit;
	javax.swing.JTextArea textArea;
	JFileChooser fc = new JFileChooser();
	File file;
	String fileName;
	
	public SimpleEditor2() {}

	public static void main(String[] args) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SimpleEditor2().createAndShowGUI();
			}
		});
	}

	protected void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("SE2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent contentPane = new JPanel(new BorderLayout());
		textArea = new JTextArea();
		textArea.setEditable(true);
		javax.swing.JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane);
		frame.setContentPane(contentPane);
		frame.setJMenuBar(createMenuBar());
		initListeners();
		frame.setSize(1280, 720);
		frame.setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuItemNew = new JMenuItem("New..", KeyEvent.VK_N);
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		menuItemOpen = new JMenuItem("Open..", KeyEvent.VK_O);
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		menuItemSaveAs = new JMenuItem("Save As..", KeyEvent.VK_S);
		menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
		menuBar.add(fileMenu);
		fileMenu.add(menuItemNew);
		fileMenu.add(menuItemOpen);
		fileMenu.add(menuItemSave);
		fileMenu.addSeparator();
		fileMenu.add(menuItemSaveAs);
		fileMenu.add(menuItemExit);
		return menuBar;
	}
	
	private void initListeners(){
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		menuItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
					frame.setTitle("SE2 - " + fc.getSelectedFile());
					file = fc.getSelectedFile();
					textArea.setText("");
					try{
						BufferedReader in = new BufferedReader(new FileReader(file));
						textArea.read(in, null);
					}catch (Exception ex){
						ex.printStackTrace();
					}
				}				
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		}); // end addActionListener for Open 
		menuItemSaveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					try{
						textArea.write(new FileWriter(file));
					}catch (IOException ex){
						System.out.println(ex.getMessage());
					}
					frame.setTitle("File Saved - " + fc.getSelectedFile());
				}
			}
		}); // end addActionListener for SaveAs
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null){
					if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
						file = fc.getSelectedFile();
				}else {
					try{
						textArea.write(new FileWriter(file));
					}catch(IOException ex){
						System.out.println(ex.getMessage());
					}
				}
			}
		}); // end addActionListener for Save
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	} // end initListeners()
}
