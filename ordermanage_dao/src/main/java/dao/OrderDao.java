package dao;

import domain.Orders;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface OrderDao {
    @Select("select * from orders")
    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "product",column = "productId",one = @One(select = "dao.ProductDao.findById"))
    })
    List<Orders> findAll();
}
