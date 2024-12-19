package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockerSolutionListStore {

  private static BlockerSolutionListStore blockerSolutionListStore;

  /**
   * returns the shared instance of the BlockerSolutionListStore which contains list of blocker and
   * solution
   *
   * @return
   */
  public static BlockerSolutionListStore getInstance() {
    if (blockerSolutionListStore == null) {
      blockerSolutionListStore = new BlockerSolutionListStore();
    }
    return blockerSolutionListStore;
  }

  private Map<UserStory, Double> defaultBlockerList;
  private Map<UserStory, SolutionListValue> defaultSolutionList;

  private BlockerSolutionListStore() {
    defaultBlockerList = new HashMap<>();
    defaultSolutionList = new HashMap<>();
  }

  public void populateDefaultList() {
    List<UserStory> duplicateBlockerList = new ArrayList<>();

    defaultBlockerList.clear();
    Random random = new Random();
    List<UserStory> userStoryList = UserStoryStore.getInstance().getUserStories();
    int count = 0;
    while (count != 3) {
      int random_index = random.nextInt(userStoryList.size());
      double random_probability = random.nextDouble(0, 1);
      UserStory userStory = userStoryList.get(random_index);
      defaultBlockerList.put(userStory, random_probability);
      duplicateBlockerList.add(userStory);
      userStoryList.remove(userStory);
      count++;
    }

    // Solution list structure : solutionStory : { probability:"",blockedUserStory:""}
    for (int i = 0; i < duplicateBlockerList.size(); i++) {
      UserStory solutionUserStory = userStoryList.get(i);

      double random_probability = random.nextDouble(0, 1);
      int random_blockedUserStory_index = random.nextInt(duplicateBlockerList.size());

      SolutionListValue solutionListValue = new SolutionListValue();
      solutionListValue.setProbability(random_probability);
      solutionListValue.setBlockedUserStory(random_blockedUserStory_index);

      defaultSolutionList.put(solutionUserStory, solutionListValue);
      duplicateBlockerList.remove(random_blockedUserStory_index);
    }
  }
}
