package org.pinky83.bean;

import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

@Component
@Data
public class InjectedBean {

    private String message;

    private Locale locale;

    public InjectedBean(final MessageSource messageSource) {
        this.message = messageSource.getMessage("app.end", null, getLocale());
    }

    private Locale getLocale() {
        Scanner enter = new Scanner(System.in);

        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");

        String answer = "";

        while(!options.contains(answer)) {
            System.out.println("Select an option: ");
            System.out.println(" 1. Ukrainian ");
            System.out.println(" 2. French ");
            System.out.println(" 3. Deutsche ");
            System.out.println(" 4. Default (English) ");
            answer = enter.nextLine();
        }

        switch (answer) {
            case "1" : {
                return new Locale("ua");
            }
            case "2" :  {
                return Locale.FRANCE;
            }
            case "3" : {
                return Locale.GERMANY;
            }
            case "4" :  {
                return Locale.getDefault();
            }
        }
        return Locale.getDefault();
    }
}
