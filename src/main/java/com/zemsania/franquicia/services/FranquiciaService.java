package com.zemsania.franquicia.services;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.repositories.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class FranquiciaService {
    @Autowired
    FranquiciaRepository franquiciaRepository;

    @Autowired
    SucursalService sucursalService;

    public boolean franquiciaExists(String idFranquicia){
        return franquiciaRepository.existsById(Long.valueOf(idFranquicia));
    }
    public List<Franquicia> findFranquiciaList(){
        return franquiciaRepository.findAll();
    }

    public Franquicia addFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public List<Sucursal> findSucursalListByFranquiciaId(String idFranquicia){
        return franquiciaRepository.findById(Long.valueOf(idFranquicia)).get().getSucursalList();
    }
    public Sucursal addSucursal(String idFranquicia, Sucursal sucursal) {
        Franquicia franquicia = franquiciaRepository.findById(Long.valueOf(idFranquicia)).get();
        sucursal.setFranquicia(franquicia);
        return sucursalService.addSucursal(sucursal);
    }

    public HashMap findMaxStockInSucursal(String idFranquicia){
        Franquicia franquicia = franquiciaRepository.findById(Long.valueOf(idFranquicia)).get();
        List<Sucursal> sucursalList = franquicia.getSucursalList();
        HashMap stockInSucursal = new HashMap<String, Producto>();
        sucursalList.forEach(sucursal -> stockInSucursal.put(sucursal.findMaxStock().getName(), sucursal.getName()));
        return stockInSucursal;
    }
}
