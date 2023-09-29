package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.model.AuditEntity;
import com.portalClientesPrimadera.repository.AuditRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuditsController.class)
public class AuditsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditRepository auditRepository;

    @Test
    public void testGetAuditListWhenCalledThenReturnListOfAuditEntities() throws Exception {
        // Arrange
        AuditEntity audit1 = new AuditEntity();
        AuditEntity audit2 = new AuditEntity();
        List<AuditEntity> auditList = Arrays.asList(audit1, audit2);
        when(auditRepository.findAll()).thenReturn(auditList);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/audits"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").exists());
    }
}