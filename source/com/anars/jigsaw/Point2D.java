package com.anars.jigsaw;

import java.text.DecimalFormat;

public class Point2D {

    private double _x;
    private double _y;
    // ---
    public Point2D(double x, double y) {
        setX(x);
        setY(y);
    }
    // ---
    public Point2D(int x, int y) {
        this((double)x, (double)y);
    }
    // ---
    public Point2D(float x, float y) {
        this((double)x, (double)y);
    }
    // ---
    public String toString() {
        return ((new DecimalFormat("0.#####")).format(_x) + "," + (new DecimalFormat("0.#####")).format(_y));
    }
    // ---
    public void setX(double x) {
        if(Double.isInfinite(x))
            throw new IllegalArgumentException("X Coordinate cannot be infinite.");
        if(Double.isNaN(x))
            throw new IllegalArgumentException("X Coordinate must be a number.");
        _x = x == 0.0 ? 0.0 : x;
    }
    // ---
    public void setX(int x) {
        setX((double)x);
    }
    // ---
    public void setX(float x) {
        setX((double)x);
    }
    // ---
    public void setY(double y) {
        if(Double.isInfinite(y))
            throw new IllegalArgumentException("Y Coordinate cannot be infinite.");
        if(Double.isNaN(y))
            throw new IllegalArgumentException("Y Coordinate must be a number.");
        _y = y == 0.0 ? 0.0 : y;
    }
    // ---
    public void setY(int y) {
        setY((double)y);
    }
    // ---
    public void setY(float y) {
        setY((double)y);
    }
    // ---
    public double getX() {
        return (_x);
    }
    // ---
    public double getXDouble() {
        return (_x);
    }
    // ---
    public int getXInt() {
        return ((new Double(_x)).intValue());
    }
    // ---
    public float getXFloat() {
        return ((new Double(_x)).floatValue());
    }
    // ---
    public long getXLong() {
        return ((new Double(_x)).longValue());
    }
    // ---
    public short getXShort() {
        return ((new Double(_x)).shortValue());
    }
    // ---
    public double getY() {
        return (_y);
    }
    // ---
    public double getYDouble() {
        return (_y);
    }
    // ---
    public int getYInt() {
        return ((new Double(_y)).intValue());
    }
    // ---
    public float getYFloat() {
        return ((new Double(_y)).floatValue());
    }
    // ---
    public long getYLong() {
        return ((new Double(_y)).longValue());
    }
    // ---
    public short getYShort() {
        return ((new Double(_y)).shortValue());
    }
    // ---
    public double distanceFromOrigin() {
        return distance(0.0, 0.0);
    }
    // ---
    public double distance(double x, double y) {
        return (Math.sqrt(Math.pow(_x - x, 2) + Math.pow(_y - y, 2)));
    }
    // ---
    public double distance(Point2D point) {
        return (distance(point.getX(), point.getY()));
    }
    // ---
    public double distance(int x, int y) {
        return (distance((double)x, (double)y));
    }
    // ---
    public double distance(long x, long y) {
        return (distance((double)x, (double)y));
    }
    // ---
    public double distance(short x, short y) {
        return (distance((double)x, (double)y));
    }
    // ---
    public double distance(float x, float y) {
        return (distance((double)x, (double)y));
    }
    // ---
    public Point2D midPoint(double x, double y) {
        return (new Point2D((x + _x) / 2, (y + _y) / 2));
    }
    // ---
    public Point2D midPoint(Point2D point) {
        return (midPoint(point.getX(), point.getY()));
    }
    // ---
    public Point2D midPoint(int x, int y) {
        return (midPoint((double)x, (double)y));
    }
    // ---
    public Point2D midPoint(long x, long y) {
        return (midPoint((double)x, (double)y));
    }
    // ---
    public Point2D midPoint(short x, short y) {
        return (midPoint((double)x, (double)y));
    }
    // ---
    public Point2D midPoint(float x, float y) {
        return (midPoint((double)x, (double)y));
    }
    // ---
    public Point2D add(double x, double y) {
        return (new Point2D(_x + x, _y + y));
    }
    // ---
    public Point2D add(Point2D point) {
        return (add(point.getX(), point.getY()));
    }
    // ---
    public Point2D add(double value) {
        return (add(value, value));
    }
    // ---
    public Point2D add(int x, int y) {
        return (add((double)x, (double)y));
    }
    // ---
    public Point2D add(int value) {
        return (add((double)value, (double)value));
    }
    // ---
    public Point2D add(float x, float y) {
        return (add((double)x, (double)y));
    }
    // ---
    public Point2D add(float value) {
        return (add((double)value, (double)value));
    }
    // ---
    public Point2D add(long x, long y) {
        return (add((double)x, (double)y));
    }
    // ---
    public Point2D add(long value) {
        return (add((double)value, (double)value));
    }
    // ---
    public Point2D add(short x, short y) {
        return (add((double)x, (double)y));
    }
    // ---
    public Point2D add(short value) {
        return (add((double)value, (double)value));
    }
    // ---
    public Point2D subtract(Point2D point) {
        return (subtract(point.getX(), point.getY()));
    }
    // ---
    public Point2D subtract(double x, double y) {
        return (new Point2D(_x - x, _y - y));
    }
    // ---
    public Point2D subtract(double value) {
        return (subtract(value, value));
    }
    // ---
    public Point2D subtract(int x, int y) {
        return (subtract((double)x, (double)y));
    }
    // ---
    public Point2D subtract(int value) {
        return (subtract((double)value, (double)value));
    }
    // ---
    public Point2D subtract(float x, float y) {
        return (subtract((double)x, (double)y));
    }
    // ---
    public Point2D subtract(float value) {
        return (subtract((double)value, (double)value));
    }
    // ---
    public Point2D subtract(long x, long y) {
        return (subtract((double)x, (double)y));
    }
    // ---
    public Point2D subtract(long value) {
        return (subtract((double)value, (double)value));
    }
    // ---
    public Point2D subtract(short x, short y) {
        return (subtract((double)x, (double)y));
    }
    // ---
    public Point2D subtract(short value) {
        return (subtract((double)value, (double)value));
    }
    // ---
    public Point2D normalize() {
        double distance = distanceFromOrigin();
        return (new Point2D(_x / distance, _y / distance));
    }
    // ---
    public Point2D divide(double x, double y) {
        return (new Point2D(_x / x, _y / y));
    }
    // ---
    public Point2D divide(Point2D point) {
        return (divide(point.getX(), point.getY()));
    }
    // ---
    public Point2D divide(double value) {
        return (divide(value, value));
    }
    // ---
    public Point2D divide(int x, int y) {
        return (divide((double)x, (double)y));
    }
    // ---
    public Point2D divide(int value) {
        return (divide((double)value, (double)value));
    }
    // ---
    public Point2D divide(float x, float y) {
        return (divide((double)x, (double)y));
    }
    // ---
    public Point2D divide(float value) {
        return (divide((double)value, (double)value));
    }
    // ---
    public Point2D divide(long x, long y) {
        return (divide((double)x, (double)y));
    }
    // ---
    public Point2D divide(long value) {
        return (divide((double)value, (double)value));
    }
    // ---
    public Point2D divide(short x, short y) {
        return (divide((double)x, (double)y));
    }
    // ---
    public Point2D divide(short value) {
        return (divide((double)value, (double)value));
    }
    // ---
    public Point2D multiply(double x, double y) {
        return (new Point2D(_x * x, _y * y));
    }
    // ---
    public Point2D multiply(Point2D point) {
        return (multiply(point.getX(), point.getY()));
    }
    // ---
    public Point2D multiply(double value) {
        return (multiply(value, value));
    }
    // ---
    public Point2D multiply(int x, int y) {
        return (multiply((double)x, (double)y));
    }
    // ---
    public Point2D multiply(int value) {
        return (multiply((double)value, (double)value));
    }
    // ---
    public Point2D multiply(float x, float y) {
        return (multiply((double)x, (double)y));
    }
    // ---
    public Point2D multiply(float value) {
        return (multiply((double)value, (double)value));
    }
    // ---
    public Point2D multiply(long x, long y) {
        return (multiply((double)x, (double)y));
    }
    // ---
    public Point2D multiply(long value) {
        return (multiply((double)value, (double)value));
    }
    // ---
    public Point2D multiply(short x, short y) {
        return (multiply((double)x, (double)y));
    }
    // ---
    public Point2D multiply(short value) {
        return (multiply((double)value, (double)value));
    }
}
