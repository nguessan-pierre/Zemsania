package com.zemsania.franquicia.controllers;

import com.zemsania.franquicia.dtos.FranquiciaDto;
import com.zemsania.franquicia.dtos.SucursalDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/franquicias")
public class FranquiciaController {

    @Autowired
    FranquiciaService franquiciaService;

    FranquiciaDto franquiciaDto = new FranquiciaDto();
    SucursalDto sucursalDto = new SucursalDto();

    @Operation(summary = "Return a list of all franquicia")
    @GetMapping
    public ResponseEntity<List<FranquiciaDto>> findFranquiciaList(){
        List<Franquicia> franquiciasList = franquiciaService.findFranquiciaList();
        return ResponseEntity.ok(franquiciaDto.convertToFranquiciaDto(franquiciasList));
    }

    @Operation(summary = "Create a new franquicia in the database")
    @PostMapping
    public ResponseEntity<Franquicia> addFranquicia(@RequestBody Franquicia franquicia){
        return ResponseEntity.ok(franquiciaService.addOrUpdateFranquicia(franquicia));
    }

    @Operation(summary = "Return a list of all sucursals linked to a franquicia")
    @GetMapping("/{id}")
    public ResponseEntity<List<SucursalDto>> findSucursalListByFranquiciaId(@PathVariable("id") @Parameter(name = "id", description = "franquicia's id") String idFranquicia){
        if(franquiciaService.franquiciaNotPresent(idFranquicia)){
            return ResponseEntity.notFound().build();
        }
        List<Sucursal> sucursalList = franquiciaService.findSucursalListByFranquiciaId(idFranquicia);
        return ResponseEntity.ok(sucursalDto.convertToSucursalDto(sucursalList));
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
