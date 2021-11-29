package com.telek.telekmath.core.geometry.lines;

import com.telek.telekmath.TMath;
import com.telek.telekmath.core.geometry.points.TPoint3D;
import com.telek.telekmath.core.geometry.vectors.TVector3D;


public class TLine3D {

    private TPoint3D zeroPoint;
    private TVector3D direction;

    /*  NORMAL CONSTRUCTORS  */

    public TLine3D(TPoint3D zeroPoint, TVector3D direction){
        this.zeroPoint = zeroPoint;
        this.direction = direction;
    }

    public TLine3D(TPoint3D zeroPoint, double a, double b, double c){
        this(zeroPoint, new TVector3D(a,b,c));
    }

    public TLine3D(double x0, double y0, double z0, TVector3D direction){
        this(new TPoint3D(x0,y0,z0), direction);
    }

    public TLine3D(double x0, double y0, double z0, double a, double b, double c){
        this(new TPoint3D(x0,y0,z0), new TVector3D(a,b,c));
    }

    public TLine3D(TPoint3D point1, TPoint3D point2){
        this(point1, new TVector3D(point2.x - point1.x, point2.y - point1.y, point2.z - point1.z));
    }


    /*  OTHER METHODS  */

    public boolean isParallelTo(TLine3D line2){
        double p = line2.direction.x;
        double q = line2.direction.y;
        double r = line2.direction.z;
        if( (p==0 || q==0 || r==0) && (p!=0 && q!=0 && r!=0) ) return false;
        double ap = this.direction.x / p;
        double bq = this.direction.y / q;
        double cr = this.direction.z / r;
        return areSame(ap, bq) && areSame(ap, cr) && areSame(bq, cr);
    }

    public boolean isPerpendicularTo(TLine3D line2){
        return areSame(0,
                this.getDirection().x * line2.getDirection().x +
                this.getDirection().y * line2.getDirection().y +
                this.getDirection().z * line2.getDirection().z
        );
    }


    /*  HELPER METHODS  */

    private static boolean areSame(double d1, double d2){
        return TMath.areEqual(d2, d1);
    }



    /*  GETTERS AND SETTERS  */

    public TPoint3D getZeroPoint() { return zeroPoint; }
    public void setZeroPoint(TPoint3D zeroPoint) { this.zeroPoint = zeroPoint; }
    public TVector3D getDirection() { return direction; }
    public void setDirection(TVector3D direction) { this.direction = direction; }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("(x-%.5f) / %.5f  =  (y-%.5f) / %.5f = (z-%.5f) / %.5f",
                zeroPoint.x, direction.x, zeroPoint.y, direction.y, zeroPoint.z, direction.z);
    }




}
