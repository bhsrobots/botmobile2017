package org.usfirst.frc.team5499.robot;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class RobotDashboard {

	RobotSubsystems subsystems;
	
	public RobotDashboard(RobotSubsystems s){
		subsystems = s;
	}
	
	public void execute (){ 
		
		SmartDashboard.putBoolean("Gear Engaged: ", subsystems.shifter.get());
		SmartDashboard.putNumber("Left Encoder Position: ", subsystems.encoderLeft.get() );
		SmartDashboard.putNumber("Right Encoder Position: ", subsystems.encoderRight.get());
		SmartDashboard.putNumber("Input Proximity Sensor", subsystems.gearProximity.getValue());
		SmartDashboard.putBoolean("Auto Switch One",subsystems.autoSwitch1.get());
		SmartDashboard.putBoolean("Auto Switch Two",subsystems.autoSwitch2.get());
		SmartDashboard.putBoolean("Auto Switch Three",subsystems.autoSwitch3.get());
		SmartDashboard.putNumber("Gyro Angle: ", subsystems.gyro.getAngle());
		
		
	}

}
