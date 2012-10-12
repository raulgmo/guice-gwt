package open.pp.sample.guicegwt.client.view;

import open.pp.sample.guicegwt.client.ServiceFactory;
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
	}

	@UiHandler("addAddress")
	public void addAddressHandler(ClickEvent event) {
	}

}
