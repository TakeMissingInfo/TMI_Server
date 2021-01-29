package com.takemissinghome.gov.service;

import com.takemissinghome.gov.property.OpenApiProperty;
import com.takemissinghome.gov.response.ResponseModel;
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
public class OpenApiService {

    private final OpenApiProperty openApiProperty;

    public ResponseModel getBenefitDataOfWeakPerson(String weakPersonCode, String benefitCode) throws IOException, JAXBException {
        String newWeakPersonPath = openApiProperty.getUrl();
        newWeakPersonPath = addWeakPersonCode(newWeakPersonPath, weakPersonCode);
        newWeakPersonPath = addBenefitCode(newWeakPersonPath, benefitCode);

        newWeakPersonPath = addServerKey(newWeakPersonPath, openApiProperty.getKey());

        HttpURLConnection conn = (HttpURLConnection) new URL(newWeakPersonPath).openConnection();
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
