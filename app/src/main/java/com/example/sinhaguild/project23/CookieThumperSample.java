package com.example.sinhaguild.project23;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * Created by Eugene Levenetc.
 */
public class CookieThumperSample {

    //fixed options
    public static final String TAG = "CookieThumperSample";
    public static final List<Integer> sizes = Arrays.asList(30, 40, 50, 60);
    public static final List<Integer> colors = Arrays.asList(Color.RED, Color.WHITE, Color.YELLOW, Color.MAGENTA, Color.BLUE);
    public static final List<Integer> position = Arrays.asList(Align.BOTTOM_OF, Align.RIGHT_OF);
    public static final List<String> animations = Arrays.asList("Shape", "Shape2", "Rotate", "SlideLeft", "SlideTop", "Circle");

    public String example = "Lorem Ipsum is simply dummy text of the printing and typesetting industry";
    //list for words and Text objects for animations
    public ArrayList<String> wordArrayList = new ArrayList<>();
    public List<Text> textObjects = new ArrayList<>();

    public Random randomizer = new Random();
    Context context;


    public CookieThumperSample(Context context) {
        this.context = context;
    }

    public CookieThumperSample(Context context, String toTalk) {
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
            if ((i % 2) == 0 && i != 0) {
                tempPaint = paint;
                Text temp = TextBuilder.
                        create(wordArrayList.get(i)).
                        setPaint(tempPaint).
                        setSize(sizes.get(randomizer.nextInt(sizes.size()))).
                        setAlpha(0).
                        setColor(colors.get(randomizer.nextInt(colors.size()))).
                        setPosition(position.get(randomizer.nextInt(position.size())), textObjects.get(i - 1)).build();
                textObjects.add(temp);
            } else if (i == 0) {
                tempPaint = paintCover;
                Text temp = TextBuilder.
                        create(wordArrayList.get(i)).
                        setPaint(tempPaint).
                        setSize(sizes.get(randomizer.nextInt(sizes.size()))).
                        setAlpha(0).
                        setColor(colors.get(randomizer.nextInt(colors.size()))).
                        setPosition(Align.SURFACE_CENTER).build();
                textObjects.add(temp);
            } else {
                tempPaint = paintGabrielle;
                Text temp = TextBuilder
                        .create(wordArrayList.get(i))
                        .setPaint(tempPaint).
                                setSize(sizes.get(randomizer.nextInt(sizes.size()))).
                                setAlpha(0).
                                setColor(colors.get(randomizer.nextInt(colors.size()))).
                                setPosition(position.get(randomizer.nextInt(position.size())), textObjects.get(i - 1)).build();
                textObjects.add(temp);
            }
        }

        //Animate words
        talk(textSurface, textObjects);
    }

    public void talk(TextSurface surface, List<Text> textList) {

        List<AnimationsSet> anim = new ArrayList<>();

        for (int i = 0; i < textList.size(); i++) {
            String temp = animations.get(randomizer.nextInt(animations.size()));

            switch (temp) {
                case "Shape":
                    Sequential seq = new Sequential(
                            ShapeReveal.create(textList.get(i), 750, SideCut.show(Side.LEFT), false),
                            Delay.duration(500));
                    anim.add(seq);
                    Log.v(TAG, "Shape Triggered for "+textList.get(i).toString());
                    break;
                case "Rotate":
                    Parallel pl = new Parallel(
                            TransSurface.toCenter(textList.get(i), 500),
                            Rotate3D.showFromSide(textList.get(i), 750, Pivot.TOP),
                            Delay.duration(500));
                    Log.v(TAG, "Rotate Triggered for "+textList.get(i).toString());
                    anim.add(pl);
                    break;
                case "SlideLeft":
                    Parallel pl2 = new Parallel(
                            new TransSurface(750, textList.get(i), Pivot.CENTER),
                            Slide.showFrom(Side.LEFT, textList.get(i), 750),
                            ChangeColor.to(textList.get(i), 750, Color.WHITE),
                            Delay.duration(500));
                    Log.v(TAG, "SlideLeft Triggered for "+textList.get(i).toString());
                    anim.add(pl2);
                    break;
                case "Circle":
                    Sequential seq2 = new Sequential(
                            ShapeReveal.create(textList.get(i), 500, Circle.show(Side.CENTER, Direction.OUT), false),
                            Delay.duration(500));
                    Log.v(TAG, "Circle Triggered for "+textList.get(i).toString());
                    anim.add(seq2);
                    break;
                case "SlideTop":
                    Parallel pl3 = new Parallel(
                            TransSurface.toCenter(textList.get(i), 500),
                            Slide.showFrom(Side.TOP, textList.get(i), 500),
                            Delay.duration(500));
                    Log.v(TAG, "SlideTop Triggered for "+textList.get(i).toString());
                    anim.add(pl3);
                    break;
                case "Shape2":
                    Parallel pl4 = new Parallel(
                            new TransSurface(500, textList.get(i), Pivot.CENTER),
                            ShapeReveal.create(textList.get(i), 1300, SideCut.show(Side.LEFT), false),
                            Delay.duration(500));
                    Log.v(TAG, "Shape2 Triggered for "+textList.get(i).toString());
                    anim.add(pl4);
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
