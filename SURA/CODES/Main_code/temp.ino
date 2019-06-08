void temperature() {

//  lcd.setCursor(0,3);
//  lcd.print("Temperature");
  for(it=0;it<120;it++){
  Vo = analogRead(ThermistorPin);
  R2 = R1 * (1023.0 / (float)Vo - 1.0);
  logR2 = log(R2);
  T = (1.0 / (c1 + c2*logR2 + c3*logR2*logR2*logR2));
  Tc = T - 273.15;
  Tf = (Tc * 9.0)/ 5.0 + 32.0; 
  


//  Serial.print(Tf);
//  Serial.print(" F; ");
//  Serial.print(Tc);
//  Serial.println(" C");   
  
  delay(50);
  }
  sumt=0;
  for(it=0;it<10;it++){
    sumt=sumt+analogRead(ThermistorPin);
  }
  final_temp=sumt/10.0;
  R2 = R1 * (1023.0 / final_temp - 1.0);
  logR2 = log(R2);
  T = (1.0 / (c1 + c2*logR2 + c3*logR2*logR2*logR2));
  Tc = T - 273.15;
  Tf = (Tc * 9.0)/ 5.0 + 32.0;
 // Serial.print("Temperature: "); 
 delay(1000);
  Serial.println(Tc);
  //lcdprint(Tc,"Temperature");
}
