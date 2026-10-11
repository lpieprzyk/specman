package net.atlanticbb.tantlinger.ui;

import net.atlanticbb.tantlinger.ui.text.actions.CopyAction;
import net.atlanticbb.tantlinger.ui.text.actions.HTMLAlignAction;
import net.atlanticbb.tantlinger.ui.text.actions.HTMLBlockAction;
import net.atlanticbb.tantlinger.ui.text.actions.HTMLInlineAction;
import org.junit.jupiter.api.Test;
import specman.graphics.SpecmanIcons;

import javax.swing.Action;
import javax.swing.Icon;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShefIconOverridesTest {

  @Test
  void inlineActionsGetBoldItalicUnderlineIcons() {
    assertEquals(SpecmanIcons.FORMAT_BOLD, ShefIconOverrides.iconPathFor(new HTMLInlineAction(HTMLInlineAction.BOLD)));
    assertEquals(SpecmanIcons.FORMAT_ITALIC, ShefIconOverrides.iconPathFor(new HTMLInlineAction(HTMLInlineAction.ITALIC)));
    assertEquals(SpecmanIcons.FORMAT_UNDERLINE, ShefIconOverrides.iconPathFor(new HTMLInlineAction(HTMLInlineAction.UNDERLINE)));
    assertNull(ShefIconOverrides.iconPathFor(new HTMLInlineAction(HTMLInlineAction.SUP)));
  }

  @Test
  void alignActionsAreToldApartByName() {
    assertEquals(SpecmanIcons.ALIGN_LEFT, ShefIconOverrides.iconPathFor(new HTMLAlignAction(HTMLAlignAction.LEFT)));
    assertEquals(SpecmanIcons.ALIGN_CENTER, ShefIconOverrides.iconPathFor(new HTMLAlignAction(HTMLAlignAction.CENTER)));
    assertEquals(SpecmanIcons.ALIGN_RIGHT, ShefIconOverrides.iconPathFor(new HTMLAlignAction(HTMLAlignAction.RIGHT)));
    assertEquals(SpecmanIcons.ALIGN_JUSTIFY, ShefIconOverrides.iconPathFor(new HTMLAlignAction(HTMLAlignAction.JUSTIFY)));
  }

  @Test
  void listBlockActionsGetListIcons() {
    assertEquals(SpecmanIcons.LIST_ORDERED, ShefIconOverrides.iconPathFor(new HTMLBlockAction(HTMLBlockAction.OL)));
    assertEquals(SpecmanIcons.LIST_UNORDERED, ShefIconOverrides.iconPathFor(new HTMLBlockAction(HTMLBlockAction.UL)));
    assertNull(ShefIconOverrides.iconPathFor(new HTMLBlockAction(HTMLBlockAction.P)));
  }

  @Test
  void applyReplacesSmallIconWithSvgIcon() {
    Action copy = new CopyAction();
    Icon before = (Icon) copy.getValue(Action.SMALL_ICON);
    ShefIconOverrides.apply(List.of(copy));
    Icon after = (Icon) copy.getValue(Action.SMALL_ICON);
    assertNotNull(after);
    assertEquals(SpecmanIcons.SIZE_SMALL, after.getIconWidth());
    assertEquals(false, after == before);
  }

}
