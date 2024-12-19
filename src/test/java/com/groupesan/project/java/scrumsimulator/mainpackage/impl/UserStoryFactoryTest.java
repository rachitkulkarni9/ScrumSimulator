package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserStoryFactoryTest {

  @Test
  public void testCreateNewUserStoryWithBusinessValue() {
    // Arrange
    UserStoryFactory factory = UserStoryFactory.getInstance();

    // Act
    UserStory userStory =
        factory.createNewUserStory("Story", "Description", 5.0, 15.0, "new", null, null);

    // Assert
    assertNotNull(userStory);
    assertEquals("Story", userStory.getName());
    assertEquals("Description", userStory.getDescription());
    assertEquals(5.0, userStory.getPointValue(), 0.01);
    assertEquals(15.0, userStory.getBusinessValue(), 0.01);
    assertEquals("new", userStory.getStatus());
  }

  @Test
  public void testFactoryCreatesUserStoryWithEdgeBusinessValue() {
    // Arrange
    UserStoryFactory factory = UserStoryFactory.getInstance();

    // Act
    UserStory userStory =
        factory.createNewUserStory("Edge Case", "High Value", 5.0, 1000.0, "new", null, null);

    // Assert
    assertNotNull(userStory);
    assertEquals("Edge Case", userStory.getName());
    assertEquals(1000.0, userStory.getBusinessValue(), 0.01);
  }

  @Test
  public void testSingletonFactoryInstance() {
    // Act
    UserStoryFactory factory1 = UserStoryFactory.getInstance();
    UserStoryFactory factory2 = UserStoryFactory.getInstance();

    // Assert
    assertNotNull(factory1);
    assertNotNull(factory2);
    assertEquals(
        factory1,
        factory2,
        "UserStoryFactory should return the same instance each time (singleton).");
  }
}
