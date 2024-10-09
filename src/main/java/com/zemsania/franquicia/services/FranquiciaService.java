package com.zemsania.franquicia.services;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.repositories.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FranquiciaService {
    @Autowired
    FranquiciaRepository franquiciaRepository;

    @Autowired
    SucursalService sucursalService;

    public boolean franquiciaNotPresent(String idFranquicia){
        return !franquiciaRepository.existsById(Long.valueOf(idFranquicia));
    }

    public Franquicia updateFranquiciaName(String idFranquicia, String franquiciaName){
        Franquicia franquicia = franquiciaRepository.findById(Long.valueOf(idFranquicia)).get();
        franquicia.setName(franquiciaName);
        return addOrUpdateFranquicia(franquicia);
    }

    public List<Franquicia> findFranquiciaList(){
        return franquiciaRepository.findAll();
    }

    public Franquicia addOrUpdateFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public List<Sucursal> findSucursalListByFranquiciaId(String idFranquicia){
        return franquiciaRepository.findById(Long.valueOf(idFranquicia)).get().getSucursalList();
    }
    public Sucursal addSucursal(String idFranquicia, Sucursal sucursal) {
        Franquicia franquicia = franquiciaRepository.findById(Long.valueOf(idFranquicia)).get();
        sucursal.setFranquicia(franquicia);
        return sucursalService.addOrUpdateSucursal(sucursal);
    }

    public HashMap<String,String> findMaxStockInSucursal(String idFranquicia){
        Franquicia franquicia = franquiciaRepository.findById(Long.valueOf(idFranquicia)).get();
        List<Sucursal> sucursalList = franquicia.getSucursalList();
        HashMap<String, String> stockInSucursal = new HashMap<>();
        sucursalList.forEach(sucursal -> stockInSucursal.put(sucursal.findMaxStock().getName(), sucursal.getName()));
        return stockInSucursal;
    }
}
