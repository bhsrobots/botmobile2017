package org.usfirst.frc.team5499.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class RobotSubsystems {
	RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	Autobot autobot;
	CANTalon leftBack, rightBack, leftFront, rightFront, climber, intake;
	ADXRS450_Gyro gyro;
	Encoder encoderLeft;
	Timer timer = new Timer();
	Solenoid shifter;

	
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
		encoderLeft = new Encoder(10, 11, false, Encoder.EncodingType.k2X);
		
		//Robot
		myRobot = new RobotDrive(leftBack, leftFront, rightBack, rightFront);
		
		shifter = new Solenoid(0);
		
	}
	
	
	
	
}
