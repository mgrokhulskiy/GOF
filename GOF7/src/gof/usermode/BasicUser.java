package gof.usermode;

import gof.Analysers.*;
import gof.LineAnalyser;
import gof.UserMode;

public class BasicUser implements UserMode {

    private LineAnalyser analysersChain;

    private static BasicUser instance = null;

    private BasicUser() {

    }

    private void initStrategy() {
        LineAnalyser pa = new PostToAll();
        LineAnalyser lu = new ListUsersRecognizer();
        LineAnalyser st = new SendToRecognizer();
        LineAnalyser pro = new PromoteRecognizer();
        pro.setNext(st);
        st.setNext(lu);
        lu.setNext(pa);
        analysersChain = pro;
    }

    public static BasicUser getInstance() {
        if (instance == null) {
            instance = new BasicUser();
            instance.initStrategy();
        }
        return instance;
    }

    @Override
    public void post(String text) {
        analysersChain.analyse(text);
    }

}
