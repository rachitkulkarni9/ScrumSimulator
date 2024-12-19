package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewUserStoryForm extends JFrame implements BaseComponent {

  Double[] pointsList = {1.0, 2.0, 3.0, 5.0, 8.0, 11.0, 19.0, 30.0, 49.0};
  Double[] valueList = {0.0, 1.0, 3.0, 7.0, 11.0, 17.0, 23.0};
  Double[] sprintNum = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};

  public NewUserStoryForm() {
    this.init();
  }

  private JTextField nameField = new JTextField();
  private JTextArea descArea = new JTextArea();
  private JComboBox<Double> pointsCombo = new JComboBox<>(pointsList);
  private JComboBox<Double> valueCombo = new JComboBox<>(valueList);
  private JComboBox<Double> sprintCombo = new JComboBox<>(sprintNum);

  public void init() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("New User Story");
    setSize(600, 500);

    nameField = new JTextField();
    descArea = new JTextArea();
    pointsCombo = new JComboBox<>(pointsList);
    valueCombo = new JComboBox<>(valueList);

    GridBagLayout myGridbagLayout = new GridBagLayout();
    JPanel myJpanel = new JPanel();
    myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    myJpanel.setLayout(myGridbagLayout);

    BorderLayout myBorderLayout = new BorderLayout();

    setLayout(myBorderLayout);

    JLabel nameLabel = new JLabel("Name:");
    myJpanel.add(
        nameLabel,
        new CustomConstraints(0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        nameField,
        new CustomConstraints(
            1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

    JLabel descLabel = new JLabel("Description:");
    myJpanel.add(
        descLabel,
        new CustomConstraints(0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        new JScrollPane(descArea),
        new CustomConstraints(1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

    JLabel pointsLabel = new JLabel("Points:");
    myJpanel.add(
        pointsLabel,
        new CustomConstraints(0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        pointsCombo,
        new CustomConstraints(
            1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

    JLabel businessLabel = new JLabel("BusinessValue:");
    myJpanel.add(
        businessLabel,
        new CustomConstraints(0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        valueCombo,
        new CustomConstraints(
            1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

    JLabel sprintLabel = new JLabel("Sprint Number:");
    myJpanel.add(
        sprintLabel,
        new CustomConstraints(0, 4, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
    myJpanel.add(
        sprintCombo,
        new CustomConstraints(
            1, 4, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

    JButton cancelButton = new JButton("Cancel");

    cancelButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            dispose();
          }
        });

    JButton submitButton = new JButton("Submit");

    submitButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            dispose();
          }
        });

    myJpanel.add(
        cancelButton,
        new CustomConstraints(0, 5, GridBagConstraints.EAST, GridBagConstraints.NONE));
    myJpanel.add(
        submitButton,
        new CustomConstraints(1, 5, GridBagConstraints.WEST, GridBagConstraints.NONE));

    add(myJpanel);
  }

  public UserStory getUserStoryObject() {
    String name = nameField.getText();
    String description = descArea.getText();
    Double points = (Double) pointsCombo.getSelectedItem();
    Double businessValue = (Double) pointsCombo.getSelectedItem();
    Double sprintNum = (Double) sprintCombo.getSelectedItem();
    UserStoryFactory userStoryFactory = UserStoryFactory.getInstance();

    UserStory userStory =
        userStoryFactory.createNewUserStory(
            name, description, points, businessValue, "new", sprintNum, "N/A");

    userStory.doRegister();

    return userStory;
  }
}
