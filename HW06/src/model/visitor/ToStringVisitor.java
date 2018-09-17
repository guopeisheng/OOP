package model.visitor;

import provided.music.*;

/** 
* @author : 
* @version :{date:} {time:} 
* @Description: 
*/
public class ToStringVisitor extends APhraseVisitor {
	/**
	 * The constructor that adds two commands, one for empty sequences, and the other for non empty sequences
	 * @return element after processing
	 */
	public ToStringVisitor() {
		//adding normal Cmd 
		this.addCmd("NESeqList", new IPhraseVisitorCmd() {
			@Override
			public Object apply(String id, IPhrase host, Object... params) {//params->vararg list of input parameters.
				//cast host into NEList
				NESeqList NEList = (NESeqList) host;

				//take out rest element (rest element are in another list)
				ISeqList restEle = NEList.getRest();

				//execute rest Elements (caseAt inside execute) using algo,first param+comma+first element in the lsit
				return restEle.execute(ToStringVisitor.this, params[0] + ", " + NEList.getFirst());
			}
		});
		//adding defaultCmd 
		this.addCmd("MTSeqList", new IPhraseVisitorCmd() {
			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				return params[0] + "}";
			}
		});

	}
}
