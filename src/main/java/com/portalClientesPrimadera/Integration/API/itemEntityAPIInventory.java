package com.portalClientesPrimadera.Integration.API;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portalClientesPrimadera.Integration.API.ContextApiInventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class itemEntityAPIInventory {

    public Long ItemNumber;
    public String PrimaryUOMCode;
    public String OrganizationCode;
    public String SummaryLevel;
    public Long InventoryItemId;
    public Long OrganizationId;
    public String Revision;
    public String SubinventoryGroup;
    public String SubinventoryCode;
    public Long LocatorId;
    public String OwningPartySiteId;
    public Long PrimaryQuantity;
    public String CreationDate;
    public Long ConsignedQuantity;
    public String CategoryId;
    public String LastUpdateDate;
    public String Category;
    public String OwningPartySite;
    public String OwningPartyId;
    public String OwningParty;
    public String Locator;
    public String PrimaryUnitOfMeasure;
    public Long MaterialStatusId;
    public String MaterialStatus;
    @JsonProperty("@context")
    public ContextApiInventory context;


}
