package pl.luciow.warehouse;

import org.mockito.ArgumentMatcher;

import pl.luciow.warehouse.model.Mail;

public class MailMatcherError extends ArgumentMatcher {

	@Override
	public boolean matches(Object argument) {
		Mail mail = (Mail)argument;
		return mail.getContent().equals("Error occured");
	}
	
}
