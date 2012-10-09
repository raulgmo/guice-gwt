package open.pp.sample.guicegwt.shared.service;

import open.pp.sample.guicegwt.shared.proxy.AccountPx;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(open.pp.sample.guicegwt.server.entity.Account.class)
public interface AccountRequest extends RequestContext {

	InstanceRequest<AccountPx, Void> persist();
}
