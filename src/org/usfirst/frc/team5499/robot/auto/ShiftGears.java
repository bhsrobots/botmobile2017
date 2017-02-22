package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.*;

public class ShiftGears extends BasicAuto {
	
	boolean gear;
	
	public ShiftGears(RobotSubsystems x, boolean shiftgear) {
		super(x);
		gear = shiftgear;
	}
	
	public void init(){
		super.init();
		subsystems.shifter.set(gear);
	}

	public void execute(){
	}
	
	public boolean done(){
		return true;
	}
	
	
	

}
