package org.usfirst.frc.team5499.robot;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team5499.robot.auto.*;



public class Autobot {
	
	RobotSubsystems subsystems;
	
	
	BasicAuto currentAction;
	List<BasicAuto> actions = new ArrayList<BasicAuto>();
	
	public double travelDistance = 0.0d;

	public Autobot(RobotSubsystems x){
		subsystems = x;
	}
	
	public void init(boolean switch1, boolean switch2){
		if(switch1==false && switch2==false){
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gear
			BasicAuto stop = new DoNothing(subsystems);
			actions.clear();
			actions.add(highgear);
			actions.add(stop);
		}
		else if(switch1==true && switch2==false){
			BasicAuto move1 = new MovePeriod(subsystems, 1, 0.5); // move for 1 second at half speed
			BasicAuto move2 = new MovePeriod(subsystems, 1, -0.5);
			BasicAuto move3 = new MoveDistance(subsystems, 12, 0.5); // move 12 inches at half speed
			BasicAuto turn1 = new TurnTime(subsystems, 1, 0.5); // turn for one second at half speed
			BasicAuto turn2 = new TurnAngle(subsystems, -45, 0.5); // turn 45 degrees in place at half speed
			BasicAuto stop = new DoNothing(subsystems);
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gear

	
			actions.clear();
			actions.add(move1);
			actions.add(move2);
			actions.add(move3);
			actions.add(turn1);
			actions.add(turn2);
			actions.add(highgear);
			actions.add(stop);
		}
		else if(switch1==false && switch2==true){
			BasicAuto turn1 = new TurnAngle(subsystems, -180, 0.5);
			BasicAuto stop = new DoNothing(subsystems);
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gear
			
			actions.clear();
			actions.add(turn1);
			actions.add(highgear);
			actions.add(stop);
		}
		else {
			BasicAuto stop = new DoNothing(subsystems);
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gear

			actions.clear();
			actions.add(highgear);
			actions.add(stop);
		}
	}
	
}
