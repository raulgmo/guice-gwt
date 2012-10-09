/**
 * 
 */
package open.pp.sample.guicegwt.server.injector;

import open.pp.sample.guicegwt.server.dao.PersonDao;

import com.google.inject.PrivateModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Private Persistent Module to connect with User unit.
 */
public class UserPersistModule extends PrivateModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("users"));
		bind(PersistenceLifeCycleManager.class).annotatedWith(
				UserPersistService.class).to(
				UserPersistenceLifeCycleManager.class);
		expose(PersistenceLifeCycleManager.class).annotatedWith(
				UserPersistService.class);
		bind(PersonDao.class).asEagerSingleton();
		expose(PersonDao.class);
	}

}
