/**
 * 
 */
package open.pp.sample.guicegwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 */
public class ListView extends Composite {

	interface ListViewUiBinder extends UiBinder<Widget, ListView> {
	}

	private HTMLPanel container;
	private static ListViewUiBinder uiBinder = GWT
			.create(ListViewUiBinder.class);

	public ListView() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		PersonListView listView = new PersonListView();
		container.add(listView);

	}

}