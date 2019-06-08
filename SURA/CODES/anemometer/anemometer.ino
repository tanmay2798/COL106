/* Encoder Library - Basic Example
 * http://www.pjrc.com/teensy/td_libs_Encoder.html
 *
 * This example code is in the public domain.
 */

#include <Encoder.h>

// Change these two numbers to the pins connected to your encoder.
//   Best Performance: both pins have interrupt capability
//   Good Performance: only the first pin has interrupt capability
//   Low Performance:  neither pin has interrupt capability
Encoder myEnc(18,19);
//   avoid using pins with LEDs attached
long t=0;
long t1=0;
long t2=0;
long n=0;
long oldPosition;
float avg_sp=0;
long pos=0;
long tim=0;
float max_sp=0.0;
void setup() {
  Serial.begin(9600);
  Serial.println("Basic Encoder Test:");
  oldPosition = myEnc.read();
}



void loop() {
  Serial.println(myEnc.read());
  long newPosition = myEnc.read();
  if (newPosition ==-1) {
    t=millis();
    t2=0;
  }

   if (newPosition==oldPosition){
     t=millis();
     n=newPosition;
     t2=0;
     
     if (avg_sp !=0 && abs(pos/2400*2*3.14)>0.1)
     {
      Serial.print("Average speed (rpm)  ");
      Serial.println(abs(avg_sp/2.4*60));
      avg_sp=0;

      Serial.print("Maximum speed (rpm)  ");
      Serial.println(abs(max_sp/2.4*60));
      
      Serial.print("Final position (rad)  ");
      Serial.println(pos);
      Serial.println(abs((pos/2400)*2*3.14));
      max_sp=0;
     }
  }
  
  if (newPosition != oldPosition) {
    oldPosition = newPosition;
    pos=newPosition-n;
    
   // Serial.println(pos);
   // Serial.print("time   ");
    tim=millis()-t;
   // Serial.println(tim);
    avg_sp= -1*pos/tim;
    if (tim%20==0 && tim!=0)
    {
      
      t1=pos-t2;
      float sp= t1/20.0;
      t2=pos;
      //Serial.println(sp);
      if (sp> max_sp)
      max_sp=sp;
     
    }
    }
}
