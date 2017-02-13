package org.usfirst.frc.team5499.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class Autobot {
	public static Autobot INSTANCE;
	RobotDrive robot;
	public final double CIRCUMFERENCE = 12.56637;	

	public Autobot(RobotDrive x){
		robot = x;
	}
	
	public void movePeriod (double duration, double speedL, double speedR) { // Moving for a specific duration
		Timer mpTimer = new Timer();
		mpTimer.reset();
		mpTimer.start();
		
		if (mpTimer.get() <= duration){
		
			robot.tankDrive(speedL, speedR);
			
		}
			
	}
	
	
}
