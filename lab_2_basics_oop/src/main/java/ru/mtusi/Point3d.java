package ru.mtusi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point3d {

    private double xCoord = 0.0d;
    private double yCoord = 0.0d;
    private double zCoord = 0.0d;

    public double distanceTo(Point3d point3d) {
        double distance = Math.sqrt(Math.pow((xCoord - point3d.getXCoord()), 2) +
                Math.pow((yCoord - point3d.getYCoord()), 2) +
                Math.pow((zCoord - point3d.getZCoord()), 2));
        return new BigDecimal(distance).setScale(2, RoundingMode.CEILING).doubleValue();
    }

}
