package com.restwebservices.restfulwebservices.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restwebservices.restfulwebservices.exception.AccountNotFoundException;
import com.restwebservices.restfulwebservices.models.Account;
import com.restwebservices.restfulwebservices.repository.AccountRepo;
import com.restwebservices.restfulwebservices.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepo accountRepo;

	@Override
	public void addAccount(Account account) throws AccountNotFoundException {
		accountRepo.save(account);

	}

	@Override
	public void editAccount(Account account) throws AccountNotFoundException {
		accountRepo.save(account);

	}

	@Override
	public void deleteAccount(long id) throws AccountNotFoundException {
		accountRepo.deleteById(id);		
	}

	@Override
	public List<Account> listAll() throws AccountNotFoundException {
		return accountRepo.findAll();
	}

	@Override
	public Account getAccount(long id) throws AccountNotFoundException {
		return accountRepo.getById(id);
	}



}
