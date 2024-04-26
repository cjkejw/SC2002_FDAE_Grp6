package login;

import customer.CustSys;

public class LoginCustomer {
	CustSys customerSystem; 
	
	public LoginCustomer(String branchName, String fullBranchName) {
		this.customerSystem = new CustSys(branchName, fullBranchName); 
	}
	
	public void options() {
		customerSystem.customerSystem();
	}
}
