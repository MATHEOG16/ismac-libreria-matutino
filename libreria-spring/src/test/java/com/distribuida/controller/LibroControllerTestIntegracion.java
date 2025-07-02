package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
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
@WebMvcTest(LibroController.class)
public class LibroControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws Exception {
        Libro libro = new Libro(1,"El final", "Twil", 200, "Aniversario", "Español", new Date(),"Un cataclismo se dio en el mundo", "Pasta dura", "054866255", 20, "Especial", "Fisico", 30.00, new Categoria(), new Autor());

        Mockito.when(libroService.findAll()).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("El final")
        );
    }


    @Test
    public void testSave() throws Exception {
        Libro libro = new Libro(0,"El fin", "Twil", 150, "Aniversario", "Español", new Date(),"Un cataclismo se dio en el mundo", "Pasta dura", "054866255", 20, "Especial", "Fisico", 30.00, new Categoria(), new Autor());

        Mockito.when(libroService.save(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro))
                )       .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("El fin")
        );
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/libros/2")).andExpect(status().isNoContent());
    }
}
