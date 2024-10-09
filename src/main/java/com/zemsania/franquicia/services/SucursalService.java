package com.zemsania.franquicia.services;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SucursalService {
    @Autowired
    SucursalRepository sucursalRepository;

    @Autowired
    ProductoService productoService;

    public boolean sucursalExists(String idSucursal){
        return sucursalRepository.existsById(Long.valueOf(idSucursal));
    }

    public Sucursal addSucursal(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }

    public Producto addProducto(String idSucursal, Producto producto){
        Sucursal sucursal = sucursalRepository.findById(Long.valueOf(idSucursal)).get();
        producto.setSucursal(sucursal);
        return productoService.addOrUpdateProducto(producto);
    }

    public void deleteProducto(Producto producto) {
        productoService.deleteProducto(producto);
    }
}
