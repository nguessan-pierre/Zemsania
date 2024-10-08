package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.services.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/franquicias")
public class FranquiciaController {

    @Autowired
    FranquiciaService franquiciaService;

    @GetMapping
    public List<Franquicia> findFranquiciaList(){
        return franquiciaService.findFranquiciaList();
    }

    @PostMapping
    public Franquicia addFranquicia(@RequestBody Franquicia franquicia){
        return franquiciaService.addFranquicia(franquicia);
    }

    @PostMapping("/{id}")
    public Sucursal addSucursalToFranquicia(@PathVariable("id") String idFranquicia, @RequestBody Sucursal sucursal){
        return franquiciaService.addSucursal(idFranquicia, sucursal);
    }

    @GetMapping("/stocks")
    public void findMaxStocksPerSucursal(){

    }
}
