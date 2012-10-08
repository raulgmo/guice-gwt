/**
 * 
 */
package open.pp.sample.guicegwt.server.injector;

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
		// bind(UserAuthSessionManager.class).to(UserAuthSessionManagerImpl.class);
		// expose(UserAuthSessionManager.class);
		// bind(UserSessionDao.class).asEagerSingleton();
		// expose(UserSessionDao.class);
	}

}
