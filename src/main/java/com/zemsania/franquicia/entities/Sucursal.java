package com.zemsania.franquicia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue
    private Long id;
    public String name;

    @OneToMany(mappedBy="sucursal")
    public List<Producto> productList;

    @ManyToOne
    @JoinColumn(name="franquicia_id", nullable=false)
    private Franquicia franquicia;
}
