package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.List;

public class SpikeStory {

  public static void createSpikeStory(
      List<String> developerList, Double pointsValue, String userStoryName) {
    UserStory blockedUserStory = null;
    StringBuilder developers = new StringBuilder();
    for (String developer : developerList) {
      developers.append(developer).append(",");
    }
    for (UserStory userStoryLoop : UserStoryStore.getInstance().getUserStories()) {
      if (userStoryLoop.getName().equalsIgnoreCase(userStoryName)) {
        blockedUserStory = userStoryLoop;
        blockedUserStory.setStatus("blocker");
        break;
      }
    }

    UserStory userStory =
        new UserStory(
            "Spike Story",
            "Story created when blocked",
            pointsValue,
            blockedUserStory.getBusinessValue(),
            "in progress",
            null,
            developers.toString(),
            blockedUserStory.getSprintNumber());
    userStory.doRegister();
    UserStoryStore.getInstance().addUserStory(userStory);
  }
}
