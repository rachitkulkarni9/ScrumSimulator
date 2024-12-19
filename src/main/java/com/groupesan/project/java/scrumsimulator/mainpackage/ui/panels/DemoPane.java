package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerSolutionListStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.ProductBacklogStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SolutionListValue;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.WizardManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DemoPane extends JFrame implements BaseComponent {
  private Player player = new Player("bob", new ScrumRole("demo"));
  private JLabel welcomeLabel;

  public DemoPane() {
    this.init();
    player.doRegister();
  }

  public void updateRoleLabel(String role) {
    welcomeLabel.setText("Hello " + role + "!");
  }

  /**
   * Initialization of Demo Pane. Demonstrates creation of User stories, Sprints, and Simulation
   * start.
   */
  public void init() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Demo");
    setSize(1200, 300);

    GridBagLayout myGridbagLayout = new GridBagLayout();
    JPanel myJpanel = new JPanel();
    myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    myJpanel.setLayout(myGridbagLayout);
    JButton blockerSetButton = new JButton("Blocker");
    JButton solutionSetButton = new JButton("Solution");

    welcomeLabel = new JLabel();
    welcomeLabel.setText("Hello!");
    myJpanel.add(
        welcomeLabel,
        new CustomConstraints(
            0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    JButton blockerProbability = new JButton("Fine Tune Selected Blocker's Probability");
    JButton solutionProbability = new JButton("Fine Tune Selected Solution's Probability");

    JButton sprintsButton = new JButton("Sprints");
    JLabel probabilityLabel = new JLabel("Probability");
    JComboBox<String> blockerDropdown = new JComboBox<String>();
    JComboBox<String> solutionDropdown = new JComboBox<String>();

    JTextField probabilityField = new JTextField();
    probabilityField.setEditable(false);
    JLabel rangeLabel = new JLabel("Range (0-1)");
    JTextField rangeField = new JTextField();

    blockerDropdown.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String selectedDropdownId =
                blockerDropdown.getSelectedItem().toString().split("-")[0].strip();
            for (UserStory userStory :
                BlockerSolutionListStore.getInstance().getDefaultBlockerList().keySet()) {
              if (userStory.getId().toString().equalsIgnoreCase(selectedDropdownId)) {
                probabilityField.setText(
                    BlockerSolutionListStore.getInstance()
                        .getDefaultBlockerList()
                        .get(userStory)
                        .toString());
                break;
              }
            }
          }
        });
    blockerProbability.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Double newRange = Double.valueOf(rangeField.getText());
            Map<UserStory, Double> blockerList =
                BlockerSolutionListStore.getInstance().getDefaultBlockerList();
            for (UserStory userStory : blockerList.keySet()) {
              if (userStory
                  .getId()
                  .toString()
                  .equalsIgnoreCase(
                      blockerDropdown.getSelectedItem().toString().split("-")[0].strip())) {
                blockerList.put(userStory, newRange);
                probabilityField.setText(newRange.toString());
                break;
              }
            }
            rangeField.setText("");
          }
        });

    blockerSetButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("Fine tuning Blocker probability");
            frame.setSize(500, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            JLabel listOfBlockers = new JLabel("Blocker");

            for (UserStory userStory :
                BlockerSolutionListStore.getInstance().getDefaultBlockerList().keySet()) {
              blockerDropdown.addItem(userStory.getId() + "-" + userStory.getName());
            }

            panel.add(
                listOfBlockers,
                new CustomConstraints(
                    0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                blockerDropdown,
                new CustomConstraints(
                    2, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                probabilityLabel,
                new CustomConstraints(
                    0, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                probabilityField,
                new CustomConstraints(
                    2, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                rangeLabel,
                new CustomConstraints(
                    0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                rangeField,
                new CustomConstraints(
                    2, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                blockerProbability,
                new CustomConstraints(
                    1, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            frame.setVisible(true);
            frame.add(panel);
          }
        });
    solutionDropdown.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String selectedDropdownId =
                solutionDropdown.getSelectedItem().toString().split("-")[0].strip();
            for (UserStory userStory :
                BlockerSolutionListStore.getInstance().getDefaultSolutionList().keySet()) {
              if (userStory.getId().toString().equalsIgnoreCase(selectedDropdownId)) {
                probabilityField.setText(
                    String.valueOf(
                        BlockerSolutionListStore.getInstance()
                            .getDefaultSolutionList()
                            .get(userStory)
                            .getProbability()));
                break;
              }
            }
          }
        });
    solutionProbability.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Double newRange = Double.valueOf(rangeField.getText());
            Map<UserStory, SolutionListValue> solutionList =
                BlockerSolutionListStore.getInstance().getDefaultSolutionList();
            for (UserStory userStory : solutionList.keySet()) {
              if (userStory
                  .getId()
                  .toString()
                  .equalsIgnoreCase(
                      solutionDropdown.getSelectedItem().toString().split("-")[0].strip())) {
                SolutionListValue solutionListValue = solutionList.get(userStory);
                solutionListValue.setProbability(newRange);
                solutionList.put(userStory, solutionListValue);
                probabilityField.setText(newRange.toString());
                break;
              }
            }
            rangeField.setText("");
          }
        });

    solutionSetButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("Fine tuning Solution probability");
            frame.setSize(500, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            JLabel listOfSolution = new JLabel("Solution");

            for (UserStory userStory :
                BlockerSolutionListStore.getInstance().getDefaultSolutionList().keySet()) {
              solutionDropdown.addItem(userStory.getId() + "-" + userStory.getName());
            }

            panel.add(
                listOfSolution,
                new CustomConstraints(
                    0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                solutionDropdown,
                new CustomConstraints(
                    2, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                probabilityLabel,
                new CustomConstraints(
                    0, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                probabilityField,
                new CustomConstraints(
                    2, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                rangeLabel,
                new CustomConstraints(
                    0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                rangeField,
                new CustomConstraints(
                    2, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            panel.add(
                solutionProbability,
                new CustomConstraints(
                    1, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
            frame.setVisible(true);
            frame.add(panel);
          }
        });
    sprintsButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            SprintListPane form = new SprintListPane();
            form.setVisible(true);
          }
        });

    SimulationStateManager simulationStateManager = new SimulationStateManager();
    SimulationPanel simulationPanel = new SimulationPanel(simulationStateManager);
    myJpanel.add(
        simulationPanel,
        new CustomConstraints(
            2, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    myJpanel.add(
        sprintsButton,
        new CustomConstraints(
            0, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    JButton userStoriesButton = new JButton("User Stories");
    userStoriesButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            UserStoryListPane form = new UserStoryListPane();
            form.setVisible(true);
          }
        });

    myJpanel.add(
        userStoriesButton,
        new CustomConstraints(
            1, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    JButton updateStoryStatusButton = new JButton("Update User Story Status");
    updateStoryStatusButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            UpdateUserStoryPanel form = new UpdateUserStoryPanel();
            form.setVisible(true);
          }
        });
    JButton productBacklogButton = new JButton("Product Backlog");
    JButton fineTuneProbabilityButton = new JButton("Fine Tune Probability");
    myJpanel.add(
        fineTuneProbabilityButton,
        new CustomConstraints(
            1, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    fineTuneProbabilityButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

            // Open a window on button click
            JFrame newFrame = new JFrame("Fine Tune Probability");
            newFrame.setSize(1100, 100);
            newFrame.setVisible(true);
            newFrame.setLocationRelativeTo(null);

            // Display a window with two buttons
            JPanel twoButtonWindow = new JPanel();
            twoButtonWindow.setLayout(new GridLayout(1, 2, 10, 10));
            twoButtonWindow.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Trigger the java method to auto populate the blocker and solution list
            BlockerSolutionListStore.getInstance().populateDefaultList();

            // Two buttons for the window
            JLabel label =
                new JLabel("Please select from the available choices to configure probability.\n");

            // Populate the screen dropdown from backend
            Map<UserStory, Double> blockerList =
                BlockerSolutionListStore.getInstance().getDefaultBlockerList();
            Map<UserStory, SolutionListValue> solutionList =
                BlockerSolutionListStore.getInstance().getDefaultSolutionList();

            twoButtonWindow.add(label);
            twoButtonWindow.add(blockerSetButton);
            twoButtonWindow.add(solutionSetButton);

            newFrame.add(twoButtonWindow);
          }
        });
    myJpanel.add(
        productBacklogButton,
        new CustomConstraints(
            3, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    productBacklogButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

            // Open a new window on button click
            JFrame newWindow = new JFrame("Product Backlog");
            newWindow.setSize(300, 500);
            newWindow.setVisible(true);
            // Display all the user stories in the window

            JPanel panel = new JPanel();

            List<UserStory> listOfUserStories =
                ProductBacklogStore.getInstance().getUserStoriesFromProductBacklog();
            panel.setLayout(new GridLayout(listOfUserStories.size(), 1, 10, 10));
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));

            for (UserStory userStory : listOfUserStories) {
              JCheckBox chinButton = new JCheckBox(userStory.getName());
              panel.add(chinButton);
            }
            newWindow.add(panel);
            int heightForWindow = 90 + (listOfUserStories.size() * 10);
            newWindow.setSize(250, heightForWindow);
          }
        });
    myJpanel.add(
        updateStoryStatusButton,
        new CustomConstraints(
            3, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    // Simulation button for Demo
    JButton simulationButton = new JButton("Add Developer");
    simulationButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            SimulationPane simulationPane = new SimulationPane();
          }
        });

    myJpanel.add(
        simulationButton,
        new CustomConstraints(
            7, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    // Modify Simulation button
    JButton modifySimulationButton = new JButton("Modify Simulation");
    modifySimulationButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            SimulationManager simulationManager = new SimulationManager();
            ModifySimulationPane modifySimulationPane = new ModifySimulationPane(simulationManager);
            modifySimulationPane.setVisible(true);
          }
        });

    // Add the button to the panel
    myJpanel.add(
        modifySimulationButton,
        new CustomConstraints(
            5, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    // *** Role Selection now through SimulationUI ***
    // JButton roleSelectionButton = new JButton("Role Selection");
    // roleSelectionButton.addActionListener(
    //         new ActionListener() {
    //             @Override
    //             public void actionPerformed(ActionEvent e) {
    //                 RoleSelectionPane roleSelectionPane = new RoleSelectionPane();
    //                 roleSelectionPane.setVisible(true);
    //             }
    //         });

    // myJpanel.add(
    //         roleSelectionButton,
    //         new CustomConstraints(
    //                 4, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    // *** Role Selection now through SimulationUI ***

    // Join Simulation button
    JButton joinSimulationButton = new JButton("Join Simulation");
    joinSimulationButton.addActionListener(
        e -> {
          SimulationUI simulationUserInterface = new SimulationUI();
          simulationUserInterface.setVisible(true);
        });

    myJpanel.add(
        joinSimulationButton,
        new CustomConstraints(
            6, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    // Simulation button for Demo
    JButton simulationSwitchRoleButton = new JButton("Switch Role");
    simulationSwitchRoleButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane(DemoPane.this);
            feedbackPanelUI.switchRole(DemoPane.this);
            feedbackPanelUI.setVisible(true);
          }
        });

    myJpanel.add(
        simulationSwitchRoleButton,
        new CustomConstraints(
            1, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    // New button for Variant Simulation UI
    JButton variantSimulationUIButton = new JButton("Variant Simulation UI");
    variantSimulationUIButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            VariantSimulationUI variantSimulationUI = new VariantSimulationUI();
            variantSimulationUI.setVisible(true);
          }
        });

    // Adding the button to the panel
    myJpanel.add(
        variantSimulationUIButton,
        new CustomConstraints(
            3, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    JButton SprintUIButton = new JButton("US Selection UI");
    SprintUIButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // Load SprintUIPane
            SprintUIPane sprintUIPane = new SprintUIPane(player);
            sprintUIPane.setVisible(true);
          }
        });

    // Adding the button to the panel
    myJpanel.add(
        SprintUIButton,
        new CustomConstraints(
            8, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    JButton newSimulationButton = new JButton("Create New Simulation");
    myJpanel.add(
        newSimulationButton,
        new CustomConstraints(
            2, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    newSimulationButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            WizardManager.get().showSimulationWizard();
          }
        });

    add(myJpanel);
  }
}
