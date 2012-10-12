package open.pp.sample.guicegwt.server.injector;

import java.util.HashMap;
import java.util.Map;

import open.pp.sample.guicegwt.server.requestfactory.SampleRequestFactoryServlet;

import com.google.inject.servlet.ServletModule;

/**
 * 
 * 
 */

public class SampleServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		filter("/*").through(UserPersistFilter.class);
		filter("/*").through(AddressPersistFilter.class);
		Map<String, String> params = new HashMap<String, String>();
		params.put("symbolMapsDirectory", "WEB-INF/classes/symbolMaps/");
		serve("/gwtRequest").with(SampleRequestFactoryServlet.class, params);
	}

}
