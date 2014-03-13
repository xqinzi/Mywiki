package com.mywiki;

import java.io.IOException;
import org.eclipse.jetty.websocket.core.api.WebSocketAdapter;

public class MyEchoSocket2  extends WebSocketAdapter{
	
	@Override
    public void onWebSocketText(String message){
		System.out.println(1+message);
        if (isNotConnected()){
            return;
        }

        try{
            // echo the data back
            getBlockingConnection().write(message);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
