package open.pp.sample.guicegwt.server.service;

import open.pp.sample.guicegwt.client.InsufficientFundException;
import open.pp.sample.guicegwt.server.dao.AccountDao;
import open.pp.sample.guicegwt.server.dao.PersonDao;
import open.pp.sample.guicegwt.server.entity.Account;
import open.pp.sample.guicegwt.server.entity.Person;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class BankService {

	@Inject
	private Injector injector;

	public String registerPerson(Person p) {
		String userId = java.util.UUID.randomUUID().toString();
		p.setId(userId);
		PersonDao pd = injector.getInstance(PersonDao.class);
		pd.savePerson(p);
		return p.getId();
	}

	public String openAccount(String personId, double openingBalance) {
		Account a = new Account();
		a.setId(java.util.UUID.randomUUID().toString());
		a.setBalance(openingBalance);
		a.setPersonId(personId);
		AccountDao ad = injector.getInstance(AccountDao.class);
		ad.saveAccount(a);
		return a.getId();
	}

	public void transfer(Account source, Account distination, double amount)
			throws InsufficientFundException {
		source.debit(amount);
		distination.credit(amount);
	}

}
