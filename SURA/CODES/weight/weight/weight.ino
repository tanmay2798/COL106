#include "HX711.h"
#define calibration_factor -8850 //This value is obtained using the SparkFun_HX711_Calibration sketch
#define DOUT  21
#define CLK  20
HX711 scale(DOUT, CLK);
void setup() {
  Serial.begin(9600);
  Serial.println("HX711 scale demo");
  scale.set_scale(calibration_factor); //This value is obtained by using the SparkFun_HX711_Calibration sketch
  scale.tare(); //Assuming there is no weight on the scale at start up, reset the scale to 0
  Serial.println("Readings:");
}
void loop() {
  Serial.print("Reading: ");
  Serial.print(scale.get_units(), 10); //scale.get_units() returns a float
  Serial.print(" kg"); //You can change this to kg but you'll need to refactor the calibration_factor
  Serial.println();
}
