package com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Simulation;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.Wizard;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.WizardHandler;
import java.util.ArrayList;
import java.util.List;

public class SimulationWizard extends Wizard<Simulation> {
  private DataModel<String> simulationName;
  private DataModel<Object> sprintCount;
  private DataModel<List<Player>> users;

  public SimulationWizard(WizardHandler<Simulation> handler) {
    super(handler);
    setTitle("New Simulation");
  }

  @Override
  protected void initDataModels() {
    this.simulationName = new DataModel<>("New Simulation");
    this.sprintCount = new DataModel<>(1);
    this.users = new DataModel<>(new ArrayList<>());
  }

  protected List<WizardPage> build() {
    return List.of(new GeneralPage(simulationName, sprintCount));
  }

  @Override
  protected Simulation process() {
    Simulation simulation =
        new Simulation(simulationName.getData(), null, (Integer) sprintCount.getData());
    for (Player player : users.getData()) {
      player.doRegister();
      simulation.addPlayer(player);
    }
    return simulation;
  }
}
