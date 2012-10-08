package open.pp.sample.guicegwt.server.injector;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class TestAccountPersistenceInitilizer {

	@Inject
	public TestAccountPersistenceInitilizer(
			@AccountPersistService PersistenceLifeCycleManager manager) {
		manager.startService();
	}

}
