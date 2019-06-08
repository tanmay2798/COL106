int triggerPin = 22; //triggering on pin 7
int echoPin = 23;    //echo on pin 8


void setup() {
  // put your setup code here, to run once:
Serial.begin(9600);  //we'll start serial comunication, so we can see the distance on the serial monitor

 pinMode(triggerPin, OUTPUT); //defining pins
  pinMode(echoPin, INPUT);

}

void loop() {
  // put your main code here, to run repeatedly:

 float duration, distance;    //Adding duration and distance
  float sum=0;
  int i=0;
//  digitalWrite(triggerPin, HIGH); //triggering the wave(like blinking an LED)
//  delay(5);
//  digitalWrite(triggerPin, LOW);
//  
//  duration = pulseIn(echoPin, HIGH); //a special function for listening and waiting for the wave
//  distance = (duration/2) / 29.1; //transforming the number to cm(if you want inches, you have to change the 29.1 with a suitable number

  for(i=0;i<10;i++){
    digitalWrite(triggerPin, HIGH); //triggering the wave(like blinking an LED)
    delay(5);
    digitalWrite(triggerPin, LOW);
    
    duration = pulseIn(echoPin, HIGH); //a special function for listening and waiting for the wave
    distance = (duration/2) / 29.1; //transforming the number to cm(if you want inches, you have to change the 29.1 with a suitable number
    duration = pulseIn(echoPin, LOW);
      Serial.println(distance);
      //arr[i]=distance;
      sum=sum+distance;
    //  Serial.println(i);
  }
  sum=sum/10.0;
  Serial.print("dis = ");
  Serial.println(sum);
}

