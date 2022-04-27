package com.backend.coding.challenge.utils.geometry;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Point {
    @NonNull
    private final Double latitude;
    @NonNull
    private final Double longitude;

    public Double calculateDistance(Point otherPoint) {
        double a = latitude - otherPoint.latitude;
        double b = longitude - otherPoint.longitude;
        return Math.hypot(a, b);
    }

}
