package com.renwfy.readingdiary.utils;

import android.graphics.Color;

/**
 * Created by LSD on 2017/6/13.
 */

public class ColorShades {

    private int mFromColor;
    private int mToColor;
    private float mShade;

    public ColorShades setFromColor(int fromColor) {
        this.mFromColor = fromColor;
        return this;
    }

    public ColorShades setToColor(int toColor) {
        this.mToColor = toColor;
        return this;
    }

    public ColorShades setFromColor(String fromColor) {
        this.mFromColor = Color.parseColor(fromColor);
        return this;
    }

    public ColorShades setToColor(String toColor) {
        this.mToColor = Color.parseColor(toColor);
        return this;
    }

    public ColorShades forLightShade(int color) {
        setFromColor(Color.WHITE);
        setToColor(color);
        return this;
    }

    public ColorShades forDarkShare(int color) {
        setFromColor(color);
        setToColor(Color.BLACK);
        return this;
    }

    public ColorShades setShade(float shade) {
        this.mShade = shade;
        return this;
    }

    /**
     * Generates the shade for the given color.
     *
     * @return the int value of the shade.
     */
    public int generate() {
        int fromR = Color.red(mFromColor);
        int fromG = Color.green(mFromColor);
        int fromB = Color.blue(mFromColor);
        int fromAlp = Color.alpha(mFromColor);

        int toR = Color.red(mToColor);
        int toG = Color.green(mToColor);
        int toB = Color.blue(mToColor);
        int toAlp = Color.alpha(mToColor);

        int diffR = toR - fromR;
        int diffG = toG - fromG;
        int diffB = toB - fromB;
        int diffAlp = toAlp - fromAlp;

        int red = fromR + (int) ((diffR * mShade));
        int green = fromG + (int) ((diffG * mShade));
        int blue = fromB + (int) ((diffB * mShade));
        int alpha = fromAlp + (int) ((diffAlp * mShade));

        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Assumes the from and to color are inverted before generating the shade.
     *
     * @return the int value of the inverted shade.
     */
    public int generateInverted() {
        int fromR = Color.red(mFromColor);
        int fromG = Color.green(mFromColor);
        int fromB = Color.blue(mFromColor);
        int fromAlp = Color.alpha(mFromColor);

        int toR = Color.red(mToColor);
        int toG = Color.green(mToColor);
        int toB = Color.blue(mToColor);
        int toAlp = Color.alpha(mToColor);

        int diffR = toR - fromR;
        int diffG = toG - fromG;
        int diffB = toB - fromB;
        int diffAlp = toAlp - fromAlp;

        int red = toR - (int) ((diffR * mShade));
        int green = toG - (int) ((diffG * mShade));
        int blue = toB - (int) ((diffB * mShade));
        int alpha = toAlp - (int) ((diffAlp * mShade));
        return Color.argb(alpha,red, green, blue);
    }

    /**
     * Gets the String equivalent of the generated shade
     *
     * @return String value of the shade
     */
    public String generateInvertedString() {
        return String.format("#%06X", 0xFFFFFF & generateInverted());
    }

    /**
     * Gets the inverted String equivalent of the generated shade
     *
     * @return String value of the shade
     */
    public String generateString() {
        return String.format("#%06X", 0xFFFFFF & generate());
    }
}
