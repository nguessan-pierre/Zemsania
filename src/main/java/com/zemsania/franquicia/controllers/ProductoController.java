package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @Operation(summary = "Return a list of all products linked to a sucursal")
    @GetMapping
    public ResponseEntity<List<Producto>> findProductList(){
        return ResponseEntity.ok(productoService.findProductoList());
    }

    @Operation(summary = "Update the information of a saved product in the database")
    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.addOrUpdateProducto(producto));
    }
}
