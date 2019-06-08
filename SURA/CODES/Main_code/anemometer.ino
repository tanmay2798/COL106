void anemometer() 
{
//  lcd.setCursor(0,3);
//  lcd.print("anemometer");
  while(1){
   
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
      
//      Serial.print("Average speed (rpm)  ");
      Serial.println(abs(avg_sp/2.4*60));
//      myFile.print("Average speed (rpm)  ");
//      myFile.println(abs(avg_sp/2.4*60));
      //lcdprint(abs(avg_sp/2.4*60),"average speed");
      avg_sp=0;
    
//      Serial.print("Maximum speed (rpm)  ");
    //  Serial.println(abs(max_sp/2.4*60));
//      myFile.print("Maximum speed (rpm)  ");
//      myFile.println(abs(max_sp/2.4*60));
  //    lcdprint(abs(max_sp/2.4*60),"max speed");
      max_sp=0;
// 791331   
//      Serial.print("Final position (rad)  ");
      delay(1000);
      Serial.println(abs(pos*0.00012637));
//      myFile.print("Final position (rad)  ");
//      myFile.println(pos);
//      myFile.println(abs((pos/2400)*2*3.14));
      //lcdprint(abs(pos*0.00012637),"final pos");
      break;
      
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
    if (tim%50==0 && tim!=0)
    {
      
      t1=pos-t2;
      float sp= abs(t1/50.0);
      t2=pos;
      //Serial.println(sp);
      if (sp> max_sp)
      max_sp=sp;
     
    }
  }
  }
  
}
