void ultrasound() 
{
  lcd.setCursor(0,3);
  lcd.print("ultrasound");
 // delay(2000);
 sumu=0;
for (i=0;i<=100;i++)
{
digitalWrite(trigPin, LOW);
delayMicroseconds(2);
// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
// Reads the echoPin, returns the sound wave travel time in microseconds
duration = pulseIn(echoPin, HIGH);
// Calculating the distance
distance= duration*0.034/2;
// Prints the distance on the Serial Monitor
Serial.println(distance);
sumu=sumu+distance;
}
Serial.print("Distance: ");
Serial.println(sumu/100.0);
vals[1]=sumu/100.0;
lcdprint(sumu/100.0,"height");
}
