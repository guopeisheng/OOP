/**
 * 
 */
package gp27_pg23.model.cmd;

import common.DataPacketChatRoom;
import common.IFailureStatusType;

/**
 * @author Gao Pan
 *
 */
public class FailureStatusType implements IFailureStatusType {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -4963715304753416230L;

	/**
	 * Original data
	 */
	private DataPacketChatRoom<?> data;

	/**
	 * Failure Information
	 */
	private String info;

	/**
	 * Construct with original data and failure information
	 * @param data Original data
	 * @param info Failure info
	 */
	public FailureStatusType(DataPacketChatRoom<?> data, String info) {
		this.data = data;
		this.info = info;
	}

	@Override
	public DataPacketChatRoom<?> getOriginalData() {
		return data;
	}

	@Override
	public String getFailureInfo() {
		return info;
	}

}
