package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.*;
import org.yearup.models.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("orders")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class OrdersController
{
    private final UserDao userDao;
    private final ProfileDao profileDao;
    private final ShoppingCartDao shoppingCartDao;
    private final OrderDao orderDao;
    private final OrderLineItemDao orderLineItemDao;

    @Autowired
    public OrdersController(UserDao userDao, ProfileDao profileDao, ShoppingCartDao shoppingCartDao,
                            OrderDao orderDao, OrderLineItemDao orderLineItemDao)
    {
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.shoppingCartDao = shoppingCartDao;
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // ✅ Insomnia expects 201
    public Order checkout(Principal principal)
    {
        try
        {
            User user = userDao.getByUserName(principal.getName());
            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            ShoppingCart cart = shoppingCartDao.getByUserId(user.getId());
            if (cart.getItems() == null || cart.getItems().isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shopping cart is empty");

            Profile profile = profileDao.getByUserId(user.getId());
            if (profile == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile is required to checkout");

            Order order = new Order();
            order.setUserId(user.getId());
            order.setDate(LocalDateTime.now());
            order.setAddress(nullSafe(profile.getAddress()));
            order.setCity(nullSafe(profile.getCity()));
            order.setState(nullSafe(profile.getState()));
            order.setZip(nullSafe(profile.getZip()));

            if (order.getAddress().isBlank() || order.getCity().isBlank() || order.getState().isBlank() || order.getZip().isBlank())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile address (address/city/state/zip) is required to checkout");

            order = orderDao.create(order);

            for (var entry : cart.getItems().entrySet())
            {
                int productId = entry.getKey();
                ShoppingCartItem cartItem = entry.getValue();

                OrderLineItem li = new OrderLineItem();
                li.setOrderId(order.getOrderId());
                li.setProductId(productId);
                li.setSalesPrice(cartItem.getProduct().getPrice());
                li.setQuantity(cartItem.getQuantity());
                li.setDiscount(java.math.BigDecimal.ZERO);

                orderLineItemDao.create(li);
            }

            shoppingCartDao.clearCart(user.getId());
            return order;
        }
        catch (ResponseStatusException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    private static String nullSafe(String s)
    {
        return s == null ? "" : s;
    }
}
