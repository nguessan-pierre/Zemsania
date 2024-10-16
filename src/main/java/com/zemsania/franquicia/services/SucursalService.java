package com.zemsania.franquicia.services;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {
    @Autowired
    SucursalRepository sucursalRepository;

    @Autowired
    ProductoService productoService;

    public boolean sucursalNotPresent(String idSucursal){
        return !sucursalRepository.existsById(Long.valueOf(idSucursal));
    }

    public List<Producto> findProductoListBySucursalId(String idSucursal){
        Sucursal sucursal = sucursalRepository.findById(Long.valueOf(idSucursal)).get();
        return sucursal.getProductList();
    }
    public Sucursal addOrUpdateSucursal(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }

    public Producto addProducto(String idSucursal, Producto producto){
        Sucursal sucursal = sucursalRepository.findById(Long.valueOf(idSucursal)).get();
        producto.setSucursal(sucursal);
        return productoService.addOrUpdateProducto(producto);
    }

    public Sucursal updateSucursalName(String idSucursal, String sucursalName){
        Sucursal sucursal = sucursalRepository.findById(Long.valueOf(idSucursal)).get();
        sucursal.setName(sucursalName);
        return addOrUpdateSucursal(sucursal);
    }

    public boolean productoNotPresent(String idProducto){
        return productoService.productoNotPresent(idProducto);
    }
    public boolean sucursalNotLinkedToProducto(String idSucursal, String idProducto){
        Producto producto = productoService.findProductoById(idProducto);
        return !producto.getSucursal().getId().equals(Long.valueOf(idSucursal));
    }

    public void deleteProducto(Producto producto) {
        productoService.deleteProducto(producto);
    }
}
