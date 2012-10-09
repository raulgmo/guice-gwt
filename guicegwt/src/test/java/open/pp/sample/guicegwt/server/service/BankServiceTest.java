/**
 * 
 */
package open.pp.sample.guicegwt.server.service;

import junit.framework.Assert;
import open.pp.sample.guicegwt.client.InsufficientFundException;
import open.pp.sample.guicegwt.server.dao.AccountDao;
import open.pp.sample.guicegwt.server.entity.Account;
import open.pp.sample.guicegwt.server.entity.Person;
import open.pp.sample.guicegwt.server.injector.AccountPersistService;
import open.pp.sample.guicegwt.server.injector.GuiceTestRunner;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.TestAccountPersistModule;
import open.pp.sample.guicegwt.server.injector.TestUserPersistModule;
import open.pp.sample.guicegwt.server.injector.UserPersistService;
import open.pp.sample.guicegwt.server.injector.WithModules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author pandurang
 * 
 */
@RunWith(GuiceTestRunner.class)
@WithModules({ TestAccountPersistModule.class, TestUserPersistModule.class })
public class BankServiceTest {
	@Inject
	protected Injector injector;

	@Inject
	@AccountPersistService
	protected PersistenceLifeCycleManager accountManager;
	@Inject
	@UserPersistService
	protected PersistenceLifeCycleManager userManager;

	@Before
	public void before() {
		userManager.beginUnitOfWork();
		accountManager.beginUnitOfWork();
	}

	@After
	public void after() {
		accountManager.endUnitOfWork();
		userManager.endUnitOfWork();
	}

	@Test
	public void registerPerson() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		BankService bs = injector.getInstance(BankService.class);
		bs.registerPerson(p);
	}

	@Test
	public void openAccountTest() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		BankService bs = injector.getInstance(BankService.class);
		String personId = bs.registerPerson(p);
		Assert.assertNotNull(personId);
		String accountId = bs.openAccount(personId, 1000);
		Assert.assertNotNull(accountId);
		AccountDao ad = injector.getInstance(AccountDao.class);
		Account a = ad.getAccountById(accountId);
		Assert.assertNotNull(a);
		Assert.assertEquals(1000.00, a.getBalance());
	}

	@Test
	public void transferTest() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		BankService bs = injector.getInstance(BankService.class);
		String personId = bs.registerPerson(p);
		Assert.assertNotNull(personId);
		String accountId1 = bs.openAccount(personId, 1000);
		Assert.assertNotNull(accountId1);
		String accountId2 = bs.openAccount(personId, 2000);
		Assert.assertNotNull(accountId2);
		AccountDao ad = injector.getInstance(AccountDao.class);
		Account a1 = ad.getAccountById(accountId1);
		Account a2 = ad.getAccountById(accountId2);

		try {
			bs.transfer(a2, a1, 500.00);
			Assert.assertEquals(1500.00, a1.getBalance());
			Assert.assertEquals(1500.00, a2.getBalance());
		} catch (InsufficientFundException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}
