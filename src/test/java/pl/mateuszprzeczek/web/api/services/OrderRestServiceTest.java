package pl.mateuszprzeczek.web.api.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.domain.Order;
import pl.mateuszprzeczek.domain.Pizza;
import pl.mateuszprzeczek.domain.User;
import pl.mateuszprzeczek.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderRestServiceTest {

	@InjectMocks
	OrderRestService orderRestService;
	@Mock
	OrderRepository orderRepository;
	

	@Test
	void allOrdersShouldReturnNotNullValue() {
		
		//given
		Iterable<Order> orders = prepareOrdersData();
		given(orderRestService.allOrders()).willReturn(orders);
		
		//when
		Iterable<Order> ordersIterable = orderRestService.allOrders();
		
		//then
		assertThat(ordersIterable.iterator().next().getId(), notNullValue());
	}
	
	
	@Test
	void testPostOrder() {
		//given
		Order order = new Order();
		order.setId(1L);
		order.setDeliveryCity("City");
		doReturn(order).when(orderRepository).save(order);
		
		//when
		Order returnedOrder = orderRestService.postOrder(order);
		
		
		assertThat(returnedOrder.getId(), equalTo(1L));
		assertThat(returnedOrder.getDeliveryCity(), equalTo("City"));
	}
	
	
	private Iterable<Order> prepareOrdersData() {
		Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
        thinCake.setType(Ingredient.Type.WRAP);
		
		Ingredient salami = new Ingredient("SALA", "salami");
        salami.setType(Ingredient.Type.PROTEIN);
        
        Pizza pizza1 = new Pizza();
        pizza1.setName("Mozarella");
        pizza1.setIngredients(Arrays.asList(thinCake));
        pizza1.setPrice(18);
        List<Pizza> pizzas = Arrays.asList(pizza1);
        
        User user = new User(3L, "Mat", "password", "Mat Mat", "Street", "City", "State", "Zip", "555555555", "email@gmail.com");
        
        
        Order order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setPlacedAt(new Date());
        order.setPizzas(pizzas);
        order.setDeliveryZip("111-111");
        order.setDeliveryStreet(user.getStreet());
        order.setDeliveryState(user.getState());
        order.setDeliveryName("Delivery Name");
        order.setDeliveryCity(user.getCity());
        order.setCcNumber("1111455545446545");
        order.setCcCVV("656");
        order.setCcExpiration("02/22");
        
        Order order2 = new Order();
        order2.setId(2L);
        order.setUser(user);
        order.setPlacedAt(new Date());
        order.setPizzas(pizzas);
        order.setDeliveryZip("111-111");
        order.setDeliveryStreet(user.getStreet());
        order.setDeliveryState(user.getState());
        order.setDeliveryName("Delivery Name");
        order.setDeliveryCity(user.getCity());
        order.setCcNumber("1111455545446545");
        order.setCcCVV("656");
        order.setCcExpiration("02/22");
        
        
        
		return Arrays.asList(order, order2);
	}
}
