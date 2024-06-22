package com.example.productAPI.repositories;

import com.example.productAPI.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> { //Qual a entidade que esse repositório irá contemplar + qual é o tipo do ID




}