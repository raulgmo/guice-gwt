package open.pp.sample.guicegwt.server.dao;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;
import open.pp.sample.guicegwt.server.entity.Account;
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

@RunWith(GuiceTestRunner.class)
@WithModules({ TestAccountPersistModule.class })
public class AccountDaoTest {
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
	public void saveTest() {
		Account a = new Account();
		a.setName("Sample Account");
		a.setBalance(100);
		a.setPersonId(java.util.UUID.randomUUID().toString());
		a.setId(java.util.UUID.randomUUID().toString());
		AccountDao ad = injector.getInstance(AccountDao.class);
		ad.saveAccount(a);

		Account actual = ad.getAccountById(a.getId());
		Assert.assertEquals(a, actual);
	}

	@Test
	public void getAccountByPersonIdTest() {
		String personId = java.util.UUID.randomUUID().toString();
		Account a = new Account();
		a.setName("Sample Account1");
		a.setBalance(100);
		a.setPersonId(personId);
		a.setId(java.util.UUID.randomUUID().toString());
		AccountDao ad = injector.getInstance(AccountDao.class);
		ad.saveAccount(a);

		Account a1 = new Account();
		a1.setName("Sample Account1");
		a1.setBalance(100);
		a1.setPersonId(personId);
		a1.setId(java.util.UUID.randomUUID().toString());
		ad.saveAccount(a1);

		List<Account> list = ad.getAllAccountsByPersonId(a1.getPersonId());
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void removeTest() {
		Account a = new Account();
		a.setName("Sample Account");
		a.setBalance(100);
		a.setPersonId(java.util.UUID.randomUUID().toString());
		a.setId(java.util.UUID.randomUUID().toString());
		AccountDao ad = injector.getInstance(AccountDao.class);
		ad.saveAccount(a);

		Account actual = ad.getAccountById(a.getId());
		Assert.assertEquals(a, actual);
		ad.removeAccount(a.getId());

		actual = ad.getAccountById(a.getId());
		Assert.assertNull(actual);
	}
}
