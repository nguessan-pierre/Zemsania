package com.zemsania.franquicia.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Franquicia {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy="franquicia")
    private List<Sucursal> sucursalList;

}
