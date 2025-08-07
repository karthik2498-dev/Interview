package com.demo.interview.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.interview.dto.UserAccountDTO;
import com.demo.interview.entity.UserAccount;
import com.demo.interview.repository.UserAccountRepo;

@Service
public class UserAccountService {
	
	private UserAccountRepo useraccrepo;

	public UserAccountService(UserAccountDTO useraccdto,UserAccountRepo useraccrepo) {
		super();
		this.useraccrepo=useraccrepo;
	}
	

	public String SaveUserAccount(List<UserAccountDTO> useracc) throws SQLException {
		
		try {
			for(UserAccountDTO userdto : useracc) {
				UserAccount user = new UserAccount();
				user.setAccountNum(userdto.getAccountNum());
				user.setAddress(userdto.getAddress());
				user.setBalance(userdto.getBalance());
				user.setName(userdto.getName());
				user.setUserID(userdto.getUserID());
				useraccrepo.save(user);
			}
			return "Successfully Updated";
		}catch(Exception e) {
		return "Failed to Insert";
		}
	}
	
	public String DeleteAccountByAccID(Long Accnum) throws SQLException{
		useraccrepo.deleteById(Accnum);
		return "Successfull Deleted";
	}
	
	public Optional<UserAccount> GetAccountDetailsbyID(Long Accnum) throws SQLException {
		Optional<UserAccount> result=useraccrepo.findByAccountNum(Accnum);
		return result;
	}
}
