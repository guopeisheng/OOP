package view;

import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;

import net.miginfocom.swing.MigLayout;

/**
 * Ball GUI
 * 
 * @param <TDropListItem>
 */
public class BallGUI<TDropListItem, TPaintDropListItem> extends JFrame {
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -3750673698467716232L;
	// private JTextField textField;
	/**
	 * Adapter back to the model for control tasks.
	 */
	private IModelControlAdapter<TDropListItem, TPaintDropListItem> _modelControlAdpt;
	/**
	 * Adapter back to the model for update tasks.
	 */
	private IModelUpdateAdapter _modelUpdateAdpt;
	/**
	 * The top drop list, used to select what strategy to use in a new ball and to
	 * switch the switcher to.
	 */
	private JComboBox<TDropListItem> _list1DL;
	/**
	 * Bottom drop list, used for combining with the top list selection.
	 */
	private JComboBox<TDropListItem> _list2DL;
	/**
	 * text field
	 */
	private JTextField txtUpdateStrategy;
	/**
	 * center panel
	 */
	private JPanel centerPanel;
	/**
	 * txt field
	 */
	private JTextField txtPaint;

	/**
	 * BallGUI constructer
	 * 
	 * @param modelCtrlAdpt control adapter
	 * @param modelUpdateAdpt update adapter
	 */
	public BallGUI(IModelControlAdapter<TDropListItem, TPaintDropListItem> modelCtrlAdpt,
			IModelUpdateAdapter modelUpdateAdpt) {
		this._modelControlAdpt = modelCtrlAdpt;
		this._modelUpdateAdpt = modelUpdateAdpt;
		initGUI();

	}

	/**
	 * set visiable
	 */
	public void start() {
		this.setVisible(true);
	}

	/**
	 * get center panel
	 * @return center panel
	 */
	public Component getCenterPanel() {
		return centerPanel;
	}

	/**
	 * Create the frame.
	 */
	public void initGUI() {

		JPanel panel = new JPanel();
		panel.setToolTipText("uppanel");
		panel.setBackground(Color.PINK);
		getContentPane().add(panel, BorderLayout.NORTH);
		GridBagConstraints gbs = new GridBagConstraints();
		gbs.fill = GridBagConstraints.BOTH;
		gbs.gridwidth = 1;
		gbs.gridheight = 1;
		gbs.insets = new Insets(5, 5, 5, 5);
		gbs.weightx = 1;
		gbs.weighty = 1;
		gbs.gridx = 0;
		gbs.gridy = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 650, 500);

		JPanel pnl1 = new JPanel();
		pnl1.setToolTipText("the first panel");
		pnl1.setLayout(new GridLayout(0, 1, 0, 0));

		txtUpdateStrategy = new JTextField();
		pnl1.add(txtUpdateStrategy);
		txtUpdateStrategy.setText("Straight");
		txtUpdateStrategy.setToolTipText("please input strategy");
		txtUpdateStrategy.setColumns(10);

		JButton btnAddToList = new JButton("Add to lists");
		pnl1.add(btnAddToList);
		btnAddToList.setToolTipText("click to add strategy");
		btnAddToList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TDropListItem o = _modelControlAdpt.addStrategy(txtUpdateStrategy.getText());
				if (null == o)
					return; // just in case
				_list1DL.insertItemAt(o, 0);
				_list2DL.insertItemAt(o, 0);
			}
		});

		JPanel pnl2 = new JPanel();
		pnl2.setToolTipText("the second panel");
		pnl2.setLayout(new GridLayout(0, 1, 0, 0));
		JComboBox<TPaintDropListItem> _listPaintStrategy = new JComboBox<TPaintDropListItem>();
		JButton btnMakeSelectedBall = new JButton("Make selected ball");
		pnl2.add(btnMakeSelectedBall);
		btnMakeSelectedBall.setToolTipText("click to make selected ball of first dropdown list");
		btnMakeSelectedBall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.makeBall(_list1DL.getItemAt(_list1DL.getSelectedIndex()),
						_listPaintStrategy.getItemAt(_listPaintStrategy.getSelectedIndex()));

			}

		});

		_list1DL = new JComboBox<TDropListItem>();
		pnl2.add(_list1DL);
		_list1DL.setToolTipText("select strategy");

		_list2DL = new JComboBox<TDropListItem>();
		pnl2.add(_list2DL);
		_list2DL.setToolTipText("select strategy");

		JButton btnCombine = new JButton("Combine！");
		pnl2.add(btnCombine);
		btnCombine.setToolTipText("combine two strategy");
		btnCombine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem o = _modelControlAdpt.combineStrategies(_list1DL.getItemAt(_list1DL.getSelectedIndex()),
						_list2DL.getItemAt(_list2DL.getSelectedIndex()));
				if (null == o)
					return; // just in case
				_list1DL.insertItemAt(o, 0);
				_list2DL.insertItemAt(o, 0);
			}
		});

		JPanel pnl3 = new JPanel();
		pnl3.setToolTipText("the third panel");
		pnl3.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnMakeSwitcher = new JButton("Make Switcher");
		pnl3.add(btnMakeSwitcher);
		btnMakeSwitcher.setToolTipText("make a default straight ball");
		btnMakeSwitcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.makeSwitchBall(_listPaintStrategy.getItemAt(_listPaintStrategy.getSelectedIndex()));

			}
		});

		JButton btnSwitch = new JButton("Switch");
		pnl3.add(btnSwitch);
		btnSwitch.setToolTipText("switch ball");
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.switchStrategy(_list1DL.getItemAt(_list1DL.getSelectedIndex()));

			}
		});
		panel.setLayout(new MigLayout("", "[117px][160px][90.00px][][82.00px,grow]", "[116px,grow]"));
		panel.add(pnl1, "cell 0 0,alignx center,aligny center");
		panel.add(pnl2, "cell 1 0,alignx center,aligny center");
		panel.add(pnl3, "cell 2 0,alignx center,aligny center");

		JButton btnClearall = new JButton("ClearAll");
		btnClearall.setToolTipText("clear panel");
		panel.add(btnClearall, "cell 3 0,alignx center,aligny center");

		JPanel pnl4 = new JPanel();
		pnl4.setToolTipText("the fourth panel");
		panel.add(pnl4, "cell 4 0,alignx center,aligny center");
		pnl4.setLayout(new GridLayout(0, 1, 0, 0));

		txtPaint = new JTextField();
		txtPaint.setToolTipText("text in a new paint strategy");
		txtPaint.setText("Ball");
		pnl4.add(txtPaint);
		txtPaint.setColumns(6);

		_listPaintStrategy.setToolTipText("the list of PaintStrategy");
		pnl4.add(_listPaintStrategy);
		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("add a new Paint Strategy");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TPaintDropListItem o = _modelControlAdpt.addPaintStrategy(txtPaint.getText());
				if (o == null)
					return;
				_listPaintStrategy.insertItemAt(o, 0);
			}
		});
		pnl4.add(btnAdd);

		btnClearall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.clear();
			}
		});
		gbs.fill = GridBagConstraints.BOTH;
		gbs.gridwidth = 1;
		gbs.gridheight = 1;
		gbs.insets = new Insets(5, 5, 5, 5);
		gbs.weightx = 1;
		gbs.weighty = 1;
		gbs.gridx = 1;
		gbs.gridy = 0;
		// JComboBox J1=new JComboBox

		JPanel Display = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6952656931251224807L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				_modelUpdateAdpt.update(g);

			}
		};
		Display.setToolTipText("display panel");
		getContentPane().add(Display, BorderLayout.CENTER);
		this.centerPanel = Display;
		Display.setLayout(new GridLayout(1, 0, 0, 0));
	}

	/**
	 * update centerPanel
	 */
	public void update() {
		centerPanel.repaint();

	}

}
