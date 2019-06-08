int ThermistorPin = 0;
int Vo;
float R1 = 10000;
float logR2, R2, T, Tc, Tf;
float c1 = 1.009249522e-03, c2 = 2.378405444e-04, c3 = 2.019202697e-07;
float sum=0,i=0,final_temp=0;

void setup() {
Serial.begin(9600);
}

void loop() {

  for(i=0;i<120;i++){
  Vo = analogRead(ThermistorPin);
  R2 = R1 * (1023.0 / (float)Vo - 1.0);
  logR2 = log(R2);
  T = (1.0 / (c1 + c2*logR2 + c3*logR2*logR2*logR2));
  Tc = T - 273.15;
  Tf = (Tc * 9.0)/ 5.0 + 32.0; 
  

  Serial.print("Temperature: "); 
  Serial.print(Tf);
  Serial.print(" F; ");
  Serial.print(Tc);
  Serial.println(" C");   
  
  delay(500);
  }
  sum=0;
  for(i=0;i<10;i++){
    sum=sum+analogRead(ThermistorPin);
  }
  final_temp=sum/10.0;
  R2 = R1 * (1023.0 / final_temp - 1.0);
  logR2 = log(R2);
  T = (1.0 / (c1 + c2*logR2 + c3*logR2*logR2*logR2));
  Tc = T - 273.15;
  Tf = (Tc * 9.0)/ 5.0 + 32.0;

  Serial.println(Tc);
  delay(3000);
}
