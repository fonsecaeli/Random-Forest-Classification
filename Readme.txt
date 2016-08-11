Random Forest Classification

This readme is intended to help the user get started in using the Random Forest Classification program


1) Understanding the basics
	A) The purpose of this program is to classify data into a decision tree:

Outlook, Humidity,Golf?
Sunny,   85,      no
Cloudy,  80,      yes
Rainy,   50,      yes
Rainy,   90,      no

From the data above we can generate a decision tree like so:

		HUMIDITY
	      />=85	\<85
	Outlook 	Outlook
     /Rainy  \Sunny    /Rainy  \Cloudy
    no      no         yes     yes



	
	B) The way you input the data into the program is simple:
You put the data into a spreadsheet program in this format:

HEADER,HEADER,HEADER,....,HEADER,FINAL_HEADER
INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
	..................
	..................
	..................
INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
INPUT, INPUT, INPUT,.....,INPUT, OUTPUT
 
And then save the file as a .csv file.
After that you can start up the program and click the 'Load...' button and select the saved csv file





2) How to use the program






