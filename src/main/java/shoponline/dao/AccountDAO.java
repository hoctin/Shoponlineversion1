package shoponline.dao;

import shoponline.entity.Account;

public interface AccountDAO {
  public Account findAccount(String userName);
}
