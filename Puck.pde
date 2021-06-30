class Puck{
  int x = 450;
  int y = height - 20;
  int w = 40;
  int h = 5;
  int r = 5;
  
  float change = 0;
  
  void show(){
    strokeWeight(0);
    fill(0);
    rectMode(CENTER);
    rect(x,y,r * w,r * h);
  }
  
  void move(float steps){
    change = steps;
    
  }
  void rectdirection(){
    x += change; 
    x = constrain(x,100,height - 100);
  }
}
