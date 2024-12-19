package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperStore {
  private static DeveloperStore developerStore;
  List<String> developerList;

  public static DeveloperStore getInstance() {
    if (developerStore == null) {
      developerStore = new DeveloperStore();
    }
    return developerStore;
  }
}
