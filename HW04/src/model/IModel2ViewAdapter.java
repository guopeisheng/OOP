package model;

import java.awt.Component;

/**
 * model to view adpter
 */
public interface IModel2ViewAdapter {
	/**
	 * The method that tells the view to update
	 */
	public void update();

	/**
	 * get Canvas from view
	 * @return canvas
	 */
	public Component getComponent();

	/**
	 * No-op "null" adapter See the web page on the Null Object Design Pattern at
	 * http://cnx.org/content/m17227/latest/
	 */
	public static final IModel2ViewAdapter NULL_OBJECT = new IModel2ViewAdapter() {
		public void update() {
		}

		public Component getComponent() {
			return null;
		}
	};

}
