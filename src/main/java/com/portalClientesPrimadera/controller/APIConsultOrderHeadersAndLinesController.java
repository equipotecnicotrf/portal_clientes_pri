package com.portalClientesPrimadera.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portalClientesPrimadera.Integration.API.APIConsultOrderHeaders;
import com.portalClientesPrimadera.Integration.API.APIConsultOrderHeadersResponse;
import com.portalClientesPrimadera.Integration.API.APIConsultOrderLines;
import com.portalClientesPrimadera.Integration.API.APIConsultOrderLinesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class APIConsultOrderHeadersAndLinesController {

    APIConsultOrderHeaders apiConsultOrderHeaders;
    APIConsultOrderLines apiConsultOrderLines;

    @Autowired
    public APIConsultOrderLinesController(APIConsultOrderHeaders apiConsultOrderHeaders, APIConsultOrderLines apiConsultOrderLines) {
        this.apiConsultOrderHeaders = apiConsultOrderHeaders;
        this.apiConsultOrderLines = apiConsultOrderLines;
    }

    @GetMapping("/getOrdersLinesAndHeaders")
    public ResponseEntity<APIConsultOrderLinesResponse[][]> getOrdersLinesAndHeaders(
            @RequestParam(name = "buyingPartyId") Long buyingPartyId,
            @RequestParam(name = "transactionTypeCode") String transactionTypeCode,
            @RequestParam(name = "creationDate") String creationDate,
            @RequestParam(name = "statusCode") String statusCode) {
        try {
            ResponseEntity<APIConsultOrderHeadersResponse[]> headersResponse = apiConsultOrderHeaders.getOrdersHeaders(
                    buyingPartyId,
                    transactionTypeCode,
                    creationDate,
                    statusCode
            );

            if (headersResponse.getStatusCode() == HttpStatus.OK) {
                APIConsultOrderHeadersResponse[] headers = headersResponse.getBody();

                if (headers.length > 0) {
                    ResponseEntity<APIConsultOrderLinesResponse[][]> linesResponse = apiConsultOrderLines.getOrdersLinesAndHeadres(headers);

                    if (linesResponse.getStatusCode() == HttpStatus.OK) {
                        APIConsultOrderLinesResponse[][] lines = linesResponse.getBody();

                        for (int i = 0; i < lines.length; i++) {
                            APIConsultOrderHeadersResponse header = headers[i];
                            APIConsultOrderLinesResponse[] linesForHeader = lines[i];

                            System.out.println("Encabezado para OrderKey: " + header.getOrderKey());

                            if (linesForHeader != null) {
                                // Iterar a través de las líneas de este encabezado
                                for (APIConsultOrderLinesResponse line : linesForHeader) {
                                    System.out.println("Linea ID: " + line.getFulfillLineId());
                                    System.out.println("Source Transaction Number: " + line.getSourceTransactionNumber());
                                    System.out.println("Ordered Quantity: " + line.getOrderedQuantity());
                                    System.out.println("Requested Ship Date: " + line.getRequestedShipDate());
                                    System.out.println("Unit List Price: " + line.getUnitListPrice());
                                    System.out.println("Unit Selling Price: " + line.getUnitSellingPrice());
                                    System.out.println();
                                }
                            } else {
                                System.err.println("No se encontraron líneas para este encabezado.");
                            }
                        }
                    } else {
                        System.err.println("Error en la consulta de líneas: " + linesResponse.getStatusCode());
                    }
                } else {
                    System.err.println("No se encontraron encabezados para consultar líneas.");
                }
            } else {
                System.err.println("Error en la consulta de encabezados: " + headersResponse.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // Devuelve una respuesta apropiada aquí según tus necesidades
        return ResponseEntity.ok(null);
    }

}
