package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.targetSystem;

public class Shooter extends Command {

  private final targetSystem m_targetSystem;
  private final double m_speed;

  public Shooter(targetSystem subsystem, double speed) {
    m_targetSystem = subsystem;
    m_speed = speed;
    addRequirements(m_targetSystem);
  }

  @Override
  public void initialize() {
    m_targetSystem.runMotor(m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_targetSystem.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return false; // runs while held
  }
}
