package garcia.andres.earthquakemonitor;

/*
 * Created by Andrés on 14/02/2015.
 */
public class QuakeStruct {
    // Initialization of the struct fields
    private int Icon;
    private String title;
    private Double mag;
    private String place;
    private Long time;
    private Long updated;
    private int tz;
    private String url;
    private String detail;
    private String felt; // int felt, sometimes null
    private String cdi; // double cdi, sometimes null
    private String mmi; // double mmi, sometimes null
    private String alert;
    private String status;
    private int tsunami;
    private int sig;
    private String net;
    private String code;
    private String ids;
    private String sources;
    private String types;
    private String nst; // int nst, sometimes null
    private String dmin; // Double dmin, sometimes null
    private String rms; // Double rms, sometimes null
    private String gap; // Double gap, sometimes null
    private String magType;
    private String type;
    private Double latitude;
    private Double longitude;
    private Double depth;
    private String ID;
    private int bColor;
    private int pin;
    private String titleQ;

    // Constructor
    public QuakeStruct (int Icon, String title, Double mag, String place, Long time, Long updated, int tz, String url,
                         String detail, String felt, String cdi, String mmi, String alert, String status,
                         int tsunami, int sig, String net, String code, String ids, String sources,
                         String types, String nst, String dmin, String rms, String gap, String magType,
                         String type, Double latitude, Double longitude, Double depth, String ID, int bColor, int pin, String titleQ){
        // Passes the values of the variables that are changing in the adapter
        // The values change outside the constructor and the constructor uses them in an element
        super();
        this.Icon = Icon;
        this.title = title;
        this.mag = mag;
        this.place = place;
        this.time = time;
        this.updated = updated;
        this.tz = tz;
        this.url = url;
        this.detail = detail;
        this.felt = felt;
        this.cdi = cdi;
        this.mmi = mmi;
        this.alert = alert;
        this.status = status;
        this.tsunami = tsunami;
        this.sig = sig;
        this.net = net;
        this.code = code;
        this.ids = ids;
        this.sources = sources;
        this.types = types;
        this.nst = nst;
        this.dmin = dmin;
        this.rms = rms;
        this.gap = gap;
        this.magType = magType;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.ID = ID;
        this.bColor = bColor;
        this.pin = pin;
        this.titleQ = titleQ;
    }

    // Create getters and setters (methods)
    public String getTitle() {
        return title;
    }
/*
    public void setTitle(String title) {
        this.title = title;
    }*/

    public Double getMag() {
        return mag;
    }
/*
    public void setMag(Double mag) {
        this.mag = mag;
    }*/

    public String getPlace() {
        return place;
    }
/*
    public void setPlace(String place) {
        this.place = place;
    }
*/
    public Long getTime() {
        return time;
    }
/*
    public void setTime(Long time) {
        this.time = time;
    }
*/
    public Long getUpdated() {
        return updated;
    }
/*
    public void setUpdated(Long updated) {
        this.updated = updated;
    }
*/
    public int getTz() {
        return tz;
    }
/*
    public void setTz(int tz) {
        this.tz = tz;
    }
*/
    public String getUrl() {
        return url;
    }
/*
    public void setUrl(String url) {
        this.url = url;
    }
*/
    public String getDetail() {
        return detail;
    }
/*
    public void setDetail(String detail) {
        this.detail = detail;
    }
*/
    public String getFelt() {
        return felt;
    }
/*
    public void setFelt(String felt) {
        this.felt = felt;
    }
*/
    public String getCdi() {
        return cdi;
    }
/*
    public void setCdi(String cdi) {
        this.cdi = cdi;
    }
*/
    public String getMmi() {
        return mmi;
    }
/*
    public void setMmi(String mmi) {
        this.mmi = mmi;
    }
*/
    public String getAlert() {
        return alert;
    }
/*
    public void setAlert(String alert) {
        this.alert = alert;
    }
*/
    public String getStatus() {
        return status;
    }
/*
    public void setStatus(String status) {
        this.status = status;
    }
*/
    public int getTsunami() {
        return tsunami;
    }
/*
    public void setTsunami(int tsunami) {
        this.tsunami = tsunami;
    }
*/
    public int getSig() {
        return sig;
    }
/*
    public void setSig(int sig) {
        this.sig = sig;
    }
*/
    public String getNet() {
        return net;
    }
/*
    public void setNet(String net) {
        this.net = net;
    }
*/
    public String getCode() {
        return code;
    }
/*
    public void setCode(String code) {
        this.code = code;
    }
*/
    public String getIds() {
        return ids;
    }
/*
    public void setIds(String ids) {
        this.ids = ids;
    }
*/
    public String getSources() {
        return sources;
    }
/*
    public void setSources(String sources) {
        this.sources = sources;
    }
*/
    public String getTypes() {
        return types;
    }
/*
    public void setTypes(String types) {
        this.types = types;
    }
*/
    public String getNst() {
        return nst;
    }
/*
    public void setNst(String nst) {
        this.nst = nst;
    }
*/
    public String getDmin() {
        return dmin;
    }
/*
    public void setDmin(String dmin) {
        this.dmin = dmin;
    }
*/
    public String getRms() {
        return rms;
    }
/*
    public void setRms(String rms) {
        this.rms = rms;
    }
*/
    public String getGap() {
        return gap;
    }
/*
    public void setGap(String gap) {
        this.gap = gap;
    }
*/
    public String getMagType() {
        return magType;
    }
/*
    public void setMagType(String magType) {
        this.magType = magType;
    }
*/
    public String getType() {
        return type;
    }
/*
    public void setType(String type) {
        this.type = type;
    }
*/
    public int getIcon() {
        return Icon;
    }
/*
    public void setIcon(int icon) {
        Icon = icon;
    }
*/
    public Double getLatitude() {
        return latitude;
    }
/*
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
*/
    public Double getLongitude() {
        return longitude;
    }
/*
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
*/
    public Double getDepth() {
        return depth;
    }
/*
    public void setDepth(Double depth) {
        this.depth = depth;
    }
*/
    public String getID() {
        return ID;
    }
/*
    public void setID(String ID) {
        this.ID = ID;
    }
*/
    public int getbColor() {
        return bColor;
    }
/*
    public void setbColor(int bColor) {
        this.bColor = bColor;
    }
*/
    public int getPin() {
        return pin;
    }
/*
    public void setPin(int pin) {
        this.pin = pin;
    }
*/
    public String getTitleQ() {
        return titleQ;
    }
/*
    public void setTitleQ(String titleQ) {
        this.titleQ = titleQ;
    }*/
}
