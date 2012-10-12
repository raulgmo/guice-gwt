package open.pp.sample.guicegwt.client;

import open.pp.sample.guicegwt.shared.service.MVPRequestFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class ServiceFactory {

	private static ServiceFactory instance;

	private EventBus eventBus = new SimpleEventBus();
	private MVPRequestFactory requestFactory = GWT
			.create(MVPRequestFactory.class);

	private ServiceFactory() {
		requestFactory.initialize(eventBus);
	}

	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public MVPRequestFactory getRequestFactory() {
		return requestFactory;
	}
}
