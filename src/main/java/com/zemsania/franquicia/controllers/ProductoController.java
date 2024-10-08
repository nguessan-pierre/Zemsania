package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PutMapping
    public Producto updateProducto(@RequestBody Producto producto){
        return productoService.addOrUpdateProducto(producto);
    }
}
