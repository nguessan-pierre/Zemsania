package com.zemsania.franquicia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int stock;

    @ManyToOne
    @JoinColumn(name="sucursal_id", nullable=false)
    private Sucursal sucursal;
}
