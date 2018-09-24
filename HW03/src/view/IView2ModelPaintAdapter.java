package view;

import java.awt.Graphics;

/**
 * @author zihanli
 *the interface that enables the view to talk to the model to paint
 */
public interface IView2ModelPaintAdapter {

	/**
	 * tell the model to paint
	 */
	public void paint(Graphics g);

	/**
	 * Null Object Design Pattern
	 */
	public static final IView2ModelPaintAdapter NULL_OBJECT = new IView2ModelPaintAdapter() {
		public void paint(Graphics g) {
		}

	};

}
