package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sucursals")
public class SucursalController {
    @Autowired
    SucursalService sucursalService;

    @PostMapping("{id}")
    public ResponseEntity<Producto> addProducto(@PathVariable("id") String idSucursal, @RequestBody Producto producto){
        if(!sucursalService.sucursalExists(idSucursal)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalService.addProducto(idSucursal, producto));
    }

    //USing the idSucursal is useless in that case, should we remove the method in ProductoController ?
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable("id") String idSucursal, @RequestBody Producto producto){
        if(!sucursalService.sucursalExists(idSucursal)){
            return ResponseEntity.notFound().build();
        }
        sucursalService.deleteProducto(producto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
