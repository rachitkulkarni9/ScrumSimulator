package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.DeveloperStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SpikeStoryForm extends JFrame {

  Double[] effortPointsList = {1.0, 2.0, 3.0, 5.0, 8.0, 11.0, 19.0, 30.0, 49.0};
  UserStoryListPane parentWindow;

  public SpikeStoryForm(UserStoryListPane userStoryListPane) {
    parentWindow = userStoryListPane;
    this.init();
  }

  private JPopupMenu developerNameField;
  private JComboBox<Double> effortPointsCombo = new JComboBox<>(effortPointsList);
  private JComboBox<String> blockingStoryCombo = new JComboBox<>(getUserStories());
  private JButton submitButton = new JButton("Submit");

  public void init() {
    developerNameField = new JPopupMenu();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Spike Story");
    setSize(500, 200);
    setLocationRelativeTo(null);
    GridBagLayout myGridbagLayout = new GridBagLayout();
    JPanel myJpanel = new JPanel();
    myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    myJpanel.setLayout(myGridbagLayout);

    developerNameField = new JPopupMenu();

    for (String developerName : DeveloperStore.getInstance().getDeveloperList()) {
      JCheckBox jCheckBox = new JCheckBox(developerName);
      developerNameField.add(jCheckBox);
    }
    JLabel developerLabel = new JLabel("Developers Working:");
    JLabel effortPointsLabel = new JLabel("Effort Points:");
    JLabel blockingStoryLabel = new JLabel("Select Blocked Story:");

    JButton dropdownButton = new JButton("Select Developers");
    dropdownButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            developerNameField.show(dropdownButton, 3, dropdownButton.getHeight());
          }
        });
    myJpanel.add(
        developerLabel,
        new CustomConstraints(0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        dropdownButton,
        new CustomConstraints(
            1, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        effortPointsLabel,
        new CustomConstraints(0, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        effortPointsCombo,
        new CustomConstraints(
            1, 1, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        blockingStoryLabel,
        new CustomConstraints(0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        blockingStoryCombo,
        new CustomConstraints(
            1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
    JPanel buttonPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);

    JButton cancelButton = new JButton("Cancel");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    buttonPanel.add(cancelButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    buttonPanel.add(submitButton, gbc);

    cancelButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            dispose();
          }
        });
    List<String> spikeDeveloperList = new ArrayList<>();
    submitButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            for (Component component : developerNameField.getComponents()) {
              if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                  spikeDeveloperList.add(checkBox.getText());
                }
              }
            }
            Double selectedEffortPoint = (Double) effortPointsCombo.getSelectedItem();
            String userStory = (String) blockingStoryCombo.getSelectedItem();
            SpikeStory.createSpikeStory(spikeDeveloperList, selectedEffortPoint, userStory);
            JOptionPane.showMessageDialog(null, "Spike story created successfully");
            parentWindow.closeWindow();
            dispose();
          }
        });
    myJpanel.add(
        buttonPanel,
        new CustomConstraints(0, 3, GridBagConstraints.CENTER, GridBagConstraints.NONE));

    add(myJpanel);
  }

  private String[] getUserStories() {
    UserStoryStore userStoryStore = UserStoryStore.getInstance();
    List<UserStory> userStories = userStoryStore.getUserStories();
    return userStories.stream().map(UserStory::getName).toArray(String[]::new);
  }
}
