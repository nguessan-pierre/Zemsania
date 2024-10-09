package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.services.FranquiciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/*
actualizar el nombre de una franquicia
acutalizar el nombre de une sucursal
actualizar el nombre de un producto
 */

@RestController
@RequestMapping("/v1/franquicias")
public class FranquiciaController {

    @Autowired
    FranquiciaService franquiciaService;

    @Operation(summary = "Return a list of all franquicia")
    @GetMapping
    public ResponseEntity<List<Franquicia>> findFranquiciaList(){
        return ResponseEntity.ok(franquiciaService.findFranquiciaList());
    }

    @Operation(summary = "Save in the database a franquicia")
    @PostMapping
    public ResponseEntity<Franquicia> addFranquicia(@RequestBody Franquicia franquicia){
        return ResponseEntity.ok(franquiciaService.addOrUpdateFranquicia(franquicia));
    }

    @Operation(summary = "Return a list of all sucursals linked to a franquicia")
    @GetMapping("/{id}")
    public ResponseEntity<List<Sucursal>> findSucursalListByFranquiciaId(@PathVariable("id") @Parameter(name = "id", description = "franquicia's id") String idFranquicia){
        if(franquiciaService.franquiciaNotPresent(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquiciaService.findSucursalListByFranquiciaId(idFranquicia));
    }

    @Operation(summary = "Add a sucursal to an exisiting franquicia")
    @PostMapping("/{id}")
    public ResponseEntity<Sucursal> addSucursalToFranquicia(@PathVariable("id") @Parameter(name = "id", description = "franquicia's id") String idFranquicia, @RequestBody Sucursal sucursal){
        if(franquiciaService.franquiciaNotPresent(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquiciaService.addSucursal(idFranquicia, sucursal));
    }

    @Operation(summary = "Update a franquicia name")
    @PostMapping("/{id}/name")
    public ResponseEntity<Franquicia> updateFranquiciaName(@PathVariable("id") @Parameter(name = "id", description = "franquicia's id") String idFranquicia, @RequestBody String franquiciaName){
        if(franquiciaService.franquiciaNotPresent(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquiciaService.updateFranquiciaName(idFranquicia, franquiciaName));
    }

    @Operation(summary = "Return a list of each products stored in the most important quantity in each sucursal of a franquicia")
    @GetMapping("/{id}/stocks")
    public ResponseEntity<HashMap> findMaxStocksPerSucursal(@PathVariable("id") @Parameter(name = "id", description = "franquicia's id") String idFranquicia){
        if(franquiciaService.franquiciaNotPresent(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquiciaService.findMaxStockInSucursal(idFranquicia));
    }
}
