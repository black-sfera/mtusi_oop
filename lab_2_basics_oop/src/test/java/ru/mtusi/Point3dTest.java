package ru.mtusi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест класса Point3d, который должен ")
public class Point3dTest {

    private double coordX;
    private double coordY;
    private double coordZ;

    private Point3d basePoint;

    @BeforeEach
    public void SetUp() {
        basePoint = new Point3d();
    }

    private static double[][] dataSetForDistanceTo() {
        return new double[][] {{1.0d, 2.0d, 2.0d}, {-1.0d, -2.0d, -2.0d}, {0.0d, 0.0d, 0.0d}, {0.0d, 0.0d , 3.0d}};
    }

    @ParameterizedTest
    @MethodSource("dataSetForDistanceTo")
    @DisplayName("выводить расстояние между двумя точками.")
    public void distanceTo(double[] coords) {
        coordX = coords[0];
        coordY = coords[1];
        coordZ = coords[2];
        double expected = new BigDecimal(Math.sqrt(coordX*coordX + coordY*coordY + coordZ*coordZ))
                .setScale(2, RoundingMode.CEILING).doubleValue();
        Point3d testPoint = new Point3d(coordX, coordY, coordZ);

        double result = basePoint.distanceTo(testPoint);

        assertEquals(expected, result);
    }

}
