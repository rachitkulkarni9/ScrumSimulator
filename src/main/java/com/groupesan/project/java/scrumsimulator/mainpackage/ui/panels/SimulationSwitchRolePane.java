package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SimulationSwitchRolePane extends JFrame {

  private JRadioButton developerRadioButton;
  private JRadioButton scrumMasterRadioButton;
  private JRadioButton productOwnerRadioButton;
  private ButtonGroup roleButtonGroup;
  private JButton switchButton;
  private String currentRole = "";

  public String getCurrentRole() {
    return currentRole;
  }

  public void switchRole(DemoPane demoPane) {
    if (developerRadioButton.isSelected()) {
      currentRole = "Developer";
    } else if (scrumMasterRadioButton.isSelected()) {
      currentRole = "Scrum Master";
    } else if (productOwnerRadioButton.isSelected()) {
      currentRole = "Product Owner";
    } else {
      currentRole = "";
    }
    demoPane.updateRoleLabel(currentRole);
  }

  public SimulationSwitchRolePane(DemoPane demoPane) {
    setTitle("Switch Role");
    setSize(400, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 1));

    JLabel label = new JLabel("Roles:");
    panel.add(label);

    developerRadioButton = new JRadioButton("Developer");
    scrumMasterRadioButton = new JRadioButton("Scrum Master");
    productOwnerRadioButton = new JRadioButton("Product Owner");
    roleButtonGroup = new ButtonGroup();
    roleButtonGroup.add(developerRadioButton);
    roleButtonGroup.add(scrumMasterRadioButton);
    roleButtonGroup.add(productOwnerRadioButton);
    panel.add(developerRadioButton);
    panel.add(scrumMasterRadioButton);
    panel.add(productOwnerRadioButton);

    switchButton = new JButton("Switch Role");
    switchButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // Logic for join button
            onSwitchButtonClicked(demoPane);
          }
        });

    setLayout(new BorderLayout());
    add(switchButton, BorderLayout.SOUTH);
    add(panel);
  }

  private void onSwitchButtonClicked(DemoPane demoPane) {
    if (developerRadioButton.isSelected()) {
      currentRole = "Developer";
      JOptionPane.showMessageDialog(
          null, "Switched to Developer", "Role Switching", JOptionPane.PLAIN_MESSAGE);
    } else if (scrumMasterRadioButton.isSelected()) {
      currentRole = "Scrum Master";
      JOptionPane.showMessageDialog(
          null, "Switched to Scrum Master", "Role Switching", JOptionPane.PLAIN_MESSAGE);
    } else if (productOwnerRadioButton.isSelected()) {
      currentRole = "Product Owner";
      JOptionPane.showMessageDialog(
          null, "Switched to Product Owner", "Role Switching", JOptionPane.PLAIN_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null, "Failed to Switch Role", "Role Switching Error", JOptionPane.ERROR_MESSAGE);
    }
    demoPane.updateRoleLabel(currentRole);
    roleButtonGroup.clearSelection();
    dispose();
    return;
  }
}