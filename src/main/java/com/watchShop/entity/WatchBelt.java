package com.watchShop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
@Entity
public class WatchBelt {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String title;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Material material;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @NotNull
    private double price;

    public WatchBelt() {
    }

    public WatchBelt(String title, Material material, double price) {
        this.title = title;
        this.material = material;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
