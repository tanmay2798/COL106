char sbuffer[30], ch;
byte pos, read1, read2, read3;


void setup()
{
  Serial1.begin(9600);
  Serial.begin(9600);
  pos = 0;
}

// -=-=- Program Loop -=-=-=
void loop()
{
  while(Serial1.available() > 0)
  {
    ch = Serial1.read(); //loop till character received
    if(ch == 0x0A) // if received character is <LF>, 0x0A, 10 then process buffer
    {
      pos = 0; // buffer position reset for next reading
      
      // extract data from serial buffer to 8 bit integer value
      // convert data from ASCII to decimal
      read1 = ((sbuffer[1]-'0')*100) + ((sbuffer[2]-'0')*10) +(sbuffer[3]-'0');
      read2 = ((sbuffer[6]-'0')*100) + ((sbuffer[7]-'0')*10) +(sbuffer[8]-'0');
      read3 = ((sbuffer[11]-'0')*100) + ((sbuffer[12]-'0')*10) +(sbuffer[13]-'0');
      Serial.println(read1);
      Serial.println(read2);
      Serial.println(read3);
      // Do whatever you wish to do with this sensor integer variables
      // Show on LCD or Do some action as per your application
      // Value of variables will be between 0-255
      
    } 
    else 
    { //store serial data to buffer
      sbuffer[pos++] = ch;
      if(pos > sizeof(sbuffer))
      {
        pos = 0;
      }
      
    }
  } // end while
}
