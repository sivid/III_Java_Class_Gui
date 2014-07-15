package homework_5;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class SimpleEditor {
	javax.swing.JTextArea textArea;
	javax.swing.JScrollPane scrollPane;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItemNew;
	JMenuItem menuItemOpen;
	JMenuItem menuItemSave;
	JMenuItem menuItemSaveAs;
	JMenuItem menuItemExit;
	JFileChooser fc;
	JFrame frame;
	File file;
	
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
		SimpleEditor sEditor = new SimpleEditor();
		frame.setJMenuBar(sEditor.createMenuBar());
		frame.setContentPane(sEditor.createContentPane());
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
	
	private JMenuBar createMenuBar() {
		// Create the menu bar.
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		menuItemNew = new JMenuItem("New..", KeyEvent.VK_N);
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		menuItemNew.getAccessibleContext().setAccessibleDescription("what");
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		menu.add(menuItemNew);

		menuItemOpen = new JMenuItem("Open..", KeyEvent.VK_O);
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		menuItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					textArea.setText("");
					try{
						// 
						/*can't use those, they're incompatible with textArea.write()
						 * Scanner scan = new Scanner(new FileReader(file));
							while (scan.hasNext()){
							textArea.append(scan.nextLine());
							textArea.append(System.lineSeparator());
							scan.close();
							*/
						BufferedReader in = new BufferedReader(new FileReader(file));
						textArea.read(in, null);
						
						// TODO: WHY @_@
						//frame.setTitle("SimpleEditor - ");
						System.out.println("SimpleEditor - " + file.getAbsolutePath());
					}catch (Exception ex){
						System.out.println(ex.getMessage());
					}
				} else {
					textArea.append("Open command cancelled by user.\n");
				}
				
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		}); // end addActionListener for Open 
		menu.add(menuItemOpen);

		menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					BufferedWriter out = new BufferedWriter(new FileWriter(file));
					textArea.write(out);
				}catch(IOException ex){
					System.out.println(ex.getMessage());
				}/*finally{
					frame.setTitle("SimpleEditor - " + file.getAbsolutePath());
				}*/
			}
		}); // end addActionListener for Save
		menu.add(menuItemSave);
		menu.addSeparator();

		menuItemSaveAs = new JMenuItem("Save As..", KeyEvent.VK_S);
		menuItemSaveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(frame);
				File file = fc.getSelectedFile();
				if (returnVal == JFileChooser.APPROVE_OPTION){
					try{
						BufferedWriter out = new BufferedWriter(new FileWriter(file));
						textArea.write(out);
					}catch (IOException ex){
						System.out.println(ex.getMessage());
					}
					/*frame.setTitle("File Saved");
					try{
						Thread.sleep(3000);
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}finally{
						frame.setTitle("SimpleEditor - " + file.getAbsolutePath());
					}*/
				}
			}
		}); // end addActionListener for SaveAs
		menu.add(menuItemSaveAs);

		menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(menuItemExit);
		return menuBar;
	} // end createMenuBar()

	public Container createContentPane() {
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);
		// Create a scrolled text area.
		textArea = new JTextArea(5, 30);
		textArea.setEditable(true);
		scrollPane = new JScrollPane(textArea);
		// Add the text area to the content pane.
		contentPane.add(scrollPane, BorderLayout.CENTER);
		return contentPane;
	} // end createContentPane()

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				new SimpleEditor().createAndShowGUI();
			}
		});
	}

}
