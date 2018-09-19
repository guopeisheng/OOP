package ballworld.model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import ballworld.model.Ball;

// strategy to paint a shape

public class ShapePaintStrategy extends APaintStrategy {
	private Shape shape;

	public ShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at);
		this.shape = shape;
	}

	public ShapePaintStrategy(Shape shape) {
		this(new AffineTransform(), shape);
	}

	public void paintItemfrm(Graphics g, Ball ball, AffineTransform at) {
		((Graphics2D) g).fill(at.createTransformedShape(shape));
	}
}
