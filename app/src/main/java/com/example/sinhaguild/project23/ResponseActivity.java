package com.example.sinhaguild.project23;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.example.sinhaguild.project23.data.TextSurfaceConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.AnimationsSet;
import su.levenetc.android.textsurface.animations.ChangeColor;
import su.levenetc.android.textsurface.animations.Circle;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Parallel;
import su.levenetc.android.textsurface.animations.Rotate3D;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.SideCut;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Direction;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.Side;

public class ResponseActivity {

    //fixed options
    public static final String TAG = "ResponseActivity";
    //list for words and Text objects for ANIMATIONS
    public ArrayList<String> wordArrayList = new ArrayList<>();
    public List<Text> textObjects = new ArrayList<>();
    public Random randomizer = new Random();
    public String example;
    Context context;
    //"Lorem Ipsum is simply dummy text of the printing and typesetting industry";


    public ResponseActivity(Context context) {
        this.context = context;
    }

    public ResponseActivity(Context context, String toTalk) {
        this.context = context;
        this.example = toTalk;
    }

    public void play(TextSurface textSurface, AssetManager assetManager, String example) {

        final Typeface robotoBlack = Typeface.createFromAsset(assetManager, "fonts/Roboto-Light.ttf");
        final Typeface gabrielle = Typeface.createFromAsset(assetManager, "fonts/Gabrielle.ttf");
        final Typeface cover = Typeface.createFromAsset(assetManager, "fonts/cover.ttf");

        /**
         * Initialize paint values for typefaces
         */
        Paint paint = new Paint();
        Paint paintGabrielle = new Paint();
        Paint paintCover = new Paint();

        paint.setAntiAlias(true);
        paintGabrielle.setAntiAlias(true);
        paintCover.setAntiAlias(true);

        paint.setTypeface(robotoBlack);
        paintGabrielle.setTypeface(gabrielle);
        paintCover.setTypeface(cover);


        /**
         * Build array of words from a string
         */
        buildWordsArray(example);

        for (int i = 0; i < wordArrayList.size(); i++) {
            Paint tempPaint;
            if (i == 0) {
                tempPaint = paintCover;
                Text temp = TextBuilder.
                        create(wordArrayList.get(i)).
                        setPaint(tempPaint).
                        setSize(TextSurfaceConfig.TEXT_SIZES.get(randomizer.nextInt(TextSurfaceConfig.TEXT_SIZES.size()))).
                        setAlpha(0).
                        setColor(TextSurfaceConfig.TEXT_COLORS.get(randomizer.nextInt(TextSurfaceConfig.TEXT_COLORS.size()))).
                        setPosition(Align.SURFACE_CENTER).build();
                textObjects.add(temp);
            } else if ((i % 2) == 0) {
                tempPaint = paint;
                Text temp = TextBuilder.
                        create(wordArrayList.get(i)).
                        setPaint(tempPaint).
                        setSize(TextSurfaceConfig.TEXT_SIZES.get(randomizer.nextInt(TextSurfaceConfig.TEXT_SIZES.size()))).
                        setAlpha(0).
                        setColor(TextSurfaceConfig.TEXT_COLORS.get(randomizer.nextInt(TextSurfaceConfig.TEXT_COLORS.size()))).
                        setPosition(TextSurfaceConfig.TEXT_POSITION.get(randomizer.nextInt(TextSurfaceConfig.TEXT_POSITION.size())), textObjects.get(i - 1)).build();
                textObjects.add(temp);
            } else {
                tempPaint = paintGabrielle;
                Text temp = TextBuilder
                        .create(wordArrayList.get(i))
                        .setPaint(tempPaint).
                                setSize(TextSurfaceConfig.TEXT_SIZES.get(randomizer.nextInt(TextSurfaceConfig.TEXT_SIZES.size()))).
                                setAlpha(0).
                                setColor(TextSurfaceConfig.TEXT_COLORS.get(randomizer.nextInt(TextSurfaceConfig.TEXT_COLORS.size()))).
                                setPosition(TextSurfaceConfig.TEXT_POSITION.get(randomizer.nextInt(TextSurfaceConfig.TEXT_POSITION.size())), textObjects.get(i - 1)).build();
                textObjects.add(temp);
            }
        }

        //Animate words
        talk(textSurface, textObjects);
    }

    public void talk(TextSurface surface, List<Text> textList) {

        List<AnimationsSet> anim = new ArrayList<>();

        for (int i = 0; i < textList.size(); i++) {
            String temp = TextSurfaceConfig.ANIMATIONS.get(randomizer.nextInt(TextSurfaceConfig.ANIMATIONS.size()));

            switch (temp) {
                case "Shape":
                    Sequential shape = new Sequential(
                            ShapeReveal.create(textList.get(i), 750, SideCut.show(Side.LEFT), false),
                            Delay.duration(500));
                    anim.add(shape);
                    Log.v(TAG, "Shape Triggered for " + textList.get(i).toString());
                    break;
                case "Rotate":
                    Parallel rot = new Parallel(
                            TransSurface.toCenter(textList.get(i), 500),
                            Rotate3D.showFromSide(textList.get(i), 750, Pivot.TOP),
                            Delay.duration(500));
                    Log.v(TAG, "Rotate Triggered for " + textList.get(i).toString());
                    anim.add(rot);
                    break;
                case "Rotate_clock":
                    Parallel clock = new Parallel(
                            TransSurface.toCenter(textList.get(i), 500),
                            Rotate3D.showFromCenter(textList.get(i), 750, Direction.CLOCK, Pivot.TOP),
                            Delay.duration(500));
                    Log.v(TAG, "Rotate Triggered for " + textList.get(i).toString());
                    anim.add(clock);
                    break;
                case "SlideLeft":
                    Parallel left = new Parallel(
                            new TransSurface(750, textList.get(i), Pivot.CENTER),
                            Slide.showFrom(Side.LEFT, textList.get(i), 750),
                            ChangeColor.to(textList.get(i), 750, Color.WHITE),
                            Delay.duration(500));
                    Log.v(TAG, "SlideLeft Triggered for " + textList.get(i).toString());
                    anim.add(left);
                    break;
                case "Circle":
                    Sequential circle = new Sequential(
                            ShapeReveal.create(textList.get(i), 500, Circle.show(Side.CENTER, Direction.OUT), false),
                            Delay.duration(500));
                    Log.v(TAG, "Circle Triggered for " + textList.get(i).toString());
                    anim.add(circle);
                    break;
                case "SlideTop":
                    Parallel top = new Parallel(
                            TransSurface.toCenter(textList.get(i), 500),
                            Slide.showFrom(Side.TOP, textList.get(i), 500),
                            Delay.duration(500));
                    Log.v(TAG, "SlideTop Triggered for " + textList.get(i).toString());
                    anim.add(top);
                    break;
                case "Shape_long":
                    Parallel cut = new Parallel(
                            new TransSurface(500, textList.get(i), Pivot.CENTER),
                            ShapeReveal.create(textList.get(i), 1300, SideCut.show(Side.LEFT), false),
                            Delay.duration(500));
                    Log.v(TAG, "Shape2 Triggered for " + textList.get(i).toString());
                    anim.add(cut);
                    break;
            }
        }

        /**
         * Generic signature is surface.play(new Sequential(AnimationsSet Object))
         * I will pass a list here built dynamically
         */

        surface.play(new Sequential(anim.toArray(new AnimationsSet[anim.size()])));

    }

    /**
     * Build array of words from supplied string
     *
     * @param sentence
     */
    public void buildWordsArray(String sentence) {
        wordArrayList = new ArrayList<String>();
        for (String word : sentence.split(" ")) {
            String temp = " " + word;
            wordArrayList.add(temp);
        }
        Log.v(TAG, wordArrayList.toString());
    }

}


//		textSurface.play(
//				new Sequential(ShapeReveal.create(one, 750, SideCut.show(Side.LEFT), false),
//						new Parallel(ShapeReveal.create(one, 600, SideCut.hide(Side.LEFT), false), new Sequential(Delay.duration(300), ShapeReveal.create(one, 600, SideCut.show(Side.LEFT), false))),
//						new Parallel(new TransSurface(500, two, Pivot.CENTER), ShapeReveal.create(two, 1300, SideCut.show(Side.LEFT), false)),
//						Delay.duration(500),
//						new Parallel(new TransSurface(750, three, Pivot.CENTER), Slide.showFrom(Side.LEFT, three, 750), ChangeColor.to(three, 750, Color.WHITE)),
//						Delay.duration(500),
//						new Parallel(TransSurface.toCenter(four, 500), Rotate3D.showFromSide(four, 750, Pivot.TOP)),
//						new Parallel(TransSurface.toCenter(five, 500), Slide.showFrom(Side.TOP, five, 500)),
//						new Parallel(TransSurface.toCenter(six, 750), Slide.showFrom(Side.LEFT, six, 500)),
//						Delay.duration(500),
//						new Parallel(new TransSurface(1500, nine, Pivot.CENTER), new Sequential(
//										new Sequential(ShapeReveal.create(seven, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
//										new Sequential(ShapeReveal.create(eight, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
//										new Sequential(ShapeReveal.create(nine, 500, Circle.show(Side.CENTER, Direction.OUT), false))
//								)
//						),
//						Delay.duration(200),
//						new Parallel(
//								ShapeReveal.create(seven, 1500, SideCut.hide(Side.LEFT), true),
//								new Sequential(Delay.duration(250), ShapeReveal.create(eight, 1500, SideCut.hide(Side.LEFT), true)),
//								new Sequential(Delay.duration(500), ShapeReveal.create(nine, 1500, SideCut.hide(Side.LEFT), true)),
//								Alpha.hide(six, 1500),
//								Alpha.hide(five, 1500)
//						)
//				)
//		);
