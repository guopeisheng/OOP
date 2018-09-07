/**
 * 
 */
package gp27_pg23.model.cmd;

import java.io.Serializable;

/**
 * File data type
 * @author Gao Pan
 *
 */
public class FileType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 4058885759393908549L;

	/**
	 * File as byte array
	 */
	private byte[][] file;

	/**
	 * Construct with the byte array
	 * @param file The byte array representing the file
	 */
	public FileType(byte[][] file) {
		this.file = file;
	}

	/**
	 * Get the byte array for the file
	 * @return The byte array for the file
	 */
	public byte[][] getFile() {
		return file;
	}
}
