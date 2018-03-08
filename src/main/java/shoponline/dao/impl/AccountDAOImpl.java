package shoponline.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import shoponline.dao.AccountDAO;
import shoponline.entity.Account;

/**
 * @author hieu.tpk
 */
public class AccountDAOImpl implements AccountDAO {

  private SessionFactory sessionFactory;

  @Override
  public Account findAccount(String userName) {
    Session session = sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Account.class);
    criteria.add(Restrictions.eq("userName", userName));
    return (Account) criteria.uniqueResult();
  }
}
