package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.awt.Color;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class ElevatorGyro {

	ADXRS450_Gyro ElevatorGyro = new ADXRS450_Gyro();
	double switchAngle = 45.0;
	double scaleAngle = 80.0; //Adjust these numbers accordingly
	
	
	public ElevatorGyro() {
		ElevatorGyro.calibrate();
		SmartDashboard.putNumber("getAngle", ElevatorGyro.getAngle());
		
	}
	
	/*public int position(double angle) {
		if (angle == switchAngle) {
			return 0; //If the shooter is at the switch position, it returns a 0 and shows blue on the smart dashboard
			//SmartDashboard.putData("Switch", Color.blue);
		}
		
		else if (angle == scaleAngle) {
			return 1; //If the shooter is at the scale position, it returns a 1 and shows green on the smart dashboard
			//SmartDashboard.putData("Scale", Color.green);
		}
	}
	*/
}
