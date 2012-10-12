/**
 * 
 */
package open.pp.sample.guicegwt.server.injector;

import open.pp.sample.guicegwt.server.dao.AddressDao;

import com.google.inject.PrivateModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Private Persistent Module to connect with Accounts unit.
 */
public class AddressPersistModule extends PrivateModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("addresses"));
		bind(PersistenceLifeCycleManager.class).annotatedWith(
				AddressPersistService.class).to(
				AddressPersistenceLifeCycleManager.class);
		expose(PersistenceLifeCycleManager.class).annotatedWith(
				AddressPersistService.class);
		bind(AddressDao.class).asEagerSingleton();
		expose(AddressDao.class);
	}

}
