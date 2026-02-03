package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class Shoot extends Command {

  private final Shooter m_Shooter;
  private final double m_speed;

  public Shoot(Shooter subsystem, double speed) {
    m_Shooter = subsystem;
    m_speed = speed;
    addRequirements(m_Shooter);
  }

  @Override
  public void initialize() {
    m_Shooter.setMotor(m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_Shooter.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return false; // runs while held
  }
}
