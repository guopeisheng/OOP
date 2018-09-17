package view;

/** 
* @author : 
* @version :{date:} {time:} 
* @Description: 
*/
public interface IModelAdapter<TInstrument> {
	void Load(String filename);

	void Parse();

	void Play(TInstrument instrument);

	void Stop();

	public static final IModelAdapter<?> NULL_OBJECT = new IModelAdapter<Object>() {

		/**
		 * load abc file using full path ("/songs/" + txtInput.getText() + ".abc")
		 * @param partial filename
		 */
		@Override
		public void Load(String filename) {
		}

		/**
		 * Parse
		 */
		@Override
		public void Parse() {
		}

		/**
		 * Load
		 * @param instrument
		 */
		@Override
		public void Play(Object instrument) {
		}

		/**
		 * Stop
		 */
		@Override
		public void Stop() {
		}
	};
}
