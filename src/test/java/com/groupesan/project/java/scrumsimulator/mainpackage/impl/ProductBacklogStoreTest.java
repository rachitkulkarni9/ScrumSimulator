package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class ProductBacklogStoreTest {
  ProductBacklogStore productbacklog;

  @BeforeEach
  public void setup() {
    productbacklog = ProductBacklogStore.getInstance();
  }

  @Test
  public void getUserStoriesFromProductBacklogTest() {

    try (MockedStatic<UserStoryStore> mockedStore = mockStatic(UserStoryStore.class)) {
      UserStoryStore mockUserStoryStore = mock(UserStoryStore.class);
      List<UserStory> userstorieslist = new ArrayList<UserStory>();
      UserStory userstory1 = new UserStory("XYZ", "ABC", 2.0, 1.0, "new", null, null, null);
      userstory1.doRegister();

      UserStory userstory2 =
          new UserStory(
              "Rachana Rocks", "That is not true", 2.0, 3.0, "completed", null, null, null);
      userstory2.doRegister();
      userstorieslist.add(userstory1);
      userstorieslist.add(userstory2);
      when(mockUserStoryStore.getUserStories()).thenReturn(userstorieslist);
      mockedStore.when(UserStoryStore::getInstance).thenReturn(mockUserStoryStore);
      List<UserStory> userstory_returned = productbacklog.getUserStoriesFromProductBacklog();
      assertTrue(userstory_returned.size() == 1);
      assertEquals(userstory_returned.get(0).getStatus(), "new");
    }
  }
}
