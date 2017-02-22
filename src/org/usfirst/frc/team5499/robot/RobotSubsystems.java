package org.usfirst.frc.team5499.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

public class RobotSubsystems {
	public RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	Autobot autobot;
	public CANTalon leftBack, rightBack, leftFront, rightFront, climber, intake;
	public boolean intakeEnabled = true;
	public boolean haveAGear = false;
	public boolean hadAGear = false; // did we have a gear in the last loop?
	public boolean stopWithGear = false;
	
	public ADXRS450_Gyro gyro;
	public boolean gyroCalibrating;
	public Encoder encoderLeft, encoderRight;
	public Solenoid shifter;
	public AnalogInput gearProximity;
	public DigitalInput autoSwitch1, autoSwitch2, autoSwitch3;
	
	public int autoState;
	public Timer timer = new Timer();
	
	public final boolean HIGH = true;
	public final boolean LOW = false;
	
	public void inits() {
		// Joysticks
		leftStick =  new Joystick(0);
		rightStick = new Joystick(1);
		
		// Drive Talons
		leftBack = new CANTalon(9);
		rightBack = new CANTalon(2);
		leftFront = new CANTalon(10);
		rightFront = new CANTalon(1);
		
		//Climber Talon
		climber = new CANTalon(6);
		
		// Intake Talon
		intake = new CANTalon(4); 
		
		//Gyro
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		
		//Encoders
		encoderLeft = new Encoder(2, 3, false, Encoder.EncodingType.k2X);
		encoderRight = new Encoder(4, 5, false, Encoder.EncodingType.k2X);
		
		//Robot
		myRobot = new RobotDrive(leftBack, leftFront, rightBack, rightFront);
		
		// Solenoids
		shifter = new Solenoid(0);
		
		// Analog Inputs
		gearProximity = new AnalogInput(0);
		
		// Digital Inputs
		autoSwitch1 = new DigitalInput(0);
		autoSwitch2 = new DigitalInput(1);
		//autoSwitch3 = new DigitalInput(2);
		
		autoState = 0;
		
	}
	
	public void execute(){
		// GEAR STOP LOGIC
		/*
		if (gearProximity.getVoltage() > 0.5){
			haveAGear = true;
			if(!hadAGear){ // of this is the first loop where we see a gear, reset the timer
				timer.reset();
				timer.start();
			}
		}
		else haveAGear = false;
		
		if(haveAGear && timer.get()>0.4){
			stopWithGear = true;
		}
		else stopWithGear = false;*/
		
		// SIMPLE IMPLEMENTATION no delay
		if (gearProximity.getVoltage() > 0.7){
			haveAGear = true;
			stopWithGear = true;
		}
		else {
			haveAGear = false;
			stopWithGear = false;
		}
		
		
		hadAGear = haveAGear;
	}
}
