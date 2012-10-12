package open.pp.sample.guicegwt.client.view;

import open.pp.sample.guicegwt.client.ServiceFactory;
import open.pp.sample.guicegwt.shared.proxy.AddressPx;
import open.pp.sample.guicegwt.shared.service.PersonServiceRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddressDetView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, AddressDetView> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	TextBox add1;
	@UiField
	TextBox add2;
	@UiField
	TextBox city;
	@UiField
	TextBox state;
	@UiField
	TextBox country;

	HTMLPanel container;
	AddressPx add;

	public AddressDetView() {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
	}

	public AddressPx getAddress() {
		if (add == null) {
			PersonServiceRequest psr = ServiceFactory.getInstance()
					.getRequestFactory().getPersonService();
			add = psr.create(AddressPx.class);
		}
		add.setAdd1(this.add1.getText());
		add.setAdd2(this.add2.getText());
		add.setCity(this.city.getText());
		add.setState(this.state.getText());
		add.setCountry(this.country.getText());
		return add;
	}

	public void setAddressDetails(AddressPx person) {
		add = person;
		this.add1.setText(add.getAdd1());
		this.add2.setText(add.getAdd2());
		this.city.setText(add.getCity());
		this.state.setText(add.getState());
		this.country.setText(add.getCountry());
	}

}
