class Square {
    private boolean wallUp;
    private boolean wallDown;
    private boolean wallLeft;
    private boolean wallRight;

    public Square() {
        wallUp = false;
        wallDown = false;
        wallLeft = false;
        wallRight = false;
    }

    public Square(boolean wallUp, boolean wallDown, boolean wallLeft, boolean wallRight){
        this.wallUp = wallUp;
        this.wallDown = wallDown;
        this.wallLeft = wallLeft;
        this.wallRight = wallRight;
    }


}