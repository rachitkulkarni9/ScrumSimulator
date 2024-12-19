package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditUserStoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.UserStoryListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserStoryWidget extends JPanel implements BaseComponent {

  JLabel id;
  JLabel points;
  JLabel name;
  JLabel desc;
  JLabel status;
  JLabel developer;
  JLabel blocking_us;
  JLabel sprint_number;

  // TODO: This is a non transient field and this class is supposed to be serializable. this needs
  // to be dealt with before this object can be serialized
  private UserStory userStory;
  private UserStoryListPane parentWindow;

  MouseAdapter openEditDialog =
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          new EditUserStoryForm(userStory, parentWindow) {
            {
              setVisible(true);
              addWindowListener(
                  new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                      init();
                    }
                  });
            }
          };
        }
      };

  public UserStoryWidget(UserStory userStory, UserStoryListPane parentWindow) {
    this.userStory = userStory;
    this.parentWindow = parentWindow;
    this.init();
  }

  public void init() {
    removeAll();

    id = new JLabel(userStory.getId().toString());
    id.addMouseListener(openEditDialog);
    points = new JLabel(Double.toString(userStory.getPointValue()));
    points.addMouseListener(openEditDialog);
    name = new JLabel(userStory.getName());
    name.addMouseListener(openEditDialog);
    desc = new JLabel(userStory.getDescription());
    desc.addMouseListener(openEditDialog);
    status = new JLabel(userStory.getStatus());
    developer = new JLabel(userStory.getDevelopers());
    String blockingUserStoryName =
        (userStory.getBlockingUserStory() != null
                && userStory.getBlockingUserStory().getName() != null)
            ? userStory.getBlockingUserStory().getName()
            : "N/A"; // or any default message you prefer

    blocking_us = new JLabel(blockingUserStoryName);
    sprint_number = new JLabel(Double.toString(userStory.getSprintNumber()));
    sprint_number.addMouseListener(openEditDialog);

    GridBagLayout myGridBagLayout = new GridBagLayout();

    setLayout(myGridBagLayout);

    add(
        id,
        new CustomConstraints(
            0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
    add(
        points,
        new CustomConstraints(
            1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
    add(
        name,
        new CustomConstraints(
            2, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
    add(
        desc,
        new CustomConstraints(
            3, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
    add(
        status,
        new CustomConstraints(
            4, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
    add(
        developer,
        new CustomConstraints(
            5, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));

    add(
        sprint_number,
        new CustomConstraints(
            6, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
    add(
        blocking_us,
        new CustomConstraints(
            7, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
  }
}
