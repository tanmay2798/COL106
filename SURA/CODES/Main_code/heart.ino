void heart()
{
//  while(check==true)
//  {
//  while(Serial3.available() > 0)
//  {
//    ch = Serial3.read(); //loop till character received
//    if(ch == 0x0A) // if received character is <LF>, 0x0A, 10 then process buffer
//    {
//      posi = 0; // buffer position reset for next reading
//      
//      // extract data from serial buffer to 8 bit integer value
//      // convert data from ASCII to decimal
//      read3 = ((sbuffer[11]-'0')*100) + ((sbuffer[12]-'0')*10) +(sbuffer[13]-'0');
//     
//      Serial.println(read3);
//      check=!check;
//      lcdprint(read3,"heart rate");
//      // Do whatever you wish to do with this sensor integer variables
//      // Show on LCD or Do some action as per your application
//      // Value of variables will be between 0-255
//      
//    } 
//    else 
//    { //store serial data to buffer
//      sbuffer[posi++] = ch;
//      if(posi > sizeof(sbuffer))
//      {
//        posi = 0;
//      }
//      
//    }
//  } // end while
// }
}
