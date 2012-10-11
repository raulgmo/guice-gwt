package open.pp.sample.guicegwt.shared.service;

import open.pp.sample.guicegwt.server.requestfactory.SampleServiceLocator;
import open.pp.sample.guicegwt.server.service.BankService;
import open.pp.sample.guicegwt.shared.proxy.PersonPx;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = BankService.class, locator = SampleServiceLocator.class)
public interface BankServiceRequest extends RequestContext {

	Request<String> registerPerson(PersonPx p);
}
