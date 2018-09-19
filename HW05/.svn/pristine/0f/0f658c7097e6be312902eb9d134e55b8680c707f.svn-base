package util;

/**
 * A CopyOnWriteArraySet-based IDispatcher that dispatches to its IObservers sequentially.
 *
 * @author Stephen Wong
 * @author Derek Peirce
 *
 * @param <TDispMsg> The type of message sent to the registered IObservers
 */
public class SetDispatcherSequential<TDispMsg> extends ASetDispatcher<TDispMsg> {

	/**
	 * Implementation: Sequential iteration through the collection of IObservers.
	 * @param msg The type of message sent to the registered IObservers 
	 */
	@Override
	public void dispatch(TDispMsg msg) {
		getCollection().forEach(o -> {
			o.update(this, msg);
		});
	}

}