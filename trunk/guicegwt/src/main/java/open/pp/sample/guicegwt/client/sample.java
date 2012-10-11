package open.pp.sample.guicegwt.client;

import open.pp.sample.guicegwt.shared.proxy.PersonPx;
import open.pp.sample.guicegwt.shared.service.BankServiceRequest;
import open.pp.sample.guicegwt.shared.service.MVPRequestFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class sample implements EntryPoint {
	public void onModuleLoad() {
		EventBus eventBus = new SimpleEventBus();

		MVPRequestFactory requestFactory = GWT.create(MVPRequestFactory.class);
		requestFactory.initialize(eventBus);
		BankServiceRequest bsr = requestFactory.getBankService();
		PersonPx person = bsr.create(PersonPx.class);
		person.setFname("WebFname");
		person.setLname("WebLname");
		person.setEmailId("web@gmail.com");
		bsr.registerPerson(person).fire(new Receiver<String>() {

			@Override
			public void onSuccess(String response) {
				Window.alert("Person Saved => " + response);
			}
		});
	}
}
