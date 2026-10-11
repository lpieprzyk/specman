package specman.graphics;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IconResourcesTest {

  @Test
  void allDeclaredIconsAreLoadable() {
    assertFalse(SpecmanIcons.allPaths().isEmpty());
    for (String path : SpecmanIcons.allPaths()) {
      Icon icon = IconReader.readSvgIcon(path, SpecmanIcons.SIZE_SMALL);
      assertEquals(SpecmanIcons.SIZE_SMALL, icon.getIconWidth(), path);
      assertEquals(SpecmanIcons.SIZE_SMALL, icon.getIconHeight(), path);
    }
  }

  @Test
  void missingResourceIsRejected() {
    assertThrows(IllegalArgumentException.class, () -> IconReader.readSvgIcon("images/svg/material/does-not-exist.svg", SpecmanIcons.SIZE_SMALL));
  }

}
