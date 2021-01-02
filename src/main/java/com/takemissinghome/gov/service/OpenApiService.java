package com.takemissinghome.gov.service;

import com.takemissinghome.gov.domain.response.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final Resource serverKeyFile = new ClassPathResource("openapi/openapi_server_key");
    private final String lowInComeUrl = "http://api.korea.go.kr/openapi/svc/list?lrgAstCd=&mdmAstCd=&smallAstCd=&jrsdOrgCd=&srhQuery=%EC%A0%80%EC%86%8C%EB%93%9D%EC%B8%B5&pageIndex=1&pageSize=100&format=xml&serviceKey=";

    public String getDate() throws IOException, JAXBException {

        String serverKey = Files.readAllLines(serverKeyFile.getFile().toPath()).get(0);
        HttpURLConnection conn = (HttpURLConnection) new URL(lowInComeUrl + serverKey).openConnection();
        conn.connect();

        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));

        StringBuilder xmlInfo = convertTo(reader);

        JAXBContext jaxbContext = JAXBContext.newInstance(ResponseModel.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final ResponseModel responseModel = (ResponseModel) unmarshaller.unmarshal(new StringReader(xmlInfo.toString()));

        return xmlInfo.toString();
    }

    private StringBuilder convertTo(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb;
    }
}
