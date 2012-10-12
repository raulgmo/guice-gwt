package open.pp.sample.guicegwt.server.injector;

import java.util.HashMap;
import java.util.Map;

import open.pp.sample.guicegwt.server.requestfactory.RequestFactoryExceptionHandler;
import open.pp.sample.guicegwt.server.requestfactory.SampleRequestFactoryServlet;
import open.pp.sample.guicegwt.server.requestfactory.SampleServiceLayerDecorator;

import com.google.inject.servlet.ServletModule;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

/**
 * 
 * 
 */

public class SampleServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		filter("/*").through(UserPersistFilter.class);
		filter("/*").through(AddressPersistFilter.class);
		bind(ExceptionHandler.class).to(RequestFactoryExceptionHandler.class);
		bind(ServiceLayerDecorator.class).to(SampleServiceLayerDecorator.class);
		Map<String, String> params = new HashMap<String, String>();
		params.put("symbolMapsDirectory", "WEB-INF/classes/symbolMaps/");
		serve("/gwtRequest").with(SampleRequestFactoryServlet.class, params);
	}

}
