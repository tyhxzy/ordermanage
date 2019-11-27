package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.OrderDao;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    public PageInfo<Orders> findAll(int pageNum,int pageSize){
        // 查询之前说明页数和每页条数
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> all = orderDao.findAll();
        // 查询后将结果封装到PageInfo中
        return new PageInfo<>(all);
    }
    public Orders findDesc(String id){
        return orderDao.findById(id);
    }
}
