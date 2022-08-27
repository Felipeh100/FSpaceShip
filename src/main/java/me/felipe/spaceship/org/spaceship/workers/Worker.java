package me.felipe.spaceship.org.spaceship.workers;

import lombok.Getter;

public class Worker {

    @Getter private String name;
    @Getter private double price;

    public Worker(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
    }

    public static class Builder {
        private String name;
        private double price;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Worker build() {
            return new Worker(this);
        }
    }
}
