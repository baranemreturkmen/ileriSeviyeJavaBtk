package com.springIocAnnotation;

public class CustomerManager implements ICustomerService{
	
	ICustomerDal customerDal;

	
	//constructor injection
	public CustomerManager(ICustomerDal customerDal) {
		
		this.customerDal = customerDal;
		
	}
	
	@Override
	public void add() {
		
		customerDal.add();
		
	}
	
	

}
