package org.usfirst.frc.team2264.robot;

//@Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Variables {
	public static RobotChoice whichRobot = RobotChoice.MARS;
	// Variables class to change numbers and use them throughout the code
	
	// Motors
	
	public static final int frontLN = 4;
	public static final int frontRN = 3;
	public static int backLN = 6;
	public static int backRN = 5;
	public static final double intakeSpeed = .5; ///SET THIS NUMBER///
	public static final double conveyorSpeed = .5; //SET THIS NUMBER///
	public static final double shooterSpeed = .5; //SET THIS NUMBER///
	
	
	// Joysticks
	public static final int rightStickPort = 1;
	public static final int leftStickPort = 0;
	
	//Gamepad
	public static final int controllerPort = 9999; ///SET THIS NUMBER///
	// 2 shooting
	// 2 moving
	// 2 grabbing
	
	public Variables() {
		if(whichRobot == RobotChoice.MARS) {
			backLN = 6;
			backRN = 5;
		}
		else if(whichRobot == RobotChoice.VERONICA) {
			backLN = 1;
			backRN = 2;
		}
		else {
			backLN = 25;
			backRN = 26;
		} 
	}

}

