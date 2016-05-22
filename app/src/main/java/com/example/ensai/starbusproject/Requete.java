package com.example.ensai.starbusproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Requete {

    public static final String BASEURL = "http://data.keolis-rennes.com/json/?";
    public static final String KEY = "1RJLZ38TUFZSWTW";
    private String cmd;
    private String version;
    private Map<String,String> params = new HashMap<String,String>();

    public Requete(String cmd, String version) {
        this.cmd = cmd;
        this.version = version;
    }



    private final String metroProche = "http://data.keolis-rennes.com/json/?cmd=getmetrostations&param[mode]=proximity&param[type]=coords&param[lat]=48.09511&param[lng]=-1.6788&version=2.0&key=1RJLZ38TUFZSWTW";
    private final String busProche = "http://data.keolis-rennes.com/json/?cmd=getstation&param[request]=proximity&param[mode]=coord&param[lat]=48.09511&param[long]=-1.6788&version=1.0&key=1RJLZ38TUFZSWTW";

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public String getURL() {
        String url = BASEURL;
        url = BASEURL + "cmd="+cmd;
        url = ajouterParametre(url, "version", version, false);
        url = ajouterParametre(url, "key", KEY, false);
        for (Entry<String,String> entry : params.entrySet()) {
            url = ajouterParametre(url, entry.getKey(), entry.getValue(), true);
        }
        return url;
    }

    private static String ajouterParametre(String url,String key, String value,boolean subparam) {
        return url + "&" + (subparam ? "param["+key+"]" : key) + "=" + value;
    }
}
