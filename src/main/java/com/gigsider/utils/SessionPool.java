package com.gigsider.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionPool {

    public static HashMap<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    public static HttpSession getExistSession(String userID)
    {
        return sessions.get(userID);
    }

    public static void destroyExistSession(String userID)
    {
        HttpSession session = (HttpSession)sessions.get(userID);
        session.invalidate();
        sessions.remove(userID);
    }

}
