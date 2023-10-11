package com.portalClientesPrimadera.Integration.API;

import com.portalClientesPrimadera.model.itemEntityAPIInventory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiInventoryResponse {

    private List<itemEntityAPIInventory> items;
    public Long count;
    public Boolean hasMore;
    public Long limit;
    public Long offset;
    public List<LinksApiInventory> links;

    public List<itemEntityAPIInventory> getItems() {
        return items;
    }

    public void setItems(List<itemEntityAPIInventory> items) {
        this.items = items;
    }

}
