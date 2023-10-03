*Written in Markdown*

# README — ImageProcessing

The ImageProcessing package functions on the MVC design pattern. The model, coupled with utilities, handles all Image data.
It can load images, save images, and process images according to various commands. The controller fields and parses user input to determine what 
commands the model should execute. The view outputs results, serving as a communication interface to the user.

### Model

The Model is represented by the **ImageProcessingModel** interface, which can execute various operations on a set of Images. It is implemented by the 
**BasicImageProcessingModel** class, which can load, edit, and save images, while maintaining a running state of images being processed. Also, 
**MockImageProcessingModel** class logs all calls made to it to a log.

Each Image in the BasicImageProcessingModel is represented by the **Image** interface. Images have various operations to edit and 
import them, as implemented by a **BasicImage** or a **GreyscaleImage** (a black-and-white image). 

Images are composed of matrices of pixels, represented by the **Pixel** interface. Pixel's also have various operations to edit them, and are either an
**RGBPixel** or **GreyscalePixel** (a black-and-white pixel).

All import operations done by reading from a ppm file or
jpef/raw/tiff/bmp/png...

### Controller

#### Synchronous 
The **ImageProcessingController** interface represents operations that should be offered by an image processor controller, 
consisting only of the startProcessor() method to start the controller. The **TextScriptedImageProcessingController**
implements this by reading text input from a specified source, and using a HashMap of commands 
to parse the input and call the relevant command on the model, while 
outputting results to the specified view.

#### Asynchronous
The **SwingAppFeatures** interface represents operations that should be offered
by a controller for a swing app. These operations are event handlers
for asynchronous user-driven events, emitted by the view.


The interface is implemented by the **SwingController** which 
contains methods for each event emitted by the view. It parses these events and 
delegates state changes to the model, and outputs corresponding information via the view.

### View

#### Text
The **ImageProcessingView** interface outlines the operations of an image processing view, consisting of the
renderMessage() and saveImageToFile(...) methods. Class **TextScriptImageProcessingView** implements it by transmitting messages to the
given Appendable output object.

#### GUI
The **ImageProcessingSwingView** interface outlines the operations that should
be offered by a swing image processing view. 
These operations consist of various ways to output information (which
is to be fed by the controller) to the user — primarily be rendering
messages and showing images. 

Class **SwingView** implements the interface
by displaying a GUI for the user upon 
construction (with custom Swing components: 
**Button** / **Histogram** / **Text**).
The class consists of listeners (which await for asynchronous events from the user
and delegate event handling to the controller) and handlers (which handle the 
output of data, as driven by the controller).

### Tests and Resources

Each component is tested by its corresponding test class: **TestModel**, **TestView**, and **TestController**.
Tests are applied on sample data found in the res/ directory. The Controller->Model connection is tested
by the **MockImageProcessingModel** class, which logs all calls to the model to ensure that the controller
is correctly using the model.

## Version 2 Changes
In version 2, support for blur, sharpen, greyscale, and sepia operaitons
were added to the model by adding new methods for each operation. Support for the corresponding commands
for each operation were added to the controller as well.

The save operation was moved from the model to the view, to better
fit the MVC design pattern. Load and save
were refactored to use ImageIO, allowing for more file types 
(including .png and .jpeg) to supported for load and save. The load
and save are done primarily by new ImageReadUtils and ImageWriteUitls
utility classes.

## Version 3 Changes
New Controller/View with Swing GUI: 

The **ImageProcessingSwingView** interface
outlines the operations that should be done by the View, and is implemented
by the **SwingView** class, which draws the view using Swing,
and then asynchronously awaits for user events, which are delegated
to the controller for processing. Operations that should be offered by the controller
are outlined in the **SwingAppFeatures** interface, and are implemented
by the **Swing Controller**. This controller processes events, and then
delegates work to the model and view.

A new *makeHistogramHashmap()* method was added to the **BasicImageProcessingModel**
to be used for drawing histograms for a given model.

## Developer Notes
The use of arrays pixels is very 
inefficient, and yields quickly to
Java heap space exceptions from the
extremely large amounts of objects
being created and edited, particularly
for large pictures and complex operations.
Further development should
focus simplifying the data structure
to address such issues.


---
Image citation:
Self drawn.
