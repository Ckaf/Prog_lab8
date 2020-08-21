package GeneralTools;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Class that stores information received from the server
 */
public class Answer implements Serializable {
    public String UserColor;
    public String cmd;
    public String answer;
    public String answer1;
    public String autorizatonflag;
    int SIZE=16384;
    public byte[] file=new byte[SIZE];
    public Integer wrong;
    public LinkedList<String> list= new LinkedList<String>();
    public String getAnswer(){return answer;}
    public Integer getWrong(){return wrong;}
    public void setAnswer(String answer){
        this.answer=answer;
}
    public void setAutorizatonflag(String autorizatonflag){this.autorizatonflag=autorizatonflag;}
    public String getAutorizatonflag(){return autorizatonflag;}
}
