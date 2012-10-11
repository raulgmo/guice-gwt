package open.pp.sample.guicegwt.server.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import open.pp.sample.guicegwt.client.InsufficientFundException;

/**
 * 
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Account.getByPersonId", query = "select a from Account a where a.personId = :personId") })
public class Account extends AccountBase {

	@Id
	private String id;
	@Version
	private Integer version;
	@Basic
	private String name;
	@Basic
	private String description;
	@Basic
	private String personId;
	@Basic
	private double balance;

	@Override
	public int hashCode() {
		if (id != null)
			return id.hashCode();
		else
			return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else {
			if (hashCode() != other.hashCode())
				return false;
			else if (!id.equals(other.id))
				return false;
		}
		return true;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void credit(double amount) {
		this.balance += amount;
	}

	public void debit(double amount) throws InsufficientFundException {
		if (this.balance < amount) {
			throw new InsufficientFundException();
		}
		this.balance -= amount;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", version=" + version + ", name=" + name
				+ ", description=" + description + ", personId=" + personId
				+ ", balance=" + balance + "]";
	}

}
