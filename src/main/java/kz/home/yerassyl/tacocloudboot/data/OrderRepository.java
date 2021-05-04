package kz.home.yerassyl.tacocloudboot.data;

import kz.home.yerassyl.tacocloudboot.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {}
