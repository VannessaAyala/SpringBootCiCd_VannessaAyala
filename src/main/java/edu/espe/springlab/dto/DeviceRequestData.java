package edu.espe.springlab.dto;

import jakarta.validation.constraints.*;

public class DeviceRequestData {

    @NotBlank @Size(min = 2, max = 120)
    private String name;

    @NotBlank @Size(max = 120)
    private String serial;

    @NotBlank @Size(max = 120)
    private String category;

    @Min(0)
    private Integer stock;

    public @NotBlank @Size(min = 2, max = 120) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 2, max = 120) String name) {
        this.name = name;
    }

    public @NotBlank @Size(max = 120) String getSerial() {
        return serial;
    }

    public void setSerial(@NotBlank @Size(max = 120) String serial) {
        this.serial = serial;
    }

    public @NotBlank @Size(max = 120) String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank @Size(max = 120) String category) {
        this.category = category;
    }

    public @Min(0) Integer getStock() {
        return stock;
    }

    public void setStock(@Min(0) Integer stock) {
        this.stock = stock;
    }
}