package com.zemsania.franquicia.services;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.repositories.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranquiciaService {
    @Autowired
    FranquiciaRepository franquiciaRepository;

    @Autowired
    SucursalService sucursalService;

    public List<Franquicia> findFranquiciaList(){
        return franquiciaRepository.findAll();
    }

    public Franquicia addFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public Sucursal addSucursal(String idFranquicia, Sucursal sucursal) {
        Optional<Franquicia> franquiciaOptional = franquiciaRepository.findById(Long.valueOf(idFranquicia));
        if (franquiciaOptional.isEmpty()){
            throw new NullPointerException("franquicia doesn't exist");
        }
        sucursal.setFranquicia(franquiciaOptional.get());
        return sucursalService.addSucursal(sucursal);
    }
}
