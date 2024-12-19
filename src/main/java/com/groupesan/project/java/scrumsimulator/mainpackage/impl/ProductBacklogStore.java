package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductBacklogStore {
  private static ProductBacklogStore productBacklogStore;

  /**
   * returns the shared instance of the ProductBacklogStore which contains all user stories in the
   * product backlog.
   *
   * @return
   */
  public static ProductBacklogStore getInstance() {
    if (productBacklogStore == null) {
      productBacklogStore = new ProductBacklogStore();
    }
    return productBacklogStore;
  }

  private List<UserStory> productBacklog;

  private ProductBacklogStore() {
    productBacklog = new ArrayList<>();
  }

  public void addUserStoryInProductBacklog(UserStory userStory) {
    if (userStory.getStatus().equals("new")) productBacklog.add(userStory);
  }

  public List<UserStory> getUserStoriesFromProductBacklog() {
    List<UserStory> userstorieslist = UserStoryStore.getInstance().getUserStories();
    List<UserStory> productBacklog = new ArrayList<>();
    for (UserStory product : userstorieslist) {
      if (product.getStatus().equals("new")) {
        productBacklog.add(product);
      }
    }
    return productBacklog;
  }
}
