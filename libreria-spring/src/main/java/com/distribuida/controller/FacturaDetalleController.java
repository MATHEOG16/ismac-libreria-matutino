package com.distribuida.controller;

import com.distribuida.model.FacturaDetalle;
import com.distribuida.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturaDetalles")
public class FacturaDetalleController {

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    @GetMapping
    public ResponseEntity<List<FacturaDetalle>> findAll(){
        List<FacturaDetalle> facturaDetalles = facturaDetalleService.findAll();
        return ResponseEntity.ok(facturaDetalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalle> findOne(@PathVariable int id){
        FacturaDetalle facturaDetalle = facturaDetalleService.findOne(id);
        if (facturaDetalle == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaDetalle);
    }

    @PostMapping
    public ResponseEntity<FacturaDetalle> save(@RequestBody FacturaDetalle facturaDetalle){
        FacturaDetalle facturaDetalleNuevo = facturaDetalleService.save(facturaDetalle);
        return ResponseEntity.ok(facturaDetalleNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDetalle> update(@PathVariable int id, @PathVariable int idFactura, @PathVariable int idLibro, @RequestBody FacturaDetalle facturaDetalle){
        FacturaDetalle facturaDetalleActualizado = facturaDetalleService.update(id, idFactura, idLibro, facturaDetalle);
        if (facturaDetalleActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaDetalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        facturaDetalleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
