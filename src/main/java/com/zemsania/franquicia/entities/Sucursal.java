package com.zemsania.franquicia.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
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

    @JsonManagedReference
    @OneToMany(mappedBy="sucursal")
    public List<Producto> productList;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="franquicia_id", nullable=false)
    private Franquicia franquicia;

    /**
     *
     * @return the product which is stored the most in the sucursal
     * Doesn't take into account that many products can have the same stock in that case it will only return the first result.
     */
    public Producto findMaxStock(){
        return getProductList().stream().max(Comparator.comparing(Producto::getStock)).orElseThrow();
    }
}
