package ballworld.view;

//TODO edit GUI

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The BallGUI class is to create a GUI for user interface.
 */
public class MyGUI<TStrategyDropListItem, TPaintDropListItem> extends JFrame {

	/**
	 * serialVersionUID helps the Java system reliably serialize and de-serialize
	 * (reconstruct) objects by providing a unique identifier for the class.
	 * Serialization is used whenever objects are shipped from one place to
	 * another, such as across a network.
	 */
	private static final long serialVersionUID = 4033244772609849208L;

	private IModelControlAdapter<TStrategyDropListItem, TPaintDropListItem> modelCtrlAdpt;
	private IModelUpdateAdapter modelPaintAdpt;

	// the content panel
	private JPanel contentPane;

	// the input panel
	private final JPanel ctlPane = new JPanel();

	// the display panel
	private JPanel dispPane = new JPanel() {
		/**
		* This serialVersionUID serves the same purpose.
		*/
		private static final long serialVersionUID = 6768993845471290438L;

		// Overridden paintComponent method to paint a shape in the panel.
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // clear the panel and redo the background
			modelPaintAdpt.update(g); // call back to the model to paint the ball
		}
	};

	private final JTextField inputTf = new JTextField();
	private final JTextField paintinputTf = new JTextField();

	private final JComboBox<TStrategyDropListItem> upperComboBox = new JComboBox<TStrategyDropListItem>();
	private final JComboBox<TStrategyDropListItem> lowerComboBox = new JComboBox<TStrategyDropListItem>();
	private final JComboBox<TPaintDropListItem> paintBox = new JComboBox<TPaintDropListItem>();

	private final JButton btnMakeIt = new JButton("Make Item");
	private final JButton btnCombine = new JButton("Combine ");
	private final JButton btnMakeSwitcher = new JButton("Make Switcher");
	private final JButton btnSwitch = new JButton("Switch!");
	private final JButton btnAdd = new JButton("Add");
	private final JButton btnAddPaint = new JButton("Add Paint Strategy");
	private final JButton btnClearAll = new JButton("Clear All");

	private final JPanel addToListPane = new JPanel();
	private final JPanel switchPane = new JPanel();
	private final JPanel combPane = new JPanel();
	private final JPanel paintStrategyPane = new JPanel();

	public MyGUI(IModelUpdateAdapter modelPaintAdpt,
			IModelControlAdapter<TStrategyDropListItem, TPaintDropListItem> modelCtrlAdpt) {
		this.modelPaintAdpt = modelPaintAdpt;
		this.modelCtrlAdpt = modelCtrlAdpt;
		initGUI();
	}

	public void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// set control panel

		ctlPane.setBackground(Color.GREEN);
		ctlPane.setToolTipText("Control panel which controls the input of component");
		ctlPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		contentPane.add(ctlPane, BorderLayout.NORTH);

		// set display panel
		dispPane.setToolTipText("The panel where balls will be painted.");
		dispPane.setBackground(Color.WHITE);
		contentPane.add(dispPane, BorderLayout.CENTER);

		GridLayout gbl_addToListPane = new GridLayout(2, 1);
		addToListPane.setLayout(gbl_addToListPane);
		addToListPane.add(btnAdd);
		GridBagConstraints gbc_textAddToList = new GridBagConstraints();
		addToListPane.add(inputTf, gbc_textAddToList);
		inputTf.setText("Collide");
		inputTf.setToolTipText(
				"<html>Where users input Strategies and add then to the display panel.<br>options:<ul>ColorChanging, Curve, Breathing, Straight, Wander</ul>\r\n<br>Collide option:<ul>Collide,Magnetic</ul>\r\n<br>Interaction options:<ul>AverageMass, Eat</ul></html>");
		btnAdd.setToolTipText("Add the model Strategy");
		ctlPane.add(addToListPane);

		GridLayout gbl_switchPane = new GridLayout(2, 1);
		switchPane.setLayout(gbl_switchPane);
		ctlPane.add(switchPane);

		GridLayout gl_combPane = new GridLayout(4, 1);
		combPane.setLayout(gl_combPane);
		combPane.add(btnMakeIt);
		btnMakeIt.setToolTipText("Make an item");
		GridBagConstraints gbc_upperList = new GridBagConstraints();
		combPane.add(upperComboBox, gbc_upperList);
		upperComboBox.setToolTipText("All the Strategy you add");
		GridBagConstraints gbc_lowerList = new GridBagConstraints();
		combPane.add(lowerComboBox, gbc_lowerList);
		combPane.add(lowerComboBox);
		lowerComboBox.setToolTipText("All the strategy you want is here");
		combPane.add(btnCombine);
		btnCombine.setToolTipText("Combine two strategies");
		ctlPane.add(combPane);
		ctlPane.add(btnMakeSwitcher);
		btnMakeSwitcher.setToolTipText("Make the item you switch to");

		btnMakeSwitcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.makeSwitchBall(paintBox.getItemAt(paintBox.getSelectedIndex()));
			}
		});
		ctlPane.add(btnSwitch);
		btnSwitch.setToolTipText("Change the ball to the strategy you choose");

		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.switchStrategy(upperComboBox.getItemAt(upperComboBox.getSelectedIndex()));
			}
		});

		GridLayout gbl_paintStrategyPane = new GridLayout(3, 1);
		paintStrategyPane.setLayout(gbl_paintStrategyPane);
		paintStrategyPane.add(btnAddPaint);
		btnAddPaint.setToolTipText("Add a paint strategy");
		GridBagConstraints gbc_paintinputTf = new GridBagConstraints();
		paintStrategyPane.add(paintinputTf, gbc_paintinputTf);
		paintinputTf.setToolTipText(
				"<html>Where users can input Strategies and add then to the display panel.\r\n\r\n<br>Feasible input:<ul>Ball, Ellipse, Rectangle, Square, Mario, SwimFish, Fish2, Fish1</html>");
		paintinputTf.setText("Ball");
		paintStrategyPane.add(paintBox);
		ctlPane.add(paintStrategyPane);

		btnClearAll.setToolTipText("A button which allows users to clear all the balls on the display panel");
		btnClearAll.addActionListener((e) -> modelCtrlAdpt.clearBalls());
		ctlPane.add(btnClearAll);

		// set action listener
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TStrategyDropListItem o = modelCtrlAdpt.addStrategy(inputTf.getText());
				if (null == o) {
					return;
				}
				upperComboBox.insertItemAt(o, 0);
				lowerComboBox.insertItemAt(o, 0);
			}
		});

		btnMakeIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.makeBall(upperComboBox.getItemAt(upperComboBox.getSelectedIndex()),
						paintBox.getItemAt(paintBox.getSelectedIndex()));
			}
		});

		btnCombine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TStrategyDropListItem o = modelCtrlAdpt.combineStrategies(
						upperComboBox.getItemAt(upperComboBox.getSelectedIndex()),
						lowerComboBox.getItemAt(lowerComboBox.getSelectedIndex()));
				if (null == o) {
					return;
				}
				upperComboBox.insertItemAt(o, 0);
				lowerComboBox.insertItemAt(o, 0);
			}
		});

		btnAddPaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TPaintDropListItem o = modelCtrlAdpt.addPaintStrategy(paintinputTf.getText());
				if (null == o) {
					return;
				}
				paintBox.insertItemAt(o, 0);
			}
		});
	}

	// starts the application
	public void start() {
		setVisible(true);
	}

	public MyGUI() {
		initGUI();
	}

	// repaint the display panel to update the view.
	public void update() {
		dispPane.repaint();
	}

	// called by model to view adapter in order to retrieve the panel for detecting collision with walls.
	public Component getComponent() {
		return dispPane;
	}
}
