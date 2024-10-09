package com.zemsania.franquicia.services;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.entities.Sucursal;
import com.zemsania.franquicia.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> findProductoList(){
        return productoRepository.findAll();
    }

    public boolean productoNotPresent(String idProducto){
        return !productoRepository.existsById(Long.valueOf(idProducto));
    }

    public Producto addOrUpdateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Producto producto){
        productoRepository.delete(producto);
    }

    public Producto updateProductoName(String idProducto, String productoName) {
        Producto producto = productoRepository.findById(Long.valueOf(idProducto)).get();
        producto.setName(productoName);
        return addOrUpdateProducto(producto);
    }
}
