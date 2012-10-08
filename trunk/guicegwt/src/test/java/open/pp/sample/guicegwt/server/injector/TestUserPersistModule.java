package open.pp.sample.guicegwt.server.injector;

import open.pp.sample.guicegwt.server.entity.PersonTest;

public class TestUserPersistModule extends UserPersistModule {

	@Override
	protected void configure() {
		super.configure();
		bind(TestUserPersistenceInitilizer.class).asEagerSingleton();
		expose(TestUserPersistenceInitilizer.class);
		bind(PersonTest.class).asEagerSingleton();
		expose(PersonTest.class);
	}
}
