package open.pp.sample.guicegwt.server.entity;

import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.UserPersistService;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * Generic @Locator for objects that extend EntityBase
 */
public class UserEntityLocator extends Locator<UserBase, String> {

	@Inject
	Injector injector;
	@Inject
	@UserPersistService
	PersistenceLifeCycleManager manager;

	@Override
	public UserBase create(Class<? extends UserBase> clazz) {
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
	public UserBase find(Class<? extends UserBase> clazz, String id) {
		return manager.getEntityManager().find(clazz, id);
	}

	/**
	 * it's never called
	 */
	@Override
	public Class<UserBase> getDomainType() {
		throw new UnsupportedOperationException();
		// or return null;
	}

	@Override
	public String getId(UserBase domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(UserBase domainObject) {
		return domainObject.getVersion();
	}

}