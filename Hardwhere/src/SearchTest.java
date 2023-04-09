import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchTest {
    public String HD(String text) throws IOException {
        String page = "https://www.homedepot.com/s/"+text+"?NCNI-5";
        //Connecting to the web page
        Connection conn = Jsoup.connect(page);
        //executing the get request
        Document doc = conn.get();
        //Retrieving the contents (body) of the web page
        String result = doc.body().text();
        //System.out.println(result);
        result=result.substring(result.indexOf("First, select"));
        result=result.substring(0,result.lastIndexOf("Add to Cart"));

        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        data.add(new ArrayList<String>());
        data.get(0).add("Name");
        data.add(new ArrayList<String>());
        data.get(1).add("Price");
        data.add(new ArrayList<String>());
        data.get(2).add("Model");


        //add name
        String info="";
        boolean go=false;
        int step=0;
        String sword="";
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if(sword.equals("Add")||sword.equals("Shop")){
                go=false;
                sword="";
                data.get(0).add(info);
                info="";
                step=0;
            }
            if(ch.equals(")")||go){
                if(step>15){

                    sword+=ch;
                    if(ch.equals(" ")){

                        info+=sword;
                        sword="";

                    }
                }

                go=true;
                step++;
            }

        }
        //add price
        go=false;
        info="";
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if (ch.equals("(")){
                go=false;
                data.get(1).add(info);
                info="";
            }
            if(ch.equals("$")||go){
                info+=ch;
                go=true;
            }

        }
        //add model
        step=0;
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if(step==15){
                go=false;
                data.get(2).add(info);
                info="";
                step=0;
            }
            if(ch.equals(")")||go){
                if(step>1){
                    info+=ch;
                }

                go=true;
                step++;
            }

        }

        return Arrays.toString(data.get(0).toArray())+"\n"+Arrays.toString(data.get(1).toArray())+"\n"+Arrays.toString(data.get(2).toArray());
    }
    public String ACE(String text) throws IOException {
        String page = "https://www.acehardware.com/search?query="+text;
        //Connecting to the web page
        Connection conn = Jsoup.connect(page);
        //executing the get request
        Document doc = conn.get();
        //Retrieving the contents (body) of the web page
        String result = doc.body().text();
        System.out.println(result);

        result=result.substring(result.indexOf("Compare")+1);
        result=result.substring(result.indexOf("Compare"));
        result=result.substring(0,result.lastIndexOf("Showing "));

        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        data.add(new ArrayList<String>());
        data.get(0).add("Name");
        data.add(new ArrayList<String>());
        data.get(1).add("Price");
        result+=result+"Compare    .";


        //add name
        String info="";
        boolean go=false;
        int step=0;
        String sword="";
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if(sword.equals("Compare")||sword.equals("comparison.Compare")||go){
                go=true;
                info+=ch;
            }
            if(sword.equals("Reviews")) {
                go = false;
                info=info.substring(0,info.length()-11);
                data.get(0).add(info);
                info = "";
                sword = "";
            }
            sword+=ch;



            if(ch.equals(" ")) {
                sword = "";
            }


        }
        //add price
        go=false;
        info="";
        sword = "";

        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if (ch.equals(" ")&&go){
                go=false;
                data.get(1).add(info);
                info="";

                i=result.indexOf("Compare",i);
            }
            if(ch.equals("$")||go){
                info+=ch;
                go=true;
            }

        }
        return Arrays.toString(data.get(0).toArray())+"\n"+Arrays.toString(data.get(1).toArray());
    }


    public static void main(String[] args) throws IOException {
        /*
        String page = "https://www.homedepot.com/s/wood?NCNI-5";
        //Connecting to the web page
        Connection conn = Jsoup.connect(page);
        //executing the get request
        Document doc = conn.get();
        //Retrieving the contents (body) of the web page
        String result = doc.body().text();
        System.out.println(result);
        result=result.substring(result.lastIndexOf("View All"));

        result=result.substring(0,result.lastIndexOf("Add to Cart"));

        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        data.add(new ArrayList<String>());
        data.get(0).add("Name");
        data.add(new ArrayList<String>());
        data.get(1).add("Price");
        data.add(new ArrayList<String>());
        data.get(2).add("Model");


        //add name
        String info="";
        boolean go=false;
        int step=0;
        String sword="";
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if(sword.equals("Add")||sword.equals("Shop")){
                go=false;
                sword="";
                info=info.substring(1);
                if (info.substring(0).equals("#")) {
                    info.substring(1);
                }
                data.get(0).add(info);
                info="";
                step=0;
            }
            if(sword.equals("Model")){
                i+=8;
                sword="";
            }
            if(ch.equals(")")||go){

                    sword+=ch;
                    if(ch.equals(" ")){

                        info+=sword;
                        sword="";

                    }


                go=true;

            }
            step++;
        }
        //add price
        go=false;
        info="";
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if (ch.equals("(")){
                go=false;
                data.get(1).add(info);
                info="";
            }
            if(ch.equals("$")||go){
                info+=ch;
                go=true;
            }

        }
        //add model
        step=0;
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);

            if(step==15){
                go=false;
                data.get(2).add(info);
                info="";
                step=0;
            }
            if(ch.equals(")")||go){
                if(step>1){
                    info+=ch;
                }

                go=true;
                step++;
            }

        }
        System.out.println(data.get(0));
        System.out.println(data.get(1));
        System.out.println(data.get(2));


        String page = "https://www.acehardware.com/search?query=wood";
        //Connecting to the web page
        Connection conn = Jsoup.connect(page);
        //executing the get request
        Document doc = conn.get();
        //Retrieving the contents (body) of the web page
        String result = doc.body().text();
        //System.out.println(result);

        result=result.substring(result.indexOf("Compare")+1);
        result=result.substring(result.indexOf("Compare"));
        result=result.substring(0,result.lastIndexOf("Showing "));

        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        data.add(new ArrayList<String>());
        data.get(0).add("Name");
        data.add(new ArrayList<String>());
        data.get(1).add("Price");
        result+=result+"Compare    .";


        //add name
        String info="";
        boolean go=false;
        int step=0;
        String sword="";
        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if(sword.equals("Compare")||sword.equals("comparison.Compare")||go){
                go=true;
                info+=ch;
            }
            if(sword.equals("Reviews")||sword.equals("Review")) {
                go = false;
                info=info.substring(0,info.length()-11);
                data.get(0).add(info);
                info = "";
                sword = "";
            }
            sword+=ch;



            if(ch.equals(" ")) {
                sword = "";
            }


        }
        //add price
        go=false;
        info="";
        sword = "";

        for (int i=0;i<result.length();i++){
            String ch=result.substring(i,i+1);
            if (ch.equals(" ")&&go){
                go=false;
                data.get(1).add(info);
                info="";

                i=result.indexOf("Compare",i);
            }
            if(ch.equals("$")||go){
                info+=ch;
                go=true;
            }

        }



        System.out.println(data.get(0));
        System.out.println(data.get(1));

         */
    }
}
