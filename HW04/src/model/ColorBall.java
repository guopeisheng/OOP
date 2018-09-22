package model;

import java.awt.Color;
import java.awt.Component;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import util.Dispatcher;

/**
 * class colorBall
 *
 */
public class ColorBall implements Observer {
	/**
	 * location of the ball
	 */
	private Point location;
	/**
	 * velocity of the ball
	 */
	private Point velocity;
	/**
	 * color of the ball
	 */
	private Color color;
	/**
	 * radius of the ball
	 */
	private int radius;
	/**
	 * updateStrategy of the ball
	 */
	private IUpdateStrategy strategy;
	/**
	 * paint Strategy
	 */
	private IPaintStrategy paintStrategy;
	/**
	 * container
	 */
	private Component container;

	// private int height;
	// private int width;
	/**
	 * constructor
	 * 
	 * @param p point
	 * @param r radius
	 * @param v velocity
	 * @param c color
	 * @param container container
	 * @param strategy strategy
	 * @param paintStrategy paint strategy
	 */
	public ColorBall(Point p, int r, Point v, Color c, Component container, IUpdateStrategy strategy,
			IPaintStrategy paintStrategy) {
		this.location = p;
		this.velocity = v;
		this.color = c;
		this.radius = r;
		this.container = container;
		this.strategy = strategy;
		setPaintStrategy(paintStrategy);
	}

	/**
	 * 
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param r radius
	 */
	public void setRadius(int r) {
		radius = r;
	}

	/**
	 * 
	 * @param p position
	 */
	public void setPosition(Point p) {
		location = p;
	}

	/**
	 * 
	 * @param velocity
	 */
	public void setVelocity(Point v) {
		velocity = v;
	}

	/**
	 * 
	 * @param c color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * 
	 * @return  color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * 
	 * @return location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * 
	 * @return velocity
	 */
	public Point getVelocity() {
		return velocity;
	}

	/**
	 * 
	 * @return strategy
	 */
	public IUpdateStrategy getStrategy() {
		return strategy;
	}

	/**
	 * 
	 * @param Strategy update strategy
	 */
	public void setStrategy(IUpdateStrategy Strategy) {
		this.strategy = Strategy;
	}

	/**
	 * get canvas
	 * @return canvas
	 */
	public Component getComponent() {
		return this.container;
	}

	/**
	 * set paint strategy
	 * @param paintStrategy paintStrategy
	 */
	public void setPaintStrategy(IPaintStrategy paintStrategy) {
		this.paintStrategy = paintStrategy;
		this.paintStrategy.init(this);
	}
	//	/**
	//	 * 
	//	 * @param g
	//	 */
	//	public void paint(Graphics g) {
	//		g.setColor(color);
	//		g.fillOval(location.x - radius, location.y - radius, 2 * radius, 2 * radius);
	//
	//	}

	/**
	 * the method to bounce a ball
	 */
	public void bounce() {
		int width = container.getWidth();
		int height = container.getHeight();
		if (location.x < radius) // left
		{
			location.x = radius;
			velocity.x = -velocity.x;
		}
		if (location.x + radius >= width)// right
		{
			location.x = width - radius;
			velocity.x = -velocity.x;
		}
		if (location.y < radius) // upper
		{
			location.y = radius;
			velocity.y = -velocity.y;
		}
		if (location.y + radius >= height) // down
		{
			location.y = height - radius;
			velocity.y = -velocity.y;
		}

	}

	/**
	 * move the ball by x and y increments
	 */
	public void move() {
		location.x = location.x + velocity.x;
		location.y = location.y + velocity.y;
	}

	/**
	 * The update method called by the main ball Dispatcher to notify all the balls to perform the given command.
	 * The given command is executed.
	 * @param o The Dispatcher that sent the update request.
	 * @param cmd The IBallCmd that will be run.
	 */
	public void update(Observable o, Object cmd) {
		((IBallCmd) cmd).apply(this, (Dispatcher) o);
	}

	/**
	 * paint method
	 * @param g where to paint
	 */
	public void paint(Graphics g) {
		paintStrategy.paint((Graphics) g, this);
	}

	/**
	 * update strategy's state
	 * @param disp dispatcher
	 */
	public void updateState(Dispatcher disp) {
		strategy.updateState(this, disp);
	}
	//	/**
	//	 * update strategy
	//	 */
	//	public void update(Observable o, Object g) {
	//		strategy.updateState(this);
	//		move();
	//		bounce();
	//		paintStrategy.paint((Graphics) g, this);
	//	}

}
