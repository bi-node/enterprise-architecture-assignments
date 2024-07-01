package bank.service;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repositories.TraceRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.repositories.AccountRepository;
import bank.repositories.CustomerRepository;

import java.time.LocalDateTime;

@Service
public class BankService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	TraceRecordRepository traceRecordRepository;
	
	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){

		try {
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, "Welcome " + customerName);
			TraceRecord rec=new TraceRecord("Customer "+customerName+" created with account# "+AccountNumber, LocalDateTime.now());
			traceRecordRepository.save(rec);
		}
		catch (Exception e){
			emailSender.sendEmail(emailAddress, "Failure to create customer "+customerName);
			TraceRecord rec=new TraceRecord("Could not create Customer  "+customerName + "with account#"+ AccountNumber, LocalDateTime.now());
			traceRecordRepository.save(rec);
		}
	}

}
