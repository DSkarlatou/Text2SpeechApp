/* The class has been created with the assistance of the window builder tool*/

package view;

import javax.imageio.ImageIO;
import javax.swing.UIManager;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import command.CommandsFactory;
import model.TTSFacade;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Test2SpeechEditorView{

	private static boolean pressed = false;
	private static boolean saved = false;
	private static int buttonsPressed = 0;
	private static JFrame frame = new JFrame();
	private static JTextPane txtpn;
	private static String path = null;
	private static String cryption = null;
	private static String highlightedText = null;
	private TTSFacade audioManager = new TTSFacade();
	private static CommandsFactory commandFactory = new CommandsFactory();
	
	private int fontSize = 20;

	// menu bar
	private JMenuBar menuBar;
	
	// buttons
	private JButton btnPlay = new JButton("play");
	private JButton btnStop = new JButton("stop audio");
	private static JButton btnRecord = new JButton("record");
	private JButton btnStopRecording = new JButton("stop rec");
	private JButton btnReplay = new JButton("replay");
	private JButton[] buttons = {btnPlay,btnStop,btnRecord,btnStopRecording,btnReplay};
	
	// sliders
	private JSlider sliderVolume = new JSlider(0,100,100);
	private JSlider sliderPitch = new JSlider(50,500,130);
	private JSlider sliderRate = new JSlider(50,250,150);
	private JSlider[] sliders = {sliderVolume, sliderPitch, sliderRate};
	
	// icons
	private Image frameIcon = new ImageIcon(this.getClass().getResource("/frameIcon.png")).getImage();
	private Image icnPlayAudio = new ImageIcon(this.getClass().getResource("/playAudio.png")).getImage();
	private Image icnStopAudio = new ImageIcon(this.getClass().getResource("/stopAudio.png")).getImage();
	private Image icnRecord = new ImageIcon(this.getClass().getResource("/record.png")).getImage();
	private Image icnStopRecord = new ImageIcon(this.getClass().getResource("/stopRecord.png")).getImage();
	private Image icnReplay = new ImageIcon(this.getClass().getResource("/replay.png")).getImage();
	private Image icnOpenFile = new ImageIcon(this.getClass().getResource("/openFile.png")).getImage();
	private Image icnSaveAs = new ImageIcon(this.getClass().getResource("/saveAs.png")).getImage();
	private Image icnSave = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
	private Image icnHelp = new ImageIcon(this.getClass().getResource("/help.png")).getImage();
	private Image icnAudioHighlighted = new ImageIcon(this.getClass().getResource("/audioHighlighted.png")).getImage();
	private Image icnFontSize = new ImageIcon(this.getClass().getResource("/fontSize.png")).getImage();

	// labels
	private JLabel lblVolume = new JLabel("Volume");
	private JLabel lblPitch = new JLabel("Pitch");
	private JLabel lblRate = new JLabel("Rate");
	private JLabel[] labels = {lblVolume, lblPitch, lblRate};
	
	// checkbox
	private JCheckBox chckbxEditable;
	
	// themes
	private JMenuItem blackOnWhite = new JMenuItem("black on white");
	private JMenuItem whiteOnBlack = new JMenuItem("white on black");
	private JMenuItem cyanOnBlack = new JMenuItem("cyan on black");
	private JMenuItem orangeOnBlack = new JMenuItem("orange on black");
	private JMenuItem orangeOnDarkgrey = new JMenuItem("orange on dark grey");
	private JMenuItem magentaOnGreen = new JMenuItem("magenta on green");
	private JMenuItem greenOnMagenta = new JMenuItem("green on magenta");
	private JMenuItem cyanOnMagenta = new JMenuItem("cyan on magenta");
	private JMenuItem magentaOnCyan = new JMenuItem("magenta on cyan");
	private JMenuItem redOnCyan = new JMenuItem("red on cyan");
	private JMenuItem[] themes = {blackOnWhite, whiteOnBlack, cyanOnBlack, orangeOnBlack, orangeOnDarkgrey, magentaOnGreen, greenOnMagenta,cyanOnMagenta, magentaOnCyan, redOnCyan};
	
	public Test2SpeechEditorView(){
		initialize();
	}
	
	public void UIrunner() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize(){
		processKeys();
		commandFactory.setAudioManager(audioManager);
		BufferedImage imageBackground = null;
		try {
			imageBackground = ImageIO.read(getClass().getResource("/background.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		frame = new JFrame();
		
		frame.setContentPane(new BackgroundPanel(imageBackground));
		
		frame.setTitle("Text To Speech");
		frame.setIconImage(frameIcon);
		frame.setBounds(100, 100, 924, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	if(buttonsPressed > 0)
            	{
            		Test2SpeechEditorView.exitPopUp();
            	}
                e.getWindow().dispose();
            }
        });
		
		frame.setMinimumSize(new Dimension(800, 180));
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu FILEMENU = new JMenu("FILE");
		JMenuItem mntmOpen = new JMenuItem("open");
		JMenuItem mntmSaveAs = new JMenuItem("save as");
		JMenuItem mntmSave = new JMenuItem("save");
		JMenuItem mntmHelp = new JMenuItem("help");
		
		menuSettings(FILEMENU);
		menuItemSettings(mntmOpen);
		menuItemSettings(mntmSaveAs);
		menuItemSettings(mntmSave);
		menuItemSettings(mntmHelp);
		
		mntmOpen.setIcon(new ImageIcon(icnOpenFile));
		mntmSaveAs.setIcon(new ImageIcon(icnSaveAs));
		mntmSave.setIcon(new ImageIcon(icnSave));
		mntmHelp.setIcon(new ImageIcon(icnHelp));
		
		// set action listeners and connect them to the commandFactory
		mntmOpen.addActionListener(commandFactory.createCommand("open"));
		mntmSaveAs.addActionListener(commandFactory.createCommand("saveAs"));
		mntmSave.addActionListener(commandFactory.createCommand("save"));
		mntmHelp.addActionListener(commandFactory.createCommand("help"));
		
		FILEMENU.add(mntmOpen);
		FILEMENU.add(mntmSaveAs);
		FILEMENU.add(mntmSave);
		FILEMENU.add(mntmHelp);
		
		JMenu AUDIOMENU = new JMenu("AUDIO");
		JMenuItem PLAYHIGHLIGHTED = new JMenuItem("play highlighted text");
		
		menuSettings(AUDIOMENU);
		
		PLAYHIGHLIGHTED.setIcon(new ImageIcon(icnAudioHighlighted));
		
		menuItemSettings(PLAYHIGHLIGHTED);
		// set action listeners and connect them to the commandFactory
		PLAYHIGHLIGHTED.addActionListener(commandFactory.createCommand("playHighlighted"));
		
		AUDIOMENU.add(PLAYHIGHLIGHTED);
		
		JMenu OPTIONS = new JMenu("OPTIONS");
		JMenuItem changeTextSize = new JMenuItem("change text size");
		
		menuSettings(OPTIONS);
		menuItemSettings(changeTextSize);
		
		changeTextSize.setIcon(new ImageIcon(icnFontSize));

		changeTextSize.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {fontSizePopUp();}}); 
		
		OPTIONS.add(changeTextSize);
		
		JMenu THEME = new JMenu("THEME");
		
		menuSettings(THEME);
		
		for(JMenuItem m : themes)
		{
			menuItemSettings(m);
			THEME.add(m);
		}

		// set theme action listeners
		blackOnWhite.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("blackOnWhite");}});
		whiteOnBlack.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("whiteOnBlack");}});
		cyanOnBlack.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("cyanOnBlack");}});
		orangeOnBlack.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("orangeOnBlack");}});
		orangeOnDarkgrey.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("orangeOnDarkgrey");}});
		magentaOnGreen.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("magentaOnGreen");}});
		greenOnMagenta.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("greenOnMagenta");}});
		cyanOnMagenta.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("cyanOnMagenta");}});
		magentaOnCyan.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("magentaOnCyan");}});
		redOnCyan.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {theme("redOnCyan");frame.revalidate();frame.repaint();}});

		menuBar.add(FILEMENU);
		menuBar.add(AUDIOMENU);
		menuBar.add(THEME);
		menuBar.add(OPTIONS);
		//-------------------------------------------------------------------------------------------
		btnPlay.setIcon(new ImageIcon(icnPlayAudio));
		btnStop.setIcon(new ImageIcon(icnStopAudio));
		btnRecord.setIcon(new ImageIcon(icnRecord));
		btnStopRecording.setIcon(new ImageIcon(icnStopRecord));
		btnReplay.setIcon(new ImageIcon(icnReplay));
		//-------------------------------------------------------------------------------------------
		btnPlay.addActionListener(commandFactory.createCommand("play"));
		btnStop.addActionListener(commandFactory.createCommand("stopAudio"));
		btnRecord.addActionListener(commandFactory.createCommand("record"));
		btnStopRecording.addActionListener(commandFactory.createCommand("stopRecording"));
		btnReplay.addActionListener(commandFactory.createCommand("replay"));

		// ------------------------------------------------------------------------------------------
		// set change listeners for the voice tuning
		sliderVolume.addChangeListener(new ChangeListener() {@Override public void stateChanged(ChangeEvent e) {audioManager.setVolume(sliderVolume.getValue());}});
		sliderPitch.addChangeListener(new ChangeListener() {@Override public void stateChanged(ChangeEvent e) {audioManager.setPitch(sliderPitch.getValue());}});
		sliderRate.addChangeListener(new ChangeListener() {@Override public void stateChanged(ChangeEvent e) {audioManager.setRate(sliderRate.getValue());}});
		
		//-------------------------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		setTxtpn(new JTextPane());
		getTxtpn().setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		getTxtpn().setFocusCycleRoot(false);
		scrollPane.setViewportView(getTxtpn());
		
		// checkbox responsible for the editing process
		chckbxEditable = new JCheckBox("check to edit");
		chckbxEditable.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxEditable.setContentAreaFilled(false);
		chckbxEditable.setBorderPainted(false);
		chckbxEditable.setBackground(new Color(204, 255, 255));
		chckbxEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxEditable.isSelected())
					txtpn.setEditable(true);
				else if(!chckbxEditable.isSelected())
					txtpn.setEditable(false);
			}
		});
		
		//-------------------------------------------------------------------------------------------
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(btnPlay)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStop)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRecord, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStopRecording, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReplay)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(sliderRate, 0, 0, Short.MAX_VALUE)
								.addComponent(sliderPitch, 0, 0, Short.MAX_VALUE)
								.addComponent(sliderVolume, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRate, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVolume)
								.addComponent(lblPitch))
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
							.addComponent(chckbxEditable)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE))
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(72)
									.addComponent(chckbxEditable))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnRecord, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnReplay, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnStopRecording, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblVolume, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addComponent(sliderVolume, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
									.addGap(11)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPitch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(sliderPitch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(11)
											.addComponent(sliderRate, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(15)
											.addComponent(lblRate, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))))))
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		//---------------------------------------------------------------------------------------------------------------
		customizeMenuBar(menuBar, Color.WHITE, Color.BLACK);
		labelSettings(Color.BLACK);
		buttonSettings(Color.BLACK);
		textpaneSettings(Color.WHITE, Color.BLACK);
		sliderSettings(Color.BLACK);
		chckbxEditable.setForeground(Color.BLACK);
	}
	
	//pop up to ask the user to choose the encoding || decoding
	public static String cryptionPopUp(String cryption) //either encryption, or decryption
	{
		if(path == null)
		{
			return null;
		}
		Object[] cryptionPossibilities = {"none", "atbash", "rot13"};
		String s = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "What kind of " + cryption + " do you need?",
		                    cryption,
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    cryptionPossibilities,
		                    "none");
		if(s == null)
			s = "none";
		
		cryption = s;
		return s;
	}
	
	// opens the file chooser pop up
	public static String fileChooser(String choice)
	{
		int returnVal = 0;
		JFileChooser chooser = new JFileChooser();
		chooser = acceptableFilters(chooser);
		
		if(choice.equals("open"))
		{
			// This if checks if there are changes in the editor prior to opening a new document
			if(buttonsPressed > 0)
        	{
				Test2SpeechEditorView.exitPopUp();
				pressed = false;
				buttonsPressed = 0;
        	}
			returnVal = chooser.showOpenDialog(null);
		}
		else if(choice.equals("save")) {
			returnVal = chooser.showSaveDialog(null);
		}

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().toString();
		}
		
		else if(returnVal == JFileChooser.CANCEL_OPTION)
		{
			path = null;
		}
		
		return path;
	}
	
	// showcases the acceptable formats
	public static JFileChooser acceptableFilters(JFileChooser chooser)
	{
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word Documents, *.docx","docx"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel, *.xlsx","xlsx"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files, *.txt","txt"));
		chooser.setAcceptAllFileFilterUsed(false);
		return chooser;
	}
	
	// insert the lst arraylist to the editor
	public static void insertInTextPane(ArrayList<String> lst) throws BadLocationException
	{
		txtpn.setText(""); //this is to clear previous text on textPane
		Document doc = txtpn.getDocument();
		
		for(String i : lst)
		{
			doc.insertString(doc.getLength(), i, null);
			doc.insertString(doc.getLength(), "\n", null); 
		}
	}
	
	//Displays an error message
	public static void errorDialog(String errorMessage) 
	{
		path = null;
		JOptionPane.showMessageDialog(frame,
			    errorMessage,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
		return;
	}
	// pop up about the text size
	public void fontSizePopUp()
	{
		JTextField size = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("size: "));
	    myPanel.add(size);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	             "Enter font size", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) 
	    	return;
	    
	    char[] chars = size.getText().toCharArray();
	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	        	Test2SpeechEditorView.linePopUpInvalid("Not a number");
	            return;
	        }
	    }
	    
	    if (result == JOptionPane.OK_OPTION) {
	    	if(size.getText().isEmpty())
	    	{
	    		return;
	    	}
	    	getTxtpn().setFont(new Font("Tahoma", Font.PLAIN, Integer.parseInt(size.getText())));
	    }
	    
	}
	
	public static void linePopUpInvalid(String errorMessage)
	{
		JOptionPane.showMessageDialog(frame,
			    errorMessage,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

	// handles the keyboard input
	private void processKeys(){ //DO NOT TOUCH, VERY SENSITIVE TO CHANGE
	    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
	        new KeyEventDispatcher()  { 
	            public boolean dispatchKeyEvent(KeyEvent e){
	                if(e.getID() == KeyEvent.KEY_PRESSED && txtpn.isEditable()){
	                	buttonsPressed++;
	                    pressed = true;
	                    saved = false;
	                }
	                pressed = false;
	                return pressed;
	            } 
	    });
	}
	
	// checks for changes if the user tried to exit
	public static void exitPopUp()
	{
		if(saved == false)
		{
			int n = JOptionPane.showConfirmDialog(
				    frame,
				    "You have unsaved changes.\nWould you like to save?",
				    "Save changes ?",
				    JOptionPane.YES_NO_OPTION);
			if(n == 0)
			{
				if(path == null) {
					ActionListener al = commandFactory.createCommand("saveAs");
					al.actionPerformed(null);
				}
				else if (path != null)
				{
					ActionListener al = commandFactory.createCommand("save");
					al.actionPerformed(null);
				}
			}	
		}
	}

	// GETTERS & SETTERS
	public static JTextPane getTxtpn() {
		return txtpn;
	}

	public void setTxtpn(JTextPane txtpn) {
		this.txtpn = txtpn;
		txtpn.setEditable(false);
	}

	public static String getHighlightedText()
	{
		highlightedText = txtpn.getSelectedText();
		return highlightedText;
	}

	public static String getPath() {
		return path;
	}

	public static String getCryption() {
		return cryption;
	}
	
	public static JButton getRecordButton()
	{
		return btnRecord;
	}

	// GUI settings and methods
	private void menuItemSettings(JMenuItem menuItem)
	{
		menuItem.setFont(new Font("Tahoma", Font.BOLD, 18));
		menuItem.setSelected(true);
	}

	private void menuSettings(JMenu menu)
	{
		menu.setFont(new Font("Tahoma", Font.BOLD, 20));
		menu.setSelected(true);
		
	}
	
	private void buttonSettings(Color foreground)
	{
		for(JButton b : buttons)
		{
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setForeground(foreground);
		}
	}
	
	private void sliderSettings(Color color)
	{
		if(color == null) {color = Color.BLACK;}
		for(JSlider s : sliders)
		{
			s.setUI(new BasicSliderUI(s));
			s.setBackground(color);
			s.setOpaque(false);
		}
	}
	
	private void labelSettings(Color foreground)
	{
		for(JLabel l : labels)
		{
			l.setFont(new Font("Tahoma", Font.BOLD, 12));
			l.setForeground(foreground);
		}
	}
	
	private void textpaneSettings(Color background, Color foreground)
	{
		UIDefaults defaults = new UIDefaults();
		defaults.put("EditorPane[Enabled].backgroundPainter", background);
		txtpn.putClientProperty("Nimbus.Overrides", defaults);
		txtpn.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
		txtpn.setBackground(background);
		txtpn.setForeground(foreground);
	}
	
	private void theme(String theme)
	{
		Color background = Color.WHITE;
		Color font = Color.BLACK;
		switch(theme) {
		case "blackOnWhite":
			background = Color.WHITE;
			font = Color.BLACK;
			break;
		case "whiteOnBlack":
			background = Color.BLACK;
			font = Color.WHITE;
			break;
		case "orangeOnBlack":
			background = Color.BLACK;
			font = Color.ORANGE;
			break;
		case "cyanOnBlack":
			background = Color.BLACK;
			font = Color.CYAN;
			break;
		case "orangeOnDarkgrey":
			background = Color.DARK_GRAY;
			font = Color.ORANGE;
			break;
		case "magentaOnGreen":
			background = Color.GREEN;
			font = Color.MAGENTA;
			break;	
		case "greenOnMagenta":
			background = Color.MAGENTA;
			font = Color.GREEN;
			break;		
		case "cyanOnMagenta":
			background = Color.MAGENTA;
			font = Color.CYAN;
			break;		
		case "magentaOnCyan":
			background = Color.CYAN;
			font = Color.MAGENTA;
			break;		
		case "redOnCyan":			
	        background = Color.CYAN;
			font = Color.RED;
			break;			
		}

		customizeMenuBar(menuBar, background, font); 
		labelSettings(font);
		buttonSettings(font);
		textpaneSettings(background, font);
		sliderSettings(font);
		chckbxEditable.setForeground(font);
	}
	
	private void customizeMenuBar(JMenuBar menuBar, Color backgroundColor, Color fontColor) {
		
		menuBar.setUI(new BasicMenuBarUI() {
		    	
			@Override
			public void paint(Graphics g, JComponent c) {
				g.setColor(backgroundColor);
				g.fillRect(0, 0, c.getWidth(), c.getHeight());
			}
		});
	
		MenuElement[] menus = menuBar.getSubElements();
	
		for (MenuElement menuElement : menus) {
	
			JMenu menu = (JMenu) menuElement.getComponent();
			changeComponentColors(menu, backgroundColor, fontColor);
			menu.setOpaque(true);
	
			MenuElement[] menuElements = menu.getSubElements();
	
			for (MenuElement popupMenuElement : menuElements) {
	
				JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
				popupMenu.setBorder(null);
	
				MenuElement[] menuItens = popupMenuElement.getSubElements();
	
				for (MenuElement menuItemElement : menuItens) {
	
					JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
					changeComponentColors(menuItem, backgroundColor, fontColor);
					menuItem.setOpaque(true);
				}
			}
		}
	}

	private void changeComponentColors(Component comp,  Color backgroundColor, Color fontColor) {
	    comp.setBackground(backgroundColor);
	    comp.setForeground(fontColor);
	    if(comp.getClass().getCanonicalName().equals("javax.swing.JMenuItem"))
	    {
	    	((JMenuItem) comp).setBorder(BorderFactory.createLineBorder(fontColor, 1));
	    }
	}

	public static void setSavedStatus(boolean b) //this is so that if we have saved and tried to exit the pop up unsaved changes does not pop up
	{
		saved = b;
	}
}
