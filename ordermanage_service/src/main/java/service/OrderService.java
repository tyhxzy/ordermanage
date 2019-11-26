package service;

import dao.OrderDao;
import domain.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderDao orderDao;
    public List<Orders> findAll(){
        return orderDao.findAll();
    }
}
