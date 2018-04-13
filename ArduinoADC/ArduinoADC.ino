
/*
   Este código permite imprime un mensaje en el Puerto Serie a intérvalos de
   1 segundo entre un mensaje y otro. El mensaje consiste en la palabra "mensaje"
   seguida de un número que corresponde al valor del contador n.
   @author Antony Garcia Gonzalez, Panama Hitek
*/

int n = 0; //contador
void setup() {
  //Iniciamos la comunicación con el puerto serie
  Serial.begin(9600);
}
//////
void loop() {
  //Se crea la variable que contendrá el mensaje a imprimir
  String output = "Dato capturado";
  //Se concatena el valor del contador al mensaje
  int  value = analogRead(0);
  value = map(value,0,1023,0,1024);
  analogWrite(0,value);
  delay(1);
  output += value;
  //Se imprime el mensaje en el Puerto Serie
  Serial.println(output);
  n++; //Se aumenta el valor del contador
  delay(1000); //Retraso de 1 segundo
}
