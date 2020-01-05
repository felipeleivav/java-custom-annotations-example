package io.leiva;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bot {

    @MessageFilter("hola")
    public String responderHola() {
        return "y chao!";
    }

    @MessageFilter("dame la hora")
    public String responderConLaHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

}
