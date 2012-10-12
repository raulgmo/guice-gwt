package open.pp.sample.guicegwt.client.view;

import open.pp.sample.guicegwt.shared.proxy.AddressPx;

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

	public AddressDetView() {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
	}

	public AddressPx fillAddress(AddressPx address) {
		if (address != null) {
			address.setAdd1(this.add1.getText());
			address.setAdd2(this.add2.getText());
			address.setCity(this.city.getText());
			address.setState(this.state.getText());
			address.setCountry(this.country.getText());
			return address;
		}
		return null;
	}

	public void setAddressDetails(AddressPx person) {
		if (person != null) {
			this.add1.setText(person.getAdd1());
			this.add2.setText(person.getAdd2());
			this.city.setText(person.getCity());
			this.state.setText(person.getState());
			this.country.setText(person.getCountry());
		}
	}

}
