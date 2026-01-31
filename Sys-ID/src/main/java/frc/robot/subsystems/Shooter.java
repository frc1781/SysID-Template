package frc.robot.subsystems;

import edu.wpi.first.units.measure.MutAngle;
import edu.wpi.first.units.measure.MutAngularVelocity;
import edu.wpi.first.units.measure.MutVoltage;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.revrobotics.spark.SparkFlex; // Ensure REVLib is installed
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  // 1. Declare the motor controller
  private final SparkFlex m_flexMotor;
  private SysIdRoutine sysIdRoutine;

      // mutable stuff idfkwhy

    private final MutVoltage m_appliedVoltage = Volts.mutable(0);
    private final MutAngle m_angle = Rotations.mutable(0);
    private final MutAngularVelocity m_velocity = RotationsPerSecond.mutable(0);

  public Shooter() {
    // 2. Initialize the Spark Flex with CAN ID 41
    m_flexMotor = new SparkFlex(41, MotorType.kBrushless);

    sysIdRoutine = new SysIdRoutine(
        new SysIdRoutine.Config(),
        new SysIdRoutine.Mechanism(
            voltage -> {
              m_flexMotor.setVoltage(voltage);
            }, 
            log -> {
              log.motor("shooter")
                  .voltage(
                      m_appliedVoltage.mut_replace(
                          m_flexMotor.getBusVoltage(), Volts))
                  .angularPosition(
                      m_angle.mut_replace(m_flexMotor.getEncoder().getPosition(), Rotations))
                  .angularVelocity(
                      m_velocity.mut_replace(m_flexMotor.getEncoder().getVelocity(), RotationsPerSecond));
            },
            this));

  }

  // 3. Create a method to set the motor speed
  public void runMotor(double speed) {
    m_flexMotor.set(speed);
  }

  // 4. Create a method to stop the motor
  public void stopMotor() {
    m_flexMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public SysIdRoutine shooterSysID() {
    return sysIdRoutine;
  }
}