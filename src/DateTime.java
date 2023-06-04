public class DateTime extends Date {
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        super(year, month, day);
        if(hour>=0 && hour<=23)
            this.hour = hour;
        else
            this.hour = 0;
        if(minute>=0 && minute<=59)
            this.minute = minute;
        else
            this.minute = 0;
    }

    /**
     *
     * @return hashcode of DateTime - the sum of the values, each at a different product of 10
     */
    @Override
    public int hashCode(){
        return (this.year*100000000) + (this.month*1000000) + (this.day*10000)
                + (this.hour*100) + (this.day);
    }

    /**
     *
     * @param datetime - another object, intended to be typed DateTime
     * @return if the values of both DateTime objects are equal (if the other object even is a DateTime)
     */
    @Override
    public boolean equals(Object datetime){
        if (datetime instanceof DateTime)
            return this.hashCode() == ((DateTime)datetime).hashCode();
        else
            return false;
    }

    /**
     *
     * @return a String representing the DateTime
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d %02d:%02d", this.day, this.month, this.year,
                                                       this.hour, this.minute);
    }


    public void setHour(int i) {
        if(i>=0 && i<=23)
            this.hour = i;
        else
            this.hour = 0;
    }

    public void setMinute(int i) {
        if(i>=0 && i<=59)
            this.minute = i;
        else
            this.minute = 0;
    }
}

