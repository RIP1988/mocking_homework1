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
import pl.luciow.warehouse.impl.WarehouseImpl;
import pl.luciow.warehouse.model.Item;
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
    	order.setItems(lista);//dodac do listy itemow jakis element i sprawdzic czy warehouse ma poprawne
    	//elementy zgodne z tym co dodalismy do ordera
    	Mockito.doCallRealMethod().when(warehouseMock).addItems(Mockito.any(List.class));
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	orderService.cancelOrder(order);
    	//dlaczego tutaj w warehouse mock nie ma zadnej listy, skoro powinna powstac po wywolaniu cancelOrder dla orderService?
    	//no i niby mam wyzej doCallRealMethod dla add Items, a po wejsciu w debugerze do cancelOrder, a w niej do addItems
    	//debug lata po jakichs metodach mocka, a nie po rzeczywistej metodzie warehouse.
    	assertEquals(warehouseMock.getItems().get(0).getName(), item.getName());
    }

    @Test 
    public void processPaymentThrowTest() {
    	PaymentService paymentServiceMock = Mockito.mock(PaymentService.class);
    	MailService mailServiceMock = Mockito.mock(MailService.class);
    	try {
    	Mockito.when(paymentServiceMock.processPayment(new Payment()).thenThrow(new Exception());//dlaczego ta metoda nie chce przejsc?
    	paymentServiceMock.processPayment(new Payment());
    	}
    	catch (Exception e) {
    		assertTrue(true);
    	}
    }

    @Test
    public void processPaymentSuccessTest() {
    }

}
