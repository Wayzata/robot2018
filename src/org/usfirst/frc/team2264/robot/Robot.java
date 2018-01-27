//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import  edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;


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
	RobotChoice whichRobot = RobotChoice.MARS;
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	TalonSRX left;
	TalonSRX right;
	TalonSRX leftFront;
	TalonSRX rightFront;
	TalonSRX intakeLeft;
	TalonSRX intakeRight;
	TalonSRX conveyorLeft;
	TalonSRX conveyorRight;
	TalonSRX shooterLeft;
	TalonSRX shooterRight;
	Joystick leftJoystick; 
	Joystick rightJoystick;
	ADXRS450_Gyro Gyro;
	GyroHandler GyroHandle = new GyroHandler();
	AnalogInput LettuceAssimilator = new AnalogInput(0);
	ControlMode mode;
	Autonomous auto;
	long autoStartTime;
	long timeInAuto;
	// final long lettuce = 76698484856769;

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		//left = new TalonSRX(4);
		//right = new TalonSRX(3);

		if(whichRobot == RobotChoice.VERONICA) {
			left = new TalonSRX(25);
			right = new TalonSRX(26);
		}
		else if(whichRobot == RobotChoice.BOB) {
			left = new TalonSRX(2);
			right = new TalonSRX(1);
		}
		else if(whichRobot == RobotChoice.MARS) {
			left = new TalonSRX(6);
			right = new TalonSRX(5);
			leftFront = new TalonSRX(4);
			rightFront = new TalonSRX(3);
		}

		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		Gyro = new ADXRS450_Gyro();
		mode = ControlMode.PercentOutput;
		auto = new Autonomous();
		
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
		autoStartTime = System.currentTimeMillis();//
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		Gyro.calibrate();
		gyroPluggedIn = GyroHandle.checkIfGyroIsPluggedIn(Gyro);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		timeInAuto = System.currentTimeMillis() - autoStartTime;
		SmartDashboard.putNumber("Gyro Data:", Gyro.getAngle());
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			System.out.println("auto.leftLeft about to begin");
			auto.leftLeft(left, right, timeInAuto, Gyro);
		break;
		}
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//utilities.angleDifference(Gyro.getAngle(), 40);
		
		if(whichRobot == RobotChoice.MARS) {
			driveTrainFour(leftJoystick, rightJoystick);
		}
		else {
			driveTrainTwo(leftJoystick, rightJoystick);
		}
		SmartDashboard.putNumber("Gyro Data", Gyro.getAngle());
		SmartDashboard.putNumber("LettuceAssimilator value:", (LettuceAssimilator.getVoltage() - 0.065) * 8.5034);
		
		// 0in reading: 0.0647
		//?? 2.388
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}


	public void driveTrainTwo(Joystick leftJoystick, Joystick rightJoystick) {
		left.set(ControlMode.PercentOutput, -0.4 * JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getLeft(leftJoystick, rightJoystick)));
		right.set(ControlMode.PercentOutput, 0.4 * 0.9 * JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getRight(rightJoystick)));
	}
	
	public void driveTrainFour(Joystick leftJoystick, Joystick rightJoystick) {
		left.set(ControlMode.PercentOutput, -1 * 0.3 * JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getLeft(leftJoystick, rightJoystick)));
		leftFront.set(ControlMode.PercentOutput, -1 * 0.3 * JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getLeft(leftJoystick, rightJoystick)));
		right.set(ControlMode.PercentOutput, 0.3 * JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getRight(rightJoystick)));
		rightFront.set(ControlMode.PercentOutput, 0.3 * JoystickAdjustment.sensitivityAdjustment(JoystickAdjustment.getRight(rightJoystick)));
		
	}

}