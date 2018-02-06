package org.usfirst.frc.team2264.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import java.awt.Color;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.hal.SolenoidJNI;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


// @Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Robot extends IterativeRobot {
	
// Main class that will use other classes and call methods to run the robot
	
	//autonomous+ smart dashboard
	final String driveAuto = "drive straight";
	final String centerAuto = "Center Auto";
	final String leftAuto= "Left Auto";
	final String rightAuto= "Right Auto";
	final String noAuto= "no Auto";
	String autoSelected;
	String gameData= DriverStation.getInstance().getGameSpecificMessage();
	long autoStartTime;
	long timeInAuto;
	SendableChooser<String> chooser = new SendableChooser<>();
	Autos auto;
	double shootingSpeed;
	//Joysticks
	Joystick leftJ;
	Joystick rightJ;
	XboxController controller;
	
	//Motors/Talons
	TalonSRX frontLeft;
	TalonSRX frontRight;
	TalonSRX backLeft;
	TalonSRX backRight;
	TalonSRX intakeLeft;
	TalonSRX intakeRight;
	TalonSRX conveyorLeft;
	TalonSRX conveyorRight;
	TalonSRX shooterLeft;
	TalonSRX shooterRight;
	TalonSRX liftMotor;
	
	

	CameraServer connor = CameraServer.getInstance();
	
	//Gyro
	ADXRS450_Gyro Gyro;
	double gyroInitial;
	double gyroTrack;
	boolean gyroPluggedIn;
	
	Shooter shooter;
	Intake intake;
	Conveyor conveyor;
	Pneumatic pneumatics= new Pneumatic();
	
	
	public void robotInit() {
		
		
		// Method that will run only one time in Teleop
		
		
		chooser.addObject("Center Auto", centerAuto);
		chooser.addObject("Left Auto", leftAuto);
		chooser.addObject("Right Auto", rightAuto);
		chooser.addObject("Drive straight", driveAuto);
		chooser.addDefault("no auto", noAuto);
		SmartDashboard.putData("Auto choices", chooser);
		// Motors
		frontLeft = new TalonSRX(Variables.frontLN);
		frontRight = new TalonSRX(Variables.frontRN);
		backLeft = new TalonSRX(Variables.backLN);
		backRight = new TalonSRX(Variables.backRN);
		liftMotor = new TalonSRX(Variables.liftElevator);
	
		// Joysticks
		leftJ = new Joystick(Variables.leftStickPort);
		rightJ = new Joystick(Variables.rightStickPort);
		controller = new XboxController(Variables.controllerPort);
		
		shooter = new Shooter();
		intake = new Intake();
		conveyor = new Conveyor();

	/*	
		int readVal = solenoid.getAll();
		SmartDashboard.putNumber("getAll", readVal);
		readVal = solenoid.getPCMSolenoidBlackList();
		SmartDashboard.putNumbenr("getPCM", readVal);
		boolean boolVal = solenoid.getPCMSolenoidVoltageFault();
		SmartDashboard.putBoolean("getPCMSolenoidVoltageFault", boolVal);
		boolVal = solenoid.getPCMSolenoidVoltageStickyFault();
		SmartDashboard.putBoolean("getPCMSolenoidVoltageStickyFault", boolVal);
	 */		
		//Start the camera server
		connor.startAutomaticCapture();
		
		// Gyro
		Gyro = new ADXRS450_Gyro();
	}
	
	public void autonomousInit() {
		// Method that will run only one time in autonomous
		Gyro.calibrate();
		gyroPluggedIn = Util.gyroCheck(Gyro);
		this.autoStartTime = System.currentTimeMillis();
		autoSelected = chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
	}


	@Override
	public void autonomousPeriodic() {


	/**
	 * This function is called periodically during autonomous
	 */
	
		timeInAuto=System.currentTimeMillis()- autoStartTime;
		switch (autoSelected) {
		case leftAuto:
			if(auto.getSwitch(gameData, 0)){
			auto.leftLeft(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto);
			}
			else{
				auto.leftRight(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto);
			}
			break;
		case rightAuto:
			if(auto.getSwitch(gameData, 1)){
			auto.rightRight(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto);
			}
			else{
				auto.rightLeft(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto);
			}
		case centerAuto:
			//auto.center(left, right);
			break;
		//case straightSwitch:
			//side=1;
		//	auto.sideChoice(left, right, side);
		case driveAuto:
			
			auto.crossLineAuto();
		default:
			auto.stop(frontLeft,frontRight, backLeft, backRight);
			break;
		}
		
	}
	

	
	void checkButtons() {
		//shooter controls
		if(controller.getBumperPressed(GenericHID.Hand.kLeft))
		{
			shooter.startShooter(shooterLeft, shooterRight,shootingSpeed);
		}
		//intake control
		
		if(controller.getBumperPressed(GenericHID.Hand.kRight)) {
			conveyor.startConveyor(conveyorLeft, conveyorRight);
		}
		if(controller.getAButton()) {
			pneumatics.extendArms();
			System.out.println("fdergerg");
		}
		if(controller.getYButton()) {
			pneumatics.retractArms();
		}
		if(controller.getBackButton()) {
			shootingSpeed=Variables.switchShooterSpeed;
		}
		if(controller.getStartButton()) {
			shootingSpeed=Variables.scaleShooterSpeed;	
		}
		
	}
	void checkIntakeControls() {
		if(leftJ.getRawButton(3)) {
			intake.doubleIntake(intakeLeft,	intakeRight);
		}
		if(leftJ.getRawButton(2)) {
			intake.doubleOutput(intakeLeft, intakeRight);
		}
		if(rightJ.getRawButton(3)) {
			intake.turn(intakeLeft, intakeRight);
		}
		if(rightJ.getRawButton(2)) {
			intake.stop(intakeLeft, intakeRight);
}
		
		
	}
	
	void elevatorControls() {
		if (controller.getTriggerAxis(GenericHID.Hand.kLeft)>.1) {
		//if(leftJ.getRawButton(6)) {
			pneumatics.raiseShooter();
		}
		else if (controller.getTriggerAxis(GenericHID.Hand.kRight)>.1) {
		//if(leftJ.getRawButton(7)) {
			pneumatics.lowerShooter();
		}
	
	}
	
	void checkPressure() {
		if(pneumatics.compressor.getPressureSwitchValue()) {
		//	SmartDashboard.putData("Air Tank Full", (Sendable) Color.GREEN);
		}
		else {
			//SmartDashboard.putData("Air Tank Full", (Sendable) Color.red);
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//compressor.setClosedLoopControl(true);
		//solenoid.set(DoubleSolenoid.Value.kForward);
		
		// Method that will be constantly called during Teleop
		checkButtons();
		elevatorControls();
		checkPressure();
		SmartDashboard.putNumber("Gyro Value: ", Gyro.getAngle());
		
		if(Variables.whichRobot == RobotChoice.MARS) {
		DriveTrain.MotorSet(leftJ, rightJ, frontLeft, frontRight, backLeft, backRight);
		}
		
		else {
			DriveTrain.MotorSetTwo(leftJ, rightJ, backLeft, backRight);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
		
		//System.out.println(solenoid.getName());
		
		//System.out.println("Post: " + solenoid.get());
		
	//	System.out.println("Talon Numbers: " + frontLeft.getDeviceID() + ", " + frontRight.getDeviceID() + ", " + backLeft.getDeviceID() + ", " + backRight.getDeviceID());
		
		//SmartDashboard.putData("sol", solenoid.get());
		
	}

}

