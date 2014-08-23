# SimpleFingerGestures 
 An android library to implement simple 1 or 2 finger gestures easily

[![Build Status](https://travis-ci.org/championswimmer/SimpleFingerGestures_Android_Library.svg)](https://travis-ci.org/championswimmer/SimpleFingerGestures_Android_Library)

## Library
 The library is inside the <a href="./libSFG">libSFG</a> folder

## Sample App
 The sample App is inside the <a href="./sample">sample</a> folder



### Implementing SimpleFingerGestures

 1. Create an object of class SimpleFingerGestures

        private SimpleFingerGestures mySfg = new SimpleFingerGestures();

 2. Implement the required gestures via this object

        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
                    @Override
                    public boolean onSwipeUp(int fingers, long gestureDuration) {
                        grtv.setText("swiped " + fingers + " up");
                        return false;
                    }
        
                    @Override
                    public boolean onSwipeDown(int fingers, long gestureDuration) {
                        grtv.setText("swiped " + fingers + " down");
                        return false;
                    }
        
                    @Override
                    public boolean onSwipeLeft(int fingers, long gestureDuration) {
                        grtv.setText("swiped " + fingers + " left");
                        return false;
                    }
        
                    @Override
                    public boolean onSwipeRight(int fingers, long gestureDuration) {
                        grtv.setText("swiped " + fingers + " right");
                        return false;
                    }
        
                    @Override
                    public boolean onPinch(int fingers, long gestureDuration) {
                        grtv.setText("pinch");
                        return false;
                    }
        
                    @Override
                    public boolean onUnpinch(int fingers, long gestureDuration) {
                        grtv.setText("unpinch");
                        return false;
                    }
                });

 3. And finally set this object onto your view's OnTouchListener

        myView.setOnTouchListener(mySfg);

    This can be set as the OnTouchListener of any object that is derived from android.view.View



### How to add to your project

The easiest way to add to your project is the download the latest zip from the Releases tab.
Inside you'll find a compiled library in .jar format that you can just drop in to the
libs folder of your Android app project for it to get automatically added as a library

### Documentation

Find documentation at  

http://championswimmer.github.io/SimpleFingerGestures_Android_Library/documentation

