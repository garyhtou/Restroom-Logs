@echo off
echo We have to use this when running the program from a .jar because we must pass an argument to the method which has to be done while calling the program :(
echo Press a key to start the program. This will be removed in the actual program
pause
echo Program has started
@echo on
java -splash:assets/RestroomLogsSplashscreen.png -jar RestroomLogsProgram.jar
@echo off
echo Program has been closed
pause