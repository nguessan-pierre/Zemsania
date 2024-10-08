package com.zemsania.franquicia.services;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public Producto addOrUpdateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Producto producto){
        productoRepository.delete(producto);
    }
}
