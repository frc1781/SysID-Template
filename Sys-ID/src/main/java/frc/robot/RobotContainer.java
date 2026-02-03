package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Shooter; // Import your subsystem

public class RobotContainer {
  // 1. Declare your subsystem
  private final Shooter m_Shooter = new Shooter();

  // 2. Setup the controller (Port 0 is default)
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings(); 
  }

  private void configureBindings() {
    m_driverController.a().whileTrue(
      m_Shooter.shooterSysID().dynamic(SysIdRoutine.Direction.kForward)
      .andThen(m_Shooter.shooterSysID().quasistatic(SysIdRoutine.Direction.kForward))
      .andThen(m_Shooter.shooterSysID().dynamic(SysIdRoutine.Direction.kReverse))
      .andThen(m_Shooter.shooterSysID().quasistatic(SysIdRoutine.Direction.kReverse))
      .andThen(m_Shooter.imDone()) 
    );
    m_driverController.b().whileTrue(new Shoot(m_Shooter, 100));
  }

  public Command getAutonomousCommand() {
    // For now, this returns null (no auto logic yet)
    return null; 
  }
}