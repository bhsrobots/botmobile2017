package org.usfirst.frc.team5499.robot;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class RobotDashboard {
	
	/*
	 * gear: Gear shifter status
	 * lEnc: Left encoder position
	 */
	public static void execute (boolean gear, int lEnc){ 
		SmartDashboard.putBoolean("Gear Engaged: ", gear);
		SmartDashboard.putNumber("Left Encoder Position: ", lEnc);
		
	}

}
