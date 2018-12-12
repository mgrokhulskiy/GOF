package gof.usermode;

import gof.Analysers.*;
import gof.LineAnalyser;
import gof.UserMode;

public class AdminUser implements UserMode {

    private LineAnalyser analysersChain;

    private static AdminUser instance = null;

    private AdminUser() {

    }

    private void initStrategy() {
        LineAnalyser pa = new PostToAll();
        LineAnalyser lu = new ListUsersRecognizer();
        LineAnalyser st = new SendToRecognizer();
        LineAnalyser de = new DemoteRecognizer();
        LineAnalyser spy = new SpyRecognizer();
        spy.setNext(de);
        de.setNext(st);
        st.setNext(lu);
        lu.setNext(pa);
        analysersChain = spy;
    }

    public static AdminUser getInstance() {
        if (instance == null) {
            instance = new AdminUser();
            instance.initStrategy();
        }
        return instance;
    }

    @Override
    public void post(String text) {
        
        analysersChain.analyse(text);
    }

}
