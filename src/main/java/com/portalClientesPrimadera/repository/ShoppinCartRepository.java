package com.portalClientesPrimadera.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.ShoppingCartEntity;

@Repository
public interface ShoppinCartRepository extends JpaRepository <ShoppingCartEntity, Long> {
   /*  @Query(value = "SELECT a.SITE_USE_ID, a.CUST_ACCOUNT_ID, u.CUST_ACCOUNT_ID FROM ADDRESSES a JOIN USERS u ON a.CUST_ACCOUNT_ID = u.CUST_ACCOUNT_ID", nativeQuery = true)
public ArrayList<AddressesEntity> FindBySite();*/
}
