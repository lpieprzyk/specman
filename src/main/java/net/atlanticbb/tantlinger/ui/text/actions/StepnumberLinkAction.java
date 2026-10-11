package net.atlanticbb.tantlinger.ui.text.actions;

import specman.graphics.IconReader;
import specman.graphics.SpecmanIcons;


import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.Serial;

public class StepnumberLinkAction extends BasicEditAction {
    @Serial
    private static final long serialVersionUID = 1L;
    StepnumberLinkDialog dialog;

    public StepnumberLinkAction() {
        super("Link to Stepnumber...");
        this.putValue("SmallIcon", IconReader.readSvgIcon(SpecmanIcons.STEPNUMBER_LINK, SpecmanIcons.SIZE_SMALL));
        this.putValue("ShortDescription", this.getValue("Name"));
    }

    /**
     * Always recreate the dialog since the stepnumberlinks are only fetched on creation
     */
    protected void doEdit(ActionEvent e, JEditorPane ed) {
        Component c = SwingUtilities.getWindowAncestor(ed);

        if (c instanceof Frame) {
            this.dialog = new StepnumberLinkDialog((Frame) c, ed);
        } else {
            if (!(c instanceof Dialog)) {
                return;
            }

            this.dialog = new StepnumberLinkDialog((Dialog) c, ed);
        }

        if (!this.dialog.isVisible()) {
            this.dialog.setLocationRelativeTo(c);
            this.dialog.setVisible(true);
        }

    }

    protected void updateContextState(JEditorPane editor) {
        if (this.dialog != null) {
            this.dialog.setJTextComponent(editor);
        }

    }
}