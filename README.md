We were able to implement image mosaicking correctly, supported a script command for it and exposed
it through the GUI.

---
# Implementation of Mosaic
### Model
The model was designed with the command pattern, which we extended to add mosaic functionality
A new MosaicCommand function object that implements the IMECommand interface was created, 
such that it can be executed on an image to produce a mosaic image. 

#### Implementation of Mosaic:
The mosaic relies on a BFS approach — seeds are randomly chosed
in a graph of pixels, and then the seeds search for neighboring pixels
to add to their mosaic tile until no more neighboring pixels are available.
The pixel in each tile is then turned to the average value pixel
of the entire tile.

### Controller
The "model" command was added to the GUIController and IMEControllerImpl command maps,
such that the controller could be used to execute the command with the model. 

### View
The "Mosaic" option was added to the OptionPanel interface in the View, such that the
user could use the GUI to make the Mosaic command. The GUIController was updated
such that parseInput could properly errors/exceptions from the MosaicCommand
function object.

### Tests
Some changes to the MockView were made such that tests could be 
run more efficiently. Particularly, the MockViewInvalidInput class was
modified such that the input could be specified by the user — this
made testing various inputs very easy.