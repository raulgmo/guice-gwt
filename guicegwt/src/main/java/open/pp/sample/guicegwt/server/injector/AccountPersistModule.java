/**
 * 
 */
package open.pp.sample.guicegwt.server.injector;

import open.pp.sample.guicegwt.server.dao.AccountDao;

import com.google.inject.PrivateModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Private Persistent Module to connect with Accounts unit.
 */
public class AccountPersistModule extends PrivateModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("accounts"));
		bind(PersistenceLifeCycleManager.class).annotatedWith(
				AccountPersistService.class).to(
				AccountPersistenceLifeCycleManager.class);
		expose(PersistenceLifeCycleManager.class).annotatedWith(
				AccountPersistService.class);
		bind(AccountDao.class).asEagerSingleton();
		expose(AccountDao.class);
	}

}
