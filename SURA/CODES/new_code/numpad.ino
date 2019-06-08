void numpad()
{
  
  key = keypad.getKey();
  if(key!=NO_KEY){
    lcd.setCursor(0,2);    
    lcd.print(key);
  Serial.println(key);
  }
}
