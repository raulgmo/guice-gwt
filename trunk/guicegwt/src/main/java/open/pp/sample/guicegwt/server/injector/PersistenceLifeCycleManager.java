package open.pp.sample.guicegwt.server.injector;

import javax.persistence.EntityManager;

public interface PersistenceLifeCycleManager {
	public abstract void startService();

	public abstract void stopService();

	public abstract void beginUnitOfWork();

	public abstract void endUnitOfWork();

	public abstract EntityManager getEntityManager();
}
