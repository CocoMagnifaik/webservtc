package org.mongodb;

import java.net.UnknownHostException;

import com.mongodb.MongoException;

public class Kill {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
                
		Customers cus = new Customers();
		try {
			//cus.listCustomers();
			cus.findCustomers("Rakotozandry");
			//cus.insertCustomers("Randriamilamina", "Cedrick");
			//cus.deleteCustomers("Randriamilamina");
			//cus.updateCustomers("Rakoto", "Rakotozandry");
		} catch (UnknownHostException e) {
	    } catch (MongoException e) {
	    }
	}
}