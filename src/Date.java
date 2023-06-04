public class Date {
    protected int year;
    protected int month;
    protected int day;
    public Date(int year, int month, int day){
        if(year>=-3999 && year<=3999)
            this.year = year;
        else
            this.year = 0;
        if(month>=1 && month<=12)
            this.month = month;
        else
            this.month = 1;
        if(day>=1 && day<=31)
            this.day = day;
        else
            this.day = 1;
    }

    /**
     *
     * @return hashcode of Date - the sum of the values, each at a different product of 10
     */
    @Override
    public int hashCode(){
        return (this.year*10000) + (this.month*100) + (this.day);
    }

    public boolean equals(Object date){
        if (date instanceof Date)
            return this.hashCode() == ((Date)date).hashCode();
        else
            return false;
    }

    /**
     *
     * @return a String representing the Date
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", this.day, this.month, this.year);
    }

    public void setMonth(int i) {
        if(i>=1 && i<=12)
            this.month = i;
        else
            this.month = 1;
    }
}
