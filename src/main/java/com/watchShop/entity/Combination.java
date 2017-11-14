package com.watchShop.entity;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
public class Combination {
    private WatchBelt watchBelt;
    private Watch watch;
    private double price;

    public Combination(WatchBelt watchBelt, Watch watch) {
        this.watchBelt = watchBelt;
        this.watch = watch;
        this.price = watch.getPrice() + watchBelt.getPrice();
    }

    public WatchBelt getWatchBelt() {
        return watchBelt;
    }

    public void setWatchBelt(WatchBelt watchBelt) {
        this.watchBelt = watchBelt;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
