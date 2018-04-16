package com.example.rishabh.medhisine;

public class substitute {

    String medname;
    String  brandname;
    String price;
    String packsize;

    public substitute(String mmedname, String mbrandname, String mprice, String mpacksize)
    {
        medname = mmedname;
        brandname = mbrandname;
        price = mprice;
        packsize = mpacksize;
    }

    public String getMedname()
    {
        return medname;
    }
    public String getBrandname()
    {
        return brandname;
    }
    public String getPrice()
    {
        return price;
    }
    public String getPacksize()
    {
        return packsize;
    }

}
