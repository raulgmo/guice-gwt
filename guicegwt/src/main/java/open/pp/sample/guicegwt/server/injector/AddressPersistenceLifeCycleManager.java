package open.pp.sample.guicegwt.server.injector;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;

@Singleton
public class AddressPersistenceLifeCycleManager implements
		PersistenceLifeCycleManager {

	private final UnitOfWork unitOfWork;
	private final PersistService persistService;
	private final Provider<EntityManager> entityManager;

	@Inject
	public AddressPersistenceLifeCycleManager(UnitOfWork unitOfWork,
			PersistService persistService, Provider<EntityManager> entityManager) {
		this.unitOfWork = unitOfWork;
		this.persistService = persistService;
		this.entityManager = entityManager;
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

	public EntityManager getEntityManager() {
		return entityManager.get();
	}
}
