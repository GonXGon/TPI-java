int lives = 3;
int score = 0;

Puck puck;
Ball ball;

void setup(){
 size(900,900);
 puck = new Puck();
 ball = new Ball();
}
void draw(){
 background(255);
 puck.show();
 puck.rectdirection();
 
 ball.bshow();
 ball.direction();
 ball.speed();
 ball.bounce();
 ball.touch();
 
 fill(0);
 text(lives,200,100);
}

 void keyReleased(){
  puck.move(0);
 }
 
 void keyPressed(){
  if(key == 'd'){
     puck.move(9);
  }
   else if(key == 'a'){
     puck.move(-9); 
  }
}
