package specman;

import specman.graphics.IconReader;
import specman.graphics.SpecmanIcons;
import specman.ops.buttons.AcceptChangesADBLOpButton;
import specman.ops.buttons.BirdsViewSpecmanOpButton;
import specman.ops.buttons.CollapseAllOpButton;
import specman.ops.buttons.DeleteStepOpButton;
import specman.ops.buttons.ExpandAllOpButton;
import specman.ops.buttons.ExportPDFOpButton;
import specman.ops.buttons.MergeChangeSetsADBLOpButton;
import specman.ops.buttons.RejectChangesADBLOpButton;
import specman.ops.buttons.ReviewOpButton;

import specman.ops.buttons.ToggleBorderTypeOpButton;
import specman.ops.buttons.ToneOpButton;

import javax.swing.*;
import java.awt.*;

public class DiagramToolBar extends JToolBar {


  private final JToggleButton aenderungenVerfolgen;
  private final AcceptChangesADBLOpButton aenderungenUebernehmen;
  private final RejectChangesADBLOpButton aenderungenVerwerfen;
  private final MergeChangeSetsADBLOpButton mergeChangeSets;
  private final ZoomComboBox zoom;

  DiagramToolBar(Specman specman) {
    setFloatable(false);

    ToneOpButton einfaerben = new ToneOpButton(specman);
    DeleteStepOpButton loeschen = new DeleteStepOpButton(specman);
    ToggleBorderTypeOpButton toggleBorderType = new ToggleBorderTypeOpButton(specman);

    aenderungenVerfolgen = new JToggleButton();
    aenderungenUebernehmen = new AcceptChangesADBLOpButton(specman);
    aenderungenVerwerfen = new RejectChangesADBLOpButton(specman);
    mergeChangeSets = new MergeChangeSetsADBLOpButton(specman);
    updateChangeSetColor(specman.changeset());
    aenderungenVerfolgen.addChangeListener(e ->
        aenderungenVerfolgen.setBackground(aenderungenVerfolgen.isSelected()
            ? specman.changeset().activeButtonColor()
            : specman.changeset().buttonColor()));

    ReviewOpButton review = new ReviewOpButton(specman);
    zoom = new ZoomComboBox(specman);
    CollapseAllOpButton collapseAll = new CollapseAllOpButton(specman);
    ExpandAllOpButton expandAll = new ExpandAllOpButton(specman);
    BirdsViewSpecmanOpButton birdsview =new BirdsViewSpecmanOpButton(specman);
    ExportPDFOpButton exportPDF = new ExportPDFOpButton(specman);

    addButton(einfaerben, SpecmanIcons.TONE, "Hintergrund schattieren");
    addButton(loeschen, SpecmanIcons.DELETE, "Schritt löschen");
    addButton(toggleBorderType, SpecmanIcons.TOGGLE_BORDER, "Rahmen umschalten");
    addSeparator();
    addButton(aenderungenVerfolgen, SpecmanIcons.TRACK_CHANGES, "Änderungen verfolgen");
    addButton(aenderungenUebernehmen, SpecmanIcons.ACCEPT_CHANGES, "Änderungen übernehmen");
    addButton(aenderungenVerwerfen, SpecmanIcons.REJECT_CHANGES, "Änderungen verwerfen");
    addButton(mergeChangeSets, SpecmanIcons.MERGE_CHANGE_SETS, "Change Sets zusammenführen");
    addButton(review, SpecmanIcons.REVIEW, "Für Review zusammenklappen");
    addSeparator();
    add(zoom);
    addButton(collapseAll, SpecmanIcons.COLLAPSE_ALL, "Alle Schritte einklappen");
    addButton(expandAll, SpecmanIcons.EXPAND_ALL, "Alle Schritte ausklappen");
    addButton(birdsview, SpecmanIcons.BIRDS_VIEW, "Bird's View");
    addButton(exportPDF, SpecmanIcons.PDF, "PDF exportieren");
  }

  private void addButton(AbstractButton button, String iconPath, String tooltip) {
    button.setIcon(IconReader.readSvgIcon(iconPath, SpecmanIcons.SIZE_TOOLBAR));
    button.setMargin(new Insets(0, 0, 0, 0));
    button.setToolTipText(tooltip);
    add(button);
  }

  public void setChangeModeEnabled(boolean enabled) {
    aenderungenVerfolgen.setSelected(enabled);
  }

  public boolean isChangeModeEnabled() {
    return aenderungenVerfolgen.isSelected();
  }

  public void updateChangeSet(ChangeSet changeSet) {
    updateChangeSetColor(changeSet);
  }

  private void updateChangeSetColor(ChangeSet changeSet) {
    aenderungenVerfolgen.setBackground(aenderungenVerfolgen.isSelected()
        ? changeSet.activeButtonColor()
        : changeSet.buttonColor());
    aenderungenUebernehmen.setBackground(changeSet.buttonColor());
    aenderungenVerwerfen.setBackground(changeSet.buttonColor());
    mergeChangeSets.setBackground(changeSet.buttonColor());
  }

  public void updateZoomDisplay(int prozent) {
    zoom.updateDisplay(prozent);
  }

}
