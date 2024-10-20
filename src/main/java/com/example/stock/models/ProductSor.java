package com.example.stock.models;


import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class ProductSor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le nom de la pièce est obligatoire")
    private String name;
    @NotBlank(message = "La référence est obligatoire")
    private String ref;
    @NotBlank(message = "Le nom de la machine est obligatoire")
    private String nameMach;
    @NotBlank(message = "La référence de la machine est obligatoire")
    private String refMach; 
    @NotBlank(message = "Le nom de mécanicien est obligatoire")
    private String Mecan; 
    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

}
