package org.Kaminski;

import org.Kaminski.userservice.ui.UserConsoleApp;
import org.Kaminski.userservice.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        try{
            UserConsoleApp.start();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}