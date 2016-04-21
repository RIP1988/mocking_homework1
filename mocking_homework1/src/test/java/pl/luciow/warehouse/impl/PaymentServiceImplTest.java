/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.luciow.warehouse.PaymentProcessor;
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
    private PaymentProcessor paymentProcessor;

    @Mock
    private PaymentValidator paymentValidator;

    @Test(expected = ValidatorException.class)
    public void processPaymentTest() throws Exception {

    }
}
