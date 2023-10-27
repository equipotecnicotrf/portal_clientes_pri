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

    @Query(value = "SELECT u, s, sle, i, p  FROM ShoppingCartEntity s, UsersEntity u, ShoppingCartLinesEntity sle, ItemsEntity i , PricesEntity p  WHERE u.CP_user_id = s.CP_user_id AND s.CP_cart_id = sle.CP_cart_id AND sle.inventory_item_id = i.inventory_item_id AND i.inventory_item_id = p.Inventory_item_id and u.party_id = p.Cust_account_id AND s.cust_account_id = :Cust_account_id AND s.CP_user_id = :CP_user_id")
    public List<ArrayList> findByshopintcartAndLinesCustAccountIdAndCpUserId(@Param("Cust_account_id") Long cust_account_id, @Param("CP_user_id") Long CP_user_id);
}
