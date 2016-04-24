package pl.luciow.warehouse;

import org.mockito.ArgumentMatcher;

public class MailMatcher extends ArgumentMatcher {

	@Override
	public boolean matches(Object argument) {
		return (String)argument == "Error occured";
	}
	
}
