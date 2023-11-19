package org.example.view;

import org.example.Controller;
import org.example.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.List;

public class HtmlView implements View{
    private final String filePath ="C:\\Users\\My PC\\IdeaProjects\\Agregator\\src\\main\\java\\org\\example\\view.vacancies.html";

    private Controller controller;
    @Override
    public void update(List<Vacancy> vacancies) {
       try{
            String newContent = getUpdatedFileContent(vacancies);
            updateFile(newContent);
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }
    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document doc = getDocument();
            Elements templateHidden = doc.getElementsByClass("template");
            Element template = templateHidden.clone().removeAttr("style").removeClass("template").get(0);


            Elements prevVacancies = doc.getElementsByClass("vacancy");

            for (Element redundant : prevVacancies) {
                if (!redundant.hasClass("template")) {
                    redundant.remove();
                }
            }
            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = template.clone();

                Element vacancyLink = vacancyElement.getElementsByAttribute("href").get(0);
                vacancyLink.appendText(vacancy.getTitle());
                vacancyLink.attr("href", vacancy.getUrl());
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Elements salaryEls = vacancyElement.getElementsByClass("salary");
                Element salary = salaryEls.get(0);
                salary.appendText(vacancy.getSalary());

                templateHidden.before(vacancyElement.outerHtml());
            }
            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
    }
    private void updateFile(String content) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(new File(filePath))){
            fos.write(content.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected Document getDocument()throws IOException{
       return Jsoup.parse(new File(filePath),"UTF-8");
    }
}
