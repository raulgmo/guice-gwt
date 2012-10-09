package open.pp.sample.guicegwt.shared.service;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface MVPRequestFactory extends RequestFactory {

	AccountRequest getAccountRequest();

	PersonRequest getPersonRequest();
}
