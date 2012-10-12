package open.pp.sample.guicegwt.client.view;

import java.util.List;

import open.pp.sample.guicegwt.client.ServiceFactory;
import open.pp.sample.guicegwt.shared.proxy.PersonPx;
import open.pp.sample.guicegwt.shared.service.PersonServiceRequest;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class PersonCellTable extends Composite {
	interface MyUiBinder extends UiBinder<Widget, PersonCellTable> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	@UiField
	CellTable<PersonPx> table;

	@UiField
	SimplePager pager;

	ListDataProvider<PersonPx> dp = new ListDataProvider<PersonPx>();
	SingleSelectionModel<PersonPx> selectionModel;

	public PersonCellTable() {
		initWidget(uiBinder.createAndBindUi(this));
		table.setPageSize(10);
		Column<PersonPx, Boolean> selCol = new Column<PersonPx, Boolean>(
				new CheckboxCell()) {

			@Override
			public Boolean getValue(PersonPx object) {
				return false;
			}
		};
		table.addColumn(selCol, "");

		Column<PersonPx, PersonPx> unitDet = new Column<PersonPx, PersonPx>(
				new PersonCell()) {
			@Override
			public PersonPx getValue(PersonPx object) {
				return object;
			}
		};
		table.addColumn(unitDet, "Person Details");
		selectionModel = new SingleSelectionModel<PersonPx>();
		table.setSelectionModel(selectionModel);
		Label label = new Label("No data");
		table.setEmptyTableWidget(label);
		
		pager.setRangeLimited(true);
		pager.setDisplay(table);

		dp.addDataDisplay(table);
		refreshData();
	}

	public void addSelectionChangeHandler(SelectionChangeEvent.Handler handler) {
		selectionModel.addSelectionChangeHandler(handler);
	}

	public PersonPx getSelected() {
		return selectionModel.getSelectedObject();
	}

	public void refreshData() {
		PersonServiceRequest bsr = ServiceFactory.getInstance()
				.getRequestFactory().getPersonService();
		bsr.getAllPersons().fire(new Receiver<List<PersonPx>>() {

			@Override
			public void onSuccess(List<PersonPx> response) {
				dp.setList(response);
				dp.refresh();
			}
		});
	}

}
