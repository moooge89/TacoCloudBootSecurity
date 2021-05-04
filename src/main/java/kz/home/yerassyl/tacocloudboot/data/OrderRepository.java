package kz.home.yerassyl.tacocloudboot.data;

import kz.home.yerassyl.tacocloudboot.models.Order;

public interface OrderRepository {

    Order save(Order order);
}
