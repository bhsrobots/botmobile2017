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
	RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	Autobot autobot;
	CANTalon leftBack, rightBack, leftFront, rightFront, climber, intake;
	
	ADXRS450_Gyro gyro;
	public boolean gyroCalibrating;
	Encoder encoderLeft, encoderRight;
	Solenoid shifter;
	AnalogInput gearProximity;
	DigitalInput autoSwitch1, autoSwitch2, autoSwitch3;
	
	Timer timer = new Timer();
	
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
		
	}
}
