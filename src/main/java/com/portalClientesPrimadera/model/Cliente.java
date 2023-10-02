package com.portalClientesPrimadera.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

        private String accountName;
        private String custAccountId;
        private String partyId;

        public Cliente(String accountName, String custAccountId, String partyId) {
            this.accountName = accountName;
            this.custAccountId = custAccountId;
            this.partyId = partyId;
        }

        public String getAccountName() {
            return accountName;
        }

        public String getCustAccountId() {
            return custAccountId;
        }

        public String getPartyId() {
            return partyId;
        }
    }


