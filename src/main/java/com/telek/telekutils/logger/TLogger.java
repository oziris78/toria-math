package com.telek.telekutils.logger;




public class TLogger {

    //////////////
    /*  FIELDS  */
    //////////////

    private LogLevel currentLevel;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    // betikten 115, 116

    public TLogger(LogLevel initialLevel){
        this.currentLevel = initialLevel;
    }


    /**  Creates a logger with a current logging level of {@link LogLevel#INFO}.  */
    public TLogger(){
        this(LogLevel.INFO);
    }


    ///////////////
    /*  METHODS  */
    ///////////////




    ///////////////
    /*  HELPERS  */
    ///////////////

}
