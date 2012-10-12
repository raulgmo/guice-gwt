/**
 * 
 */
package open.pp.sample.guicegwt.server.injector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 *
 */
public class SampleServletContextListner extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new SampleServletModule(),
				new UserPersistModule(), new AddressPersistModule());
	}

}
