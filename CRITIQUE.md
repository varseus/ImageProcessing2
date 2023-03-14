# Code Critique

### Design

Overall, the given implementation of Image Processing
was very flexible. The use of the command pattern made it such
that creating new model functionality was as simple as creating a new
function object that implemented the IMECommand interface — there were no changes to
the existing model classes. Likewise, the controllers
could be extended by simply adding the new "Mosaic" command to the controller hashmap. And the GUI
view had a very convenient OptionPanel class, through which a new "Mosaic" option could
be added to the options list, which can be used by users to trigger the
corresponding controller command.

The drawbacks of the command pattern are the clunkiness of the command function
objects. As a result, there are dozens of large command objects as opposed
to one simple model object that has methods for each command. As well, through
the command hashmap, the controller becomes slightly coupled with the model. If a completely
different model were to be required (such as with the solitaire project, which had
English/Triangle/European models), it would not be as simple as just changing which model the
controller was referencing. One would also have to change the controller's command map, and
create all the new function objects for the new model. In this sense, the model is "concrete" in
that it is restricted to only accepting certain kinds of command function objects — those which
execute on images. A new feature that batch operated on images or could combine many images would
require larger changes. However, we agree
that these drawbacks are worth the sacrifice for the gained flexibility.

### Implementation

- The text scripted controller did not use a view, and so feedback could not be
outputted to the user. A new view should be created that can output
to the console, and it should implement a corresponding interface.

- The user feedback that was offered in the GUI relied on many confusing if/else statements
to handle various exceptions. To simplify this, the exceptions being thrown should be specific and
readable, and the GUI should simply display their messages to the user.

- It is abnormal that load and save are methods in the controller — we believe that these
best belong in the model and view respectively.

- The mock views were not very usable as they had hardcoded inputs for the controller — these would be 
much more usable if they could send various choosable inputs to the controller, to test
various inputs.

- There was no mock view to for use testing the controller.

- No Jar instructions given in the USEME.


- The GUI view was implemented impressively cleanly, and was very easy
to extends using the OptionPanel component!

### Documentation
The documentation was, for the most part, very specific
and helpful in deciphering what classes/methods did. The 
code was also clean and easy to read.

All "IME" classes were confusing — what does IME stand for? What is
the IMEView supposed to represent — a GUI view or a scripted view?