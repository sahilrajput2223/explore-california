package com.example.explore_california.models;

import lombok.Getter;

@Getter
public enum Region {

    Central_Coast("Central Coast"), Southern_California("Southern California"), Northern_California("Northern California"), Varies("Varies"), Napa_Sonoma_Counties("Napa/Sonoma Counties");

    private final String label;

    Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String byLabel) {
        for (Region r : Region.values()) {
            if (r.label.equalsIgnoreCase(byLabel)) return r;
        }
        return null;
    }

    public String getLabel() {
        return label;
    }
}
