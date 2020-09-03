package GeneralTools;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Locale;

/**
 * Class that stores information received from the client
 */
public class Information implements Serializable {
    //transient
    public Locale locale;
    private static final long serialVersionUID=34567890L;
    public InetSocketAddress address;
    public boolean isUpdate;
    public String regtype;
    public String login;
    public String pass;
    public String cmdtype;
    int SIZE=16384;
    byte[] file=new byte[SIZE];
    public String name;
    public String count;
    public String exp;
    public String form;
    public String semestr;
    public String groupAdmin;
    public String height;
    public String weight;
    public String eyeColor;
    public String X;
    public String Y;
    public String idstr;
    public void setParametrs(String name, String count, String exp, String form, String semestr, String groupAdmin, String height, String weight, String eyeColor, String X, String Y) {
        this.count = count;
        this.name = name;
        this.exp = exp;
        this.form = form;
        this.eyeColor = eyeColor;
        this.semestr = semestr;
        this.groupAdmin = groupAdmin;
        this.height = height;
        this.weight = weight;
        this.X = X;
        this.Y = Y;
    }

}
