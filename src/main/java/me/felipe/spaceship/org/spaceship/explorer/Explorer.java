package me.felipe.spaceship.org.spaceship.explorer;

import org.bukkit.Location;

public class Explorer {

    private String id;


    private double fuel;
    private double drops;
    private boolean activated;

    private Location location;

    public Explorer(Builder builder) {
        this.id = builder.id;
        this.fuel = builder.fuel;
        this.drops = builder.drops;
        this.activated = builder.activated;
    }

    public static class Builder {

        private String id;

        private double fuel;
        private double drops;
        private boolean activated;

        public Explorer build() {
            return new Explorer(this);
        }
    }
}
