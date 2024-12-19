package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserStoryTest {
  private UserStory myUserStory;
  private UserStory userStory;
  private UserStory newUserStory;

  @BeforeEach
  public void setup() {
    myUserStory =
        UserStoryFactory.getInstance()
            .createNewUserStory(
                "predefinedUS1", "description1", 1.0, 1.0, "in progress", null, null);

    userStory = new UserStory("User Story 1", "Description", 5.0, 8.0, "new", null, null, null);
    newUserStory =
        new UserStory("Blocking Story", "Description", 5.0, 8.0, "blocker", null, null, null);
  }

  @Test
  public void testUserStoryUnregistered1() {
    // modified from example code from Baeldung
    // https://www.baeldung.com/junit-assert-exception

    Exception exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              ScrumIdentifier id = myUserStory.getId();
            });

    String actualMessage = exception.getMessage();

    assertEquals(
        "This UserStory has not been registered and does not have an ID yet!", actualMessage);
  }

  @Test
  public void testUserStoryUnregistered2() {
    String string = myUserStory.toString();

    assertEquals("(unregistered) - predefinedUS1", string);
  }

  @Test
  public void testUserStoryRegistered() {
    myUserStory.doRegister();

    ScrumIdentifier id = myUserStory.getId();

    assertNotNull(id);
  }

  @Test
  public void testGetBusinessValue() {
    assertEquals(8.0, userStory.getBusinessValue(), 0.01);
  }

  @Test
  public void testSetBusinessValue() {
    userStory.setBusinessValue(10.0);
    assertEquals(10.0, userStory.getBusinessValue(), 0.01);
  }

  @Test
  public void testSetBusinessValueNegative() {
    userStory.setBusinessValue(-5.0);
  }

  @Test
  public void testSetNameDoesNotAlterBusinessValue() {
    userStory.setName("Updated Story Name");
    assertEquals(8.0, userStory.getBusinessValue(), 0.01);
  }

  @Test
  public void testStatusUsingConstructor() {
    assertEquals(myUserStory.getStatus(), "in progress");
    myUserStory.setStatus("new");
    assertEquals(myUserStory.getStatus(), "new");
  }

  @Test
  public void testStoryUsingConstructor() {
    userStory.setBlockingUserStory(newUserStory);
    assertEquals(
        newUserStory,
        userStory.getBlockingUserStory(),
        "The blocking user story should match the one set.");
  }
}
