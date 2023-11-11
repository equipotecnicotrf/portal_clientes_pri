package com.portalClientesPrimadera.controller;


import com.portalClientesPrimadera.model.IVAEntity;
import com.portalClientesPrimadera.model.ItemsEntity;
import com.portalClientesPrimadera.model.PricesEntity;
import com.portalClientesPrimadera.model.UsersEntity;
import com.portalClientesPrimadera.repository.IVARepository;
import com.portalClientesPrimadera.repository.ItemsRepository;
import com.portalClientesPrimadera.repository.PricesRepository;
import com.portalClientesPrimadera.repository.UsersRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.portalClientesPrimadera.Integration.API.APIPostSalesOrderForPrices.postSalesOrderForPrices;

@RestController
@RequestMapping("/api/v1")
public class APIPricesController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private PricesRepository pricesRepository;

    @GetMapping("/generatePrices")
    public IVAEntity generatePrices() throws JSONException {
        List<Long> users = usersRepository.findDistinctpartyid();
        List<ItemsEntity> items = itemsRepository.findByItemsPrices();
        //Borrar tabla antes de registrar-
        pricesRepository.deleteAll();

        int count = 0;//para ciclos de users
        int count2 = 0;//para ciclos de items

        //Para controlar los ciclos de users
        for (Long user : users) {
            //if (count == 1){
            //    break;
            //}
            count++;
            //para controlar los ciclos de items
            for (ItemsEntity item : items) {
                //if (count2 == 1000){
                   // break;
                //}
                count2++;
                JSONObject result = postSalesOrderForPrices (user, item.getInventory_item_id());

                if (result != null) {
                    //if (result.has("chargeComponent")) {

                    //JSONArray chargeComponents = result.getJSONArray("chargeComponents");

                    // Verificar si chargeComponents no está vacío
                    //if (chargeComponents.length() > 0) {
                    PricesEntity priceEntity = new PricesEntity();
                    priceEntity.setCust_account_id(user);
                    priceEntity.setInventory_item_id(item.getInventory_item_id());
                    priceEntity.setOrganization_id(result.optLong("InventoryOrganizationId"));
                    priceEntity.setCurrency_code(result.optString("CurrencyCode"));
                    priceEntity.setUnit_price(result.optDouble("Value"));
                    //priceEntity.setCP_IVA_id(2L);

                    // Guardar el PriceEntity en la base de datos
                    pricesRepository.save(priceEntity);

                }
            }
        }
        return null;
    }

}
