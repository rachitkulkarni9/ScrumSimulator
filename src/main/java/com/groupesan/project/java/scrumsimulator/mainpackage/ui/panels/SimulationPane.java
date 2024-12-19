package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Developer;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.DeveloperStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * SimulationPane is a part of the UI in the scrum simulator.
 *
 * <p>Todo: logic/controller portions of original FeedbackPanel.java
 *
 * @version 0.1
 * @since 2023-11-8
 */
public class SimulationPane {
  private JButton addDeveloperButton;
  private JPopupMenu developerNameField;
  private int countOfDevelopers = 0;
  private String windowWidth;

  /** The simulation Pane for adding new users. */
  public SimulationPane() {

    JFrame frame = new JFrame();
    windowWidth = String.valueOf(300);
    frame.setTitle("Developer Details");
    frame.setSize(Integer.parseInt(windowWidth), 270);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setVisible(true);
    JPanel panel = new JPanel();
    GridBagLayout gridBagLayout = new GridBagLayout();
    panel.setLayout(gridBagLayout);

    JLabel developerLabel = new JLabel("Developer Name:");
    List<JCheckBox> checkBoxMenuItems = new ArrayList<>();
    addDeveloperButton = new JButton("Add Developer");
    JTextArea displayExistingDeveloperList = new JTextArea();

    StringBuilder existingDevelopers = new StringBuilder();
    for (String developer : DeveloperStore.getInstance().getDeveloperList()) {
      countOfDevelopers++;
      existingDevelopers.append(developer).append("\n");
    }
    displayExistingDeveloperList.setText(existingDevelopers.toString());
    displayExistingDeveloperList.setEditable(false);

    JTextField newDeveloperField = new JTextField();
    JLabel error = new JLabel();
    error.hide();
    panel.add(
        developerLabel,
        new CustomConstraints(
            0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    panel.add(
        newDeveloperField,
        new CustomConstraints(
            1, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    panel.add(
        error,
        new CustomConstraints(
            0, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    panel.add(
        addDeveloperButton,
        new CustomConstraints(
            0, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    panel.add(
        new JLabel("Developer list present in the system"),
        new CustomConstraints(
            0, 4, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    panel.add(
        displayExistingDeveloperList,
        new CustomConstraints(
            0, 5, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

    addDeveloperButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            countOfDevelopers =
                Developer.appendSelectedOptions(
                    newDeveloperField.getText(), displayExistingDeveloperList, error, frame);
          }
        });
    frame.setLayout(new BorderLayout());
    frame.add(panel);
  }
}
