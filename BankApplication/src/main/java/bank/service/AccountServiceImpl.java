package bank.service;

import bank.AddBankEvent;
import bank.TraceRecord;
import bank.TraceRecordRepository;
import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountDto;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private TraceRecordRepository traceRecordRepository;

	private AccountRepository accountRepository;
	private CurrencyConverter currencyConverter;
	private JMSSender jmsSender;
	private Logger logger;

	public AccountServiceImpl(AccountRepository accountRepository, CurrencyConverter currencyConverter, JMSSender jmsSender, Logger logger) {
		this.accountRepository = accountRepository;
		this.currencyConverter = currencyConverter;
		this.jmsSender = jmsSender;
		this.logger = logger;
	}

	public AccountDto createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
		// Calling publisher event before returning
		eventPublisher.publishEvent(new AddBankEvent(accountNumber, "XYZ Bank", "Account created", customerName, AddBankEvent.EventType.CREATE_ACCOUNT));
		//adding trace record
		TraceRecord traceRecord = new TraceRecord(LocalDateTime.now(), accountNumber, "CREATE_ACCOUNT", 0);
		traceRecordRepository.save(traceRecord);
		return AccountMapper.accountToAccountDto(account);
	}



	public void deposit(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			account.deposit(amount);
			accountRepository.save(account);
			logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
			eventPublisher.publishEvent(new AddBankEvent(accountNumber, "XYZ Bank", amount + " deposited to your account", account.getCustomer().getName(), AddBankEvent.EventType.DEPOSIT));
			// Save Trace Record
			TraceRecord traceRecord = new TraceRecord(LocalDateTime.now(), accountNumber, "DEPOSIT", amount);
			traceRecordRepository.save(traceRecord);


			if (amount > 10000) {
				jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
			}
		}
	}

	public AccountDto getAccount(long accountNumber) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			return AccountMapper.accountToAccountDto(account);
		}
		return null;
	}

	public Collection<AccountDto> getAllAccounts() {
		return AccountMapper.accountToAccountDto(accountRepository.findAll());
	}

	@Override
	public void withdraw(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			account.withdraw(amount);
			accountRepository.save(account);
			logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
			eventPublisher.publishEvent(new AddBankEvent(accountNumber, "XYZ Bank", amount + " withdrawn from your account", account.getCustomer().getName(), AddBankEvent.EventType.WITHDRAW));
			// Save Trace Record
			TraceRecord traceRecord = new TraceRecord(LocalDateTime.now(), accountNumber, "WITHDRAW", amount);
			traceRecordRepository.save(traceRecord);
		}
	}
	public void depositEuros(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.deposit(amountDollars);
			accountRepository.save(account);
			logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
			if (amountDollars > 10000) {
				jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
			}
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.withdraw(amountDollars);
			accountRepository.save(account);
			logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
		}
	}

	@Override
	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Optional<Account> fromAccountOpt = accountRepository.findByAccountNumber(fromAccountNumber);
		Optional<Account> toAccountOpt = accountRepository.findByAccountNumber(toAccountNumber);
		if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
			Account fromAccount = fromAccountOpt.get();
			Account toAccount = toAccountOpt.get();
			fromAccount.transferFunds(toAccount, amount, description);
			accountRepository.save(fromAccount);
			accountRepository.save(toAccount);
			logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
			eventPublisher.publishEvent(new AddBankEvent(fromAccountNumber, "XYZ Bank", amount + " transferred to account " + toAccountNumber, fromAccount.getCustomer().getName(), AddBankEvent.EventType.TRANSFER_FUNDS));
			if (amount > 10000) {
				jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccountNumber + " to account with accountNumber= " + toAccountNumber);
			}
		}
		// Save Trace Record
		TraceRecord traceRecord = new TraceRecord(LocalDateTime.now(), fromAccountNumber, "TRANSFER_FUNDS", amount);
		traceRecordRepository.save(traceRecord);
	}

	public void allAccountDetails()
	{
		List<Account> accounts=accountRepository.findAll();
		System.out.println("*******************ACCOUNT DETAILS**********************");
		for(Account account: accounts)
		{
			System.out.println("Account Number: " +account.getAccountNumber());
			System.out.println("Customer Name: " + account.getCustomer().getName());
			System.out.println("Total Balance: " + account.getBalance());
			System.out.println("****************EOD*********************************");
		}
	}

}
