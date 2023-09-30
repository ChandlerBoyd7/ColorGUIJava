Color Data Management GUI

Description
This program is a simple Java GUI application that allows users to manage color data. Users can input the name of a color and its corresponding RGB values, navigate through saved colors, view a list of saved colors, and save or load color data from a file.

Features
Add Color Data: Allows users to input a color name and its RGB values.
Navigate Colors: Users can navigate through the saved colors using the 'Next' and 'Previous' buttons.
List Colors: Display a list of all saved colors with their RGB values.
Save/Load: The application can save color data to a file (colors.dat) and load color data from the same file.
Dynamic Color Display: The bottom panel of the GUI updates its background color to reflect the current color being viewed.
User Guide
Add Color Data
Enter a color name in the 'Name' field.
Input the Red, Green, and Blue values in the corresponding fields (valid values range from 0 to 255).
Click on the 'Add' button to save the color.
Navigate Colors
Click on the 'Next' button to view the next saved color.
Click on the 'Previous' button to view the previous saved color.
List Colors
Click on the 'List' button to view a list of all saved colors in the text area.

Save/Load Color Data
To save the color data to a file, click on the 'Save' button.
To load color data from a file, click on the 'Load' button.
Error Handling
The application has basic error handling for:

Null or empty color names.
Non-numeric RGB values.
RGB values outside the range of 0 to 255.
Classes
GUI: Main class responsible for rendering the GUI and handling user actions.
GData: A simple data class that holds information about a color – its name and RGB values. This class implements Serializable for file operations.
Usage
Compile and run the GUI class to start the application.
