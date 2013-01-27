package open.pp.sample.guicegwt.server.injector;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;

@Singleton
public class UserPersistenceLifeCycleManager implements PersistenceLifeCycleManager {

	private final UnitOfWork		unitOfWork;
	private final PersistService	persistService;

	@Inject
	public UserPersistenceLifeCycleManager(UnitOfWork unitOfWork, PersistService persistService) {
		this.unitOfWork = unitOfWork;
		this.persistService = persistService;
	}

	public void startService() {
		this.persistService.start();
	}

	public void stopService() {
		this.persistService.stop();
	}

	public void beginUnitOfWork() {
		this.unitOfWork.begin();
	}

	public void endUnitOfWork() {
		this.unitOfWork.end();
	}

}
