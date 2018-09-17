package model;

import provided.util.ABCInstrument;

/** 
* @author : 
* @version :{date:} {time:} 
* @Description: 
*/
public interface IViewAdapter {
	/**
	 * set text view's text area
	 * Abstract 
	 * @param content
	 */
	void setTxtArea(String content);

	/**
	 * add instrument into JComboBox
	 *  Abstract
	 *  @param instrument
	 */
	void addInstrument(ABCInstrument instrument);

	public static final IViewAdapter NULL_OBJECT = new IViewAdapter() {
		/** 
		 * no-op at this time
		 */
		@Override
		public void setTxtArea(String content) {
			//no-op
		}

		/** 
		 * no-op at this time
		 */
		@Override
		public void addInstrument(ABCInstrument instrument) {
			//no-op
		}
	};
}
