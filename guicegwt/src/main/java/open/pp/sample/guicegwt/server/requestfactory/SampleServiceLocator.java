package open.pp.sample.guicegwt.server.requestfactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class SampleServiceLocator implements ServiceLocator {

	@Inject
	private Injector injector;

	@Override
	public Object getInstance(Class<?> clazz) {
		return injector.getInstance(clazz);
	}
}
