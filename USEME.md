Instructions for using the text-based commands/scripting:
- load image-path image-name  - loads into the program the image at image-path
  as image-name. For example, "load res/River.jpg river" would load res/River.jpg
  into the program under the name 'river'. Load should always be called first in the program.
  Supported file types are ".ppm", ".jpg" and ".jpeg", ".png" and ".bmp".
- red-component image-name dest-image-name - creates a greyscale image using the red component
  of each pixel of the image at image-name and calls the result dest-image-name.
  For example, "red-component river red" would create the red component greyscale of "river" and call
  it "red".
- green-component image-name dest-image-name - creates a greyscale image using the green component
  of each pixel of the image at image-name and calls the result dest-image-name.
  For example, "green-component river green" would create the green component greyscale of "river" and
  call it "green".
- blue-component image-name dest-image-name - creates a greyscale image using the blue component
  of each pixel of the image at image-name and calls the result dest-image-name.
  For example, "blue-component river blue" would create the blue component greyscale of "river" and
  call it "blue".
- value-component image-name dest-image-name - creates a greyscale image using the value component
  of each pixel of the image at image-name and calls the result dest-image-name.
  For example, "value-component river value" would create the value component greyscale of "river"
  and call it "value".
- intensity-component image-name dest-image-name - creates a greyscale image using the intensity
  component of each pixel of the image at image-name and calls the result dest-image-name.
  For example, "intensity-component river intensity" would create the intensity component greyscale of
  "river" and call it "intensity".
- luma-component image-name dest-image-name - creates a greyscale image using the luma component
  of each pixel of the image at image-name and calls the result dest-image-name.
  For example, "luma-component river luma" would create the luma component greyscale of "river"
  and call it "luma".
- horizontal-flip image-name dest-image-name - creates an image which is the horizontal flip of the
  image at image-name, and calls it dest-image-name. For example, "horizontal-flip river horiz" would
  create the horizontal flip of "river" and call it "horiz".
- vertical-flip image-name dest-image-name - creates an image which is the vertical flip of the
  image at image-name, and calls it dest-image-name. For example, "vertical-flip river vert" would
  create the vertical flip of "river" and call it "vert".
- brighten value image-name dest-image-name - creates a brightened/darkened version of the image at
  image-name and calls it dest-image-name. Value must be an integer, and determines how much the image
  is brightened/darkened. If value > 0, it will brighten, and if value < 0, it will
  darken. For example, "brighten -50 river dark" will create an image where every rgb component is
  50 less than "river" (or 0), and call it "dark".
- blur image-name dest-image-name - creates a blurred version of the image at image-name and calls
  it dest-image-name. For example, "blur river blurred" will create a blurred version of "river" and
  call it "blurred".
- sharpen image-name dest-image-name - creates a sharpened version of the image at image-name and
  calls it dest-image-name. For example, "sharpen river sharp" will create a sharpened version of
  "river" and call it "sharp".
- greyscale image-name dest-image-name - creates a greyscale version of the image at image-name and
  calls it dest-image-name (this does the same thing as luma-component). For example, "greyscale river
  grey" will create a greyscale version of "river" and call it "grey".
- sepia image-name dest-image-name - creates a sepia version of the image at image-name and calls it
  dest-image-name. For example, "sepia river sepia-tone" will create a sepia version of "river" and
  call it "sepia-tone".
- mosaic seed-number image-name dest-image-name - creates a mosaic version of the image with 
  seed-number seeds at image-name and calls it dest-image-name. 
  For example, "mosaic 1000 river mosaic-river" will create a mosaic version of "river"
  with 1000 tiles and call it "mosaic-river".
- save image-name image-path - saves the image at image-name to the location specified by
  image-path. For example, "save river res/River.bmp" will save "river" in the res/ folder as
  River.bmp. Supported file types are ".ppm", ".jpg" and ".jpeg", ".png" and ".bmp".
- quit - quits the program. This should always be the last command in a script

Instructions for using the GUI:

- To use the load functionality, click on the 'Load' button. This will pop up a menu where you can 
select an image file from. Click ok once you have selected an image, and the selected image will
show up in the GUI, along with its corresponding histogram.

- To use any of the image processing commands offered (except brighten/darken), select the desired
command from the dropdown labeled 'Commands:'. Once you click the desired option, the image and
histogram will automatically update to reflect the command being applied.

- To use the brighten/darken commands, click on their options in the commands dropdown menu. This 
will bring up a pop-up window, prompting you for a positive integer to brighten/darken by. Type the
desired amount to brighten/darken by into the text box and press 'OK'. The image and histogram will 
automatically update to reflect the command being applied.

- To use the mosaic command, click on the options in the commands dropdown menu. This
  will bring up a pop-up window, prompting you for a nuumber of seeds to 
  amke the mosaic with. Type the
  desired amount into the text box and press 'OK'. The image and histogram will
  automatically update to reflect the command being applied.

- To use the save functionality, click on the 'Save' button. This will pop up a menu where you can 
select a folder to save to, as well as type a file name to save the image to. You must use one of
the extensions listed here: ".ppm", ".bmp", ".jpg", ".jpeg", ".bmp", ".png". Failing to do so will
result in the file not being saved. Once you have selected a folder and file name, click ok and the
image will be saved. It will show up in the window, if you would to continue editing, and save 
another version in the future.

The image is also scrollable, simply use the scroll bars to navigate.


To run the program with command line arguments, make the first argument "-file", and the second a
".txt" file containing a script full of the above commands. To run the program with interactive
text entry, make the first argument "-text". If no command line arguments are
provided, the default is to run the program with the GUI.