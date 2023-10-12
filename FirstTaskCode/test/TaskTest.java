package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContactTest {

	private Contact setupScenary1() {
		String n = "Juan Reyes";
		String e = "jmreyes@icesi.edu.co";
		String p = "3019876543";
		Contact newContact = new Contact(n, e, p);
		return newContact;
	}

    
}