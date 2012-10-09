package open.pp.sample.guicegwt.shared.proxy;

import open.pp.sample.guicegwt.server.entity.UserEntityLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = open.pp.sample.guicegwt.server.entity.Person.class, locator = UserEntityLocator.class)
public interface PersonPx extends EntityProxy {

	String getId();

	String getTitle();

	void setTitle(String title);

	String getFname();

	void setFname(String fname);

	String getLname();

	void setLname(String lname);

	String getEmailId();

	void setEmailId(String emailId);

	EntityProxyId<PersonPx> stableId();

}
