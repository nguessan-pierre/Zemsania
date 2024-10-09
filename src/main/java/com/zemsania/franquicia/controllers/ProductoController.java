package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findProductList(){
        return ResponseEntity.ok(productoService.findProductoList());
    }

    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.addOrUpdateProducto(producto));
    }
}
