package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sucursals")
public class SucursalController {
    @Autowired
    SucursalService sucursalService;

    @PostMapping("{id}")
    public Producto addProducto(@PathVariable("id") String idSucursal, @RequestBody Producto producto){
        return sucursalService.addProducto(idSucursal, producto);
    }

    @DeleteMapping("{id}")
    public void deleteProducto(@PathVariable("id") String idSucursal, @RequestBody Producto producto){
        sucursalService.deleteProducto(idSucursal, producto);
    }

}
