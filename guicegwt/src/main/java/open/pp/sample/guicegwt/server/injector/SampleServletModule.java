package open.pp.sample.guicegwt.server.injector;

import com.google.inject.servlet.ServletModule;

/**
 * 
 * 
 */

public class SampleServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		filter("/*").through(UserPersistFilter.class);
		filter("/*").through(AccountPersistFilter.class);
	}

}
