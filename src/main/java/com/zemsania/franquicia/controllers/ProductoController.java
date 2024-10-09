package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @Operation(summary = "Return a list of all products registereds")
    @GetMapping
    public ResponseEntity<List<Producto>> findProductList(){
        return ResponseEntity.ok(productoService.findProductoList());
    }

    @Operation(summary = "Update the information of a saved product in the database")
    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.addOrUpdateProducto(producto));
    }

    @Operation(summary = "Update a producto name")
    @PostMapping("/{id}/name")
    public ResponseEntity<Producto> updateProductoName(@PathVariable("id") @Parameter(name = "id", description = "producto's id") String idProducto, @RequestBody String productoName){
        if(productoService.productoNotPresent(idProducto)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoService.updateProductoName(idProducto, productoName));
    }

    @Operation(summary = "Update a producto stock")
    @PostMapping("/{id}/stock")
    public ResponseEntity<Producto> updateProductoStock(@PathVariable("id") @Parameter(name = "id", description = "producto's id") String idProducto, @RequestBody int productoStock){
        if(productoService.productoNotPresent(idProducto)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoService.updateProductoStock(idProducto, productoStock));
    }
}
