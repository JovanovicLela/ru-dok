package dsw.rudok.app.gui.swing.view;

import dsw.rudok.app.core.Repository;
import dsw.rudok.app.gui.swing.controller.ActionManager;
import dsw.rudok.app.gui.swing.errorHandler.ErrorHandler;
import dsw.rudok.app.gui.swing.errorHandler.MyError;
import dsw.rudok.app.gui.swing.observer.Subject;
import dsw.rudok.app.gui.swing.tree.RuTree;
import dsw.rudok.app.gui.swing.tree.view.RuTreeImplementation;
import dsw.rudok.app.repository.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame  {

    private static  MainFrame instance = null;

    private static final long serialVersionUID = 1;

    private ActionManager actionManager;
    private StateManager stateManager;

    private AboutDialog aboutDialog;
    private HelpDialog helpDialog;

    private JToolBar toolBar;
    private ProjectPanel desktop;
    private JMenuBar menu;
    private StatusBar statusBar;
    private Palette palette;
    private DocumentPanel desktopPane;
    /* desktopPane ce u sebi sadrzati kolekciju view-a,
    gde svaki od njih predstavlja jedan JInternalFrame i na sebe iscrtava komponente*/

    private Repository documentRepository;
    private RuTree tree;
    private JTree workspaceTree;

    private ErrorHandler error;
    private Subject subject = new Subject(); //observer pattern

    public  boolean saved = true;

    public MainFrame(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] opcije;
                opcije = new Object[]{"Cancel", "Exit"};

                int odgovor = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Do you want to exit workspace?", "Confirm exit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcije, opcije[1]);
                if (odgovor == JOptionPane.YES_OPTION || odgovor == JOptionPane.DEFAULT_OPTION) {
                    MainFrame.getInstance().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
                 if (odgovor == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }

            }
        });
    }
    private void initialise(){

        actionManager = new ActionManager();
        stateManager = new StateManager();

        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            final String DEFAULT_FONT_FAMILY = "Times New Roman";
            final int DEFAULT_FONT_SIZE = 20;
            UIManager.put("TextPane.font",
                    new Font(DEFAULT_FONT_FAMILY, Font.BOLD, DEFAULT_FONT_SIZE));
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void initialiseWorkspaceTree(){
        tree = new RuTreeImplementation();

        workspaceTree = tree.generateTree(documentRepository.getWorkspace());
        ToolTipManager.sharedInstance().registerComponent(workspaceTree);

        initialiseGUI();

    }

    private void initialiseGUI(){

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight =  screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2,screenHeight / 2);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RuDok");
        //setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);


        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);

        palette = new Palette();
        getContentPane().add(palette, BorderLayout.EAST);

        statusBar = new StatusBar();
        add(statusBar, BorderLayout.SOUTH);

        desktop = new ProjectPanel();
        desktopPane = new DocumentPanel();

        aboutDialog = new AboutDialog();
        helpDialog = new HelpDialog();

        JScrollPane scroll = new JScrollPane(workspaceTree);
        scroll.setMinimumSize(new Dimension(200, 150));
        scroll.setVerticalScrollBarPolicy(22);
        scroll.setVisible(true);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, desktop);
        split.setVisible(true);

        getContentPane().add(split,  BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        ImageIcon img = new ImageIcon(getClass().getResource("images/layer32-min.png"));
        this.setIconImage(img.getImage());

    }
    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return  instance;
    }


    @Override
    public synchronized WindowListener[] getWindowListeners() {
        return super.getWindowListeners();
    }

    public HelpDialog getHelpDialog() {
        return helpDialog;
    }

    public void setHelpDialog(HelpDialog helpDialog) {
        this.helpDialog = helpDialog;
    }

    public DocumentPanel getDesktopPane() {
        return desktopPane;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public ActionManager getActionManager(){
        return actionManager;
    }
    public void setDocumentRepository(Repository documentRepository) {
        this.documentRepository = documentRepository;
    }
    public JTree getWorkspaceTree(){
        return workspaceTree;
    }
    public void setWorkspaceTree(JTree workspaceTree) {
        this.workspaceTree = workspaceTree;
    }
    public RuTree getTree(){
        return tree;
    }
    public Subject getSubject(){return subject;}

    public ErrorHandler getError() {
        return error;
    }

    public void setError(ErrorHandler error) {
        this.error = error;
    }

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public AboutDialog getAboutDialog() {
        return aboutDialog;
    }

    public void setAboutDialog(AboutDialog aboutDialog) {
        this.aboutDialog = aboutDialog;
    }

    public Repository getDocumentRepository() {
        return documentRepository;
    }

    public void setTree(RuTree tree) {
        this.tree = tree;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public ProjectPanel getDesktop() {
        return desktop;
    }

    public void setDesktop(ProjectPanel desktop) {
        this.desktop = desktop;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
    }

    public void setSubject(Subject subject) {

        this.subject = subject;
    }

  public void showError(MyError message){
      JOptionPane.showMessageDialog(null, message.getMessage());
    }
}