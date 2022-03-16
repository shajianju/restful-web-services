package com.restwebservices.restfulwebservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restwebservices.restfulwebservices.exception.AccountNotFoundException;
import com.restwebservices.restfulwebservices.models.Account;

@Service
public interface AccountService {
	
	public void addAccount(Account Account) throws AccountNotFoundException;
	public void editAccount(Account Account) throws AccountNotFoundException;
	public void deleteAccount(long id) throws AccountNotFoundException;
	public List<Account> listAll() throws AccountNotFoundException;
	public Account getAccount(long id) throws AccountNotFoundException;
	
	

}
