/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse;

import org.junit.Test;
import org.mockito.Mockito;

import pl.luciow.warehouse.impl.OrderServiceImpl;
import pl.luciow.warehouse.impl.WarehouseImpl;
import pl.luciow.warehouse.model.NotEnoughItemsException;
import pl.luciow.warehouse.model.Order;
import pl.luciow.warehouse.model.OrderProcessException;

import java.util.List;
;/**
 *
 * @author Mariusz
 */
public class OrderServiceTest {

    private OrderService orderService;

    @SuppressWarnings({ "unchecked" })
	@Test
    public void fillOrderSuccesTest() throws NotEnoughItemsException, OrderProcessException {
    	Warehouse warehouseMock = Mockito.mock(Warehouse.class);
    	Mockito.when(warehouseMock.removeItems(Mockito.any(List.class))).thenReturn(null);
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	orderService.fillOrder(new Order());
    }

    @SuppressWarnings("unchecked")
	@Test (expected = OrderProcessException.class)
    public void fillOrderThrowTest() throws NotEnoughItemsException, OrderProcessException  {
    	Warehouse warehouseMock = Mockito.mock(Warehouse.class);
    	Mockito.when(warehouseMock.removeItems(Mockito.any(List.class))).thenThrow(new NotEnoughItemsException());
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	orderService.fillOrder(new Order());
    }

    @SuppressWarnings("unchecked")
	@Test
    public void cancelOrderTest() throws OrderProcessException {
    	Warehouse warehouseMock = Mockito.mock(WarehouseImpl.class);
    	Order orderMock = Mockito.mock(Order.class);
    	Mockito.doCallRealMethod().when(warehouseMock).addItems(Mockito.any(List.class));
    	Mockito.doCallRealMethod().when(orderMock).setItems(Mockito.any(List.class));
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	orderService.cancelOrder(orderMock);
    }

    @Test
    public void processPaymentThrowTest() {
    }

    @Test
    public void processPaymentSuccessTest() {
    }

}
