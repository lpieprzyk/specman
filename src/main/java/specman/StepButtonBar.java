package specman;

import specman.draganddrop.DragMouseAdapter;
import specman.draganddrop.DragSource;
import specman.graphics.IconReader;
import specman.graphics.SpecmanIcons;
import specman.ops.buttons.CreateBreakStepOpButton;
import specman.ops.buttons.CreateCaseBranchADBLOpButton;
import specman.ops.buttons.CreateCaseStepOpButton;
import specman.ops.buttons.CreateCatchSequenceADBLOpButton;
import specman.ops.buttons.CreateIfElseStepOpButton;
import specman.ops.buttons.CreateIfStepOpButton;
import specman.ops.buttons.CreateSimpleStepOpButton;
import specman.ops.buttons.CreateSubsequenceStepOpButton;
import specman.ops.buttons.CreateWhileStepOpButton;
import specman.ops.buttons.CreateWhileWhileStepOpButton;
import specman.ops.buttons.DragSourceProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class StepButtonBar extends JToolBar {

  private static final float TEXT_SCALE = 1.2f;
  private static final String PREF_SHOW_TEXT = "stepButtonBarShowText";

  private final List<JButton> stepButtons = new ArrayList<>();
  private final JButton toggleButton = new JButton();
  private boolean showText;

  StepButtonBar(Specman specman) {
    super(JToolBar.VERTICAL);
    showText = Preferences.userNodeForPackage(Specman.class).getBoolean(PREF_SHOW_TEXT, true);

    toggleButton.setMargin(new Insets(0, 0, 0, 0));
    toggleButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    toggleButton.addActionListener(e -> toggleShowText());
    add(toggleButton);
    addSeparator();

    addDragButton(new CreateSimpleStepOpButton(specman), SpecmanIcons.STEP_SIMPLE, "Simple step", specman);
    addDragButton(new CreateWhileStepOpButton(specman), SpecmanIcons.STEP_WHILE, "While step", specman);
    addDragButton(new CreateWhileWhileStepOpButton(specman), SpecmanIcons.STEP_WHILEWHILE, "While-while step", specman);
    addDragButton(new CreateIfElseStepOpButton(specman), SpecmanIcons.STEP_IFELSE, "If-else step", specman);
    addDragButton(new CreateIfStepOpButton(specman), SpecmanIcons.STEP_IF, "If step", specman);
    addDragButton(new CreateCaseStepOpButton(specman), SpecmanIcons.STEP_CASE, "Case step", specman);
    addDragButton(new CreateSubsequenceStepOpButton(specman), SpecmanIcons.STEP_SUBSEQUENCE, "Subsequence step", specman);
    addDragButton(new CreateBreakStepOpButton(specman), SpecmanIcons.STEP_BREAK, "Break step", specman);
    addDragButton(new CreateCatchSequenceADBLOpButton(specman), SpecmanIcons.STEP_CATCH, "Catch block", specman);
    addDragButton(new CreateCaseBranchADBLOpButton(specman), SpecmanIcons.STEP_CASE_BRANCH, "Case branch", specman);

    applyShowText();
  }

  private void addDragButton(JButton button, String iconPath, String label, Specman specman) {
    button.setIcon(IconReader.readSvgIcon(iconPath, SpecmanIcons.SIZE_TOOLBAR));
    button.putClientProperty("label", label);
    button.setMargin(new Insets(0, 0, 0, 0));
    button.setFont(button.getFont().deriveFont(button.getFont().getSize2D() * TEXT_SCALE));
    button.setIconTextGap(8);
    button.setHorizontalAlignment(SwingConstants.LEADING);
    button.setHorizontalTextPosition(SwingConstants.TRAILING);
    button.setAlignmentX(Component.LEFT_ALIGNMENT);
    stepButtons.add(button);
    add(button);
    DragMouseAdapter dragAdapter = new DragMouseAdapter(specman, this);
    button.addMouseListener(dragAdapter);
    button.addMouseMotionListener(dragAdapter);
  }

  private void toggleShowText() {
    showText = !showText;
    Preferences.userNodeForPackage(Specman.class).putBoolean(PREF_SHOW_TEXT, showText);
    applyShowText();
    Container topLevel = getTopLevelAncestor();
    if (topLevel != null) {
      topLevel.revalidate();
      topLevel.repaint();
    }
  }

  private void applyShowText() {
    toggleButton.setIcon(IconReader.readSvgIcon(showText ? SpecmanIcons.PANEL_HIDE_TEXT : SpecmanIcons.PANEL_SHOW_TEXT,
        SpecmanIcons.SIZE_TOOLBAR));
    toggleButton.setToolTipText(showText ? "Show icons only" : "Show icons with text");
    for (JButton button : stepButtons) {
      String label = (String) button.getClientProperty("label");
      button.setText(showText ? label : null);
      button.setToolTipText(showText ? null : label);
      button.setPreferredSize(null);
    }
    if (showText) {
      int maxWidth = stepButtons.stream().mapToInt(b -> b.getPreferredSize().width).max().orElse(0);
      for (JButton button : stepButtons) {
        button.setPreferredSize(new Dimension(maxWidth, button.getPreferredSize().height));
        button.setMaximumSize(button.getPreferredSize());
      }
      toggleButton.setMaximumSize(new Dimension(maxWidth, toggleButton.getPreferredSize().height));
    }
    else {
      for (JButton button : stepButtons) {
        button.setMaximumSize(null);
      }
      toggleButton.setMaximumSize(null);
    }
  }

  public DragSource dragSourceFor(JButton button) {
    return ((DragSourceProvider) button).dragSource();
  }

}
