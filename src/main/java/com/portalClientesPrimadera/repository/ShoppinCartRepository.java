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
}
