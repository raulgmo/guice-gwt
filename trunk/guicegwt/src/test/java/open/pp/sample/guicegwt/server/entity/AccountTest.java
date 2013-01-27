package open.pp.sample.guicegwt.server.entity;

import javax.persistence.EntityManager;

import junit.framework.Assert;
import open.pp.sample.guicegwt.server.injector.AddressPersistService;
import open.pp.sample.guicegwt.server.injector.GuiceTestRunner;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.TestAddressPersistModule;
import open.pp.sample.guicegwt.server.injector.WithModules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

@RunWith(GuiceTestRunner.class)
@WithModules({ TestAddressPersistModule.class })
public class AccountTest {
	@Inject
	protected Injector						injector;
	@Inject
	@AddressPersistService
	protected PersistenceLifeCycleManager	manager;
	@Inject
	Provider<EntityManager>					em;

	private EntityManager getEntityManager() {
		return em.get();
	}

	@Before
	public void before() {
		manager.beginUnitOfWork();
	}

	@After
	public void after() {
		manager.endUnitOfWork();
	}

	@Test
	@Transactional
	public void create() {
		try {
			Address uas = new Address();
			uas.setId(java.util.UUID.randomUUID().toString());
			uas.setCity("Mumbai");
			getEntityManager().persist(uas);
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	@Transactional
	public void find() {
		try {
			Address uas = new Address();
			uas.setId(java.util.UUID.randomUUID().toString());
			uas.setCity("Mumbai");
			getEntityManager().persist(uas);
			Address uas1 = getEntityManager().find(Address.class, uas.getId());
			Assert.assertEquals(uas, uas1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
