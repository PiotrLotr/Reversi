package other;

public enum Vector {

     plusX(1,0),
     minusX(-1,0),
     plusY(0,1),
     minusY(0,-1),
     plusXplusY(1,1),
     plusXminusY(1,-1),
     minusXplusY(-1,1),
     minusXminuxY(-1,-1);

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
