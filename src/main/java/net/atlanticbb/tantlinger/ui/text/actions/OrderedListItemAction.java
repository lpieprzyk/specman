package net.atlanticbb.tantlinger.ui.text.actions;

import specman.graphics.IconReader;
import specman.graphics.SpecmanIcons;

import specman.Specman;
import static specman.Specman.editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.Serial;

public class OrderedListItemAction extends BasicEditAction {
  @Serial
  private static final long serialVersionUID = 1L;

  public OrderedListItemAction() {
    super("List item");
    this.putValue("SmallIcon", IconReader.readSvgIcon(SpecmanIcons.LIST_ORDERED, SpecmanIcons.SIZE_SMALL));
    this.putValue("ShortDescription", this.getValue("Name"));
  }

  @Override
  protected void doEdit(ActionEvent actionEvent, JEditorPane jEditorPane) {
    editor().toggleListItem(true);
  }

}
