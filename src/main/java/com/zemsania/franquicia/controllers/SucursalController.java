package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.services.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sucursals")
public class SucursalController {
    @Autowired
    SucursalService sucursalService;

    @Operation(summary = "Add a product in the database linked to an existing sucursal")
    @PostMapping("{id}")
    public ResponseEntity<Producto> addProducto(@PathVariable("id") @Parameter(name = "id", description = "sucursal's id") String idSucursal, @RequestBody Producto producto){
        if(sucursalService.sucursalNotPresent(idSucursal)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalService.addProducto(idSucursal, producto));
    }

    @Operation(summary = "Get a list of every product stored in a sucursal")
    @GetMapping("{id}/stocks")
    public ResponseEntity<List<Producto>> findProductoListBySucursalId(@PathVariable("id") @Parameter(name = "id", description = "sucursal's id") String idSucursal){
        if(sucursalService.sucursalNotPresent(idSucursal)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalService.findProductoListBySucursalId(idSucursal));
    }

    @Operation(summary = "Update a sucursal name")
    @PostMapping("/{id}/name")
    public ResponseEntity<Sucursal> updateSucursalName(@PathVariable("id") @Parameter(name = "id", description = "sucursal's id") String idSucursal, @RequestBody String sucursalName){
        if(sucursalService.sucursalNotPresent(idSucursal)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalService.updateSucursalName(idSucursal, sucursalName));
    }

    @Operation(summary = "Delete an exisiting product linked to a sucursal")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable("id") @Parameter(name = "id", description = "sucursal's id") String idSucursal, @RequestBody Producto producto){
        if(sucursalService.sucursalNotPresent(idSucursal)){
            return ResponseEntity.notFound().build();
        }
        if(sucursalService.productoNotPresent(String.valueOf(producto.getId()))){
            return ResponseEntity.notFound().build();
        }
        if(sucursalService.sucursalNotLinkedToProducto(idSucursal, producto.getId().toString())){
            return ResponseEntity.badRequest().build();
        }
        sucursalService.deleteProducto(producto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
