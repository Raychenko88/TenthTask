package ua.prom;

import java.io.Writer;
import java.util.logging.Logger;
import ua.prom.model.ProductModel;
import ua.prom.service.ParserService;
import ua.prom.service.WriteInfo;

public class Runner {
        private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data";
        String url = "https://prom.ua/p960465027-omen-8750h16gb2401tbwin10-gtx1050ti.html";
        ProductModel product = ParserService.getProduct(url);
        WriteInfo.writeToFile(filePath, product);

//        LOGGER.info(product.toString());
//        LOGGER.info(WriteInfo.getXmlObject(product));
    }
}
