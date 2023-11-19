package org.example.model;

import org.example.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy{
    private static final String URL_FORMAT  = "https://hh.ru/search/vacancy?text=Java+%s&area=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> allVacancies = new ArrayList<>();
        int page = 0;
        try{
            do{
                Document doc = getDocument(searchString,page);
                Elements vacanciesHtmlList = doc.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy");
                if(!vacanciesHtmlList.isEmpty()){
                    for(Element e:vacanciesHtmlList){
                        Elements links = e.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-title");
                        Elements locations = e.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-address");
                        Elements companyName = e.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-employer");
                        Elements salary = e.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-compensation");

                        Vacancy vacancy = new Vacancy();
                        vacancy.setSiteName("hh.ru");
                        vacancy.setTitle(links.get(0).text());
                        vacancy.setUrl(links.get(0).attr("href"));
                        vacancy.setCity(locations.get(0).text());
                        vacancy.setCompanyName(companyName.get(0).text());
                        vacancy.setSalary(salary.size()>0 ? salary.get(0).text() :"");
                        allVacancies.add(vacancy);
                    }
                    page++;
                }
            }while(true);
        }catch(IOException e){
            e.printStackTrace();
        }
        return allVacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
            return  Jsoup.connect(String.format(URL_FORMAT,searchString,page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                .referrer("https://hh.ru/")
                .get();
    }
}
