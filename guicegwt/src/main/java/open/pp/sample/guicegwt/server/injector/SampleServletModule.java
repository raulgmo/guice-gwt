package open.pp.sample.guicegwt.server.injector;

import open.pp.sample.guicegwt.server.requestfactory.SampleRequestFactoryServlet;

import com.google.inject.servlet.ServletModule;

/**
 * 
 * 
 */

public class SampleServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		// try {
		// bind(SampleRequestFactoryServlet.class).toConstructor(
		// SampleRequestFactoryServlet.class
		// .getConstructor(Injector.class));
		// } catch (SecurityException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (NoSuchMethodException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		serve("/gwtRequest").with(SampleRequestFactoryServlet.class);
		filter("/*").through(UserPersistFilter.class);
		filter("/*").through(AccountPersistFilter.class);
	}

}
