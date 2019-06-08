#include<LiquidCrystal.h>
#include <Keypad.h>
#include <Encoder.h>
#include <SPI.h>
#include <SD.h>
#include "HX711.h"
#define calibration_factor -8850 //This value is obtained using the SparkFun_HX711_Calibration sketch
#define DOUT  21
#define CLK  20



//LOAD_CELL
  HX711 scale(DOUT, CLK);
  float weightf=0;
  float sumw=0;


////SD_CARD
File myFile;
String f="";
int count=0;
float vals[]={1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0};
int l=7;
 int newf=10000;
int test =vals[0];

//
//LCD_VARIABLES
  LiquidCrystal lcd(12, 11, 5, 4, 3, 2);  // sets the interfacing pins

//ULTRASOUND_VARIABLES
  float duration=0, distance=0;  
  int trigPin = 22; //triggering on pin 7
  int echoPin = 23;    //echo on pin 8
  float arr;
  float sumu=0;
  int i;
  
 
//NUMPAD_VARIABLES
  const byte ROWS = 4; //four rows
  const byte COLS = 4; //four columns
  char keys[ROWS][COLS] = {
    {'1','2','3','A'},
    {'4','5','6','B'},
    {'7','8','9','C'},
    {'*','0','#','D'}
  };
  
  byte rowPins[ROWS] = {17, 16, 15, 14}; //connect to the row pinouts of the keypad
  byte colPins[COLS] = {9, 8, 7, 6}; //connect to the column pinouts of the keypad
  
  Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );
  char key;

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
  
  Serial.begin(9600);  //we'll start serial comunication, so we can see the distance on the serial monitor
//Serial.print("j");
  SD.begin(53);

//SD_CARD
   //myFile = SD.open("test."+key, FILE_WRITE);

  //LCD_VARIABLES
    lcd.begin(16, 2);  // initializes the 16x2 LCD
   
 
  //ULTRASOUND_VARIABLES
    pinMode(trigPin, OUTPUT); //defining pins
    pinMode(echoPin, INPUT);

   //ANEMOMETER_VARIABLES
    oldPosition=myEnc.read();

   //LOAD_CELL
    scale.set_scale(calibration_factor); //This value is obtained by using the SparkFun_HX711_Calibration sketch
    scale.tare(); //Assuming there is no weight on the scale at start up, reset the scale to 0
  
//Serial.print("j");
}

void loop()
{

//  Serial.print("j");
  numpad();
  if (key=='0')
  {
    count=0;
    vals[0]=1.0;
    for (i=1;i<l;i++)
    vals[i]=-1.0;
    if (f.length()>0)
    {
      newf= (f.substring(0,5)).toInt();
    myFile.close();
    }
    Serial.println("Enter your 5 digit registration number (Press # if new user) : ");
     lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("Enter your 5 digit registration number (Press # if new user) : ");
  
  f="";
 
  for (i=1; i<=5;i++)
  {
     key=NO_KEY; 
    while(key == NO_KEY)
    {
      numpad();
      
    }
    if (key == '#')
    {
      newf++;
      f=String(newf);
      count = 2;
       break;
    }
  
  else if (key!='#' && key != NO_KEY)
    f.concat(key);
  }
  f.concat(".txt");
  Serial.println(f);
if (!SD.exists(f) && count!=2)
{
      Serial.println(" File not available!! ");
      lcd.clear();
  lcd.setCursor(0,0);
  lcd.print(" File not available!! "); 
  count=1;
   }  
else if(SD.exists(f) && count!=2)
Serial.println(" File available ");

else
{
  Serial.print("Your new file name : ");
   Serial.println(newf);
}
   


if (count!=1)
   myFile = SD.open(f, FILE_READ);
   //if (count == 2)
    //myFile.println("Test1");

    if(count == 0)
    {
      int chk=0;
    while (myFile.available() != 0) { 
      if ((char)myFile.read()=='\n')
     {         chk++;
      if (chk%l==0)
      test=test+1.0;   
     }
    }
   // String l_line = myfile.readStringUntil('\n');    
    //l_line.trim();
    vals[0]=test;    
    }
    
    Serial.println(" Press 1 for Height Measurement /n Press 2 for Weight Measurement /n Press 3 for Lung Capacity Measurement /n Press 0 for Start Over /n Press * to get results (or log out/store values)");
    lcd.clear();
  lcd.setCursor(0,0);
  lcd.print(" Press 1 for Height Measurement /n Press 2 for Weight Measurement /n Press 3 for Lung Capacity Measurement /n Press 0 for Start Over /n Press * to get results (or log out/store values)");
key=NO_KEY;
  }

 
 
  if(key=='1')
  ultrasound();                  // FUNCTION CALLED
 // delay(1000);
 if(key=='2')
  weight();
//  delay(1000);
 if(key=='3')
  anemometer();
 // delay(1000);
 if(key=='4')
  temperature();
 if(key=='*')
 {
  myFile.close();
  myFile = SD.open(f, FILE_WRITE);
  for(i=0;i<l;i++)
    myFile.println(vals[i]);
 
  float heights[(int)test];
   float weights[(int)test];
  float poss[(int)test];
  float avg_sps[(int)test];
  float max_sps[(int)test];
  float final_temp[(int)test];
 myFile.close();
  myFile = SD.open(f, FILE_READ);
  i=0;
  myFile.seek(0);
  
  while (myFile.available() != 0) {
   
        String l_line = myFile.readStringUntil('\n');    
    l_line.trim();
    
     float v = l_line.toFloat();
     if ((i-1)%l==0 && v!=-1.0)
     heights[(int)((i-1)/l)]=v;
     if ((i-2)%l==0 && v!=-1.0)
    weights[(int)((i-2)/l)]=v;
     if ((i-3)%l==0 && v!=-1.0)
     poss[(int)((i-3)/l)]=v;
     if ((i-4)%l==0 && v!=-1.0)
      avg_sps[(int)((i-4)/l)]=v;
     if ((i-5)%l==0 && v!=-1.0)
     max_sps[(int)((i-5)/l)]=v;
     if ((i-6)%l==0 && v!=-1.0)
     final_temp[(int)((i-6)/l)]=v;
      i++;
  }
  Serial.println("\n heights: ");
  for (i=0;i<(int)test;i++)
  Serial.println(heights[i]);
  Serial.println("\n weights: ");
  for (i=0;i<(int)test;i++)
  Serial.println(weights[i]);
  Serial.println("\n poss: ");
  for (i=0;i<(int)test;i++)
  Serial.println(poss[i]);
  Serial.println("\n  avg_sps: ");
  for (i=0;i<(int)test;i++)
  Serial.println(avg_sps[i]);
  Serial.println("\n max_sps: ");
  for (i=0;i<(int)test;i++)
  Serial.println(max_sps[i]);
  Serial.println("\n final_temp: ");
  for (i=0;i<(int)test;i++)
  Serial.println(final_temp[i]);
    }
 
 
  //delay(5000);
}
