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

        mySfg.setOn1FingerGestureListener(new SimpleFingerGestures.On1FingerGestureListener() {
                    @Override
                    public boolean onSwipeUp() {
                        Toast.makeText(getBaseContext(), "swiped up", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onSwipeDown() {
                        Toast.makeText(getBaseContext(), "swiped down", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onSwipeLeft() {
                        Toast.makeText(getBaseContext(), "swiped left", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onSwipeRight() {
                        Toast.makeText(getBaseContext(), "swiped right", Toast.LENGTH_SHORT).show();
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

