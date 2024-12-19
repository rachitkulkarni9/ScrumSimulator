package com.groupesan.project.java.scrumsimulator.mainpackage.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.DeveloperStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.DemoPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
  public App() {}

  public void start() {
    this.loadTheme();
    SwingUtilities.invokeLater(
        new Runnable() {
          @Override
          public void run() {
            // Initialize User Stories in helper function now
            initializeUserStories();
            initializeDeveloperList();
            initializeDeveloperList();
            // Load DemoPane
            DemoPane form = new DemoPane();
            form.setVisible(true);
          }
        });
  }

  private void initializeDeveloperList() {

    List<String> developerName = new ArrayList<>();
    developerName.add("Rachit Kulkarni");
    developerName.add("Dheemanth Raviraj");
    developerName.add("Pavan Samarth");
    developerName.add("Rachana Angara");
    developerName.add("Ritu Malav");

    DeveloperStore.getInstance().setDeveloperList(developerName);
  }

  private void initializeUserStories() {
    UserStory a =
        UserStoryFactory.getInstance()
            .createNewUserStory("predefinedUS1", "description1", 1.0, 1.0, "new", 1.0, "N/A");
    a.doRegister();
    UserStoryStore.getInstance().addUserStory(a);

    UserStory b =
        UserStoryFactory.getInstance()
            .createNewUserStory(
                "predefinedUS2", "description2", 2.0, 2.0, "in progress", 1.0, "N/A");
    b.doRegister();
    UserStoryStore.getInstance().addUserStory(b);

    UserStory c =
        UserStoryFactory.getInstance()
            .createNewUserStory("predefinedUS3", "description3", 3.0, 3.0, "completed", 1.0, "N/A");
    c.doRegister();
    UserStoryStore.getInstance().addUserStory(c);
    UserStory d =
        UserStoryFactory.getInstance()
            .createNewUserStory("predefinedUS4", "description1", 1.0, 1.0, "new", 2.0, "N/A");
    d.doRegister();
    UserStoryStore.getInstance().addUserStory(d);

    UserStory e =
        UserStoryFactory.getInstance()
            .createNewUserStory("predefinedUS5", "description1", 1.0, 1.0, "new", 2.0, "N/A");
    e.doRegister();
    UserStoryStore.getInstance().addUserStory(e);

    UserStory f =
        UserStoryFactory.getInstance()
            .createNewUserStory("predefinedUS6", "description1", 1.0, 1.0, "new", 2.0, "N/A");
    f.doRegister();
    UserStoryStore.getInstance().addUserStory(f);

    UserStory g =
        UserStoryFactory.getInstance()
            .createNewUserStory("predefinedUS7", "description1", 1.0, 1.0, "new", 2.0, "N/A");
    g.doRegister();
    UserStoryStore.getInstance().addUserStory(g);

    UserStory h =
        UserStoryFactory.getInstance()
            .createNewUserStory("predefinedUS8", "description1", 1.0, 1.0, "new", 2.0, "N/A");
    h.doRegister();
    UserStoryStore.getInstance().addUserStory(h);
  }

  private void loadTheme() {
    try {
      // TODO support setting theme from a configuration file
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (UnsupportedLookAndFeelException e) {
      throw new RuntimeException(e);
    }
  }
}
