class Ball{
  float x = width/2;
  float y = height/2;

  float speedx = 0;
  float speedy = 5;
  
  float r = 5;
  
  void bshow(){
   fill(0);
   strokeWeight(0);
   ellipse(x,y,r * 6,r * 6);
 }
 
  void direction(){
   x = constrain(x,25,height-20); 
   y = constrain(y,20,width-20);
 }
 
  void speed(){
   x = x + speedx;
   y = y + speedy;
 }
 
 void bounce(){
  if(x < 25){ 
    speedx = -speedx;
  }
  if(x > height - 20){
   speedx = -speedx; 
  }
  if(y < 20){
   speedy = -speedy; 
  }
  if(y > width - 20){
    x = width / 2;
    y = height / 2;
    lives--;
    speedx = random(-5,5);
    speedy = random(-5,5);
    if(lives == 0){
     //exit(); 
    }
  }
 }
 
 void touch(){
   float d = dist(x,y,puck.x,puck.y);
   if(d < r + puck.r){
     speedy = -speedy;
     }
   }
}
