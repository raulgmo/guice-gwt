package open.pp.sample.guicegwt.server.entity;

import open.pp.sample.guicegwt.server.injector.AccountPersistService;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * Generic @Locator for objects that extend EntityBase
 */
public class AccountEntityLocator extends Locator<AccountBase, String> {

	@Inject
	Injector injector;
	@AccountPersistService
	PersistenceLifeCycleManager manager;

	@Override
	public AccountBase create(Class<? extends AccountBase> clazz) {
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
	public AccountBase find(Class<? extends AccountBase> clazz, String id) {
		return manager.getEntityManager().find(clazz, id);
	}

	/**
	 * it's never called
	 */
	@Override
	public Class<AccountBase> getDomainType() {
		throw new UnsupportedOperationException();
		// or return null;
	}

	@Override
	public String getId(AccountBase domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(AccountBase domainObject) {
		return domainObject.getVersion();
	}

}