package com.tavant.accountspringrestapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account  {
	

	
	@Id
	private Integer accountNumber;	
	@NotBlank(message = "accountHolderfirstName should not be blank")
	private String accountHolderFirstName;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "accountHolderLastName should not be blank")
	private String accountHolderLastName;
	@NotBlank(message = "ifscCode should not blank")
	private String ifscCode;
	@Column(length=15)
	@Size(max=20)
	private String bankLocation;
	
	
	
	
}