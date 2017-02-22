package org.usfirst.frc.team5499.robot;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class RobotDashboard {

	RobotSubsystems subsystems;
	
	public RobotDashboard(RobotSubsystems s){
		subsystems = s;
	}
	
	public void execute (){ 
		
		SmartDashboard.putNumber("Subsystem Timer: ", subsystems.timer.get());
		SmartDashboard.putBoolean("Gear Engaged: ", subsystems.shifter.get());
		SmartDashboard.putNumber("Left Encoder Position: ", subsystems.encoderLeft.getRaw() );
		SmartDashboard.putNumber("Right Encoder Position: ", subsystems.encoderRight.getRaw());
		SmartDashboard.putNumber("Input Proximity Sensor", subsystems.gearProximity.getVoltage());
		SmartDashboard.putBoolean("Auto Switch One:",subsystems.autoSwitch1.get());
		SmartDashboard.putBoolean("Auto Switch Two:",subsystems.autoSwitch2.get());
		SmartDashboard.putNumber("Gyro Angle: ", subsystems.gyro.getAngle());
		SmartDashboard.putBoolean("Gyro Calibration:", subsystems.gyroCalibrating);
		SmartDashboard.putNumber("Auto State:", subsystems.autoState);
		
		SmartDashboard.putBoolean("have a gear", subsystems.haveAGear);
		SmartDashboard.putBoolean("had a gear", subsystems.hadAGear);
		SmartDashboard.putBoolean("stop with gear", subsystems.stopWithGear);
		
	}

}
