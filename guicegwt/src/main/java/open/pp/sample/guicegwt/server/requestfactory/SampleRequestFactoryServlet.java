/**
 * 
 */
package open.pp.sample.guicegwt.server.requestfactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

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
		super(new RequestFactoryExceptionHandler(), injector
				.getInstance(SampleServiceLayerDecorator.class));
	}
}
