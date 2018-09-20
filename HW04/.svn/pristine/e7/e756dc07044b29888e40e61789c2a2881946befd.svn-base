package paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.ColorBall;
import model.IPaintStrategy;

/**
 * paint strategy 
 */
public abstract class APaintStrategy implements IPaintStrategy {
	/**
	 * affine transform
	 */
	protected AffineTransform at;

	/**
	 * constructor
	 * @param at affine transform
	 */
	public APaintStrategy(AffineTransform at) {
		this.at = at;
	}

	/**
	 * init ball
	 * @param host ball
	 */
	@Override
	public void init(ColorBall host) {

	}

	/**
	 * paint method
	 * @param g draw place
	 * @param host ball
	 */
	@Override
	public void paint(Graphics g, ColorBall host) {

		double scale = host.getRadius();
		at.setToTranslation(host.getLocation().x, host.getLocation().y);
		at.scale(scale, scale);
		at.rotate(host.getVelocity().x, host.getVelocity().y);
		g.setColor(host.getColor());
		paintCfg(g, host);
		paintXfrm(g, host, at);

	}

	/**
	 * get affine
	 * @return affine
	 */
	public AffineTransform getAt() {
		return at;
	}

	/**
	 * paint cfg
	 * @param g The Graphics context that will be paint on
	 * @param host The host Ball that the required information will be pulled from.
	 */
	protected void paintCfg(Graphics g, ColorBall host) {

	}

	/**
	 * paint xfm
	* @param g The Graphics context that will be paint on
	* @param host The host Ball that the required information will be pulled from.
	* @param at transform
	*/
	public abstract void paintXfrm(Graphics g, ColorBall host, AffineTransform at);

}
