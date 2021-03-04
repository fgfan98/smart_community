package com.gigsider.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionPool {

    public static HashMap<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    public static void addToSessionPool(String userID, HttpSession session){
        sessions.put(userID, session);
    }

    public static HttpSession getExistSession(String userID) {
        return sessions.get(userID);
    }

    public static void destroyExistSession(String userID) {
        sessions.get(userID).invalidate();
        sessions.remove(userID);
    }

}
