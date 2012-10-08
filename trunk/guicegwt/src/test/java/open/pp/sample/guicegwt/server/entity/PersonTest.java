package open.pp.sample.guicegwt.server.entity;

import javax.persistence.EntityManager;

import junit.framework.Assert;
import open.pp.sample.guicegwt.server.injector.GuiceTestRunner;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.TestUserPersistModule;
import open.pp.sample.guicegwt.server.injector.UserPersistService;
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
@WithModules({ TestUserPersistModule.class })
public class PersonTest {
	@Inject
	protected Injector injector;
	@Inject
	protected Provider<EntityManager> em;
	@Inject
	@UserPersistService
	protected PersistenceLifeCycleManager manager;

	public EntityManager getEntityManager() {
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
			Person uas = new Person();
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
			Person uas = new Person();
			uas.setId(java.util.UUID.randomUUID().toString());
			uas.setName("Pandurang Patil");
			uas.setDescription("Some description");
			getEntityManager().persist(uas);
			Person uas1 = getEntityManager().find(Person.class, uas.getId());
			Assert.assertEquals(uas, uas1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
