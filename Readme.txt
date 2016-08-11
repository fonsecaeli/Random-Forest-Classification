Random Forest Classification

This readme is intended to help the user get started in using the Random Forest Classification program


1) Understanding the basics

   A) The purpose of this program is to classify data into a decision tree:

    Outlook, Humidity, Golf?
    Sunny,   85,      no
    Cloudy,  80,      yes
    Rainy,   50,      yes
    Rainy,   90,      no

    From the data above we can generate a decision tree like so:

               HUMIDITY
              />=85       \<85
        Outlook        Outlook
       /Rainy \Sunny    /Rainy \Cloudy
      no        no       yes     yes




   B) The way you input the data into the program is simple:

    You put the data into a spreadsheet program in this format:

    HEADER,HEADER,HEADER,....,HEADER,FINAL_HEADER
    INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
    INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
    INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
      .      .      .     .     .      .
      .      .      .     .     .      .
      .      .      .     .     .      .
    INPUT, INPUT, INPUT,.....,INPUT, OUTPUT

    And then save the file as a .csv file.
    After that you can start up the program and click the 'Load...' button and select the saved csv file



2) How to use the program

    A) Once the data is loaded tree visualizations are automatically created.

    To view an image of all the trees generated look under the Tree tab at the top.

    To view a fish eye view of an individual tree click on the fish eye tab.  To cycle threw all the trees that
    were generated use the two arrow buttons located in the top right and left corners of the display area.
    To move down or up a tree when I fish eye mode you click the head or children nodes respectively until
    you reach the area of the tree you want to examine.  If you wish to classify new data with the trees that
    have been generated click on the query tab and then click on the query button.  You may then select a csv
    file containing new data to be classified.  The data should be in the same format specified above except
    the classification head should be missing.  The data in the file will then be classified, the results will
    be displayed to the screen.  Another feature of the program is that you may import multiple data sets
    ksimultaneously by clicking the Load… button again.








