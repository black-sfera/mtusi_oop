package ru.mtusi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест класса Lab1, который должен ")
public class Lab1Test {

    private static final double COORD_1 = 0.0d;
    private static final double COORD_2 = 1.0d;
    private static final double COORD_3 = -1.0d;
    private static final double COORD_4 = 0.5d;
    private static final double COORD_5 = -1.5d;
    private static final double COORD_6 = 0.01d;
    private static final double COORD_7 = 0.25d;
    private static final double COORD_8 = -3.0d;
    private static final double COORD_9 = -0.36d;

    private Point3d point1;
    private Point3d point2;
    private Point3d point3;

    @BeforeEach
    public void SetUp() {
        point1 = new Point3d();
        point2 = new Point3d();
        point3 = new Point3d();
    }

    private static ArrayList<Point3d>[] dataSetForComputeArea() {
        ArrayList<Point3d> array1 = new ArrayList<>();
        array1.add(new Point3d(COORD_1, COORD_2, COORD_1));
        array1.add(new Point3d(COORD_3, COORD_3, COORD_1));
        array1.add(new Point3d(COORD_2, COORD_2, COORD_3));
        ArrayList<Point3d> array2 = new ArrayList<>();
        array2.add(new Point3d(COORD_4, COORD_6, COORD_7));
        array2.add(new Point3d(COORD_2, COORD_5, COORD_9));
        array2.add(new Point3d(COORD_8, COORD_4, COORD_5));
        ArrayList<Point3d> array3 = new ArrayList<>();
        array3.add(new Point3d(COORD_5, COORD_4, COORD_7));
        array3.add(new Point3d(COORD_9, COORD_6, COORD_3));
        array3.add(new Point3d(COORD_9, COORD_1, COORD_2));
        return new ArrayList[] {array1, array2, array3};
    }

    @Test
    @DisplayName("выдавать ошибку при попытке ввести две одинаковые точки.")
    public void computeAreaWithEqualsPoint() {
        assertThrows(IllegalArgumentException.class, () -> Lab1.computeArea(point1, point2, point3), "Calculation is not possible for these points. Some of these points are equal.");
    }

    @ParameterizedTest
    @MethodSource("dataSetForComputeArea")
    @DisplayName("выдавать площадь треугольника по трём точкам.")
    public void computeArea(ArrayList<Point3d> point3ds) {
        point1 = point3ds.get(0);
        point2 = point3ds.get(1);
        point3 = point3ds.get(2);
        double p = ( point1.distanceTo(point2) + point1.distanceTo(point3) + point2.distanceTo(point3) ) / 2;
        double expected = Math.sqrt( p * (p - point1.distanceTo(point2)) * (p - point1.distanceTo(point3)) * (p - point2.distanceTo(point3)) );

        double result = Lab1.computeArea(point1, point2, point3);

        assertEquals(expected, result);
    }

}
