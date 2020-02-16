package ua.prom.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
            product.setName(extractName(productInfoElement));
            product.setProductId(extractProductId(productInfoElement)) ;
            product.setPrice(extractProductPrise(productInfoElement));
            product.setAvailability(extractProductAvailability(productInfoElement));
            product.setImage(extractProductImage(url));
            product.setUrl(extractProductImgUrl(document));
        }
        return product;
    }

    private static String extractName(Element productInfoElement) {
        Element nameElement = productInfoElement.getElementsByAttributeValue("data-qaid","product_name").first();
        String name = nameElement.text();

        return name;
    }

    private static String extractProductId(Element productInfoElement) {
        Element productElement = productInfoElement.getElementsByAttributeValue("data-qaid","product-sku").first();
        String productId = productElement.text();


        return productId;
    }

    private static BigDecimal extractProductPrise(Element productInfoElement) {
        BigDecimal prise = null;
        Element priseElement = productInfoElement.getElementsByAttributeValue("data-qaid","product_price").first();
        if (priseElement.hasAttr("data-qaprice")){
            String priseAsText = priseElement.attr("data-qaprice");
            prise = new BigDecimal(priseAsText).setScale(2, RoundingMode.HALF_UP);

        }
        return prise;
    }

    private static String extractProductImage(String url) {

        return url;
    }

    private static String extractProductAvailability(Element productInfoElement) {
        Element productElement = productInfoElement.getElementsByAttributeValue("data-qaid","product_presence").first();
        String availability = productElement.text();

        return availability;
    }

    private static String extractProductImgUrl(Document document) {
        String imgUrl = "";
        try {
            imgUrl = document.getElementsByAttributeValue("property", "og:image").first().attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
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
