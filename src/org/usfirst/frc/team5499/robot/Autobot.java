package org.usfirst.frc.team5499.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;


public class Autobot {
	public static Autobot INSTANCE;
	RobotSubsystems subsystem;
	RobotDrive robot;
	public final double CIRCUMFERENCE = 12.56637;	
	boolean finished; // a boolean every autonomous method will call
	Timer mpTimer = new Timer(); //WILLIAM - why is it called mpTimer? I moved it to the class so we could use it for timeouts

	public Autobot(RobotSubsystems x){
		subsystem = x;
	}
// every method in autonomous should be a boolean. It returns false until it is finished
	public boolean movePeriod (double duration, double speedL, double speedR) { // Moving for a specific duration
		finished = false;
		mpTimer.reset();
		mpTimer.start();
		
		if (mpTimer.get() <= duration){
		
			subsystem.myRobot.tankDrive(speedL, speedR);
			finished = false;
		}
		else{
			finished = true;
		}
		return finished;
			
	}
	
	public boolean moveDistance(double distance, double speed) {
		finished = false;
		mpTimer.reset();
		mpTimer.start();
		
		
		return finished;
	}
	
	
}
