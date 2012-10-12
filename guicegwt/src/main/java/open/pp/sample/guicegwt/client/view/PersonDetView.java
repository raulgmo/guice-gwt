package open.pp.sample.guicegwt.client.view;

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

	Set<AddressDetView> addViews = new HashSet<AddressDetView>();

	public PersonDetView() {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
	}

	@UiHandler("save")
	public void saveHandler(ClickEvent event) {

	}

	public PersonPx getNewMemberUnitDet() {
		if (p == null) {
			PersonServiceRequest psr = ServiceFactory.getInstance()
					.getRequestFactory().getPersonService();
			p = psr.create(PersonPx.class);
		}
		p.setTitle(this.title.getText());
		p.setFname(this.fname.getText());
		p.setLname(this.lname.getText());
		p.setEmailId(this.emailId.getText());
		return p;
	}

	public void setPersonDetails(PersonPx person) {
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
	}

}
