package open.pp.sample.guicegwt.client.view;

import open.pp.sample.guicegwt.shared.proxy.PersonPx;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.user.client.ui.HTMLPanel;

public class PersonCell extends AbstractCell<PersonPx> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, PersonPx person);
	}

	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);

	@UiField
	HTMLPanel expView;

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PersonPx value, SafeHtmlBuilder sb) {
		renderer.render(sb, value);
	}
}
