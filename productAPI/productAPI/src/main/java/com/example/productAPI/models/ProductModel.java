package com.example.productAPI.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel implements Serializable { // Serializable > os objetos dessa classe model podem ser transformados (serializada) e depois voltar ao normal (desserializada) para ser salvo em um arquivo ou enviado pela internet
    private static final long serialVersionUID = 1L; //Controle de versão para as classes quando forem serializadas

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;

    private String name;

    private BigDecimal value;


    // Getters e Setters
    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
