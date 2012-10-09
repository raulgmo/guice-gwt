/**
 * 
 */
package open.pp.sample.guicegwt.server.requestfactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

/**
 *
 */
@Singleton
public class SampleRequestFactoryServlet extends RequestFactoryServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public SampleRequestFactoryServlet(Injector injector) {
		this(new DefaultExceptionHandler(), injector
				.getInstance(SampleServiceLayerDecorator.class));
	}

	public SampleRequestFactoryServlet(ExceptionHandler exceptionHandler,
			ServiceLayerDecorator... serviceDecorators) {
		super(exceptionHandler, serviceDecorators);
	}

}
