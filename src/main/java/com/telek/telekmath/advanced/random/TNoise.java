package com.telek.telekmath.advanced.random;


import com.telek.telekmath.core.geometry.vectors.TVector2D;
import com.telek.telekmath.core.geometry.vectors.TVector3D;

public class TNoise {

    /* No constructor */
    private TNoise(){}



    /**
     * A class ONLY used in TNoise class any nowhere else.
     * This class is intentionally left small and kinda unfinished for optimization purposes.
     * If you want to save your x,y datas in a vector use TVector2D or TVector3D.
     * You don't have to use this class since all TNoise methods will have a variant like func(float, float).
     * @see TVector2D
     * @see TVector3D
     */
    public static class Vec2 {
        public float x, y;
        public Vec2(float x, float y){
            this.x = x;
            this.y = y;
        }
        public float dot(Vec2 another){
            return (this.x * another.x) + (this.y * another.y);
        }
        public Vec2 add(float _x, float _y){
            return new Vec2( this.x + _x, this.y + _y );
        }
        public Vec2 add(Vec2 another){ return new Vec2( this.x + another.x, this.y + another.y ); }
    }



    /**
     * Returns a random number in range (0,1) using a 'one liner I found on the internet' for point (x,y).
     * This method has been known by the shader community for a long time.
     * @param x any float
     * @param y any float
     * @return a random number using the x,y values, returns a number in range (0, 1)
     */
    public static float canonicalRandom(float x, float y){
        return fract( (( (float) Math.sin( 12.9898f * x + 78.233 * y ) ) * 43758.5453f) );
    }


    /**
     * Returns a random number in range (0,1) using a 'one liner I found on the internet' for a vector.
     * This method has been known by the shader community for a long time.
     * @param vec x,y values
     * @return a random number using the x,y values, returns a number in range (0, 1)
     */
    public static float canonicalRandom(Vec2 vec){
        return canonicalRandom(vec.x, vec.y);
    }


    /**
     * Returns a random value in range (0,1) using the point (x,y). This noise will look blocky and grid-like. Use perlin noise
     * if you want it to look smooth.
     * @param x any float
     * @param y any float
     * @return valueNoise(x,y), returns a number in range (0, 1)
     */
    public static float valueNoise(float x, float y){
        Vec2 i = floor(x,y);
        Vec2 fvec = fract(x, y);
        Vec2 cubic = new Vec2(fvec.x * fvec.x * ( 3.0f - 2.0f * fvec.x), fvec.y * fvec.y * ( 3.0f - 2.0f * fvec.y));

        float topmix = (canonicalRandom(i) * (1-cubic.x)) + (canonicalRandom( i.add(1.0f, 0.0f) ) * cubic.x);
        float botmix = (canonicalRandom( i.add(0.0f, 1.0f) ) * (1-cubic.x)) + (canonicalRandom( i.add(1.0f, 1.0f) ) * cubic.x);

        return ( (topmix * (1-cubic.y)) + (botmix * cubic.y) );
    }



    /**
     * Returns a random value in range (0,1) using the vector. This noise will look blocky and grid-like. Use perlin noise
     * if you want it to look smooth.
     * @param vec x,y values
     * @return valueNoise(x,y), returns a number in range (0, 1)
     */
    public static float valueNoise(Vec2 vec){
        return valueNoise( vec.x, vec.y );
    }



    /**
     * Returns a 2D noise value in range (0,1) based on Morgan Mcguire's shader:  <a>https://www.shadertoy.com/view/4dS3Wd</a>
     * @param x any float
     * @param y any float
     * @return morganMcguireNoise(x,y), returns a number in range (0, 1)
     */
    public static float morganMcguireNoise(float x, float y){
        Vec2 i = floor(x, y);
        Vec2 fvec = fract(x, y);
        float a = canonicalRandom(i);
        float b = canonicalRandom(i.add(1.0f, 0.0f));
        float c = canonicalRandom(i.add(0.0f, 1.0f));
        float d = canonicalRandom(i.add(1.0f, 1.0f));
        Vec2 u = new Vec2(fvec.x * fvec.x * (3f - 2f * fvec.x), fvec.y * fvec.y * (3f - 2f * fvec.y));
        return (mix(a, b, u.x) + (c - a) * u.y * (1f - u.x) + (d - b) * u.x * u.y);
    }


    /**
     * Returns perlin noise value in range (-1, 1) for point (x,y). This is the default perlin noise.
     * @param x any float
     * @param y any float
     * @return perlinNoise(x,y), returns a number in range (-1, 1)
     */
    public static float perlinNoise(float x, float y) {
        int x0 = (int) x;
        int x1 = x0 + 1;
        int y0 = (int) y;
        int y1 = y0 + 1;
        float sx = x - (float)x0;

        return perlinInterpolate(
                perlinInterpolate( perlinDotGridGradient(x0, y0, x, y), perlinDotGridGradient(x1, y0, x, y), sx ),
                perlinInterpolate( perlinDotGridGradient(x0, y1, x, y), perlinDotGridGradient(x1, y1, x, y), sx ),
                y - (float) y0
        );
    }


    /**
     * Returns perlin noise value in range (-1, 1) for point (x,y). This is the default perlin noise.
     * @param vec any cev
     * @return perlinNoise(x,y), returns a number in range (-1, 1)
     */
    public static float perlinNoise(Vec2 vec){ return perlinNoise(vec.x, vec.y); }



    /**
     * Returns a Fractional Brownian motion value for perlin noise with the specified octaves for point (x,y).
     * This value is in range (-1, 1)
     * @param x any float for perlinNoise
     * @param y any float for perlinNoise
     * @param octaves how many times this filter will be used
     * @return 'octaves times' filtered perlinNoise(x,y) with fbm, returns a number in range (-1, 1)
     */
    public static float fbmFilteredPerlinNoise(float x, float y, int octaves){
        float normFactor = 0.0f, value = 0.0f, scale = 0.5f;
        for(int i = 0; i < octaves; i++){
            value += perlinNoise(x, y) * scale;
            normFactor += scale;
            x *= 2.0;
            y *= 2.0;
            scale *= 0.5;
        }
        return value / normFactor;
    }



    /**
     * Returns a Fractional Brownian motion value for perlin noise with the specified octaves for point (x,y).
     * This value is in range (-1, 1).
     * This method does {@link #fbmFilteredPerlinNoise(float, float, int)} with 4 octaves, returns a number in range (-1, 1).
     * @param x any float for perlinNoise
     * @param y any float for perlinNoise
     * @return fbm filtered noise
     */
    public static float fbmFilteredPerlinNoise(float x, float y){ return fbmFilteredPerlinNoise(x, y, 4); }



    /**
     * Returns a Fractional Brownian motion value for perlin noise with the specified octaves for a vector.
     * This value is in range (-1, 1)
     * @param vec any vector for perlinNoise
     * @param octaves how many times this filter will be used
     * @return 'octaves times' filtered perlinNoise(x,y) with fbm, returns a number in range (-1, 1)
     */
    public static float fbmFilteredPerlinNoise(Vec2 vec, int octaves){ return fbmFilteredPerlinNoise(vec.x, vec.y, octaves); }




    /**
     * Returns a Fractional Brownian motion value for perlin noise with the specified octaves for a vector.
     * This value is in range (-1, 1).
     * This method does {@link #fbmFilteredPerlinNoise(Vec2, int)} with 4 octaves, returns a number in range (-1, 1).
     * @param vec any x,y values for perlinNoise
     * @return fbm filtered noise
     */
    public static float fbmFilteredPerlinNoise(Vec2 vec){ return fbmFilteredPerlinNoise(vec.x, vec.y); }




    /**
     * Returns a ridge filtered noise value.
     * This filter will make for example a mountain have sharper ridges.
     * This value is in range (0, 1).
     * @param noiseValue any noise value that is returned from any noise function
     * @return noiseValue with ridge filter in range (0, 1)
     */
    public static float ridgeFilteredNoise(float noiseValue) {
        return 2f * (0.5f - Math.abs(0.5f - noiseValue));
    }


    /**
     * Returns the noise value ^ exponential. Will return in range (0,1) as long as noise value is in range (0,1).
     * @param noiseValueBetweenZeroAndOne any noise value in range (0,1)
     * @param exponent an integer specifying how many times this filter will be applied
     * @return 'exponent times' filtered noiseValueBetweenZeroAndOne, in range (0,1) if noiseValue is in (0,1)
     */
    public static float exponentiallyFilteredNoise(float noiseValueBetweenZeroAndOne, int exponent) {
        float result = 1;
        for (int i = 0; i < exponent; i++)
            result *= noiseValueBetweenZeroAndOne;
        return result;
    }



    /**
     * This method is a not-working or alternatively weirdly-working perlin noise that I've wrote. I didn't delete this
     * method because it looks weird and sometimes very random. You can set isInterestion boolean to any value
     * to get a different effect as well. This method returns a number in [0, 1) interval
     * @param x any float
     * @param y any float
     * @param isInteresting set it to anything you want just be consistent
     * @return a number in [0, 1) interval
     */
    public static float brokenPerlinNoise(float x, float y, boolean isInteresting){
        Vec2 i = floor(x,y);
        Vec2 f = fract(x,y);

        // 4 corners of a rectangle surrounding our point
        // must be up to 2pi radians to allow the random vectors to face all directions
        float tl = canonicalRandom(i) * 6.283f;
        float tr = canonicalRandom(i.add(1.0f, 0.0f)) * 6.283f;
        float bl = canonicalRandom(i.add(0.0f, 1.0f)) * 6.283f;
        float br = canonicalRandom(i.add(1.0f, 1.0f)) * 6.283f;

        // original unit vector = (0, 1) which points downwards
        Vec2 tlvec = new Vec2( (float) -Math.sin(tl), (float) Math.cos(tl) );
        Vec2 trvec = new Vec2( (float) -Math.sin(tr), (float) Math.cos(tr) );
        Vec2 blvec = new Vec2( (float) -Math.sin(bl), (float) Math.cos(bl) );
        Vec2 brvec = new Vec2( (float) -Math.sin(br), (float) Math.cos(br) );

        // getting dot product of each corner's vector and its distance vector to current point
        float tldot = tlvec.dot(f);
        float trdot = trvec.dot(f.add(-1f, 0f));
        float bldot = blvec.dot(f.add(0f, -1f));
        float brdot = brvec.dot(f.add(-1f, -1f));

        if(isInteresting){
            tldot = Math.abs(tldot);
            trdot = Math.abs(trdot);
            bldot = Math.abs(bldot);
            brdot = Math.abs(brdot);
        }

        Vec2 cubic = new Vec2(f.x * f.x * ( 3f - 2f * f.x ), f.y * f.y * ( 3f - 2f * f.y ));

        return fract(0.5f + mix( mix(tldot, trdot, cubic.x), mix(bldot, brdot, cubic.x), cubic.y));
    }




    /*  HELPER FUNCTIONS  */

    private static Vec2 perlinRandGradient(int ix, int iy) {
        float random = (float) (2920f * Math.sin(ix * 21942f + iy * 171324f + 8912f) * Math.cos( ix * 23157f * iy * 217832f + 9758f));
        return new Vec2( (float) Math.cos(random), (float) Math.sin(random) );
    }

    private static float perlinDotGridGradient(int ix, int iy, float x, float y) {
        Vec2 gradient = perlinRandGradient(ix, iy);
        return ( (x - (float) ix) * gradient.x + (y - (float) iy) * gradient.y);
    }

    private static float perlinInterpolate(float a0, float a1, float w) {
        return ((a1 - a0) * ((w * (w * 6f - 15f) + 10f) * w * w * w) + a0);
    }

    private static int floor(double x) {
        int xi = (int) x;
        return (x < xi) ? (xi - 1) : xi;
    }
    private static Vec2 floor(float x, float y) { return new Vec2( floor(x), floor(y) ); }

    private static float fract(float x) { return x - floor(x); }
    private static Vec2 fract(float x, float y) { return new Vec2( fract(x), fract(y) ); }

    private static float mix(float x, float y, float a){
        return (x * (1-a)) + (y * a);
    }

}
