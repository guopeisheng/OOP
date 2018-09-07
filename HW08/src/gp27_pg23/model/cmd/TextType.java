/**
 * 
 */
package gp27_pg23.model.cmd;

import java.io.Serializable;

/**
 * Text data type
 * @author Gao Pan
 *
 */
public class TextType implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -490399589223041528L;

	/**
	 * The text message
	 */
	String msg;

	/**
	 * Construct with the message
	 * @param msg The message
	 */
	public TextType(String msg) {
		this.msg = msg;
	}

	/**
	 * Get the message
	 * @return The message
	 */
	public String getMsg() {
		return msg;
	}
}
