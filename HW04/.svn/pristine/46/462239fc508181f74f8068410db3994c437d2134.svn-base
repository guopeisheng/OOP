package model;

import java.awt.Graphics;

/**
 * paint strategy interface
 */
public interface IPaintStrategy {
	/**
	 * init ball
	 */
	public void init(ColorBall host);

	/**
	* paint method
	* @param g paint loc
	* @param host ball
	*/
	public void paint(Graphics g, ColorBall host);

	/**
	*  null-object
	*/
	public static final IPaintStrategy NULL_OBJECT = new IPaintStrategy() {

		@Override
		public void paint(Graphics g, ColorBall host) {
		}

		@Override
		public void init(ColorBall host) {
		}
	};
}
