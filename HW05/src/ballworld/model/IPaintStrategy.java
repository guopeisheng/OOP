package ballworld.model;

import java.awt.Graphics;

/**
 * @author GPS
 *
 */
public interface IPaintStrategy {

	/**
	 * @param g
	 * @param host
	 */
	public void paint(Graphics g, Ball host);

	/**
	 * @param host
	 */
	public void init(Ball host);
}
