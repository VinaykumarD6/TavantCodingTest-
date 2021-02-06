package com.tavant.accountspringrestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.accountspringrestapi.exception.AccountNotFoundException;
import com.tavant.accountspringrestapi.exception.BlankObjectException;
import com.tavant.accountspringrestapi.exception.NoDataFoundException;
import com.tavant.accountspringrestapi.model.Account;
import com.tavant.accountspringrestapi.repository.AccountRepository;


@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	AccountRepository accountRepository;
	@GetMapping
	public String getAccount() {
		return "Hello, Welcome to our Bank";
	}
	@GetMapping("/all")
	public List<Account> getAllEmployees() throws Exception{
		List list = this.accountRepository.findAll();
	    return Optional.ofNullable(list.isEmpty()?null:list).orElseThrow(()->new NoDataFoundException("No records"));

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable("id") Integer id) throws AccountNotFoundException {
		Optional<Account> optional =  accountRepository.findById(id);
		if(optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());
		}else {
			throw new AccountNotFoundException("not found");
		}
	}
	@PostMapping
	public Account addEmployee(@RequestBody @Valid Account account) throws BlankObjectException {
		if(account.getAccountNumber()==null) {
			throw new BlankObjectException("Provide Employee Object");
		}
		return accountRepository.save(account);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		accountRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public Account updateAccount(@RequestBody @Valid Account newAccount, @PathVariable Integer id) {
		return accountRepository.findById(id).map(account->{
			account.setAccountHolderFirstName(newAccount.getAccountHolderFirstName());
			account.setAccountHolderLastName(newAccount.getAccountHolderLastName());
			return accountRepository.save(account);
		}).orElseGet(()->{
			newAccount.setAccountNumber(id);
			return accountRepository.save(newAccount);
		});		
	}
}
