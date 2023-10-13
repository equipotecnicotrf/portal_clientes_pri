package com.portalClientesPrimadera.Integration.soap;

import com.portalClientesPrimadera.model.ItemReporte;
import com.portalClientesPrimadera.model.ItemsEntity;
import com.portalClientesPrimadera.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ItemService {

    @Autowired
    private ItemsRepository itemsRepository;

    public void guardarItems(List<ItemReporte> itemReportes){
        List<ItemsEntity> itemsEntities = convertirAEntities(itemReportes);
        itemsRepository.saveAll(itemsEntities);
    }

    private List<ItemsEntity> convertirAEntities(List<ItemReporte> itemReportes){
        List<ItemsEntity> itemsEntities = new ArrayList();

        for (ItemReporte itemReporte : itemReportes){
            ItemsEntity itemEntity = new ItemsEntity();

            itemEntity.setInventory_item_id(Long.parseLong(itemReporte.getInventoryItemId()));
            itemEntity.setItem_number(itemReporte.getItemNumber());
            itemEntity.setItem_description(itemReporte.getItemDescription());

            if (itemReporte.getItemDescriptionLong() != null && !itemReporte.getItemDescriptionLong().isEmpty()) {
                itemEntity.setItem_description_long(itemReporte.getItemDescriptionLong());
            } else {
                // Proporcionar un valor predeterminado si es nulo o vacío
                itemEntity.setItem_description_long("Valor por default");
            }

            itemEntity.setItem_description_long(itemReporte.getItemDescriptionLong());
            itemEntity.setUom_code(itemReporte.getUOMCode());
            itemEntity.setUnit_of_measure(itemReporte.getUnitOfMeasure());
            itemEntity.setAtribute1(itemReporte.getAttribute1());
            itemEntity.setAtribute2(itemReporte.getAttribute2());
            itemEntity.setAtribute3(itemReporte.getAttribute3());
            itemEntity.setAtribute4(itemReporte.getAttribute4());
            itemEntity.setAtribute5(itemReporte.getAttribute5());
            itemEntity.setAtribute6(itemReporte.getAttribute6());
            itemEntity.setAtribute7(itemReporte.getAttribute7());
            try {
                if (!itemReporte.getAttribute8().isEmpty()) {
                    itemEntity.setAtribute8(Double.parseDouble(itemReporte.getAttribute8()));
                } else {
                    itemEntity.setAtribute8(0.0); // Otra opción sería usar null en lugar de 0.0
                }
            } catch (NumberFormatException | NullPointerException e) {
                // Handle invalid values or log the error
                itemEntity.setAtribute8(0.0); // You can set a default value or handle it according to your requirements
            }

            try {
                if (!itemReporte.getAttribute9().isEmpty()) {
                    itemEntity.setAtribute9(Double.parseDouble(itemReporte.getAttribute9()));
                } else {
                    itemEntity.setAtribute9(0.0); // Otra opción sería usar null en lugar de 0.0
                }
            } catch (NumberFormatException | NullPointerException e) {
                // Handle invalid values or log the error
                itemEntity.setAtribute9(0.0); // You can set a default value or handle it according to your requirements
            }

            itemsEntities.add(itemEntity);
        }

        return itemsEntities;

    }

}
