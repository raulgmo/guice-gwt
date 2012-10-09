/**
 * 
 */
package open.pp.sample.guicegwt.server.requestfactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 */
@Singleton
public class SampleServiceLayerDecorator extends ServiceLayerDecorator {

	@Inject
	private Injector injector;

	@Override
	public <T extends Locator<?, ?>> T createLocator(Class<T> clazz) {
		return injector.getInstance(clazz);
	}
}
