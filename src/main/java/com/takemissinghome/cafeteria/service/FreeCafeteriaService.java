package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.domain.FreeCafeteriaResponse;
import com.takemissinghome.cafeteria.property.FreeCafeteriaProperty;
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
public class FreeCafeteriaService {

    private final FreeCafeteriaProperty freeCafeteriaProperty;

    public FreeCafeteriaResponse findCafeteriaInfo() throws IOException, JAXBException {

        String newPath = freeCafeteriaProperty.getUrl() + freeCafeteriaProperty.getKey();
        HttpURLConnection conn = (HttpURLConnection) new URL(newPath).openConnection();
        conn.connect();

        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));

        String xmlInfo = getXmlInfo(reader);

        JAXBContext jaxbContext = JAXBContext.newInstance(FreeCafeteriaResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (FreeCafeteriaResponse) unmarshaller.unmarshal(new StringReader(xmlInfo));
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
