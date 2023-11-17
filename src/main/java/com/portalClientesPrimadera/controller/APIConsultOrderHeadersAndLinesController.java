package com.portalClientesPrimadera.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portalClientesPrimadera.Integration.API.*;
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

    @Autowired
    APIConsultOrderHeaders apiConsultOrderHeaders;
    @Autowired
    APIConsultOrderLines apiConsultOrderLines;
    @Autowired
    APIConsultOrderByOrderNumber apiConsultOrderByOrderNumber;


    @GetMapping("/getOrdersLinesAndHeaders")
    public ResponseEntity<APIConsultOrderLinesResponse[][]> getOrdersLinesAndHeaders(
            @RequestParam(name = "buyingPartyId") Long buyingPartyId,
            @RequestParam(name = "transactionTypeCode") String transactionTypeCode,
            @RequestParam(name = "creationDateFrom") String creationDateFrom,
            @RequestParam(name = "creationDateTo") String creationDateTo
            )
             {
        try {
            ResponseEntity<APIConsultOrderHeadersResponse[]> headersResponse = apiConsultOrderHeaders.getOrdersHeaders(
                    buyingPartyId,
                    transactionTypeCode,
                    creationDateFrom,
                    creationDateTo
            );

            if (headersResponse.getStatusCode() == HttpStatus.OK) {
                APIConsultOrderHeadersResponse[] headers = headersResponse.getBody();

                if (headers.length > 0) {
                    ResponseEntity<APIConsultOrderLinesResponse[][]> linesResponse = apiConsultOrderLines.getOrdersLinesAndHeadres(headers);

                    if (linesResponse.getStatusCode() == HttpStatus.OK) {
                        // Devuelve directamente la respuesta del servicio de líneas
                        return linesResponse;
                    } else {
                        System.err.println("Error en la consulta de líneas: " + linesResponse.getStatusCode());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                    }
                } else {
                    System.err.println("No se encontraron encabezados para consultar líneas.");
                }
            } else {
                System.err.println("Error en la consulta de encabezados: " + headersResponse.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error en la deserialización JSON: " + e.getMessage());
        }

        // En caso de error, puedes devolver una respuesta con un mensaje de error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("/getOrderByOrderNumber")
    public ResponseEntity<APIConsultOrderLinesResponse[][]> getOrderByOrderNumber(
            @RequestParam(name = "orderNumber") String orderNumber,
            @RequestParam(name = "buyingPartyId") Long buyingPartyId
    ){
        try {
            ResponseEntity<APIConsultOrderHeadersResponse[]> headersResponse = apiConsultOrderByOrderNumber.getOrderByNumberOrder(
                    orderNumber,
                    buyingPartyId
            );

            if (headersResponse.getStatusCode() == HttpStatus.OK) {
                APIConsultOrderHeadersResponse[] headers = headersResponse.getBody();

                if (headers.length > 0) {
                    ResponseEntity<APIConsultOrderLinesResponse[][]> linesResponse = apiConsultOrderLines.getOrdersLinesAndHeadres(headers);

                    if (linesResponse.getStatusCode() == HttpStatus.OK) {
                        // Devuelve directamente la respuesta del servicio de líneas
                        return linesResponse;
                    } else {
                        System.err.println("Error en la consulta de líneas: " + linesResponse.getStatusCode());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                    }
                } else {
                    System.err.println("No se encontraron encabezados para consultar líneas.");
                }
            } else {
                System.err.println("Error en la consulta de encabezados: " + headersResponse.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error en la deserialización JSON: " + e.getMessage());
        }

        // En caso de error, puedes devolver una respuesta con un mensaje de error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


}
