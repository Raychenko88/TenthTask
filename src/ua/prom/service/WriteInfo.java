package ua.prom.service;

import ua.prom.model.ProductModel;

import java.io.*;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class WriteInfo {
    private static String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data";
    private static String fileName = "test.xml";

    public static void creatPathAndFile(String str) {
        File filePath = new File(str);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        File file = new File(filePath + File.separator + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getXmlObject(ProductModel product) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductModel.class);  // кастрюля с ингредиентами
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); // преобразователь
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // не помню
            StringWriter sw = new StringWriter(); // объект для получения результата
            jaxbMarshaller.marshal(product, sw); // процес преобразования из объекта в writer(String обертку внутри)
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToFile( ProductModel product) {
        creatPathAndFile(fileName);
        try (FileWriter writer = new FileWriter(filePath + File.separator + fileName, false)) {
            writer.write(getXmlObject(product));
            writer.flush();
        } catch (IOException ex) {

        }

    }
}
