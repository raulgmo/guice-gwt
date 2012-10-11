package open.pp.sample.guicegwt.server.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import open.pp.sample.guicegwt.server.entity.Account;
import open.pp.sample.guicegwt.server.injector.AccountPersistService;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class AccountDao {

	@Inject
	@AccountPersistService
	PersistenceLifeCycleManager manager;

	@Transactional
	public boolean saveAccount(Account a) {
		manager.getEntityManager().persist(a);
		return true;
	}

	@Transactional
	public boolean mergeAccount(Account a) {
		manager.getEntityManager().merge(a);
		return true;
	}

	@Transactional
	public List<Account> getAllAccountsByPersonId(String personId) {
		List<Account> list = null;
		TypedQuery<Account> qry = manager.getEntityManager().createNamedQuery(
				"Account.getByPersonId", Account.class);
		qry.setParameter("personId", personId);
		if (qry != null) {
			list = qry.getResultList();
		}
		return list;
	}

	@Transactional
	public Account getAccountById(String id) {
		return manager.getEntityManager().find(Account.class, id);
	}

	@Transactional
	public boolean removeAccount(String id) {
		Account a = getAccountById(id);
		if (a != null) {
			manager.getEntityManager().remove(a);
			return true;
		}
		return false;
	}
}
