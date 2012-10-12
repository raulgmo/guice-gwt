package open.pp.sample.guicegwt.shared.proxy;

import open.pp.sample.guicegwt.server.entity.AddressEntityLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = open.pp.sample.guicegwt.server.entity.Address.class, locator = AddressEntityLocator.class)
public interface AddressPx extends EntityProxy {

	public String getId();

	public String getAdd1();

	public void setAdd1(String add1);

	public String getAdd2();

	public void setAdd2(String add2);

	public String getCity();

	public void setCity(String city);

	public String getState();

	public void setState(String state);

	public String getCountry();

	public void setCountry(String country);

	public String getPersonId();

	public void setPersonId(String personId);

	EntityProxyId<AddressPx> stableId();

}
