package com.zemsania.franquicia.dtos;

import com.zemsania.franquicia.entities.Franquicia;
import com.zemsania.franquicia.entities.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranquiciaDto {
    private Long id;
    private String name;
    private List<String> sucursalList;

    public FranquiciaDto convertToFranquiciaDto(Franquicia franquicia){
        return new FranquiciaDto(franquicia.getId(),
                franquicia.getName(),
                franquicia.getSucursalList().stream()
                        .map(Sucursal::getName)
                        .collect(Collectors.toList()));
    }

    public List<FranquiciaDto> convertToFranquiciaDto(List<Franquicia> franquiciaList){
        return franquiciaList.stream().map(this::convertToFranquiciaDto).collect(Collectors.toList());
    }
}
