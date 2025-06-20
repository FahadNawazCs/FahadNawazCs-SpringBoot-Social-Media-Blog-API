package com.example.repository;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Account;;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    boolean existsAccountByUsername(String usernmae);

    Account findByUsernameAndPassword(String username, String password);

    boolean existsAccountByAccountId(Integer id);

    // Account login(Account account, String username, String password);
}
