#include<LiquidCrystal.h>
#include <Keypad.h>
#include <Encoder.h>
#include <SPI.h>
#include <SD.h>
#include "HX711.h"
#define calibration_factor -8300  //This value is obtained using the SparkFun_HX711_Calibration sketch
#define DOUT  21
#define CLK  20

int str=0;
int siq=0;

//// bp
//char sbuffer[30], ch;
//byte posi, read1, read2, read3;
//boolean check=true;


//RESET
//  int resetpin = 24;

//LOAD_CELL
  HX711 scale(DOUT, CLK);
  float weightf=0;
  float sumw=0;


////SD_CARD
////File myFile;
//
//LCD_VARIABLES
 // LiquidCrystal lcd(12, 11, 5, 4, 3, 2);  // sets the interfacing pins

//ULTRASOUND_VARIABLES
  float duration=0, distance=0;  
  int trigPin = 22; //triggering on pin 7
  int echoPin = 23;    //echo on pin 8
  float arr;
  float sumu=0;
  int i;
 
////NUMPAD_VARIABLES
//  const byte ROWS = 4; //four rows
//  const byte COLS = 4; //four columns
//  char keys[ROWS][COLS] = {
//    {'1','2','3','A'},
//    {'4','5','6','B'},
//    {'7','8','9','C'},
//    {'*','0','#','D'}
//  };
//  
//  byte rowPins[ROWS] = {17, 16, 27,26 }; //connect to the row pinouts of the keypad
//  byte colPins[COLS] = {9, 8, 7, 6}; //connect to the column pinouts of the keypad
//  
//  Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );
//  char key;

//ANEMOMETER_VARIABLES
Encoder myEnc(18,19);
  long t=0;
  long t1=0;
  long t2=0;
  long n=0;
  long oldPosition;
  float avg_sp=0;
  long pos=0;
  long tim=0;
  float max_sp=0.0;

//TEMPERATURE_VARIABLES
  int ThermistorPin = A0;
  int Vo;
  float R1 = 10000;
  float logR2, R2, T, Tc, Tf;
  float c1 = 1.009249522e-03, c2 = 2.378405444e-04, c3 = 2.019202697e-07;
  float sumt=0,it=0,final_temp=0;

  


void setup()

{
  
//  digitalWrite(resetpin,HIGH);
//  pinMode(resetpin,OUTPUT);

  
  Serial.begin(9600);  //we'll start serial comunication, so we can see the distance on the serial monitor
//
Serial.println("k");
//  SD.begin(4);
//
//  //SD_CARD
//    myFile = SD.open("test."+key, FILE_WRITE);
//

  // bp
  Serial3.begin(9600);
  pos = 0;
  
  //LCD_VARIABLES
 //   lcd.begin(16, 2);  // initializes the 16x2 LCD
   
  
  //ULTRASOUND_VARIABLES
    pinMode(trigPin, OUTPUT); //defining pins
    pinMode(echoPin, INPUT);

   //ANEMOMETER_VARIABLES
    oldPosition=myEnc.read();

   //LOAD_CELL
    scale.set_scale(calibration_factor); //This value is obtained by using the SparkFun_HX711_Calibration sketch
    scale.tare(); //Assuming there is no weight on the scale at start up, reset the scale to 0
  

}

void loop()
{
//Serial.println("k");
 // numpad();
//  check=true;
  if(Serial.available()>0){
    str=Serial.parseInt();
   // siq=Serial.parseInt();
    Serial.println(str);
  //  Serial.println(siq);
     
    }

//     if(siq==1){
//  Serial.println("hi");
//  ultrasound();     // FUNTION CALLED
//  
//  }
  if(str==1){
  Serial.println("hi");
  ultrasound();     // FUNTION CALLED
  
  }

  if(str==2)
  weight();
  
  if( str==3)
  anemometer();

  if( str==4)
  temperature();

  if(str==5)
//  bp();

  if(str==6)
//  heart();
  
  if( str==0)
//  digitalWrite(resetpin,LOW);

  if( str==9){
    Serial.println(abs((pos/2400)*2*3.14));
    Serial.println(Tc);
    Serial.println(sumu/100.0);
    Serial.println(weightf);
    delay(5000);
  }

}
