#!/bin/bash

#defaults
###########################################################
THIS=$(readlink -f "$0")
HERE=$(dirname "$THIS")
OUTDIR="$HERE/documentation"
STYLESHEET=""
DOCLETPATH=""
DOCLET=""
EXTRA1=""
EXTRA2=""
###########################################################


#customs (only uncomment one doclet)

### doclet: doclava ###
DOCLETPATH="-docletpath $HERE/javadoc/doclava.jar"
DOCLET="-doclet com.google.doclava.Doclava"
EXTRA1="-hdf project.name \"Eventful\" "
EXTRA2="-XDignore.symbol.file"

### doclet: apiviz ###
#STYLESHEET="-stylesheetfile $HERE/javadoc/stylesheet.css"
#DOCLETPATH="-docletpath $HERE/javadoc/apiviz.jar"
#DOCLET="-doclet org.jboss.apiviz.APIviz"

###########################################################

rm -rf $OUTDIR/*
echo "DOCLETPATH $DOCLETPATH"
echo "DOCLET $DOCLET"
echo "STYLESHEET $STYLESHEET"
echo "OUTDIR $OUTDIR"
echo "EXTRA1 $EXTRA1"
read -p "Enter to continue"

cd $HERE


### module:libeventful ###
javadoc $(find libSFG/src/main/java/ -type f | grep "\.java") -d $OUTDIR $STYLESHEET $DOCLETPATH $DOCLET $EXTRA1 $EXTRA2

### module:sampleApp ###
#javadoc $(find sampleApp/src/main/java/ -type f | grep "\.java") -d $OUTDIR $STYLESHEET $DOCLETPATH $DOCLET $EXTRA1 $EXTRA2

read -p "Enter to continue"


