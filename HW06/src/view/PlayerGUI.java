package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PlayerGUI<TInstrument> extends JFrame {
	private static final long serialVersionUID = 6011572294054817374L;

	private JPanel contentPane;

	private final JPanel pnlControl = new JPanel();
	private final JTextPane txtTip = new JTextPane();
	private final JTextField txtInput = new JTextField();
	private final JButton btnLoad = new JButton("Load");
	private final JButton btnParse = new JButton("Parse");
	private final JComboBox<TInstrument> boxInstrument = new JComboBox<TInstrument>();
	private final JButton btnPlay = new JButton("Play");

	private final JPanel pnlDisplay = new JPanel();
	private final JSplitPane pnlSplit = new JSplitPane();
	private final JScrollPane pnlFile = new JScrollPane();
	private final JTextArea textFile = new JTextArea();
	private final JScrollPane pnlParsed = new JScrollPane();
	private final JTextArea textParsed = new JTextArea();

	private IModelAdapter<TInstrument> adp = (IModelAdapter<TInstrument>) IModelAdapter.NULL_OBJECT;
	private final JButton btnStop = new JButton("Stop");

	/**
	 * Create the frame.
	 */
	public PlayerGUI() {
		initGUI();
	}

	public PlayerGUI(IModelAdapter<TInstrument> modelAdp) {
		adp = (IModelAdapter<TInstrument>) modelAdp;
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//display panel setting
		contentPane.add(pnlDisplay, BorderLayout.CENTER);
		pnlDisplay.setLayout(new BorderLayout(0, 0));

		pnlSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlDisplay.add(pnlSplit);

		pnlSplit.setLeftComponent(pnlFile);
		textFile.setLineWrap(true);
		textFile.setRows(7);
		pnlFile.setViewportView(textFile);
		pnlFile.setBorder(new TitledBorder("File Content"));

		pnlSplit.setRightComponent(pnlParsed);
		textParsed.setLineWrap(true);
		pnlParsed.setViewportView(textParsed);
		pnlParsed.setBorder(new TitledBorder("Parsed IPhrase Structure"));

		//control panel setting
		pnlControl.setBackground(Color.PINK);
		contentPane.add(pnlControl, BorderLayout.NORTH);

		txtTip.setBackground(Color.PINK);
		txtTip.setText("File:");
		pnlControl.add(txtTip);

		txtInput.setText("scale");
		txtInput.setColumns(5);
		pnlControl.add(txtInput);

		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fullPath = "/songs/" + txtInput.getText() + ".abc";
				adp.Load(fullPath);
				btnParse.setEnabled(true);
			}
		});
		pnlControl.add(btnLoad);

		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adp.Parse();
				btnPlay.setEnabled(true);
			}
		});
		pnlControl.add(btnParse);

		pnlControl.add(boxInstrument);

		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adp.Parse();
				btnPlay.setEnabled(true);
			}
		});
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adp.Play(boxInstrument.getItemAt(boxInstrument.getSelectedIndex()));
			}
		});
		pnlControl.add(btnPlay);

		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adp.Stop();
			}
		});
		pnlControl.add(btnStop);
	}

	public void addInstrument(TInstrument instrument) {
		boxInstrument.addItem(instrument);
	}

	/**
	 * Start the frame
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * getter method of textParsed
	 * 
	 */
	public JTextArea getTextParsed() {
		return textParsed;
	}

	/**
	 * getter method of textFile
	 * @return 
	 */
	public JTextArea getTextFile() {
		return textFile;
	}

	public void update() {
	}

}
