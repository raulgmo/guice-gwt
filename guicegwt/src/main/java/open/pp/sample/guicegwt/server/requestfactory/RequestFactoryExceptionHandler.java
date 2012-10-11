/**
 * 
 */
package open.pp.sample.guicegwt.server.requestfactory;

import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * @author pandurang
 * 
 */
public class RequestFactoryExceptionHandler implements ExceptionHandler {

	@Override
	public ServerFailure createServerFailure(Throwable throwable) {
		throwable.printStackTrace();
		return new ServerFailure("Server Error: "
				+ (throwable == null ? null : throwable.getMessage()), null,
				null, true);
	}

}
