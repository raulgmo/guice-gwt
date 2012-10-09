package open.pp.sample.guicegwt.server.entity;

import javax.persistence.EntityManager;

import junit.framework.Assert;
import open.pp.sample.guicegwt.server.injector.AccountPersistService;
import open.pp.sample.guicegwt.server.injector.GuiceTestRunner;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.TestAccountPersistModule;
import open.pp.sample.guicegwt.server.injector.WithModules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.Transactional;

@RunWith(GuiceTestRunner.class)
@WithModules({ TestAccountPersistModule.class })
public class AccountTest {
	@Inject
	protected Injector injector;
	@Inject
	@AccountPersistService
	protected PersistenceLifeCycleManager manager;

	public EntityManager getEntityManager() {
		return manager.getEntityManager();
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
			Account uas = new Account();
			uas.setId(java.util.UUID.randomUUID().toString());
			uas.setName("Pandurang Patil");
			uas.setDescription("Some description");
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
			Account uas = new Account();
			uas.setId(java.util.UUID.randomUUID().toString());
			uas.setName("Pandurang Patil");
			uas.setDescription("Some description");
			getEntityManager().persist(uas);
			Account uas1 = getEntityManager().find(Account.class, uas.getId());
			Assert.assertEquals(uas, uas1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
