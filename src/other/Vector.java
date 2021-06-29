package other;

public enum Vector {

     RIGHT(1,0),
     LEFT(-1,0),
     BOTTOM(0,1),
     TOP(0,-1),
     RIGHT_BOTTOM(1,1),
     RIGHT_TOP(1,-1),
     LEFT_BOTTOM(-1,1),
     LEFT_TOP(-1,-1);

     private int xDir;
     private int yDir;


     Vector(int xDir, int yDir) {
          this.xDir = xDir;
          this.yDir = yDir;
     }

     public int getxDir() {
          return xDir;
     }

     public void setxDir(int xDir) {
          this.xDir = xDir;
     }

     public int getyDir() {
          return yDir;
     }

     public void setyDir(int yDir) {
          this.yDir = yDir;
     }
}
