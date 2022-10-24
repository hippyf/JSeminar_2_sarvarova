package ru.gb.jseminar;

import java.util.logging.Logger;

public class Homework {

    // Дана строка sql-запроса "select * from students".
    // Сформируйте часть WHERE этого запроса, используя StringBuilder.
    //
    // 1) Данные для фильтрации подаются в метод двумя массивами paramName и paramValue.
    // 2) Данные для фильтрации подаются в метод строкой в формате json.
    public static void main(String[] args) {
        String [] paramName = {"firstName", "secondName", "age", "city"};
        String [] paramValue = {"Ivan", "Ivanov", "30", "Moscow"};
        String q = "SELECT * FROM students WHERE ";
        Homework hw = new Homework();
        Logger log = Logger.getLogger(Homework.class.getName());
        String query = hw.updateQueryByArrays(q, paramName, paramValue);
        log.info(query);
        String json = "{'firstName' : 'Ivan', 'secondName' : 'Ivanov', 'age' : '30', 'city' : 'Moscow'}";
        query = hw.updateQueryByJson(q, json);
        log.info(query);
    }

    public String updateQueryByArrays(String q, String[] paramName, String[] paramValue){
        StringBuilder query = new StringBuilder();
        query.append(q);
        for (int i = 0; i < paramName.length; i++){          
            query.append(paramName[i]);
            query.append(" = ");
            query.append(paramValue[i]);
            if (i < paramName.length - 1) {
                query.append(" AND ");
            }
        }
        return query.toString();
    }

    public String updateQueryByJson(String q, String json){
        StringBuilder query = new StringBuilder();
        query.append(q);
        String [] jsPairs = json.substring(1, json.length()-1).split(", ");
        String [] jsWords = new String [2];
        for (int i = 0; i < jsPairs.length; i++) {
            jsWords = jsPairs[i].split(" : ");
            query.append(jsWords[0].replace("'", ""));
            query.append(" = ");
            query.append(jsWords[1].replace("'", ""));   
            if (i < jsPairs.length - 1) {
                query.append(" AND ");
            }      
        }
        return query.toString();
    }
}
