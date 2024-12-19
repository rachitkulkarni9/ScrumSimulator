package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeveloperStoreTest {
  DeveloperStore developerStore;

  @Test
  public void setterAndGetter() {
    developerStore = DeveloperStore.getInstance();

    List<String> developerList = new ArrayList<>();
    developerStore.setDeveloperList(developerList);
    assertTrue(developerStore.getDeveloperList().size() == 0);
  }
}
