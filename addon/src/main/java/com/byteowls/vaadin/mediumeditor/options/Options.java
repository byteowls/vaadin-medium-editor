package com.byteowls.vaadin.mediumeditor.options;

import java.io.Serializable;

import com.byteowls.vaadin.mediumeditor.options.Anchor.AnchorBuilder;
import com.byteowls.vaadin.mediumeditor.options.AnchorPreview.AnchorPreviewBuilder;
import com.byteowls.vaadin.mediumeditor.options.KeyboardCommands.KeyboardCommandsBuilder;
import com.byteowls.vaadin.mediumeditor.options.PasteHandler.PasteHandlerBuilder;
import com.byteowls.vaadin.mediumeditor.options.Placeholder.PlaceholderBuilder;
import com.byteowls.vaadin.mediumeditor.options.Toolbar.ToolbarBuilder;


public class Options implements Serializable {

  private static final long serialVersionUID = 3972297834616344210L;

  public String activeButtonClass;
  // TODO fontawesome doesnot work although vaadin includes fontawesome but maybe its repackaged and therefore not found
  public Object buttonLabels;
  public Integer delay = null;
  public Boolean disableReturn;
  public Boolean disableDoubleReturn;
  public Boolean disableExtraSpaces;
  public Boolean disableEditing;
  // TODO selector which is resolved in javascript
  public String elementsContainerSelector;
  // TODO 
//  public Object extensions;
  public Boolean spellcheck;
  public Boolean targetBlank;
  public Boolean autoLink;
  public Boolean imageDragging;
  
  public Toolbar toolbar;
  public AnchorPreview anchorPreview;
  public Placeholder placeholder;
  public Anchor anchor;
  public PasteHandler paste;
  public KeyboardCommands keyboardCommands;
  
  private Options(OptionsBuilder builder) {
    activeButtonClass = builder.activeButtonClass;
    buttonLabels = builder.buttonLabels;
    delay = builder.delay;
    disableReturn = builder.disableReturn;
    disableDoubleReturn = builder.disableDoubleReturn;
    disableExtraSpaces = builder.disableExtraSpaces;
    disableEditing = builder.disableEditing;
    spellcheck = builder.spellcheck;
    targetBlank = builder.targetBlank;
    autoLink = builder.autoLink;
    imageDragging = builder.imageDragging;
    
    // toolbar
    if (builder.toolbarEnabled) {
      if (builder.toolbar != null) {
        toolbar = (Toolbar) builder.toolbar.build();
      }
    } else {
//      toolbar = Boolean.FALSE;
    }
    
    // anchor preview
    if (builder.anchorPreviewEnabled) {
      if (builder.anchorPreview != null) {
        anchorPreview = builder.anchorPreview.build();
      }
    } else {
//      anchorPreview = Boolean.FALSE;
    }
    
    // placeholder
    if (builder.placeholderEnabled) {
      if (builder.placeholder != null) {
        placeholder = builder.placeholder.build();
      }
    } else {
//      placeholder = Boolean.FALSE;
    }
    
    // anchor
    if (builder.anchor != null) {
      anchor = builder.anchor.build();
    }
    
    // paste
    if (builder.pasteHandler != null) {
      paste = builder.pasteHandler.build();
    }
    
    // keyboard commands
    if (builder.keyboardCommandsEnabled) {
      if (builder.keyboardCommands != null) {
        keyboardCommands = builder.keyboardCommands.build();
      }
    } else {
//      keyboardCommands = Boolean.FALSE;
    }
    
  }
  
  public static OptionsBuilder builder() {
    return new Options.OptionsBuilder();
  }
  
  public static class OptionsBuilder {
    
    private String activeButtonClass;
    private Object buttonLabels;
    private Integer delay = null;
    private Boolean disableReturn;
    private Boolean disableDoubleReturn;
    private Boolean disableExtraSpaces;
    private Boolean disableEditing;
    private Boolean spellcheck;
    private Boolean targetBlank;
    private Boolean autoLink;
    private Boolean imageDragging;

    private boolean toolbarEnabled = true;
    private ToolbarBuilder toolbar;
    private boolean placeholderEnabled = true;
    private PlaceholderBuilder placeholder;
    
    private boolean anchorPreviewEnabled = true;
    private AnchorPreviewBuilder anchorPreview;
    
    private AnchorBuilder anchor;

    private PasteHandlerBuilder pasteHandler;
    private boolean keyboardCommandsEnabled = true;
    private KeyboardCommandsBuilder keyboardCommands;

    public OptionsBuilder activeButtonClass(String activeButtonClass) {
      this.activeButtonClass = activeButtonClass;
      return this;
    }
    
    public OptionsBuilder fontawesomeButtonLabels() {
      this.buttonLabels = "fontawesome";
      return this;
    }
    
    public OptionsBuilder defaultButtonLabels() {
      this.buttonLabels = Boolean.FALSE;
      return this;
    }

    public OptionsBuilder delay(int delay) {
      this.delay = delay;
      return this;
    }
    
    public OptionsBuilder disableReturn(boolean disableReturn) {
      this.disableReturn = disableReturn;
      return this;
    }
    
    public OptionsBuilder disableDoubleReturn(boolean disableDoubleReturn) {
      this.disableDoubleReturn = disableDoubleReturn;
      return this;
    }
    
    public OptionsBuilder disableExtraSpaces(boolean disableExtraSpaces) {
      this.disableExtraSpaces = disableExtraSpaces;
      return this;
    }
    
    public OptionsBuilder disableEditing(boolean disableEditing) {
      this.disableEditing = disableEditing;
      return this;
    }
    
    public OptionsBuilder spellcheck(boolean spellcheck) {
      this.spellcheck = spellcheck;
      return this;
    }
    
    public OptionsBuilder targetBlank(boolean targetBlank) {
      this.targetBlank = targetBlank;
      return this;
    }
    
    public OptionsBuilder autoLink(boolean autoLink) {
      this.autoLink = autoLink;
      return this;
    }
    
    public OptionsBuilder imageDragging(boolean imageDragging) {
      this.imageDragging = imageDragging;
      return this;
    }
    
    public OptionsBuilder toolbarDisabled() {
      this.toolbarEnabled = false;
      return this;
    }
    
    public ToolbarBuilder toolbar() {
      this.toolbarEnabled = true;
      if (toolbar == null) {
        toolbar = new ToolbarBuilder(this);
      }
      return toolbar;
    }
    
    public OptionsBuilder anchorPreviewDisabled() {
      this.anchorPreviewEnabled = false;
      return this;
    }
    
    public AnchorPreviewBuilder anchorPreview() {
      this.anchorPreviewEnabled = true;
      if (anchorPreview == null) {
        anchorPreview = new AnchorPreviewBuilder(this);
      }
      return anchorPreview;
    }
    
    public OptionsBuilder placeholderDisabled() {
      this.placeholderEnabled = false;
      // mixing types does not work
      return placeholder().text("").and();
    }
    
    public PlaceholderBuilder placeholder() {
      this.placeholderEnabled = true;
      if (placeholder == null) {
        placeholder = new PlaceholderBuilder(this);
      }
      return placeholder;
    }
    
    public AnchorBuilder anchor() {
      if (anchor == null) {
        anchor = new AnchorBuilder(this);
      }
      return anchor;
    }
    
    public OptionsBuilder pasteHandlingDisabled() {
      return paste().forcePlainText(false).cleanPastedHTML(false).and();
    }
    
    public PasteHandlerBuilder paste() {
      if (pasteHandler == null) {
        pasteHandler = new PasteHandlerBuilder(this);
      }
      return pasteHandler;
    }
    
    public OptionsBuilder keyboardCommandsDisabled() {
      this.keyboardCommandsEnabled = false;
      return this;
    }
    
    public KeyboardCommandsBuilder keyboadCommands() {
      this.keyboardCommandsEnabled = true;
      if (keyboardCommands == null) {
        keyboardCommands = new KeyboardCommandsBuilder(this);
      }
      return keyboardCommands;
    }

    public Options build() {
      return new Options(this);
    }
  }
  
}
