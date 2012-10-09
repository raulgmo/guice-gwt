package open.pp.sample.guicegwt.client;

import open.pp.sample.guicegwt.shared.proxy.PersonPx;
import open.pp.sample.guicegwt.shared.service.MVPRequestFactory;
import open.pp.sample.guicegwt.shared.service.PersonRequest;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class sample implements EntryPoint {
	public void onModuleLoad() {
		MVPRequestFactory requestFactory = GWT.create(MVPRequestFactory.class);
		PersonRequest pr = requestFactory.getPersonRequest();
		PersonPx person = pr.create(PersonPx.class);
		person.setFname("WebFname");
		person.setLname("WebLname");
		person.setEmailId("web@gmail.com");
		pr.persist().using(person).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				Window.alert("Person Saved");
			}
		});
	}
}
