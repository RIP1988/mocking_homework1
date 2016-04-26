/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import pl.luciow.warehouse.PaymentProcessor;
import pl.luciow.warehouse.model.Payment;
import pl.luciow.warehouse.util.PaymentValidator;
import pl.luciow.warehouse.util.ValidatorException;

/**
 *
 * @author Mariusz
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl();

    @Mock
    private PaymentProcessor paymentProcessorMock;

    @Mock
    private PaymentValidator paymentValidatorMock;

    @Test
    public void processPaymentTest() throws Exception {	    	
    			Mockito.doAnswer(new Answer() {
    			      public Object answer(InvocationOnMock invocation) {
    			          Object[] args = invocation.getArguments();
    			          @SuppressWarnings("unchecked")
    					List<String> errors = (List<String>) args[1];
    						errors.add("error");
    						return null;
    			      }})
    			  .when(paymentValidatorMock).validate(Mockito.any(Payment.class), Mockito.anyListOf(String.class));
    			try {
    				paymentServiceImpl.processPayment(new Payment());
    				Mockito.verify(paymentProcessorMock, Mockito.times(1)).processPayment(Mockito.any(Payment.class));
    			} catch (ValidatorException e) {
    				Mockito.verify(paymentProcessorMock, Mockito.times(0)).processPayment(Mockito.any(Payment.class));
    			}
    			
    		}
    }

