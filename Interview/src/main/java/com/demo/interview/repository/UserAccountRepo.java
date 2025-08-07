package com.demo.interview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.interview.dto.UserAccountDTO;
import com.demo.interview.entity.UserAccount;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount,Long> {

	public Optional<UserAccount> findByAccountNum(Long accnum);
	
}
