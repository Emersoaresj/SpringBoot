package com.example.productAPI.controllers;

import com.example.productAPI.dtos.ProductRecordDto;
import com.example.productAPI.models.ProductModel;
import com.example.productAPI.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RestController // Para indicar que tem implementação API REST
public class ProductController {

    @Autowired
    ProductRepository productRepository; //Para acesso aos métodos JPA quando houver necessidade

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){ //Vou passar um productRecordDto com validação e no corpo certo pq vem em Json
        var productModel = new ProductModel(); //criando uma instância do BD
        BeanUtils.copyProperties(productRecordDto, productModel); // Convertendo o DTO para o BD
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel)); //Retornando a resposta CRIADO + enviando no corpo da respota oq foi salvo (body.(productModel > ID, name, value)) + salvando os dados no BD

    }

    //mudança
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(name = "id")UUID id){ //Object pq vamos ter dois tipos de retorno no método (retorne diferentes tipos de objetos)
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") UUID id,
                                                 @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = productO.get(); //Não instanciando um novo (igual post) porque já existe. Então precisamos considerar o ID e não criar um novo
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id")UUID id){
        Optional<ProductModel> productO = productRepository.findById(id); //Optional porque pode ou não vir vazio (não sabemos se o ID existe)
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }


}

















