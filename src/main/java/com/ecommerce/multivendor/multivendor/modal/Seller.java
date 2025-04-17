package com.ecommerce.multivendor.multivendor.modal;

import java.util.Objects;


import com.ecommerce.multivendor.multivendor.domain.AccountStatus;
import com.ecommerce.multivendor.multivendor.domain.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;

@Entity
public class Seller {
	 @Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	
	private String sellerName;
	
	private String mobile;
	@Column(unique = true,nullable = false)
	private String email;
	
	private String password;
	@Embedded
	private BusinessDetails businessDetails=new BusinessDetails();
	@Override
	public int hashCode() {
		return Objects.hash(GSTIN, accountStatus, bankDetails, businessDetails, email, id, isEmailVerified, mobile,
				password, pickupAddress, role, sellerName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(GSTIN, other.GSTIN) && accountStatus == other.accountStatus
				&& Objects.equals(bankDetails, other.bankDetails)
				&& Objects.equals(businessDetails, other.businessDetails) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && isEmailVerified == other.isEmailVerified
				&& Objects.equals(mobile, other.mobile) && Objects.equals(password, other.password)
				&& Objects.equals(pickupAddress, other.pickupAddress) && role == other.role
				&& Objects.equals(sellerName, other.sellerName);
	}

	public Seller() {
		super();
	}

	public Seller(Long id, String sellerName, String mobile, String email, String password,
			BusinessDetails businessDetails, BankDetails bankDetails, Address pickupAddress, String gSTIN,
			UserRole role, boolean isEmailVerified, AccountStatus accountStatus) {
		super();
		this.id = id;
		this.sellerName = sellerName;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.businessDetails = businessDetails;
		this.bankDetails = bankDetails;
		this.pickupAddress = pickupAddress;
		GSTIN = gSTIN;
		this.role = role;
		this.isEmailVerified = isEmailVerified;
		this.accountStatus = accountStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Address getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(Address pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public String getGSTIN() {
		return GSTIN;
	}

	public void setGSTIN(String gSTIN) {
		GSTIN = gSTIN;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Embedded
	private BankDetails bankDetails=new BankDetails();
	@OneToOne(cascade = CascadeType.ALL)
	private Address pickupAddress=new Address();
	
	private String GSTIN;
	
	private UserRole role=UserRole.ROLE_SELLER;
	
	private boolean isEmailVerified;
	
	private AccountStatus accountStatus=AccountStatus.PENDING_VERIFICATION;
	
	
	
	
	
}
