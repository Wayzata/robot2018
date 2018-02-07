package org.usfirst.frc.team2264.robot;

//@Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Variables {
	public static RobotChoice whichRobot = RobotChoice.MARS;
	// Variables class to change numbers and use them throughout the code
	
	// Motors
	
	public static final int frontLN = 4; // Front-left talon number
	public static final int frontRN = 3; // Front-right talon number
	public static int backLN = 6; // Back-left talon number
	public static int backRN = 5; // Back-right talons number

	public static final double intakeSpeed = -.5; // Speed of the intake motors ///SET THIS NUMBER///  ///WE STILL NEED TO SET THESE NUMBERS DON'T FORGET TO DO THIS SERIOUSLY IF YOU FORGET IT WE WILL DIE///
	public static final double conveyorSpeed = .5; // Speed of the conveyor motors //SET THIS NUMBER///
	public static final double switchShooterSpeed = .5; // Speed of the shooter motors when shooting to the switch //SET THIS NUMBER///
	public static final double scaleShooterSpeed = .9; // Speed of the shooter motors when shooting to the scale //SET THIS NUMBER///

	
	public static final int liftElevator = 7; //change this
	public static final double elevateSpeed = 1; 


	
	
	// Joysticks
	public static final int rightStickPort = 0;
	public static final int leftStickPort = 1;
	
	//Gamepad
	public static final int controllerPort = 3; ///SET THIS NUMBER///
	// 2 shooting
	// 2 moving
	// 2 grabbing
	
	public Variables() {
		
		//Deterimines which robot is being used, and sets the talon numbers appropriatly
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

