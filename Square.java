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

    public boolean getWallUp() {
        return wallUp;
    }

    public boolean getWallDown() {
        return wallDown;
    }

    public boolean getWallLeft() {
        return wallLeft;
    }

    public boolean getWallRight() {
        return wallRight;
    }
}