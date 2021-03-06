package model.balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import util.Randomizer;
import strategy.*;

/**
 * An abstract class of balls
 *
 */
public class Ball implements Observer {
	/** generate random values*/
	private Randomizer rand;
	/** The color of the ball*/
	private Color color;
	/** The center point of the ball*/
	private Point location;
	/**The velocity of the ball*/
	private Point velocity;
	/** The radius of the ball*/
	private int radius;
	/** The windows that the ball will be paint*/
	private Component window;
	/** The strategy used to update the ball's state*/
	private IUpdateStrategy strategy;

	/**
	 * 
	 * @param _window The JPanel that the ball will be paint
	 * Initialize the fields of the ball
	 */

	public Ball(Component _window, IUpdateStrategy _strategy) {
		strategy = _strategy;
		rand = Randomizer.Singleton;
		color = rand.randomColor();
		radius = rand.randomInt(10, 50);
		window = _window;
		location = new Point(rand.randomInt(radius, window.getWidth() - radius),
				rand.randomInt(radius, window.getHeight() - radius));
		velocity = rand.randomVel(new Rectangle(20, 20));
		//make sure that the ball will not be static
		if (velocity.x == 0 && velocity.y == 0) {
			velocity.x++;
			velocity.y++;
		}
	}

	/** Define how the ball move
	 *  call the method called 'translate' of Point to change the center point of the ball
	 */
	public void move() {
		location.translate(velocity.x, velocity.y);
	}

	/**Determine whether the ball need to bounce
	 * if the ball has passed the widow boundaries, translate it back.
	 * change the direction of the velocity
	 */
	public void bounce() {
		// check if the ball past right wall
		if (location.x >= window.getWidth()) {
			location.translate(-2 * (location.x - window.getWidth()), 0);
			velocity.setLocation(-velocity.x, velocity.y);
		}
		// check if the ball past left wall
		else if (location.x - radius <= 0) {
			location.translate(2 * (radius - location.x), 0);
			velocity.setLocation(-velocity.x, velocity.y);
		}
		// check if the ball past bottom wall
		if (location.y >= window.getHeight()) {
			location.translate(0, -2 * (location.y - window.getHeight()));
			velocity.setLocation(velocity.x, -velocity.y);
		}
		// check if the ball past top wall
		else if (location.y - radius <= 0) {
			location.translate(0, 2 * (radius - location.y));
			velocity.setLocation(velocity.x, -velocity.y);
		}
	}

	/**
	 * all the sub-class should have this method to update the status of the ball e.g color, velocity, radius
	 */

	/**
	 * @param g the object to draw ball
	 * paint a ball on the window
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(location.x - radius, location.y - radius, radius, radius);
	}

	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * when the class is notified, use the method to paint a ball
	 */
	public void update(Observable o, Object g) {
		strategy.updateState(this);
		move();
		bounce();
		paint((Graphics) g);
	}

	/**
	 * @return the velocity of the ball
	 */
	public Point getVel() {
		return velocity;
	}

	/**
	 * @param p The value used to set the velocity of the ball
	 */
	public void setVel(Point p) {
		velocity = p;
	}

	/**
	 * @param _color set the value of the ball's color
	 */
	public void setColor(Color _color) {
		color = _color;
	}

	/**
	 * @param _radius set the value of radius
	 */
	public void setRadius(int _radius) {
		radius = _radius;
	}

}
