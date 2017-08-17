package com.anars.jigsaw;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Calendar;
import java.util.Date;

public class Generator {

    public Generator(int columns, int rows, int dpi, double radiusx, double radiusy, File output) {
        super();
        PrintWriter printWriter = null;
        int width = columns * dpi;
        int height = rows * dpi;
        try {
            printWriter = new PrintWriter(output);
            printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            printWriter.println("\n<!-- Generated by JigsawGenerator\n v1.0 on " + (new Date()) + " -->");
            printWriter.println("<!-- Copyright (c) " + Calendar.getInstance().get(Calendar.YEAR) + " Anar Software LLC. - http://anars.com -->");
            printWriter.println("\n<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + width + "\" height=\"" + height + "\">");
            printWriter.println("\t<g id=\"jigsaw\">");
            printWriter.println("\t\t<rect x=\"0\" y=\"0\" rx=\"" + radiusx + "\" ry=\"" + radiusy + "\" width=\"" + width + "\" height=\"" + height + "\" fill=\"none\" stroke=\"#000000\"/>");
            for(int row = 0; row < rows - 1; row++)
                for(int column = 0; column < columns; column++) {
                    Point2D pieceStart = new Point2D(column * width / columns, (row + 1) * height / rows);
                    Point2D pieceEnd = new Point2D((column + 1) * width / columns, (row + 1) * height / rows);
                    tab(printWriter, pieceStart, pieceEnd);
                }
            for(int row = 0; row < rows; row++)
                for(int column = 0; column < columns - 1; column++) {
                    Point2D pieceStart = new Point2D((column + 1) * width / columns, row * height / rows);
                    Point2D pieceEnd = new Point2D((column + 1) * width / columns, (row + 1) * height / rows);
                    tab(printWriter, pieceStart, pieceEnd);
                }
            printWriter.println("\t</g>");
            printWriter.println("</svg>");
        }
        catch(IOException ioException) {}
        finally {
            if(printWriter != null)
                printWriter.close();
        }
    }
    // ---
    private void tab(PrintWriter printWriter, Point2D pieceStart, Point2D pieceEnd) {
        printWriter.print("\t\t<polyline fill=\"none\" stroke=\"#000000\" stroke-width=\"1\" points=\"" + pieceStart);
        //
        double pieceLenght = pieceStart.distance(pieceEnd);
        //
        Point2D pieceDifference = pieceEnd.subtract(pieceStart);
        //
        Point2D roundedTabCenter = pieceStart.add(pieceDifference.multiply(0.4 + Math.random() * 0.2));
        //
        Point2D normalized = pieceDifference.divide(pieceDifference.distanceFromOrigin());
        //
        int tabDirection = Math.random() >= 0.5 ? 1 : -1;
        //
        Point2D otherAxis = (new Point2D(-1 * normalized.getY(), normalized.getX())).multiply(tabDirection);
        //
        Point2D tabStart = roundedTabCenter.subtract(normalized.multiply(pieceLenght * 0.1));
        Point2D tabEnd = roundedTabCenter.add(normalized.multiply(pieceLenght * 0.1));
        //
        double smallRadius = pieceLenght * (0.05 + Math.random() * 0.01);
        double largeRadius = smallRadius * 1.8;
        //
        double triangleBase = tabStart.distance(tabEnd) / 2;
        double triangleHypotenuse = smallRadius + largeRadius;
        double triangleHeight = Math.sqrt(Math.pow(triangleHypotenuse, 2) - Math.pow(triangleBase, 2));
        //
        double largeCenterDistance = smallRadius + triangleHeight;
        //
        double smallStartAngle = -2 * Math.PI / 4;
        double smallEndAngle = Math.asin(triangleHeight / triangleHypotenuse);
        double largeSliceAngle = Math.asin(triangleBase / triangleHypotenuse);
        double largeStartAngle = 2 * Math.PI * 3 / 4 - largeSliceAngle;
        double largeEndAngle = smallStartAngle + largeSliceAngle;
        printWriter.print(" " + tabStart);
        circle(printWriter, normalized, otherAxis, tabStart.add(otherAxis.multiply(smallRadius)), smallRadius, smallStartAngle, smallEndAngle);
        circle(printWriter, normalized, otherAxis, roundedTabCenter.add(otherAxis.multiply(largeCenterDistance)), largeRadius, largeStartAngle, largeEndAngle);
        circle(printWriter, normalized.multiply(-1.0), otherAxis, tabEnd.add(otherAxis.multiply(smallRadius)), smallRadius, smallEndAngle, smallStartAngle);
        printWriter.print(" " + tabEnd);
        printWriter.print(" " + pieceEnd);
        printWriter.println("\"/>");
    }
    // ---
    private void circle(PrintWriter printWriter, Point2D normalized, Point2D otherAxis, Point2D center, double radius, double startAngle, double endAngle) {
        double angleSpan = endAngle - startAngle;
        int segments = (int)(Math.ceil(20 * Math.abs(angleSpan) / (Math.PI * 2)));
        for(int index = 0; index <= segments; index++) {
            double theta = startAngle + angleSpan * index / segments;
            Point2D point = center.add(normalized.multiply(Math.cos(theta) * radius).add(otherAxis.multiply(Math.sin(theta) * radius)));
            printWriter.print(" " + point);
        }
    }
    // ---
    public static void main(String[] args) {
        int columns = 2;
        int rows = 2;
        int dpi = 96;
        double radiusx = 10.0;
        double radiusy = 10.0;
        File path = new File(System.getProperty("user.dir"));
        String filename = "jigsaw.svg";
        for(int index = 0; index < args.length; index++) {
            String values[] = args[index].split("=");
            if(values[0].equals("-columns")) {
                try {
                    columns = Integer.parseInt(values[1]);
                    if(columns < 1) {
                        System.err.println(values[0] + " value should be a positive integer number.");
                        System.exit(-1);
                    }
                }
                catch(Exception exception) {
                    System.err.println(values[0] + " value should be a positive integer number.");
                    System.exit(-2);
                }
            }
            else if(values[0].equals("-rows")) {
                try {
                    rows = Integer.parseInt(values[1]);
                    if(rows < 1) {
                        System.err.println(values[0] + " value should be a positive integer number.");
                        System.exit(-3);
                    }
                }
                catch(Exception exception) {
                    System.err.println(values[0] + " value should be a positive integer number.");
                    System.exit(-4);
                }
            }
            else if(values[0].equals("-dpi")) {
                try {
                    dpi = Integer.parseInt(values[1]);
                    if(dpi < 1) {
                        System.err.println(values[0] + " value should be a positive integer number.");
                        System.exit(-5);
                    }
                }
                catch(Exception exception) {
                    System.err.println(values[0] + " value should be a positive integer number.");
                    System.exit(-6);
                }
            }
            //     horizontal corner radius of the rect. ry Determines the horizontal corner radius of the rect.
            else if(values[0].equals("-radiusx")) {
                try {
                    radiusx = Double.parseDouble(values[1]);
                    if(dpi < 1) {
                        System.err.println(values[0] + " value should be a positive decimal number.");
                        System.exit(-5);
                    }
                }
                catch(Exception exception) {
                    System.err.println(values[0] + " value should be a positive decimal number.");
                    System.exit(-6);
                }
            }
            else if(values[0].equals("-radiusy")) {
                try {
                    radiusy = Double.parseDouble(values[1]);
                    if(dpi < 1) {
                        System.err.println(values[0] + " value should be a positive decimal number.");
                        System.exit(-5);
                    }
                }
                catch(Exception exception) {
                    System.err.println(values[0] + " value should be a positive decimal number.");
                    System.exit(-6);
                }
            }
            else if(values[0].equals("-path")) {
                try {
                    path = new File(values[1]);
                    if(!path.isDirectory()) {
                        System.err.println("-path value \"" + values[1] + "\" is not directory.");
                        System.exit(-5);
                    }
                    if(!path.exists()) {
                        System.err.println("-path value \"" + values[1] + "\" is not exists.");
                        System.exit(-5);
                    }
                }
                catch(Exception exception) {
                    System.err.println("Invalid -path value.");
                    System.exit(-9999);
                }
            }
            else if(values[0].equals("-filename")) {
                if(values[1].trim().equals("")) {
                    System.err.println("Invalid -filename value.");
                    System.exit(-9999);
                }
            }
            else if(values[0].equals("-help")) {
                System.out.println("\nJigsawGenerator version 1.0\n" + //
                    "Copyright (c) " + Calendar.getInstance().get(Calendar.YEAR) + " Anar Software LLC. < http://anars.com >\n\n" + //
                    "This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.\n\n" + //
                    "This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.\n" + //
                    "See the GNU General Public License for more details at http://www.gnu.org/licenses\n\n" + //
                    "USAGE:\n" + //
                    "\tjava -jar JigsawGenerator.jar [OPTIONS]...\n\n" + //
                    "DESCRIPTION:\n" + //
                    "\tAll parameters are optional.\n\n" + //
                    "-columns=[NUMBER]\n" + //
                    "\t. Default value is 2.\n" + //
                    "\tE.g. -columns=4\n\n" + //
                    "-rows=[NUMBER]\n" + //
                    "\t. Default value is 2.\n" + //
                    "\tE.g. -rows=4\n\n" + //
                    "-help\n" + //
                    "\tDisplays this help\n");
                System.exit(0);
            }
            else {
                System.err.println("Unknown parameter \"" + args[index] + "\"");
                System.exit(-9999);
            }
        }
        new Generator(columns, rows, dpi, radiusx, radiusy, new File(path, filename));
    }
}