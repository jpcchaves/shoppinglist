package com.shoppinglist.shoppinglist.payload.dto.shoppingcart;

import jakarta.validation.constraints.NotBlank;

public class ShoppingCartRequestDto {
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    private String description;

    public ShoppingCartRequestDto() {
    }

    public ShoppingCartRequestDto(
            String name,
            String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
