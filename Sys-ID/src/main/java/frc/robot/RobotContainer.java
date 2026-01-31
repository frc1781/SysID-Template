package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Shooter; // Import your shooter command
import frc.robot.subsystems.targetSystem; // Import your subsystem

public class RobotContainer {
  // 1. Declare your subsystem
  private final targetSystem m_targetSystem = new targetSystem();

  // 2. Setup the controller (Port 0 is default)
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // 3. Bind the 'A' button to run the shooter at 80% speed while held
    // When released, the Shooter command's end() method will stop the motor automatically.
    m_driverController.a().whileTrue(new Shooter(m_targetSystem, 0.8));
    
    // Optional: Bind 'B' button to run zzzzzzzit backwards at 20% to clear jams
    m_driverController.b().whileTrue(new Shooter(m_targetSystem, -0.2));
  }

  public Command getAutonomousCommand() {
    // For now, this returns null (no auto logic yet)
    return null; 
  }
}