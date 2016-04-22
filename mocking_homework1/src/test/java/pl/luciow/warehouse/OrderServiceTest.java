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
    	orderService.fillOrder(new Order());//sprawdzic czy zwraca to co powinien
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
    	order.setItems(new ArrayList<Item>());//dodac do listy itemow jakis element i sprawdzic czy warehouse ma poprawne elementy zgodne z tym co dodalismy do ordera
    	Mockito.doCallRealMethod().when(warehouseMock).addItems(Mockito.any(List.class));
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	orderService.cancelOrder(order);
    }

    @Test
    public void processPaymentThrowTest() {
    }

    @Test
    public void processPaymentSuccessTest() {
    }

}
