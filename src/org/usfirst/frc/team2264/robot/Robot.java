package org.usfirst.frc.team2264.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
	
	
	//CameraServer
	//CameraServer connor = CameraServer.getInstance();
	
	//Gyro
	ADXRS450_Gyro Gyro;
	double gyroInitial;
	double gyroTrack;
	boolean gyroPluggedIn;
	
	Shooter shooter = new Shooter();
	Intake intake = new Intake();
	Conveyor conveyor = new Conveyor();
	Elevator elevator = new Elevator();
	Compressor compressor = new Compressor(2);
	DoubleSolenoid solenoid = new DoubleSolenoid(0, 1);
	
	public void robotInit() {
		
		// Method that will run only one time in Teleop
		//smart dashboard
		
	//	compressor.setClosedLoopControl(true);
		
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
		
		//Start the camera server
	//connor.startAutomaticCapture();
		
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
		compressor.setClosedLoopControl(true);
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
		if(controller.getBumperPressed(GenericHID.Hand.kLeft))
		{
			shooter.startShooter(shooterLeft, shooterRight);
		}
		//if(controller.getBButtonPressed()) {
		//	shootyBoi.stopShooter(shooterLeft, shooterRight);
		//}
		if(controller.getAButtonPressed()) {
			intake.startIntake(intakeLeft, intakeRight);
		}
		if(controller.getYButtonPressed()) {
			intake.stopIntake(intakeLeft, intakeRight);
		}
		if(controller.getBumperPressed(GenericHID.Hand.kRight)) {
			conveyor.startConveyor(conveyorLeft, conveyorRight);
		}
		//if(controller.getBumperPressed(GenericHID.Hand.kRight)) {
		//	movyBoi.stopConveyor(conveyorLeft, conveyorRight);
		//}
		if(controller.getXButtonPressed())
		{	elevator.upElevator(liftMotor);
			
		}
		if(controller.getBButtonPressed())
		{
			elevator.downElevator(liftMotor);
		}
		
	}
	


	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		compressor.setClosedLoopControl(true);
		//solenoid.set(DoubleSolenoid.Value.kForward);
	//	solenoid.clearAllPCMStickyFaults();
		
		// Method that will be constantly called during Teleop
		checkButtons();
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

