package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class UserStoryStore {

  private static UserStoryStore userStoryStore;

  /**
   * returns the shared instance of the UserStoryStore which contains all user stories in the
   * system.
   *
   * @return
   */
  public static UserStoryStore getInstance() {
    if (userStoryStore == null) {
      userStoryStore = new UserStoryStore();
    }
    return userStoryStore;
  }

  private List<UserStory> userStories;

  private UserStoryStore() {
    userStories = new ArrayList<>();
  }

  public void addUserStory(UserStory userStory) {
    userStories.add(userStory);
  }

  public List<UserStory> getUserStories() {
    return new ArrayList<>(userStories);
  }

  public void setUserStories(List<UserStory> userStories) {
    this.userStories = userStories;
  }

  public void removeUserStory(UserStory userStory) {
    int index = -1;
    for (UserStory us : userStories) {
      if (us.getId().equals(userStory.getId())) {
        index = userStories.indexOf(us);
        break;
      }
    }
    userStories.remove(index);
    setUserStories(userStories);
  }
}