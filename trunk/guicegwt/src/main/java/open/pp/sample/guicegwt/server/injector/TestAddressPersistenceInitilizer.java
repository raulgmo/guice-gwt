package open.pp.sample.guicegwt.server.injector;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class TestAddressPersistenceInitilizer {

	@Inject
	public TestAddressPersistenceInitilizer(
			@AddressPersistService PersistenceLifeCycleManager manager) {
		manager.startService();
	}

}
