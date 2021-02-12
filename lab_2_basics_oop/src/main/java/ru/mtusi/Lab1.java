package ru.mtusi;

import java.util.ArrayList;

public class Lab1 {

    public static void main(String[] args) {
        ArrayList<Point3d> point3dList = toPoint3d(args);
        for (int i = 0; i < point3dList.size(); i = i + 3) {
            System.out.println("The area of a triangle of points: " +
                    point3dList.get(i).toString() + ", " +
                    point3dList.get(i+1).toString() + ", " +
                    point3dList.get(i+2).toString() + " = " +
                    computeArea(point3dList.get(i), point3dList.get(i+1), point3dList.get(i+2)) + ".");
        }
    }

    public static ArrayList<Point3d> toPoint3d(String[] args) {
        if (args.length % 9 != 0) {
            throw new IllegalArgumentException("From the entered numbers, you will not be able to create Point3d in the number of instances that is a multiple of 3.");
        }
        ArrayList<Point3d> point3dArrayList = new ArrayList<>();
        for (int i = 0; i < 9; i = i + 3) {
            double x = Double.parseDouble(args[i]);
            double y = Double.parseDouble(args[i+1]);
            double z = Double.parseDouble(args[i+2]);

            point3dArrayList.add(new Point3d(x, y, z));
        }
        return point3dArrayList;
    }

    public static double computeArea (Point3d point1, Point3d point2, Point3d point3) {
        if (point1.equals(point2) || point1.equals(point3) || point2.equals(point3)) {
            throw new IllegalArgumentException("Calculation is not possible for these points. Some of these points are equal.");
        }
        double side12 = point1.distanceTo(point2);
        double side13 = point1.distanceTo(point3);
        double side23 = point2.distanceTo(point3);
        double p = ( side12 + side13 + side23 ) / 2;
        return Math.sqrt( p * (p - side12) * (p - side13) * (p - side23) );
    }

}
