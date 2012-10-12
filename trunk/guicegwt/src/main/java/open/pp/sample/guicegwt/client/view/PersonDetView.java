package open.pp.sample.guicegwt.client.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import open.pp.sample.guicegwt.client.ServiceFactory;
import open.pp.sample.guicegwt.shared.proxy.AddressPx;
import open.pp.sample.guicegwt.shared.proxy.PersonPx;
import open.pp.sample.guicegwt.shared.service.PersonServiceRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class PersonDetView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, PersonDetView> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	TextBox title;
	@UiField
	TextBox fname;
	@UiField
	TextBox lname;
	@UiField
	TextBox emailId;

	@UiField
	Button addAddress;
	@UiField
	Button save;

	@UiField
	HTMLPanel addresses;
	HTMLPanel container;
	PersonPx p;

	List<AddressDetView> addViews = new ArrayList<AddressDetView>();
	Set<DataChangeHandler> dcHanlders = new HashSet<PersonDetView.DataChangeHandler>();
	List<AddressPx> addressdtos = new ArrayList<AddressPx>();

	public PersonDetView() {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
	}

	@UiHandler("save")
	public void saveHandler(ClickEvent event) {
		if (p == null) {
			PersonServiceRequest psr = ServiceFactory.getInstance()
					.getRequestFactory().getPersonService();
			PersonPx per = fillPersonDet(psr.create(PersonPx.class));
			psr.registerPerson(per).fire(new Receiver<String>() {

				@Override
				public void onSuccess(String response) {
					if (response != null) {
						saveAddresses(response);
						clear();
					}
				}
			});
		} else {
			PersonServiceRequest psr = ServiceFactory.getInstance()
					.getRequestFactory().getPersonService();
			PersonPx peredit = psr.edit(p);
			final PersonPx per = fillPersonDet(peredit);
			psr.savePerson(per).fire(new Receiver<Boolean>() {

				@Override
				public void onSuccess(Boolean response) {
					if (response != null && response) {
						saveAddresses(per.getId());
						clear();
					}
				}
			});
		}
	}

	private void saveAddresses(String personId) {
		List<AddressPx> adds = new ArrayList<AddressPx>();
		PersonServiceRequest psr = ServiceFactory.getInstance()
				.getRequestFactory().getPersonService();
		for (int index = 0; index < addViews.size(); index++) {
			AddressPx address = addressdtos.get(index);
			if (address == null) {
				address = psr.create(AddressPx.class);
			} else {
				address = psr.edit(address);
			}
			adds.add(addViews.get(index).fillAddress(address));
		}
		if (adds.size() > 0) {
			psr.saveAddresss(personId, adds).fire(new Receiver<Boolean>() {

				@Override
				public void onSuccess(Boolean response) {
					if (response) {
						for (DataChangeHandler handler : dcHanlders) {
							handler.onDataChange();
						}
					}
				}
			});
		}
	}

	public PersonPx fillPersonDet(PersonPx pr) {
		pr.setTitle(this.title.getText());
		pr.setFname(this.fname.getText());
		pr.setLname(this.lname.getText());
		pr.setEmailId(this.emailId.getText());
		return pr;
	}

	public void clear() {
		this.title.setText("");
		this.fname.setText("");
		this.lname.setText("");
		this.emailId.setText("");
		addresses.clear();
		addViews.clear();
		addressdtos = new ArrayList<AddressPx>();
		p = null;
	}

	public void setPersonDet(PersonPx person) {
		p = person;
		this.title.setText(p.getTitle());
		this.fname.setText(p.getFname());
		this.lname.setText(p.getLname());
		this.emailId.setText(p.getEmailId());
		addresses.clear();
		addViews.clear();
		PersonServiceRequest psr = ServiceFactory.getInstance()
				.getRequestFactory().getPersonService();
		psr.getAllAddressesOfAPerson(p.getId()).fire(
				new Receiver<List<AddressPx>>() {

					@Override
					public void onSuccess(List<AddressPx> response) {
						if (response != null && response.size() > 0) {
							addressdtos = response;
							for (AddressPx addressPx : response) {
								AddressDetView adv = new AddressDetView();
								addViews.add(adv);
								addresses.add(adv);
								adv.setAddressDetails(addressPx);
							}
						}
					}
				});
	}

	@UiHandler("addAddress")
	public void addAddressHandler(ClickEvent event) {
		AddressDetView adv = new AddressDetView();
		addViews.add(adv);
		addresses.add(adv);
		/*
		 * This is scope for improvement need to think
		 */
		addressdtos.add(null);
	}

	public void addDataChangeHandler(DataChangeHandler handler) {
		this.dcHanlders.add(handler);
	}

	public static interface DataChangeHandler {
		void onDataChange();
	}
}
