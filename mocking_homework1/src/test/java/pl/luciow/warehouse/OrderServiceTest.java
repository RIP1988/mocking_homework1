/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import pl.luciow.warehouse.impl.OrderServiceImpl;
import pl.luciow.warehouse.impl.PaymentServiceImpl;
import pl.luciow.warehouse.impl.WarehouseImpl;
import pl.luciow.warehouse.model.Item;
import pl.luciow.warehouse.model.Mail;
import pl.luciow.warehouse.model.NotEnoughItemsException;
import pl.luciow.warehouse.model.Order;
import pl.luciow.warehouse.model.OrderProcessException;
import pl.luciow.warehouse.model.Payment;

import java.util.ArrayList;
import java.util.List;
;/**
 *
 * @author Mariusz
 */
public class OrderServiceTest {

    private OrderService orderService;

    @SuppressWarnings({ "unchecked" })
	@Test
    public void fillOrderSuccesTest() {
    	Warehouse warehouseMock = Mockito.mock(Warehouse.class);
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	try {
    	Mockito.when(warehouseMock.removeItems(Mockito.any(List.class))).thenReturn(null);
    	orderService.fillOrder(new Order());
    	}
    	catch (Exception e) {
    		assertTrue(false);
    	}
    	assertTrue(true);
    }

    @SuppressWarnings("unchecked")
	@Test (expected = OrderProcessException.class)
    public void fillOrderThrowTest() throws NotEnoughItemsException, OrderProcessException  {
    	Warehouse warehouseMock = Mockito.mock(Warehouse.class);
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	Mockito.when(warehouseMock.removeItems(Mockito.any(List.class))).thenThrow(new NotEnoughItemsException());
    	orderService.fillOrder(new Order());
    }

    @SuppressWarnings("unchecked")
	@Test
    public void cancelOrderTest() throws OrderProcessException {
    	Warehouse warehouseMock = Mockito.mock(WarehouseImpl.class);
    	Order order = new Order();
    	List<Item> lista = new ArrayList<Item>();
    	Item item = new Item();
    	item.setName("Przedmiocik");
    	lista.add(item);
    	order.setItems(lista);
    	Mockito.doCallRealMethod().when(warehouseMock).addItems(Mockito.any(List.class));
    	Mockito.doCallRealMethod().when(warehouseMock).getItems();
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	orderService.cancelOrder(order);
    	assertEquals(warehouseMock.getItems().get(0).getName(), item.getName());
    }

    @Test 
    public void processPaymentThrowTest() throws Exception{
    	PaymentService paymentServiceMock = Mockito.mock(PaymentServiceImpl.class);
    	MailService mailServiceMock = Mockito.mock(MailService.class);
    	Order orderMock = Mockito.mock(Order.class);
    	orderService = new OrderServiceImpl(mailServiceMock, paymentServiceMock, null);
    	Mockito.when(paymentServiceMock.processPayment(Mockito.any(Payment.class))).thenThrow(new Exception());
    	orderService.processPayment(orderMock, new Payment());
    	Mockito.verify(mailServiceMock).sendMail((Mail)Mockito.argThat(new MailMatcherZad4()));
    }

    @Test
    public void processPaymentSuccessTest() {
    	PaymentService paymentServiceMock = Mockito.mock(PaymentServiceImpl.class);
    	MailService mailServiceMock = Mockito.mock(MailService.class);
    	Order orderMock = Mockito.mock(Order.class);
    	orderService = new OrderServiceImpl(mailServiceMock, paymentServiceMock, null);
    	try {
    	Mockito.when(paymentServiceMock.processPayment(Mockito.any(Payment.class))).thenReturn(1L);
    	}
    	catch(Exception e) {
    		assertTrue(false);
    	}
    	orderService.processPayment(orderMock, new Payment());
    	Mockito.verify(mailServiceMock).sendMail((Mail)Mockito.argThat(new MailMatcherZad5()));
    }

}
