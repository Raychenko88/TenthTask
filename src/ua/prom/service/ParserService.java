package ua.prom.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ua.prom.model.ProductModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ParserService {

    public static ProductModel getProduct(String url){
        ProductModel product = new ProductModel();
        Document document = getDocument(url);
        if (document != null){
            Element productInfoElement = document.getElementsByAttributeValue("data-qaid","main_product_info").first();
            product.setName(extraxtName(productInfoElement));
            product.setProductId(extraxtProductId(productInfoElement)) ;
            product.setPrise(extraxtProductPrise(productInfoElement));
        }
        return product;
    }

    private static String extraxtName(Element productInfoElement) {
        Element nameElement = productInfoElement.getElementsByAttributeValue("data-qaid","product_name").first();
        String name = nameElement.text();

        return name;
    }

    private static String extraxtProductId(Element productInfoElement) {
        Element productElement = productInfoElement.getElementsByAttributeValue("data-qaid","product-sku").first();
        String productId = productElement.text();


        return productId;
    }

    private static BigDecimal extraxtProductPrise(Element productInfoElement) {
        BigDecimal prise = null;
        Element priseElement = productInfoElement.getElementsByAttributeValue("data-qaid","product-sku").first();
        if (priseElement.hasAttr("data-qaprice")){
            String priseAsText = priseElement.attr("data-qaprice");
            prise = new BigDecimal(priseAsText).setScale(2, RoundingMode.HALF_UP);

        }
        return prise;
    }


    private static Document getDocument(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
