package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class UserStoryListPane extends JFrame implements BaseComponent {
  public UserStoryListPane() {
    this.init();
  }

  private List<UserStoryWidget> widgets = new ArrayList<>();

  public void init() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("User Story list");
    setSize(800, UserStoryStore.getInstance().getUserStories().size() * 10 + 400);
    setLocationRelativeTo(null);
    GridBagLayout myGridbagLayout = new GridBagLayout();
    JPanel myJpanel = new JPanel();
    myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    myJpanel.setLayout(myGridbagLayout);

    for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
      widgets.add(new UserStoryWidget(userStory, this));
    }

    JPanel subPanel = new JPanel();
    subPanel.setLayout(new GridBagLayout());
    int i = 0;
    for (UserStory us : UserStoryStore.getInstance().getUserStories()) {
      subPanel.add(
          new UserStoryWidget(us, this),
          new CustomConstraints(
              0, i++, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
    }

    myJpanel.add(
        new JScrollPane(subPanel),
        new CustomConstraints(
            0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

    JButton newSprintButton = new JButton("New User Story");
    newSprintButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            NewUserStoryForm form = new NewUserStoryForm();
            form.setVisible(true);

            form.addWindowListener(
                new java.awt.event.WindowAdapter() {
                  public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    UserStory userStory = form.getUserStoryObject();
                    UserStoryStore.getInstance().addUserStory(userStory);
                    widgets.add(new UserStoryWidget(userStory, null));
                    int idx = widgets.size() - 1;
                    subPanel.add(
                        widgets.get(idx),
                        new CustomConstraints(
                            0,
                            idx,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
                  }
                });
          }
        });
    myJpanel.add(
        newSprintButton,
        new CustomConstraints(
            0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));
    UserStoryListPane userStoryListPane = this;
    // Create the "Create Spike Story" button
    JButton createSpikeStoryButton = new JButton("Create Spike Story");
    createSpikeStoryButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            SpikeStoryForm spikeForm = new SpikeStoryForm(userStoryListPane);
            spikeForm.setVisible(true);
          }
        });
    myJpanel.add(
        createSpikeStoryButton,
        new CustomConstraints(
            0, 2, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

    add(myJpanel);
  }

  public void closeWindow() {
    dispose();
  }
}
