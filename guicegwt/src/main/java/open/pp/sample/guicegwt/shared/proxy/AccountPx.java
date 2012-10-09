package open.pp.sample.guicegwt.shared.proxy;

import open.pp.sample.guicegwt.server.entity.AccountEntityLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = open.pp.sample.guicegwt.server.entity.Account.class, locator = AccountEntityLocator.class)
public interface AccountPx extends EntityProxy {

	String getId();

	void setId(String id);

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	String getPersonId();

	void setPersonId(String personId);

	double getBalance();

	void setBalance(double balance);

	EntityProxyId<AccountPx> stableId();

}
