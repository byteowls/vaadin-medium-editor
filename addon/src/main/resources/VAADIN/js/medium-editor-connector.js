window.com_byteowls_vaadin_medium_editor_MediumEditor = function() {
  // see the javadoc of com.vaadin.ui.AbstractJavaScriptComponent for all functions on this.
  var mediumEditor;
  var e = this.getElement();
  // Please note that in JavaScript, this is not necessarily defined inside callback functions and it might therefore be necessary to assign the reference to a separate variable
  var self = this;
  var loggingEnabled = false;
  
  // called every time MediumEditorState is changed
  this.onStateChange = function() {
    var state = this.getState();
    loggingEnabled = state.loggingEnabled;
    
    
    if (typeof mediumEditor === 'undefined') {
      if (loggingEnabled) {
        console.log("medium-editor: init editor");
      }
      
      if (loggingEnabled) {
        console.log("medium-editor: setting value to\n" + state.value);
      }
      e.innerHTML = state.value || "";
      
      e.addEventListener("keydown", function(event) {
        if (event.which == 13 || event.keyCode == 13) {
          event.stopPropagation();
        }
      });
      
      mediumEditor = new MediumEditor(e, state.options);
      // maybe this event fires to often 
      // a onblur event would reduce the server roundtrips a lot
      mediumEditor.subscribe('editableInput', function (event, editable) {
        var val = e.innerHTML;
        if (loggingEnabled) {
          console.log("medium-editor: value changed to\n" + val);
        }
        self.onValueChange(val);
      });
    }
  };
  
}
