package leilani.android.hawaii.edu.shoppinglist;

/**
 * Created by Leilani on 10/3/2016.
 */

public class Grocery {
    private String m_strGrocery;
    private String m_strLocation;
    private long m_nLat;
    private long m_nLong;
    private long m_nID;

    public Grocery() {
        this.m_strGrocery = "";
        this.m_strLocation = "";
        this.m_nLat = 0;
        this.m_nLong = 0;
        this.m_nID = 0;
    }

    public Grocery(String strGrocery) {
        this.m_strGrocery = strGrocery;
        this.m_strLocation = "";
        this.m_nLat = 0;
        this.m_nLong = 0;
        this.m_nID = 0;
    }

    public Grocery(String strGrocery, String strLocation, long nLat, long nLong) {
        this.m_strGrocery = strGrocery;
        this.m_strLocation = strLocation;
        this.m_nLat = nLat;
        this.m_nLong = nLong;
        this.m_nID = 0;
    }

    public Grocery(String strGrocery, String strLocation, long nLat, long nLong, long nID) {
        this.m_strGrocery = strGrocery;
        this.m_strLocation = strLocation;
        this.m_nLat = nLat;
        this.m_nLong = nLong;
        this.m_nID = nID;
    }

    public String getGrocery() {
        return m_strGrocery;
    }

    public void setGrocery(String strGrocery) {
        this.m_strGrocery = strGrocery;
    }

    public String getLocation() {
        return m_strLocation;
    }

    public void setLocation(String strLocation) {
        this.m_strLocation = strLocation;
    }

    public long getLat() {
        return m_nLat;
    }

    public void setLat(long nLat) {
        this.m_nLat = nLat;
    }

    public long getLong() {
        return m_nLong;
    }

    public void setLong(long nLong) {
        this.m_nLong = nLong;
    }

    public long getID() {
        return m_nID;
    }

    public void setID(long nID) {
        this.m_nID = nID;
    }

    @Override
    public String toString() {
        return  m_strGrocery;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Grocery) {
            Grocery grocery2 = (Grocery) obj;

            return (this.m_strGrocery.equals(grocery2.m_strGrocery) && this.m_strLocation.equals(grocery2.m_strLocation));
        }
        return false;
    }
}

