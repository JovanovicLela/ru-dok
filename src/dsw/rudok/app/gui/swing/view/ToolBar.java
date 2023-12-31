package dsw.rudok.app.gui.swing.view;

import javax.swing.*;

public class ToolBar extends JToolBar {

    public ToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add (MainFrame.getInstance().getActionManager().getExitAction());
        add (MainFrame.getInstance().getActionManager().getSaveProjectAction());
        add (MainFrame.getInstance().getActionManager().getSaveAsAction());
        add (MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
        add (MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getShareDocAction());

        addSeparator();
        add (MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getNewDocumentAction());
        add(MainFrame.getInstance().getActionManager().getNewPageAction());

        addSeparator();
       // add(MainFrame.getInstance().getActionManager().getRenameAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getcLoseTab());
        add(MainFrame.getInstance().getActionManager().getCloseAllTabs());
        addSeparator();



    }
}
