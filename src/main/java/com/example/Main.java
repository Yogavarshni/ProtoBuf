package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.example.apis.protos.API;
import com.example.apis.protos.APIs;

public class Main {
    public static void main(String[] args) {
        try {
            API.Builder apiBuilder1 = API.newBuilder();
            APIs.Builder apisBuilder = APIs.newBuilder();
            apiBuilder1.setId("abc001");
            apiBuilder1.setName("API1");
            apiBuilder1.setDescription("functionality test");
            apiBuilder1.setVersion(1);
            apisBuilder.addApis(apiBuilder1.build());

            API.Builder apiBuilder2 = API.newBuilder();
            apiBuilder2.setId("abc002");
            apiBuilder2.setName("API2");
            apiBuilder2.setDescription("functionality test 2");
            apiBuilder2.setVersion(2);
            apisBuilder.addApis(apiBuilder2.build());

            APIs apisMessage = apisBuilder.build();
            byte[] bytes = apisMessage.toByteArray();

            FileOutputStream output = new FileOutputStream("apisbinary");
            output.write(bytes);
            output.close();
            System.out.println();
            System.out.println();
            System.out.println(Arrays.toString(bytes));
            System.out.println();

            FileInputStream input = new FileInputStream("apisbinary");
            APIs api2 = APIs.parseFrom(input);
            input.close();

            System.out.println("Deserialized original entity:\n");
            for (API api : api2.getApisList()) {
                System.out.println(api.getId() + "," + api.getName() + "," + api.getDescription()+ "," + api.getVersion());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
