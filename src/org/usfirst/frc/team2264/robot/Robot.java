package org.usfirst.frc.team2264.robot;

import javax.swing.JOptionPane;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String driveAuto = "drive straight";
	final String centerAuto = "Center Auto";
	final String leftAuto= "Left Auto";
	final String rightAuto= "Right Auto";
	//final String straightSwitch= "Straight Switch";
	final String noAuto= "no Auto";
	TalonSRX left;
	TalonSRX right;
	String autoSelected;
	int side;
	//String gameData= DriverStation.getInstance().getGameMessage();//commented because we do not have access to gameMessage
	String gameData = "LRR";//just a random configuration for the field state	Purely for testing
	long autoStartTime;
	long timeInAuto;
	SendableChooser<String> chooser = new SendableChooser<>();
	autonomous auto;
	ADXRS450_Gyro Gyro;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//chooser.addObject("Straight Switch", straightSwitch);
		chooser.addObject("Center Auto", centerAuto);
		chooser.addObject("Left Auto", leftAuto);
		chooser.addObject("Right Auto", rightAuto);
		chooser.addObject("Drive straight", driveAuto);
		chooser.addDefault("no auto", noAuto);
		SmartDashboard.putData("Auto choices", chooser);
		Gyro = new ADXRS450_Gyro();
		right = new TalonSRX(1);
		left = new TalonSRX(2);
		auto = new autonomous();
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
		this.autoStartTime = System.currentTimeMillis();
		autoSelected = chooser.getSelected();
		
		autoSelected = SmartDashboard.getString("Auto Selector", autoSelected);
		System.out.println("Auto selected: " + autoSelected);
		SmartDashboard.putString("Automode: ", autoSelected);
		Gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		timeInAuto=System.currentTimeMillis()- autoStartTime;
		SmartDashboard.putNumber("timeInAuto: ", timeInAuto);
		switch (autoSelected) {
		case leftAuto:
			if(auto.getSwitch(gameData, 0)){
				auto.leftLeft(left, right, timeInAuto, Gyro);
			}
			else{
				auto.leftRight(left, right, timeInAuto, Gyro);
			}
			break;
		case rightAuto:
			if(auto.getSwitch(gameData, 1)){
			auto.rightRight(left, right, timeInAuto, Gyro);
			}
			else{
				auto.rightLeft(left,right, timeInAuto, Gyro);
			}
		case centerAuto:
			//auto.center(left, right);
			break;
		//case straightSwitch:
			//side=1;
		//	auto.sideChoice(left, right, side);
		case driveAuto:
			
			auto.crossLineAuto(left, right, timeInAuto);
		default:
			auto.noAuto(left, right);
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
