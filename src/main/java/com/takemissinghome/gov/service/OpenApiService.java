package com.takemissinghome.gov.service;

import com.takemissinghome.gov.response.ResponseModel;
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
    private String weakPersonPath = "http://api.korea.go.kr/openapi/svc/list?pageIndex=1&pageSize=100&format=xml";

    public ResponseModel getBenefitDataOfWeakPerson(String weakPersonCode, String benefitCode) throws IOException, JAXBException {
        String newWeakPersonpath = weakPersonPath;
        newWeakPersonpath = addWeakPersonCode(newWeakPersonpath, weakPersonCode);
        newWeakPersonpath = addBenefitCode(newWeakPersonpath, benefitCode);

        String serverKey = Files.readAllLines(serverKeyFile.getFile().toPath()).get(0);
        newWeakPersonpath = addServerKey(newWeakPersonpath, serverKey);

        HttpURLConnection conn = (HttpURLConnection) new URL(newWeakPersonpath).openConnection();
        conn.connect();

        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));

        String xmlInfo = reader.readLine();
        String filteredXmlInfo = filterOutXmlInfo(xmlInfo);

        JAXBContext jaxbContext = JAXBContext.newInstance(ResponseModel.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (ResponseModel) unmarshaller.unmarshal(new StringReader(filteredXmlInfo));
    }

    private String addWeakPersonCode(String newWeakPersonPath, String weakPersonCode) {
        return newWeakPersonPath + "&srhQuery=" + weakPersonCode;
    }

    private String addBenefitCode(String newWeakPersonPath, String code) {
        return newWeakPersonPath + "&lrgAstCd=" + code;
    }

    private String addServerKey(String newWeakPersonPath, String serverKey) {
        return newWeakPersonPath + "&serviceKey=" + serverKey;
    }

    private String filterOutXmlInfo(String xmlInfo) {
        xmlInfo = xmlInfo.replaceAll("&lt;!HS&gt;", "");
        return xmlInfo.replaceAll("&lt;!HE&gt;", "");
    }
}
