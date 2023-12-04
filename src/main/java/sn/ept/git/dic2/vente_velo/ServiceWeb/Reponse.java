package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class Reponse {
    private String msg;

    public Reponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
