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
//all directions are in respect to the drivers perspective from the drivers station .
	
// Main class that will use other classes and call methods to run the robot
	
	//autonomous+ smart dashboard
	final String centerAuto = "Center Auto";
	final String leftAuto= "Left Auto";
	final String rightAuto= "Right Auto";
	final String noAuto="noAuto";
	
	String autoSelected;
	String gameData= DriverStation.getInstance().getGameSpecificMessage();
	//String gameData = "LRR";
	
	long autoStartTime;
	long timeInAuto;
	
	SendableChooser<String> chooser = new SendableChooser<>();
	Autos auto;
	double shootingSpeed = Variables.switchShooterSpeed;
	
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
	TalonSRX shooterFeedL;
	TalonSRX shooterFeedR;
	
	//CameraServer
	CameraServer intakeCamera;
	CameraServer shooterCamera;
	
	//Gyro
	ADXRS450_Gyro Gyro;

	double gyroInitial;
	double gyroTrack;
	boolean gyroPluggedIn;
	
	Shooter shooter;
	Intake intake;
	Conveyor conveyor;
	Pneumatic pneumatics;

	public void robotInit() {
		
		//Method that will run once on start-up
		
		// Motors
		frontLeft = new TalonSRX(Variables.frontLN);
		frontRight = new TalonSRX(Variables.frontRN);
		backLeft = new TalonSRX(Variables.backLN);
		backRight = new TalonSRX(Variables.backRN);
		shooterLeft = new TalonSRX(Variables.shooterLeft);
		shooterRight = new TalonSRX(Variables.shooterRight);
		shooterFeedL = new TalonSRX(Variables.shooterFeedL);
		shooterFeedR = new TalonSRX(Variables.shooterFeedR);
		conveyorLeft = new TalonSRX(Variables.conveyorLeft);
		conveyorRight = new TalonSRX(Variables.conveyorRight);
		intakeLeft = new TalonSRX(Variables.intakeLeft);
		intakeRight= new TalonSRX(Variables.intakeRight);
	
		// Joysticks
		leftJ = new Joystick(Variables.leftStickPort);
		rightJ = new Joystick(Variables.rightStickPort);
		controller = new XboxController(Variables.controllerPort);
		
		shooter = new Shooter();
		intake = new Intake();
		conveyor = new Conveyor();
		pneumatics = new Pneumatic();
		
		// Gyro
		Gyro = new ADXRS450_Gyro();
		gyroPluggedIn = Util.gyroCheck(Gyro);
		
		auto = new Autos();
		
		intakeCamera = CameraServer.getInstance();
		shooterCamera = CameraServer.getInstance();
		intakeCamera.startAutomaticCapture(1);
		shooterCamera.startAutomaticCapture();
	
		DashBoard();
	}
	
	public void autonomousInit() {
		
		// Method that will run only one time in autonomous
		Gyro.calibrate();
		pneumatics.lowerShooter();
		//lowers
		autoStartTime = System.currentTimeMillis();
		autoSelected = chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
	}


	@Override
	public void autonomousPeriodic() {
	/**
	 * This function is called periodically during autonomous
	 */
		
		//smartdashboard
		SmartDashboard.putNumber("Gyro Value: ", Gyro.getAngle());
		
		timeInAuto=System.currentTimeMillis() - autoStartTime;
	
		switch (autoSelected) {
		
		case leftAuto:
			//left left WORKS
			//left right gyro reading incorrect during 2nd turn
			if(auto.getSwitch(gameData, 0)){
				auto.leftLeft(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto, shooterLeft, shooterRight, conveyorLeft, conveyorRight, shooter, conveyor, pneumatics, shooterFeedL, shooterFeedR);
			}
			else{
				auto.leftRight(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto, shooterLeft, shooterRight, conveyorLeft, conveyorRight, shooter, conveyor, pneumatics,shooterFeedL, shooterFeedR);
			}
			break;
		case rightAuto:
			//right right doesnt work because the robot turns right instead of left
			//right left doesnt work because turns right and gyro reading incorrect during 2nd turn
			if(auto.getSwitch(gameData, 1)){
				System.out.println("RIGHT RIGHT");
				auto.rightRight(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto, shooterLeft, shooterRight, conveyorLeft, conveyorRight, shooter, conveyor, pneumatics, shooterFeedL, shooterFeedR);
			}
			else{
				System.out.println("RIGHT LEFT");
				auto.rightLeft(frontLeft,frontRight, backLeft, backRight, Gyro, timeInAuto, shooterLeft, shooterRight, conveyorLeft, conveyorRight, shooter, conveyor, pneumatics,shooterFeedL, shooterFeedR);
			}
		case centerAuto:
			//center right turns left, gyro reading incoorrect during 2nd turn
			//center left gyro reading incorrect during 2nd turn
			if(auto.getSwitch(gameData, 0)){
				auto.centerLeft(frontLeft, frontRight, backLeft, backRight, Gyro, timeInAuto, shooterLeft, shooterRight, conveyorLeft, conveyorRight, shooter, conveyor, pneumatics);
			}
			else{
				auto.centerRight(frontLeft, frontRight, backLeft, backRight, Gyro, timeInAuto, shooterLeft, shooterRight, conveyorLeft, conveyorRight, shooter, conveyor, pneumatics);
			}
			break;
		default:
			auto.stop(frontLeft, frontRight, backLeft, backRight);
			break;
		}
	}
	
	void checkButtons() {
		
		//shooter controls
		if(controller.getBButton())
		{
			shooter.startShooter(shooterLeft, shooterRight, shootingSpeed);
			shooter.startFeeder(shooterFeedL, shooterFeedR, shootingSpeed);
		}
		else {
			shooter.startShooter(shooterLeft, shooterRight, 0);
			shooter.startFeeder(shooterFeedL, shooterFeedR, 0);
		}
		
		//Intake control
		if(controller.getXButton()) {
			conveyor.startConveyor(conveyorLeft, conveyorRight);
		}
		else if(controller.getStartButton()) {
			conveyor.reverseConveyor(conveyorLeft, conveyorRight);
		}
		else{
			conveyor.stopConveyor(conveyorLeft, conveyorRight);
		}
		
		if(controller.getAButton()) {
			pneumatics.extendArms();
		}
		if(controller.getYButton()) {
			pneumatics.retractArms();
		}
		if(controller.getBackButton()) {
			shootingSpeed=Variables.switchShooterSpeed;
		}
		
		
		
	}
	
	void checkIntakeControls() {
		if(leftJ.getRawButton(3)) {
			intake.doubleIntake(intakeLeft,	intakeRight);
		}
		else if(leftJ.getRawButton(2)){
			intake.doubleOutput(intakeLeft, intakeRight);
		}
	
		else if(rightJ.getRawButton(3)) {
			intake.turn(intakeLeft, intakeRight);
		}
		else{
			intake.stop(intakeLeft, intakeRight);
		}
	}
	
	void elevatorControls() {
		if (controller.getTriggerAxis(GenericHID.Hand.kRight)>.1) {
			pneumatics.raiseShooter();
			shootingSpeed = Variables.scaleShooterSpeed;
		}
		else if (controller.getTriggerAxis(GenericHID.Hand.kLeft)>.1) {
			pneumatics.lowerShooter();
			shootingSpeed = Variables.switchShooterSpeed;
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
	
	void DashBoard() {
		
		SmartDashboard.putNumber("Gyro Value: ", Gyro.getAngle());
		SmartDashboard.putNumber("Left Joystick: ", leftJ.getY(GenericHID.Hand.kLeft));
		SmartDashboard.putNumber("Right Joystick: ", rightJ.getY(GenericHID.Hand.kRight));
		SmartDashboard.putBoolean("Arms OUT: ", pneumatics.armsOut);
		
		chooser.addObject("Center Auto", centerAuto);
		chooser.addObject("Left Auto", leftAuto);
		chooser.addObject("Right Auto", rightAuto);
		chooser.addDefault("No Auto", noAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		SmartDashboard.putString("Intake Camera", "");
		SmartDashboard.putString("Shooter Camera", "");
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		// Method that will be constantly called during Teleop
		checkButtons();
		checkIntakeControls();
		elevatorControls();
		checkPressure();
		DashBoard();
		
		DriveTrain.MotorSet(leftJ, rightJ, frontLeft, frontRight, backLeft, backRight);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}

}

