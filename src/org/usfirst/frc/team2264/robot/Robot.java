package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import  edu.wpi.first.wpilibj.ADXRS450_Gyro;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	double gyroInitial;
	double gyroTrack;
	boolean gyroPluggedIn;
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	TalonSRX leftMotor;
	TalonSRX rightMotor;
	Joystick leftJoystick; 
	Joystick rightJoystick;
	ADXRS450_Gyro Gyro;
	ControlMode mode;
	RobotChoice whichRobot = RobotChoice.VERONICA;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);

		if(whichRobot == RobotChoice.VERONICA) {
			leftMotor = new TalonSRX(25);
			rightMotor = new TalonSRX(26);
		}
		else {
			leftMotor = new TalonSRX(1);
			rightMotor = new TalonSRX(2);
		}

		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		Gyro = new ADXRS450_Gyro();
		mode = ControlMode.PercentOutput;
		gyroInitial = Gyro.getAngle();
		for(int i = 0; i < 5; i++) {
			gyroTrack = gyroTrack + Gyro.getAngle();
		}
		if(gyroTrack == (5 * gyroInitial)) {
			gyroPluggedIn = false;
		}
		else {
			gyroPluggedIn = true;
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		Gyro.calibrate();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Gyro Data:", Gyro.getAngle());

		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		driveTrain(leftJoystick, rightJoystick);
		SmartDashboard.putNumber("Gyro Data", Gyro.getAngle());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		driveTrain(leftJoystick, rightJoystick);
	}


	public void driveTrain(Joystick leftJoystick, Joystick rightJoystick) {
		leftMotor.set(ControlMode.PercentOutput, JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getLeft(leftJoystick, rightJoystick)));
		rightMotor.set(ControlMode.PercentOutput, -1 * JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getRight(rightJoystick)));
	}

}
