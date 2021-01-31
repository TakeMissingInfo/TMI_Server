package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse;
import com.takemissinghome.cafeteria.property.CafeteriaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class CafeteriaOpenApiService {

    private final CafeteriaProperties cafeteriaProperties;

    public CafeteriaOpenApiResponse getCafeteriaResponse() throws IOException, JAXBException {

        String newPath = cafeteriaProperties.getUrl() + cafeteriaProperties.getKey();
        HttpURLConnection conn = (HttpURLConnection) new URL(newPath).openConnection();
        conn.connect();

        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));

        String xmlInfo = getXmlInfo(reader);

        JAXBContext jaxbContext = JAXBContext.newInstance(CafeteriaOpenApiResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (CafeteriaOpenApiResponse) unmarshaller.unmarshal(new StringReader(xmlInfo));
    }

    private String getXmlInfo(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String current;
        while ((current = reader.readLine()) != null) {
            sb.append(current + "\n");
        }
        return sb.toString();
    }
}
