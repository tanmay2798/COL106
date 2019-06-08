void weight() {


  lcd.setCursor(0,3);
  lcd.print("weight");
  delay(2000);
  for(int i=0;i<100;i++){
    sumw=sumw+scale.get_units();

     Serial.println(scale.get_units());
      Serial.println(sumw);
  }

  weightf=sumw/100.0;

  Serial.print("WEIGHT = ");
  Serial.println(weightf); //scale.get_units() returns a float

//  myFile.print("WEIGHT = ");
//  myFile.println(weightf); //scale.get_units() returns a float
  vals[2]=weightf;
  lcdprint(weightf,"weight"); //scale.get_units() returns a float
  sumw=0;
  weightf=0;
}
