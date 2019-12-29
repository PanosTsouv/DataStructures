public class Rectangle{
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    public Rectangle()
    {
        this.xMin = 0;
        this.xMax = 0;
        this.yMin = 0;
        this.yMax = 0;
    }

    public Rectangle(int xMin, int xMax, int yMin, int yMax)
    {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public int getXMin()
    {
        return this.xMin;
    }

    public int getYMin()
    {
        return this.yMin;
    }

    public int getXMax()
    {
        return this.xMax;
    }

    public int getYMax()
    {
        return this.yMax;
    }

    public void setXMin(int xMin)
    {
        this.xMin = xMin;
    }

    public void setYMin(int yMin)
    {
        this.yMin = yMin;
    }

    public void setXMax(int xMax)
    {
        this.xMax = xMax;
    }

    public void setYMax(int yMax)
    {
        this.yMax = yMax;
    }

    public boolean contains(Point p)
    {
        if(p.getX() >= this.xMin && p.getX() <= this.xMax && p.getY() >= this.yMin && p.getY() <= this.yMax)
        {
            return true;
        }
        return false;
    }

    public boolean intersects(Rectangle that)
    {
        if(this.getXMin() > that.getXMax() || this.getXMax() < that.getXMin())
        {
            return false;
        }
        if(this.getYMin() > that.getYMax() || this.getYMax() < that.getYMin())
        {
            return false;
        }
        return true;
    }

    public double distanceTo(Point z)
    {
        return Math.sqrt(squareDistanceTo(z));
    }

    public int squareDistanceTo(Point z)
    {
        //down-left
        if(z.getX() < this.getXMin() && z.getY() < this.getYMin())
        {
            Point temp = new Point(this.getXMin(), this.getYMin());
            return temp.squareDistanceTo(z);
        }
        //down-right
        if(z.getX() > this.getXMax() && z.getY() < this.getYMin())
        {
            Point temp = new Point(this.getXMax(), this.getYMin());
            return temp.squareDistanceTo(z);
        }
        //top-left
        if(z.getX() < this.getXMin() && z.getY() > this.getYMax())
        {
            Point temp = new Point(this.getXMin(), this.getYMax());
            return temp.squareDistanceTo(z);
        }
        //top-right
        if(z.getX() > this.getXMax() && z.getY() > this.getYMax())
        {
            Point temp = new Point(this.getXMax(), this.getYMax());
            return temp.squareDistanceTo(z);
        }
        //mid-left
        if(z.getX() < this.getXMin() && z.getY() >= this.getYMin() && z.getY() <= this.getYMax())
        {
            Point temp = new Point(this.getXMin(), z.getY());
            return temp.squareDistanceTo(z);
        }
        //mid-right
        if(z.getX() > this.getXMax() && z.getY() >= this.getYMin() && z.getY() <= this.getYMax())
        {
            Point temp = new Point(this.getXMax(), z.getY());
            return temp.squareDistanceTo(z);
        }
        //mid-down
        if(z.getY() < this.getYMin() && z.getX() >= this.getXMin() && z.getX() <= this.getXMax())
        {
            Point temp = new Point(z.getX(), this.getYMin());
            return temp.squareDistanceTo(z);
        }
        //mid-top
        if(z.getY() > this.getYMax() && z.getX() >= this.getXMin() && z.getX() <= this.getXMax())
        {
            Point temp = new Point(z.getX(), this.getYMax());
            return temp.squareDistanceTo(z);
        }
        return 0;
    }

    public String toString()
    {
        return "[" + xMin + ", " + xMax + "] x [" + yMin + ", " + yMax + "]";
    }
}