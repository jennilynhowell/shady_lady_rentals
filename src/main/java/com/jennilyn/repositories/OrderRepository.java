package com.jennilyn.repositories;

import com.jennilyn.models.Order;
import com.jennilyn.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    //TODO make this work
    //List<Order> findAllByUser(long userId);
}
