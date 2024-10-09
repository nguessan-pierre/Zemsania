package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.services.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/franquicias")
public class FranquiciaController {

    @Autowired
    FranquiciaService franquiciaService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Franquicia>> findFranquiciaList(){
        return ResponseEntity.ok(franquiciaService.findFranquiciaList());
    }

    @PostMapping
    public ResponseEntity<Franquicia> addFranquicia(@RequestBody Franquicia franquicia){
        return ResponseEntity.ok(franquiciaService.addFranquicia(franquicia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Sucursal>> findSucursalListByFranquiciaId(@PathVariable("id") String idFranquicia){
        if(!franquiciaService.franquiciaExists(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquiciaService.findSucursalListByFranquiciaId(idFranquicia));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Sucursal> addSucursalToFranquicia(@PathVariable("id") String idFranquicia, @RequestBody Sucursal sucursal){
        if(!franquiciaService.franquiciaExists(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquiciaService.addSucursal(idFranquicia, sucursal));
    }

    @GetMapping("/{id}/stocks")
    public ResponseEntity<HashMap> findMaxStocksPerSucursal(@PathVariable("id") String idFranquicia){
        if(!franquiciaService.franquiciaExists(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquiciaService.findMaxStockInSucursal(idFranquicia));
    }
}
