package open.pp.sample.guicegwt.server.injector;

import open.pp.sample.guicegwt.server.entity.AccountTest;

public class TestAddressPersistModule extends AddressPersistModule {

	@Override
	protected void configure() {
		super.configure();
		bind(TestAddressPersistenceInitilizer.class).asEagerSingleton();
		expose(TestAddressPersistenceInitilizer.class);
		bind(AccountTest.class).asEagerSingleton();
		expose(AccountTest.class);
	}
}
