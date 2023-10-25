package com.portalClientesPrimadera.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

        private String accountName;
        private String custAccountId;
        private String partyId;
        private String paymentTerms;
        private String transactionalCurrencyCode;

        public Cliente(String accountName, String custAccountId, String partyId, String paymentTerms, String transactionalCurrencyCode) {
            this.accountName = accountName;
            this.custAccountId = custAccountId;
            this.partyId = partyId;
            this.paymentTerms = paymentTerms;
            this.transactionalCurrencyCode = transactionalCurrencyCode;
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


