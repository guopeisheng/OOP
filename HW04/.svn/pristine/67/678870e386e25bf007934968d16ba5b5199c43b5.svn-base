package paint;

/** 
* @author : 
* @version :{date:} {time:} 
* @Description: 
*/
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;

import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import model.ColorBall;

/**
 * image paint strategy
 */
public class ImagePaintStrategy extends APaintStrategy {
	/**
	 * image obs
	 */
	private ImageObserver imageObs;
	/**
	 * image
	 */
	private Image image;
	/**
	 * scale factor
	 */
	private double scaleFactor;
	/**
	 * fill factor
	 */
	private double fillFactor;
	/**
	 * affine trans local
	 */
	protected AffineTransform localAT = new AffineTransform();
	/**
	 * AffineTransform
	 */
	protected AffineTransform tempAT;

	/**
	 * constructor
	 * @param at affine
	 * @param filename image file
	 * @param fillFactor fill factor
	 */
	public ImagePaintStrategy(AffineTransform at, String filename, double fillFactor) {
		super(at);
		try {
			image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(filename));
		} catch (Exception e) {
			System.err.println("ImagePaintStrategy: Error reading file: " + filename + "\n" + e);
		}
		this.fillFactor = fillFactor;
	}

	/**
	 * constructor
	 * @param filename image file
	 * @param fillFactor fill factor
	 */
	public ImagePaintStrategy(String filename, double fillFactor) {
		this(new AffineTransform(), filename, fillFactor);

	}

	/**
	 * paint xfrm
	 * @param g place to draw
	 * @param host ball
	 * @param at affine transform
	 */
	public void paintXfrm(Graphics g, ColorBall host, AffineTransform at) {
		localAT.setToScale(scaleFactor, scaleFactor);
		localAT.translate(-image.getWidth(imageObs) / 2.0, -image.getHeight(imageObs) / 2.0);
		localAT.preConcatenate(at);
		((Graphics2D) g).drawImage(image, localAT, imageObs);
	}

	/**
	* init
	* @param host ball
	*/
	public void init(ColorBall host) {
		imageObs = host.getComponent();
		MediaTracker mt = new MediaTracker(host.getComponent());
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			System.out.println("ImagePaintStrategy.init(): Error waiting for image.  Exception = " + e);
		}

		scaleFactor = 2.0 / (fillFactor * (image.getWidth(imageObs) + image.getHeight(imageObs)) / 2.0);
	}

}
