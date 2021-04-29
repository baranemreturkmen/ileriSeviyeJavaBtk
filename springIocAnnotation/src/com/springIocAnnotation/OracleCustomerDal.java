package com.springIocAnnotation;

import org.springframework.beans.factory.annotation.Value;

public class OracleCustomerDal implements ICustomerDal{
	
	//values.property dosyasindaki String degerin bu class'in altindaki connectionString ozelligine karsilik geldigini soyluyorum.
	@Value("${database.connectionString}")
	String connectionString;
	
	@Override
	public void add(){
		
		System.out.println("Connection String : "+this.connectionString);
		System.out.println("Oracle veritabanina eklendi.");
		
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

}
