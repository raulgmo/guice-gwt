package open.pp.sample.guicegwt.client;

import open.pp.sample.guicegwt.client.view.PersonListView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class sample implements EntryPoint {
	public void onModuleLoad() {
		PersonListView plv = new PersonListView();
		RootPanel.get().add(plv);
	}
}
