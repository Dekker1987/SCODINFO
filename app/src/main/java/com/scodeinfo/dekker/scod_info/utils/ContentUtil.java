package com.scodeinfo.dekker.scod_info.utils;

import android.content.Context;

import com.scodeinfo.dekker.scod_info.model.ScodeModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Sergii on 24.02.2018.
 */

public class ContentUtil {

    private List<ScodeModel> scodeModels;
    private Context ctx;

   public ContentUtil(Context ctx){
        this.ctx = ctx;
        scodeModels = new ArrayList<>();
    }

    private void clearScodeModels(){
      if(scodeModels.size()>0){
            scodeModels.clear();
        }
    }

    private String getContentToParse(String fileNameToParse){
        if(ctx!=null){
            BufferedReader bufferedReader = null;
            StringBuilder fileContent = null;

            try {
                bufferedReader =
                        new BufferedReader(new InputStreamReader(ctx.getAssets().open(fileNameToParse)));

                fileContent = new StringBuilder();

                int tempContent;

                while((tempContent=bufferedReader.read())!=-1){
                    fileContent.append((char)tempContent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(bufferedReader!=null){
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return fileContent.toString();

        }
        return "";
    }

    public List<ScodeModel> getScodeList(String fileNameToParse){

        List<ScodeModel> scodeModels = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(
                    new InputSource(
                            new ByteArrayInputStream(getContentToParse(fileNameToParse).getBytes("utf-8"))));

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("scode");

            clearScodeModels();

            for(int i=0;i<nList.getLength();i++){
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    final Element eElement = (Element)nNode;
                    scodeModels.add(
                            new ScodeModel(){{
                                setScodeNo(
                                        eElement.getAttribute("no"));

                                setScodeShortDescription(
                                        eElement.getElementsByTagName("short_description")
                                                .item(0)
                                                .getTextContent());

                                setScodeFullDescription(
                                        eElement.getElementsByTagName("full_description")
                                                .item(0)
                                                .getTextContent());
                            }});
                }
            }
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }
        return scodeModels;
    }
}
