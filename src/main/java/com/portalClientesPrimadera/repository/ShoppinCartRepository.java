package com.portalClientesPrimadera.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.ShoppingCartEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ShoppinCartRepository extends JpaRepository <ShoppingCartEntity, Long> {
    @Query(value = "SELECT s FROM ShoppingCartEntity s WHERE s.cust_account_id = :Cust_account_id AND s.CP_user_id = :CP_user_id")
    public List<ShoppingCartEntity> findByCustAccountIdAndCpUserId(@Param("Cust_account_id") Long custAccountId, @Param("CP_user_id") Long cpUserId);

    @Query(value = "SELECT u, s, sle, i, p, a FROM ShoppingCartEntity s INNER JOIN UsersEntity u ON u.CP_user_id = s.CP_user_id INNER JOIN ShoppingCartLinesEntity sle ON s.CP_cart_id = sle.CP_cart_id INNER JOIN ItemsEntity i ON sle.inventory_item_id = i.inventory_item_id INNER JOIN PricesEntity p  ON i.inventory_item_id = p.Inventory_item_id AND u.party_id = p.Cust_account_id LEFT JOIN AvailabilityEntity a ON sle.inventory_item_id = a.inventory_item_id WHERE s.cust_account_id = :Cust_account_id AND s.CP_user_id = :CP_user_id ORDER BY sle.CP_cart_line_number")
    public List<ArrayList> findByshopintcartAndLinesCustAccountIdAndCpUserId(@Param("Cust_account_id") Long cust_account_id, @Param("CP_user_id") Long CP_user_id);
}
