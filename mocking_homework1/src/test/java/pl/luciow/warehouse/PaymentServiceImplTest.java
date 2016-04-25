package pl.luciow.warehouse;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import pl.luciow.warehouse.impl.PaymentServiceImpl;
import pl.luciow.warehouse.model.Payment;
import pl.luciow.warehouse.util.ValidatorUtils;

import static org.junit.Assert.*;

public class PaymentServiceImplTest {
		private PaymentService paymentService;
	public void processPaymentTest() {
		paymentService = new PaymentServiceImpl();
		ValidatorUtils validatorUtils = Mockito.mock(ValidatorUtils.class);
		PaymentProcessor paymentProcessorMock = Mockito.mock(PaymentProcessor.class);
		Mockito.doAnswer(new Answer() {
		      public Object answer(InvocationOnMock invocation) {
		          Object[] args = invocation.getArguments();
		          Mock mock = invocation.getMock();
		          return null;
		      }})
		  .when(validatorUtils).validate();
		paymentService.processPayment(new Payment());
		Mockito.verify(paymentProcessorMock, Mockito.times(0)).processPayment(Mockito.any(Payment.class));
	}
}
