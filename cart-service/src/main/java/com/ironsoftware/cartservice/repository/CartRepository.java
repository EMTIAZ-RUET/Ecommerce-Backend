package com.ironsoftware.cartservice.repository;

import com.ironsoftware.cartservice.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
    Optional<Cart> findByUserIdAndActive(String userId, boolean active);
}
