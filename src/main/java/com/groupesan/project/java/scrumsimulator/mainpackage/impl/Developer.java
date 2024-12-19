package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Developer extends ScrumRole {

  // Task implementation goes here once done

  public Developer(Player player) {
    super("Developer");
  }

  @Override
  public String toString() {
    return "[Developer]" + super.toString();
  }

  public static int appendSelectedOptions(
      String newDeveloperField,
      JTextArea displayExistingDeveloperList,
      JLabel error,
      JFrame frame) {
    List<String> newList = new ArrayList<>(DeveloperStore.getInstance().getDeveloperList());
    int countOfDevelopers = DeveloperStore.getInstance().getDeveloperList().size();
    countOfDevelopers++;
    if (newList.contains(newDeveloperField)) {
      error.setText("This developer name already exists in the database.");
      frame.setSize(frame.getWidth() + 80, frame.getHeight());
      error.show(true);
    } else {
      error.setText("");
      frame.setSize(300, frame.getHeight() + 10);
      error.show(false);
      newList.add(newDeveloperField);
      StringBuilder developerFinalList = new StringBuilder();
      for (String developerName : newList) {
        developerFinalList.append(developerName).append("\n");
      }
      DeveloperStore.getInstance().setDeveloperList(newList);
      displayExistingDeveloperList.setText(developerFinalList.toString());
    }
    return countOfDevelopers;
  }
}
