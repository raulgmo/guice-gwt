package open.pp.sample.guicegwt.server.injector;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class TestUserPersistenceInitilizer {

	@Inject
	public TestUserPersistenceInitilizer(
			@UserPersistService PersistenceLifeCycleManager manager) {
		manager.startService();
	}

}
