package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.FacturaDetalleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;

//import static java.lang.reflect.Array.get;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(FacturaDetalleController.class)
public class FacturaDetalleControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturaDetalleService facturaDetalleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws Exception {
        FacturaDetalle facturaDetalle = new FacturaDetalle(1, 2, 40.00, new Factura(), new Libro());

        Mockito.when(facturaDetalleService.findAll()).thenReturn(List.of(facturaDetalle));

        mockMvc.perform(get("/api/facturaDetalles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value(2)
        );
    }


    @Test
    public void testSave() throws Exception {
        FacturaDetalle facturaDetalle = new FacturaDetalle(0, 4, 80.00, new Factura(), new Libro());

        Mockito.when(facturaDetalleService.save(any(FacturaDetalle.class))).thenReturn(facturaDetalle);

        mockMvc.perform(post("/api/facturaDetalles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(facturaDetalle))
                )       .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(4)
        );
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/facturaDetalles/2")).andExpect(status().isNoContent());
    }
}
