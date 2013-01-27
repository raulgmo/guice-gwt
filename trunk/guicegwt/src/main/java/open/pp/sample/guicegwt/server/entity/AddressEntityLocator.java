package open.pp.sample.guicegwt.server.entity;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * Generic @Locator for objects that extend EntityBase
 */
public class AddressEntityLocator extends Locator<AddressBase, String> {

	@Inject
	Injector				injector;
	@Inject
	Provider<EntityManager>	em;

	@Override
	public AddressBase create(Class<? extends AddressBase> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AddressBase find(Class<? extends AddressBase> clazz, String id) {
		return em.get().find(clazz, id);
	}

	/**
	 * it's never called
	 */
	@Override
	public Class<AddressBase> getDomainType() {
		throw new UnsupportedOperationException();
		// or return null;
	}

	@Override
	public String getId(AddressBase domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(AddressBase domainObject) {
		return domainObject.getVersion();
	}

}