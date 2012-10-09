package open.pp.sample.guicegwt.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import open.pp.sample.guicegwt.server.entity.Account;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class AccountDao {

	@Inject
	private Provider<EntityManager> em;

	@Transactional
	public boolean saveAccount(Account a) {
		em.get().persist(a);
		return true;
	}

	@Transactional
	public boolean mergeAccount(Account a) {
		em.get().merge(a);
		return true;
	}

	@Transactional
	public List<Account> getAllAccountsByPersonId(String personId) {
		List<Account> list = null;
		TypedQuery<Account> qry = em.get().createNamedQuery(
				"Account.getByPersonId", Account.class);
		qry.setParameter("personId", personId);
		if (qry != null) {
			list = qry.getResultList();
		}
		return list;
	}

	@Transactional
	public Account getAccountById(String id) {
		return em.get().find(Account.class, id);
	}

	@Transactional
	public boolean removeAccount(String id) {
		Account a = getAccountById(id);
		if (a != null) {
			em.get().remove(a);
			return true;
		}
		return false;
	}
}
