package open.pp.sample.guicegwt.server.injector;

import open.pp.sample.guicegwt.server.entity.AccountTest;

public class TestAccountPersistModule extends AccountPersistModule {

	@Override
	protected void configure() {
		super.configure();
		bind(TestAccountPersistenceInitilizer.class).asEagerSingleton();
		expose(TestAccountPersistenceInitilizer.class);
		bind(AccountTest.class).asEagerSingleton();
		expose(AccountTest.class);
	}
}
