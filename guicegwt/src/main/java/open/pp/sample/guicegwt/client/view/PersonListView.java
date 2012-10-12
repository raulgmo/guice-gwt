package open.pp.sample.guicegwt.client.view;

import open.pp.sample.guicegwt.shared.proxy.PersonPx;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;

public class PersonListView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, PersonListView> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	HTMLPanel cellTableContainer;
	@UiField
	HTMLPanel expViewContainer;
	HTMLPanel container;

	@UiField
	PersonDetView pdv;
	PersonCellTable table = new PersonCellTable();

	public PersonListView() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		cellTableContainer.add(table);
		expViewContainer.add(pdv);
		table.addSelectionChangeHandler(new SelectionChangeHandler());
	}

	public class SelectionChangeHandler implements SelectionChangeEvent.Handler {

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			PersonPx selected = table.getSelected();
			if (selected != null) {
				pdv.setPersonDetails(selected);
			}
		}

	}

}
