package org.usfirst.frc.team2264.robot;

//@Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Variables {
	// Variables class to change numbers and use them throughout the code
	
	// Motors
	public static final int backRN = 4;
	public static final int backLN = 3;
	public static final int frontRN = 5;
	public static final int frontLN = 6;
	public static final int shooterLeft = 16;
	public static final int shooterRight = 20;
	public static final int shooterFeedL = 0; //FIND TALON
	public static final int shooterFeedR = 0; //FIND TALON
	public static final int conveyorLeft = 21;
	public static final int conveyorRight = 17;
	public static final int intakeLeft= 19;
	public static final int intakeRight= 18;

	// Speeds in Teleop
	public static final double intakeSpeed = -1; ///SET THIS NUMBER///
	public static final double conveyorSpeed = 1; //SET THIS NUMBER///
	public static final double switchShooterSpeed = .7;//SET THIS NUMBER///
	public static final double scaleShooterSpeed = 1;//SET THIS NUMBER///
	
	// Speeds in Auto
	public static final double autoSwitchSpeed = .1;
	
	// Joysticks
	public static final int rightStickPort = 0;
	public static final int leftStickPort = 1;
	
	//Gamepad
	public static final int controllerPort = 2; ///SET THIS NUMBER///
	// 2 shooting
	// 2 moving
	// 2 grabbing
	
}

