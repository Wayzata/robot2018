package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Master Code

// @Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Robot extends IterativeRobot {
	
// Main class that will use other classes and call methods to run the robot
	
	//Joysticks
	Joystick leftJ;
	Joystick rightJ;
	
	//Motors/Talons
	TalonSRX frontLeft;
	TalonSRX frontRight;
	TalonSRX backLeft;
	TalonSRX backRight;
	
	//Gyro
	ADXRS450_Gyro Gyro;
	double gyroInitial;
	double gyroTrack;
	boolean gyroPluggedIn;
	
	public void robotInit() {
		
		// Method that will run only one time in Teleop
		
		// Motors
		frontLeft = new TalonSRX(Variables.frontLN);
		frontRight = new TalonSRX(Variables.frontRN);
		backLeft = new TalonSRX(Variables.backLN);
		backRight = new TalonSRX(Variables.backRN);
	
		// Joysticks
		leftJ = new Joystick(Variables.leftStickPort);
		rightJ = new Joystick(Variables.rightStickPort);
		
		// Gyro
		Gyro = new ADXRS450_Gyro();
		
	}
	
	public void autoInit() {
		
		// Method that will run only one time in autonomous
		
		Gyro.calibrate();
		gyroPluggedIn = Util.gyroCheck(Gyro);
	}
	
	public void robotPeriodic() {
		
		// Method that will be constantly called during Teleop
		
		SmartDashboard.putNumber("Gyro Value: ", Gyro.getAngle());
	
		DriveTrain.MotorSet(leftJ, rightJ, frontLeft, frontRight, backLeft, backRight);
	
	}
	
	public void autoPeriodic() {
			
		// Method that will be constantly called during autonomous
		
		SmartDashboard.putNumber("Gyro Value: ", Gyro.getAngle());
		
		// Cases for each auto option
			//Calls a mehtod from the auto class based on case
		
	}
	
}
