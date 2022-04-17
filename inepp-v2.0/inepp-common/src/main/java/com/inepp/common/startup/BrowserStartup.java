package com.inepp.common.startup;

import org.springframework.boot.CommandLineRunner;

import java.io.IOException;

/**
 * 启动chrome浏览器
 */
public class BrowserStartup implements CommandLineRunner {
    @Override
    public void run(String... args)  {
        try {
            String cmd = "cmd /c start \"\" ";
            String chromePath = "\"C:/Program Files/Google/Chrome/Application/chrome.exe\"";
          //  String swaggerUI = " http://localhost:" + args[0] + "/swagger-ui/";
            String swaggerUI = " http://localhost:" + args[0] + "/doc.html";
            String cmdLine = cmd + chromePath + swaggerUI;
            Runtime.getRuntime().exec(cmdLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}