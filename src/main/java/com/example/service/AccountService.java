package com.example.service;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AccountService{
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(String username, String password){
        if(username.isBlank() || password.isBlank() || password.length() < 4){
            return null;
        }
        
        Account savedAccount = accountRepository.save(new Account(username, password));
        return savedAccount;
    }

    public boolean doesUsernameExist(String username){
        return accountRepository.existsAccountByUsername(username);
    }

    public Account login(Account account){
         String username = account.getUsername();
         String password = account.getPassword();

       Account loggedAccount = accountRepository.findByUsernameAndPassword(username,password);

       return loggedAccount;
    }
    
}

