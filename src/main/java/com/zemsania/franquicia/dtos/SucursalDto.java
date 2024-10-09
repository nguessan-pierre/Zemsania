package com.zemsania.franquicia.dtos;

import com.zemsania.franquicia.entities.Producto;
import com.zemsania.franquicia.entities.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDto {
    private Long id;
    private String name;
    private List<String> productList;

    public SucursalDto convertToSucursalDto(Sucursal sucursal){
        return new SucursalDto(sucursal.getId(),
                sucursal.getName(),
                sucursal.getProductList().stream()
                        .map(Producto::getName)
                        .collect(Collectors.toList()));
    }

    public List<SucursalDto> convertToSucursalDto(List<Sucursal> sucursalList){
        return sucursalList.stream().map(this::convertToSucursalDto).collect(Collectors.toList());

    }
}
