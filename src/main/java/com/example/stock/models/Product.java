package com.example.stock.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la pièce est obligatoire")
    private String name;

    @NotBlank(message = "La référence est obligatoire")
    private String ref;

    @NotBlank(message="le nom du fournisseur est obligatoire")
    private String fourniss;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Double prixunitaire;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité doit être égale ou supérieure à zéro")
    private Integer qt;

    public void decrementQuantity(int quantity) {
        this.qt -= quantity;
    }
}
