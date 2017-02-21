package org.usfirst.frc.team5499.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;


public class Autobot {
	
	public static Autobot INSTANCE;
	RobotDrive robot;
	
	RobotSubsystems subsystem;
	public final double CIRCUMFERENCE = 12.56637; // The circumference of the wheels for calculating travel distance
	boolean finished; // a boolean every autonomous method will call
	Timer AutoTimer = new Timer();
	boolean init = true;
	double timeOut = 0;
	
	public double travelDistance = 0.0d;

	public Autobot(RobotSubsystems x){
		subsystem = x;
	}
	
// every method in autonomous should be a boolean. It returns false until it is finished
	public boolean movePeriod (double d, double speedL, double speedR) { // Moving for a specific duration
		if (init) {
			finished = false;
			AutoTimer.reset();
			AutoTimer.start(); 
			timeOut = d;
			init = false;
		}
		else {
			if (AutoTimer.get() <= timeOut){
			
				subsystem.myRobot.tankDrive(speedL, speedR);
				finished = false;
				
			}
			else{
				subsystem.myRobot.tankDrive(0, 0);
				finished = true;
			}
		}
		
		if(finished){
			init = true; // reset the init variable	
		}
		return finished;
		
			
	}
	
	public boolean moveDistance(double distance, double speed) {
		
		if (init){
			finished = false;
			AutoTimer.reset();
			AutoTimer.start();
			subsystem.encoderLeft.reset();
			subsystem.encoderRight.reset();
			init = false;
			travelDistance = 0.0d;
		}
		
		travelDistance = subsystem.encoderLeft.get() * CIRCUMFERENCE / 36;	
		if (travelDistance <= distance){
			subsystem.myRobot.tankDrive(speed, speed);
		}
		else {
			finished = true;
		}
		
		
		AutoTimer.reset();
		AutoTimer.start();
		
		return finished;
	}
	
}
