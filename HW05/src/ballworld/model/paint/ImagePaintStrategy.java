package ballworld.model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import ballworld.model.Ball;

public class ImagePaintStrategy extends APaintStrategy {
	private ImageObserver imageObserver;
	private Image img;
	private double scaleFac;
	private double fillFac;
	protected AffineTransform localAT = new AffineTransform();

	public ImagePaintStrategy(String filename, double fillFactor) {
		this(new AffineTransform(), filename, fillFactor);

	}

	public ImagePaintStrategy(AffineTransform at, String filename, double fillFactor) {
		super(at);
		this.fillFac = fillFactor;
		try {
			img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(filename));
		} catch (Exception e) {
			System.err.println("ImagePaintStrategy: Error reading file: " + filename + "\n" + e);
		}
	}

	// Transform the image using AT

	public void paintItemfrm(Graphics g, Ball host, AffineTransform at) {
		localAT.setToScale(scaleFac, scaleFac);
		localAT.translate(-img.getWidth(imageObserver) / 2.0, -img.getHeight(imageObserver) / 2.0);
		localAT.preConcatenate(at);
		((Graphics2D) g).drawImage(img, localAT, imageObserver);
	}

	// scale the image and display it

	public void init(Ball host) {
		imageObserver = host.getCanvas();
		MediaTracker mt = new MediaTracker(host.getCanvas());
		mt.addImage(img, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			System.out.println("ImagePaintStrategy.init(): Error waiting for image.  Exception = " + e);
		}

		scaleFac = 2.0 / (fillFac * (img.getWidth(imageObserver) + img.getHeight(imageObserver)) / 2.0);
	}

}
