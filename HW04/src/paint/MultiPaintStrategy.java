package paint;

import java.awt.Graphics;

import java.awt.geom.AffineTransform;

import model.ColorBall;

/** 
* multipaint
*/
public class MultiPaintStrategy extends APaintStrategy {
	/**
	 * the array of paint strategy
	 */
	private APaintStrategy[] pstrats;

	/**
	 * constructor
	 * @param at transform
	 * @param pstrats paint strategies
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy[] pstrats) {
		super(at);
		this.pstrats = pstrats;
	}

	/**
	 * constructor
	 * @param pstrats paint strategies
	 */
	public MultiPaintStrategy(APaintStrategy... pstrats) {
		this(new AffineTransform(), pstrats);

	}

	/**
	 * init
	 * @param host ball
	 */
	public void init(ColorBall host) {
		for (APaintStrategy p : pstrats) {
			p.init(host);
		}
	}

	/**
	 * paint xfrm
	 * @param g place to draw
	 * @param host ball
	 * @param at transform
	 */
	public void paintXfrm(Graphics g, ColorBall host, AffineTransform at) {
		for (APaintStrategy p : pstrats) {
			p.paintXfrm(g, host, at);
		}
	}
}
