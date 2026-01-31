package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkFlex; // Ensure REVLib is installed
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class targetSystem extends SubsystemBase {
  // 1. Declare the motor controller
  private final SparkFlex m_flexMotor;

  /** Creates a new New. */
  public targetSystem() {
    // 2. Initialize the Spark Flex with CAN ID 41
    m_flexMotor = new SparkFlex(41, MotorType.kBrushless);
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
}