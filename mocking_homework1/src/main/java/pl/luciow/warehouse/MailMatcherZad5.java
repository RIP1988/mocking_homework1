package pl.luciow.warehouse;

import org.mockito.ArgumentMatcher;

import pl.luciow.warehouse.model.Mail;

public class MailMatcherZad5 extends ArgumentMatcher {
	
	@Override
	public boolean matches(Object argument) {
		Mail mail = (Mail)argument;
		return mail.getContent().equals("Success");
	}
	
}
