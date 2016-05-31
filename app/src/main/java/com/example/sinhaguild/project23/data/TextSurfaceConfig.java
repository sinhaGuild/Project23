package com.example.sinhaguild.project23.data;

import android.graphics.Color;

import java.util.Arrays;
import java.util.List;

import su.levenetc.android.textsurface.contants.Align;

/**
 * Created by anuragsinha on 16-05-30.
 */
public class TextSurfaceConfig {

    public static final List<Integer> TEXT_SIZES = Arrays.asList(
            30, 35, 40, 45, 50, 55, 60, 65);

    public static final List<Integer> TEXT_COLORS = Arrays.asList(
            Color.RED,
            Color.WHITE,
            Color.GRAY,
            Color.YELLOW);

    public static final List<Integer> TEXT_POSITION = Arrays.asList(
            Align.BOTTOM_OF,
            Align.RIGHT_OF);
    //Align.TOP_OF);

    public static final List<String> ANIMATIONS = Arrays.asList(
            "Shape",
            "Shape_long",
            "Rotate",
            "Rotate_clock",
            "SlideLeft",
            "SlideTop",
            "Circle");

}
