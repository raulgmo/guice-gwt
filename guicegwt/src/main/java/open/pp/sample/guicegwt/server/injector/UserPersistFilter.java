package open.pp.sample.guicegwt.server.injector;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserPersistFilter implements Filter {
	private final PersistenceLifeCycleManager manager;

	@Inject
	public UserPersistFilter(
			@UserPersistService PersistenceLifeCycleManager manager) {
		this.manager = manager;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.manager.startService();
	}

	public void destroy() {
		this.manager.stopService();
	}

	public void doFilter(final ServletRequest servletRequest,
			final ServletResponse servletResponse, final FilterChain filterChain)
			throws IOException, ServletException {

		this.manager.beginUnitOfWork();
		try {
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			this.manager.endUnitOfWork();
		}
	}
}
