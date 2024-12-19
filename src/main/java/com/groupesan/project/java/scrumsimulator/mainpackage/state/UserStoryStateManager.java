package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UserStoryStateManager {

  private static final String FILE_PATH = "src/main/resources/Simulation.json";

  /**
   * Method to get all user stories in a simulation.
   *
   * @return List Returns a list of all user stories in the simulation
   */
  public static List<String> getUserStories() {
    List<String> userStories = new ArrayList<>();

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode root = objectMapper.readTree(new File(FILE_PATH));

      JsonNode sprints = root.path("Simulation").path("Sprints");
      for (JsonNode sprint : sprints) {
        JsonNode userStoriesInSprint = sprint.path("User Stories");
        for (JsonNode userStory : userStoriesInSprint) {
          userStories.add(userStory.path("Description").asText());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return userStories;
  }

  public static void updateUserStoryStatus(
      String selectedUserStory,
      String selectedStatus,
      String selectedBlockingUserStory,
      JPanel panel,
      String newStatus)
      throws IOException {

    int userStoryId = Integer.parseInt(selectedUserStory.split(":")[0].split("#")[1].strip());
    if ("blocker".equals(selectedStatus)) {

      if (selectedBlockingUserStory == null || selectedBlockingUserStory.trim().isEmpty()) {
        JOptionPane.showMessageDialog(
            panel,
            "Please select a Blocking User Story",
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
    }
    int blockingUserStoryId = 0;
    if (!selectedBlockingUserStory.isEmpty()) {
      blockingUserStoryId =
          Integer.parseInt(
              selectedBlockingUserStory.split(":")[0].replaceAll("[^0-9]", "").strip());
    }
    UserStory blockingUserStory = null;

    for (UserStory story : UserStoryStore.getInstance().getUserStories()) {
      int storyId = Integer.parseInt(story.getId().toString().replaceAll("[^0-9]", "").strip());
      if (storyId == blockingUserStoryId) {
        blockingUserStory = story;
        break;
      }
    }
    if (blockingUserStory != null) {
      blockingUserStory.setStatus("in progress");
    }
    List<UserStory> userStories = UserStoryStore.getInstance().getUserStories();
    for (UserStory userStory : userStories) {
      if (userStory.getId().getValue() == userStoryId) {
        // update the user story status
        userStory.setStatus(newStatus);
        userStory.setBlockingUserStory(blockingUserStory);
      }
    }
    UserStoryStore.getInstance().setUserStories(userStories);
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode root = objectMapper.readTree(new File(FILE_PATH));

    JsonNode sprints = root.path("Simulation").path("Sprints");
    for (JsonNode sprint : sprints) {
      JsonNode userStoriesInSprint = sprint.path("User Stories");
      for (JsonNode userStory : userStoriesInSprint) {
        if (userStory.path("Id").asText().equals(String.valueOf(userStoryId))) {
          ((com.fasterxml.jackson.databind.node.ObjectNode) userStory).put("Status", newStatus);
          break;
        }
      }
    }

    objectMapper.writeValue(new File(FILE_PATH), root);
  }
}
