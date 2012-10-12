package open.pp.sample.guicegwt.shared.service;

import java.util.List;

import open.pp.sample.guicegwt.server.requestfactory.SampleServiceLocator;
import open.pp.sample.guicegwt.server.service.PersonService;
import open.pp.sample.guicegwt.shared.proxy.AddressPx;
import open.pp.sample.guicegwt.shared.proxy.PersonPx;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = PersonService.class, locator = SampleServiceLocator.class)
public interface PersonServiceRequest extends RequestContext {

	public Request<String> registerPerson(PersonPx p);

	public Request<Boolean> savePerson(PersonPx p);

	public Request<PersonPx> getPersonById(String personId);

	public Request<AddressPx> getAddressById(String accountId);

	public Request<List<PersonPx>> getAllPersons();

	public Request<List<AddressPx>> getAllAddressesOfAPerson(String personId);

	public Request<Boolean> saveAddresss(String personId,
			List<AddressPx> address);

}
