package open.pp.sample.guicegwt.shared.service;

import open.pp.sample.guicegwt.shared.proxy.PersonPx;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(open.pp.sample.guicegwt.server.entity.Person.class)
public interface PersonRequest extends RequestContext {

	InstanceRequest<PersonPx, Void> persist();
}
